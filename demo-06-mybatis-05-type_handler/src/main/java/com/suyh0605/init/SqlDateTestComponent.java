package com.suyh0605.init;

import com.suyh0605.entity.SqlDateTestEntity;
import com.suyh0605.mapper.SqlDateTestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author suyh
 * @since 2023-12-03
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SqlDateTestComponent implements ApplicationRunner {
    private final SqlDateTestMapper sqlDateTestMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();

        SqlDateTestEntity entity = new SqlDateTestEntity();
        entity.setTestSqlDate(new java.sql.Date(currentTimeMillis));
        sqlDateTestMapper.insert(entity);
    }
}
