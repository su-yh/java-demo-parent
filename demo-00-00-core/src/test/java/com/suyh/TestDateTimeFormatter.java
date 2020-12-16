/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.suyh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author 苏雲弘
 * @since 2020-12-16
 */
@Slf4j
public class TestDateTimeFormatter {


    @Test
    public void test01() {
        // LocalDate 与LocalDateTime 的格式化字符串
        final DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // LocalDate 只有日期，没有HH:mm:ss
        LocalDate localDateNow = LocalDate.now();
        String localDateNowFormat = dayFormat.format(localDateNow);

        final DateTimeFormatter DateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        // LocalDateTime 是比较详细的时间类
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        String localDateTimeNowFormat = DateTimeFormat.format(localDateTimeNow);
        log.info("localDateNowFormat: {}, localDateTimeNowFormat: {}", localDateNowFormat, localDateTimeNowFormat);
    }

    /**
     * 1.从日期获取ZonedDateTime并使用其方法toLocalDateTime（）获取LocalDateTime
     * 2.使用LocalDateTime的Instant（）工厂方法
     */
    @Test
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
    @Test
    public void test03() {
        // 系统默认时区ID
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);

        Date date = Date.from(zdt.toInstant());

        System.out.println("LocalDateTime = " + localDateTime);
        System.out.println("Date = " + date);
    }
}
