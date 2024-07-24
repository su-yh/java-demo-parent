package com.suyh1001;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author suyh
 * @since 2023-12-08
 */
public class LocalDateTimeDemoApplication {
    public static void main(String[] args) {
        func01();
        func02();
        func03();
        func04();
        func05();
    }

    private static void func01() {
        {
            LocalDateTime now = LocalDateTime.now();
            System.out.println("当前时间：" + now);
        }

        {
            LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 8, 10, 12, 23, 1223152);
            System.out.println("自定义时间：" + localDateTime);
        }
    }

    private static void func02() {
        {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime todayDate = now.withHour(0).withMinute(0).withSecond(0).withNano(0);
            System.out.println("今日零晨，精确到天：" + todayDate);
        }

        {
            LocalDateTime now = LocalDateTime.now();
            LocalDate localDate = now.toLocalDate();
            System.out.println("今日零晨，精确到天：" + localDate);
        }
    }

    private static void func03() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime todayDate = now.withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime tomorrowDate = todayDate.plusDays(1L);
        LocalDateTime yesterdayDate = todayDate.plusDays(-1L);
        System.out.println("昨天：" + yesterdayDate);
        System.out.println("今天：" + todayDate);
        System.out.println("明天：" + tomorrowDate);
    }

    private static void func04() {
        // LocalDateTime  ==> long
        LocalDateTime now = LocalDateTime.now();
        long nowTimestamp = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("nowTimestamp: " + nowTimestamp + ", currentTimeMillis: " + currentTimeMillis);
    }

    private static void func05() {
        // long  ==> LocalDateTime
        long currentTimeMillis = System.currentTimeMillis();
        Instant instant = Instant.ofEpochMilli(currentTimeMillis);
        LocalDateTime nowLocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println("nowLocalDateTime: " + nowLocalDateTime);
    }

    /**
     * 1.从日期获取ZonedDateTime并使用其方法toLocalDateTime（）获取LocalDateTimeInstant instant = date.toInstant();
     *             LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
     * 2.使用LocalDateTime的Instant（）工厂方法
     */
    public static void test06() {
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
    public static void test07() {
        // 系统默认时区ID
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);

        Date date = Date.from(zdt.toInstant());

        System.out.println("LocalDateTime = " + localDateTime);
        System.out.println("Date = " + date);
    }

}
