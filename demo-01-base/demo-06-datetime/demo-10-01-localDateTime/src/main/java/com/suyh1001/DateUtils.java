package com.suyh1001;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author suyh
 * @since 2024-08-28
 */
public final class DateUtils {
    /**
     * @param sourceDate 满足yyyyMMdd 格式的日期整数
     * @param daysToAdd  增加的天数，可以为负值。
     * @return yyyyMMdd 格式的日期结果
     */
    public static int plusDays(int sourceDate, int daysToAdd) {
        LocalDate source = convertToLocalDate(sourceDate);
        LocalDate localDate = source.plusDays(daysToAdd);

        return localDate.getYear() * 10000 + localDate.getMonthValue() * 100 + localDate.getDayOfMonth();
    }

    // 日期格式：yyyyMMdd
    public static LocalDate convertToLocalDate(int dateValue) {
        int year = dateValue / 10000;
        int month = dateValue % 10000 / 100;
        int day = dateValue % 100;

        return LocalDate.of(year, month, day);
    }

    // 日期格式：yyyyMMdd
    public static long convertToTimestamp(int dateValue) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = convertToLocalDate(dateValue);
        LocalDateTime localDateTime = localDate.atStartOfDay();
        return localDateTime.atZone(zoneId).toInstant().toEpochMilli();
    }

    // 时间戳按对应的时区转换成对应的日期，格式为：yyyyMMdd
    public static Integer timestampToDate(long timestampMillis) {
        ZoneId zoneId = ZoneId.systemDefault();

        Date date = new Date(timestampMillis);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);
        LocalDate localDate = localDateTime.toLocalDate();
        return convertToInteger(localDate);
    }

    public static Integer convertToInteger(LocalDate localDate) {
        int year = localDate.getYear();
        int monthValue = localDate.getMonthValue();
        int dayOfMonth = localDate.getDayOfMonth();

        return year * 10000 + monthValue * 100 + dayOfMonth;
    }

    public static LocalDate convertToLocalDate(Date date) {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        return instant.atZone(zoneId).toLocalDate();
    }

    public static LocalDateTime convertToLocalDateTime(Date date) {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 日期差 = dateLarger - dateSmall
     */
    public static long diffDays01(int dateSmall, int dateLarger) {
        LocalDate localDateSmall = convertToLocalDate(dateSmall);
        LocalDate localDateBigger = convertToLocalDate(dateLarger);

        return localDateBigger.toEpochDay() - localDateSmall.toEpochDay();
    }

    /**
     * 日期差 = dateLarger - dateSmall
     */
    public static long betweenDays(int dateSmall, int dateLarger) {

        LocalDate localDateSmall = convertToLocalDate(dateSmall);
        LocalDate localDateBigger = convertToLocalDate(dateLarger);

        return ChronoUnit.DAYS.between(localDateSmall, localDateBigger);
    }
}
