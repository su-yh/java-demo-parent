package com.suyh1001;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author suyh
 * @since 2023-12-08
 */
public class LocalDateDemoApplication {
    public static void main(String[] args) {
        fun01();
        fun02();
        fun03();
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
        long timestamp = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println("时间戳: " + timestamp);
    }
}
