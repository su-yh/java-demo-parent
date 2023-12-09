package com.suyh0605.init;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.suyh0605.entity.SqlDateTestEntity;
import com.suyh0605.mapper.SqlDateTestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author suyh
 * @since 2023-12-03
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SqlDateTestComponent implements ApplicationRunner {
    private final SqlDateTestMapper sqlDateTestMapper;

    /**
     * 结论：在数据库中date 类型应该是存的一个整数值。
     * 因为同样的数据，从数据库中取出来之后，不同的时区，得到的时间字符串结果(2023-12-03 00:00:00)是不一样的
     * 得到的时间戳的值都是一样的：1701532800000
     * 转换成java.util.Date 后，就会按时区处理了，结果是不一样的：
     * 中国时区: java.util.Date: 2023-12-03 00:00:00
     * 东京时区: java.util.Date: 2023-12-03 01:00:00
     *
     * 但是这里要十分注意，我在本地是以东京时区获取到的时间，并存到数据库中。
     * 最终从数据库中取出来的时间 戳并不匹配。
     * 所以这里可以明确的知道，我们将时间戳上传到数据库之后，数据库会以数据库的系统时区，来截取年月日，最终把对应的时间戳存起来。
     * 所以本地时区和线上时区需要保持一致。
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (true) {
            return;
        }

        {
            TimeZone timeZone = TimeZone.getTimeZone("Asia/Tokyo");
            TimeZone.setDefault(timeZone);

            System.out.println("当前时区: " + ZoneId.systemDefault());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date utilDate = sdf.parse("2023-12-03 00:00:00");

            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            LocalDate todayLocalDate = LocalDate.now();

            SqlDateTestEntity entity = new SqlDateTestEntity();
            entity.setTestSqlDate(sqlDate).setLocalDate(todayLocalDate);
            sqlDateTestMapper.insert(entity);

            System.out.println("插入时的时间戳：" + sqlDate.getTime());
            System.out.println("插入时的时间值：" + sqlDate);
            System.out.println("插入时的localDate值：" + todayLocalDate);
        }

        {
            TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
            TimeZone.setDefault(timeZone);

            List<SqlDateTestEntity> listResult = sqlDateTestMapper.selectList(null);
            if (listResult != null) {
                for (SqlDateTestEntity entity : listResult) {
                    /*
                     * 中国时区: java.sql.Date: 2023-12-03
                     * 中国时区: java.sql.Date.getTime(): 1701532800000
                     * 中国时区: java.util.Date: 2023-12-03 00:00:00
                     */
                    java.sql.Date testSqlDate = entity.getTestSqlDate();
                    System.out.println("中国时区: java.sql.Date: " + testSqlDate);
                    System.out.println("中国时区: java.sql.Date.getTime(): " + testSqlDate.getTime());
                    Date date = new Date(testSqlDate.getTime());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    System.out.println("中国时区: java.util.Date: " + sdf.format(date));

                    LocalDate localDate = entity.getLocalDate();
                    System.out.println("查询到的LocalDate 值：" + localDate);
                    break;
                }
            }
        }

        {
            TimeZone timeZone = TimeZone.getTimeZone("Asia/Tokyo");
            TimeZone.setDefault(timeZone);

            List<SqlDateTestEntity> listResult = sqlDateTestMapper.selectList(null);
            if (listResult != null) {
                for (SqlDateTestEntity entity : listResult) {
                    /*
                     * 东京时区: java.sql.Date: 2023-12-03
                     * 东京时区: java.sql.Date.getTime(): 1701532800000
                     * 东京时区: java.util.Date: 2023-12-03 01:00:00
                     */
                    java.sql.Date testSqlDate = entity.getTestSqlDate();
                    System.out.println("东京时区: java.sql.Date: " + testSqlDate);
                    System.out.println("东京时区: java.sql.Date.getTime(): " + testSqlDate.getTime());
                    Date date = new Date(testSqlDate.getTime());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    System.out.println("东京时区: java.util.Date: " + sdf.format(date));

                    break;
                }
            }
        }

        {
            LambdaQueryWrapper<SqlDateTestEntity> queryWrapper =  new LambdaQueryWrapper<>();
            queryWrapper.eq(SqlDateTestEntity::getLocalDate, LocalDate.now());
            List<SqlDateTestEntity> entityList = sqlDateTestMapper.selectList(queryWrapper);
            System.out.println("entityList");
        }
    }
}
