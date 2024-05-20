package com.suyh1001;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author suyh
 * @since 2023-12-08
 */
public class LocalDateDemoApplication {
    public static void main(String[] args) {
        fun01();
        fun02();
        fun03();

        fun05();
    }

    private static void fun01() {
        {
            LocalDate now = LocalDate.now();
            System.out.println("当前日期: " + now);
        }

        {
            LocalDate localDate = LocalDate.of(2023, 12, 8);
            System.out.println("自定义日期: " + localDate);
        }

        {
            // 这里的 08 里面的0 不能少，少了就会报解析异常了。
            // 但是可以使用指定的日期格式解析
            LocalDate parseLocalDate = LocalDate.parse("2023-12-08");
            System.out.println("解析自定义日期：" + parseLocalDate);
        }
    }

    private static void fun02() {
        LocalDate todayDate = LocalDate.now();
        LocalDate tomorrowDate = todayDate.plusDays(1L);
        LocalDate yesterdayDate = todayDate.plusDays(-1L);
        System.out.println("昨天：" + yesterdayDate);
        System.out.println("今天：" + todayDate);
        System.out.println("明天：" + tomorrowDate);
    }

    private static void fun03() {
        // LocalDate  ==> long
        // 好像LocalDate 没有提供直接转换成时间戳的API。
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = localDate.atStartOfDay();
        ZoneId zoneId = ZoneId.systemDefault();
        if (true) {
            // suyh - 留个示例
            zoneId = ZoneId.of("Asia/Shanghai");
        }
        long timestamp = localDateTime.atZone(zoneId).toInstant().toEpochMilli();
        System.out.println("时间戳: " + timestamp);
    }


    // 以前写的TestDateTimeFormatter 里面的代码，移动到一起. #######################################################
    public void test01() {
        // LocalDate 与LocalDateTime 的格式化字符串
        final DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // LocalDate 只有日期，没有HH:mm:ss
        LocalDate localDateNow = LocalDate.now();
        String localDateNowFormat = dayFormat.format(localDateNow);

        final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        // LocalDateTime 是比较详细的时间类
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        String localDateTimeNowFormat = dateTimeFormat.format(localDateTimeNow);
        // log.info("localDateNowFormat: {}, localDateTimeNowFormat: {}", localDateNowFormat, localDateTimeNowFormat);
    }

    /**
     * 1.从日期获取ZonedDateTime并使用其方法toLocalDateTime（）获取LocalDateTime
     * 2.使用LocalDateTime的Instant（）工厂方法
     */
    public void test02() {
        // 转换: Date  ==>  LocalDateTime
        Date date = new Date();
        ZoneId zoneId = ZoneId.systemDefault();

        {
            // 方式一
            Instant instant = date.toInstant();
            LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
            System.out.println("Date = " + date);
            System.out.println("LocalDateTime = " + localDateTime);
        }
        {
            // 方式二
            LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);
            System.out.println("Date = " + date);
            System.out.println("LocalDateTime = " + localDateTime);
        }
    }

    /**
     * 转换: LocalDateTime  ==>  Date
     * 1.使用atZone（）方法将LocalDateTime转换为ZonedDateTime
     * 2.将ZonedDateTime转换为Instant，并从中获取Date
     */
    public void test03() {
        // 系统默认时区ID
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);

        Date date = Date.from(zdt.toInstant());

        System.out.println("LocalDateTime = " + localDateTime);
        System.out.println("Date = " + date);
    }

    // 通过时区得到时区的偏移量时间。
    // 不过一般也用不到，尽量找找其他方式。
    private static void fun05() {
        // 方法一：见下面的方法二
        {
            ZonedDateTime zoneOffset = ZonedDateTime.now(
                    ZoneId.of("America/Montreal")
            );

            // 输出 ZoneOffset
            System.out.println("方法一，ZoneOffset: " + zoneOffset);
        }

        // 方法二：这个方法应该是挺好的
        {
            // 创建一个 ZoneId 对象
//        ZoneId zoneId = ZoneId.of("America/New_York");
            ZoneId zoneId = ZoneId.of("Asia/Shanghai");

            // 获取当前时间的瞬时时间
            Instant instant = Instant.now();

            // 获取 ZoneId 对应的 ZoneOffset
            ZoneOffset zoneOffset = zoneId.getRules().getOffset(instant);

            int totalSeconds = zoneOffset.getTotalSeconds();
            // 输出 ZoneOffset
            System.out.println("方法二：ZoneOffset: " + zoneOffset);
        }

        // 方法三：不如方法二好
        {
            // 比如当前系统的默认时区是北京，那么北京时区的偏移量就是8 小时。
            TimeZone defaultTimeZone = TimeZone.getDefault();
            int defaultOffset = defaultTimeZone.getRawOffset() / (60 * 60 * 1000);
            assert defaultOffset == 8;
            System.out.println("defaultOffset: " + defaultOffset);
        }

    }
}
