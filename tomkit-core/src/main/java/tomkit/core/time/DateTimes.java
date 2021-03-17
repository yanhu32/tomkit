package tomkit.core.time;

import tomkit.core.lang.Assert;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author yh
 * @since 2021/2/15
 */
public final class DateTimes {
    /**
     * 默认日期时间格式化
     */
    private static final String DEFAULT_DATE_TIME_STR = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期格式化
     */
    private static final String DEFAULT_DATE_STR = "yyyy-MM-dd";
    /**
     * 默认时间格式化
     */
    private static final String DEFAULT_TIME_STR = "HH:mm:ss";
    /**
     * 系统默认时区
     */
    private static final ZoneId ZONE_ID = ZoneId.systemDefault();
    /**
     * 默认日期时间格式化
     */
    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_STR).withZone(ZONE_ID);
    /**
     * 默认日期格式化
     */
    private static final DateTimeFormatter DEFAULT_DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DEFAULT_DATE_STR).withZone(ZONE_ID);
    /**
     * 默认时间格式化
     */
    private static final DateTimeFormatter DEFAULT_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DEFAULT_TIME_STR).withZone(ZONE_ID);

    private DateTimes() {
    }

    /**
     * 格式化日期时间
     * 支持以下类型:
     * <ul>
     *     {@link ZonedDateTime}
     *     {@link OffsetDateTime}
     *     {@link Instant}
     *     {@link LocalDateTime}
     *     {@link LocalDate}
     *     {@link LocalTime}
     * </ul>
     *
     * @param time {@link Temporal} 子类型日期时间
     * @param <T>  {@link Temporal} 子类型
     * @return 格式化后的日期时间字符串
     * @see Temporal
     * @see DateTimeFormatter#format(TemporalAccessor)
     */
    public static <T extends Temporal> String format(T time) {
        Assert.notNull(time, "time cannot be null");

        if (time instanceof LocalDateTime || time instanceof ZonedDateTime || time instanceof OffsetDateTime || time instanceof Instant) {
            return DEFAULT_DATE_TIME_FORMATTER.format(time);
        } else if (time instanceof LocalDate) {
            return DEFAULT_DATE_FORMATTER.format(time);
        } else if (time instanceof LocalTime) {
            return DEFAULT_TIME_FORMATTER.format(time);
        }
        return time.toString();
    }

    /**
     * 格式化日期
     *
     * @param date {@link Date} 日期
     * @return 格式化后的日期字符串
     */
    public static String format(Date date) {
        Assert.notNull(date, "date cannot be null");

        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_TIME_STR);
        format.setTimeZone(TimeZone.getDefault());
        return format.format(date);
    }

    /**
     * 将 {@link LocalDateTime} 转换为 {@link ZonedDateTime}
     *
     * @param time 本地日期时间
     * @return 带有时区的日期时间
     */
    public static ZonedDateTime toZonedDateTime(LocalDateTime time) {
        Assert.notNull(time, "time cannot be null");
        return ZonedDateTime.of(time, ZONE_ID);
    }

    /**
     * 将 {@link Instant} 转换为 {@link ZonedDateTime}
     *
     * @param instant 时间戳
     * @return 带有时区的日期时间
     */
    public static ZonedDateTime toZonedDateTime(Instant instant) {
        Assert.notNull(instant, "instant cannot be null");
        return ZonedDateTime.ofInstant(instant, ZONE_ID);
    }

    /**
     * 将毫秒级时间戳转换为 {@link ZonedDateTime}
     *
     * @param millisecond 毫秒级时间戳
     * @return 带有时区的日期时间
     */
    public static ZonedDateTime toZonedDateTime(long millisecond) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(millisecond), ZONE_ID);
    }

    /**
     * 将 {@link Date} 转换为 {@link ZonedDateTime}
     *
     * @param date 日期
     * @return 带有时区的日期时间
     */
    public static ZonedDateTime toZonedDateTime(Date date) {
        Assert.notNull(date, "date cannot be null");
        return ZonedDateTime.ofInstant(date.toInstant(), ZONE_ID);
    }

    /**
     * 将 {@link LocalDateTime} 转换为 {@link OffsetDateTime}
     *
     * @param time 本地日期时间
     * @return 带有时区偏移量的日期时间
     */
    public static OffsetDateTime toOffsetDateTime(LocalDateTime time) {
        Assert.notNull(time, "time cannot be null");
        return time.atZone(ZONE_ID).toOffsetDateTime();
    }

    /**
     * 将 {@link Instant} 转换为 {@link OffsetDateTime}
     *
     * @param instant 时间戳
     * @return 带有时区偏移量的日期时间
     */
    public static OffsetDateTime toOffsetDateTime(Instant instant) {
        Assert.notNull(instant, "instant cannot be null");
        return instant.atZone(ZONE_ID).toOffsetDateTime();
    }

    /**
     * 将毫秒级时间戳转换为 {@link OffsetDateTime}
     *
     * @param millisecond 毫秒级时间戳
     * @return 带有时区偏移量的日期时间
     */
    public static OffsetDateTime toOffsetDateTime(long millisecond) {
        return Instant.ofEpochMilli(millisecond).atZone(ZONE_ID).toOffsetDateTime();
    }

    /**
     * 将 {@link Date} 转换为 {@link OffsetDateTime}
     *
     * @param date 日期
     * @return 带有时区偏移量的日期时间
     */
    public static OffsetDateTime toOffsetDateTime(Date date) {
        Assert.notNull(date, "date cannot be null");
        return date.toInstant().atZone(ZONE_ID).toOffsetDateTime();
    }

    /**
     * 将 {@link Instant} 转换为 {@link LocalDateTime}
     *
     * @param instant 时间戳
     * @return 本地日期时间
     */
    public static LocalDateTime toLocalDateTime(Instant instant) {
        Assert.notNull(instant, "instant cannot be null");
        return LocalDateTime.ofInstant(instant, ZONE_ID);
    }

    /**
     * 将毫秒级时间戳转换为 {@link LocalDateTime}
     *
     * @param millisecond 毫秒级时间戳
     * @return 本地日期时间
     */
    public static LocalDateTime toLocalDateTime(long millisecond) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millisecond), ZONE_ID);
    }

    /**
     * 将 {@link Date} 转换为 {@link LocalDateTime}
     *
     * @param date 日期
     * @return 本地日期时间
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        Assert.notNull(date, "date cannot be null");
        return date.toInstant().atZone(ZONE_ID).toLocalDateTime();
    }

    /**
     * 将 {@link Instant} 转换为 {@link LocalDate}
     *
     * @param instant 时间戳
     * @return 本地日期
     */
    public static LocalDate toLocalDate(Instant instant) {
        Assert.notNull(instant, "instant cannot be null");
        return instant.atZone(ZONE_ID).toLocalDate();
    }

    /**
     * 将毫秒级时间戳转换为 {@link LocalDate}
     *
     * @param millisecond 毫秒级时间戳
     * @return 本地日期
     */
    public static LocalDate toLocalDate(long millisecond) {
        return Instant.ofEpochMilli(millisecond).atZone(ZONE_ID).toLocalDate();
    }

    /**
     * 将 {@link Date} 转换为 {@link LocalDate}
     *
     * @param date 日期
     * @return 对象
     */
    public static LocalDate toLocalDate(Date date) {
        Assert.notNull(date, "date cannot be null");
        return date.toInstant().atZone(ZONE_ID).toLocalDate();
    }

    /**
     * 将 {@link Instant} 转换为 {@link LocalTime}
     *
     * @param instant 时间戳
     * @return 本地时间
     */
    public static LocalTime toLocalTime(Instant instant) {
        Assert.notNull(instant, "instant cannot be null");
        return instant.atZone(ZONE_ID).toLocalTime();
    }

    /**
     * 将毫秒级时间戳转换为 {@link LocalTime}
     *
     * @param millisecond 毫秒级时间戳
     * @return 本地时间
     */
    public static LocalTime toLocalTime(long millisecond) {
        return Instant.ofEpochMilli(millisecond).atZone(ZONE_ID).toLocalTime();
    }

    /**
     * 将 {@link Date} 转换为 {@link LocalTime}
     *
     * @param date 日期
     * @return 对象
     */
    public static LocalTime toLocalTime(Date date) {
        Assert.notNull(date, "date cannot be null");
        return date.toInstant().atZone(ZONE_ID).toLocalTime();
    }

    /**
     * 将 {@link LocalDateTime} 转换为 {@link Instant}
     *
     * @param time 本地日期时间
     * @return 时间戳
     */
    public static Instant toInstant(LocalDateTime time) {
        Assert.notNull(time, "time cannot be null");
        return time.atZone(ZONE_ID).toInstant();
    }

    /**
     * 将 {@link OffsetDateTime} 转换为 {@link GregorianCalendar}
     *
     * @param time 带有时区偏移量的日期时间
     * @return 公历
     * @see GregorianCalendar#from(ZonedDateTime)
     */
    public static GregorianCalendar toGregorianCalendar(OffsetDateTime time) {
        Assert.notNull(time, "time cannot be null");
        return GregorianCalendar.from(time.toInstant().atZone(ZONE_ID));
    }

    /**
     * 将 {@link LocalDateTime} 转换为 {@link GregorianCalendar}
     *
     * @param time 本地日期时间
     * @return 公历
     * @see GregorianCalendar#from(ZonedDateTime)
     */
    public static GregorianCalendar toGregorianCalendar(LocalDateTime time) {
        Assert.notNull(time, "time cannot be null");
        return GregorianCalendar.from(time.atZone(ZONE_ID));
    }

    /**
     * 将 {@link Instant} 转换为 {@link GregorianCalendar}
     *
     * @param instant 时间戳
     * @return 公历
     */
    public static GregorianCalendar toGregorianCalendar(Instant instant) {
        Assert.notNull(instant, "instant cannot be null");
        return GregorianCalendar.from(instant.atZone(ZONE_ID));
    }

    /**
     * 将毫秒级时间戳转换为 {@link GregorianCalendar}
     *
     * @param millisecond 毫秒级时间戳
     * @return 公历
     */
    public static GregorianCalendar toGregorianCalendar(long millisecond) {
        return GregorianCalendar.from(Instant.ofEpochMilli(millisecond).atZone(ZONE_ID));
    }

    /**
     * 将 {@link Date} 转换为 {@link GregorianCalendar}
     *
     * @param date 日期
     * @return 公历
     * @see GregorianCalendar#from(ZonedDateTime)
     */
    public static GregorianCalendar toGregorianCalendar(Date date) {
        Assert.notNull(date, "date cannot be null");
        return GregorianCalendar.from(date.toInstant().atZone(ZONE_ID));
    }

    /**
     * 将 {@link ZonedDateTime} 转换为 {@link Date}
     *
     * @param time 带有时区的日期时间
     * @return 日期
     */
    public static Date toDate(ZonedDateTime time) {
        Assert.notNull(time, "time cannot be null");
        return Date.from(time.toInstant());
    }

    /**
     * 将 {@link OffsetDateTime} 转换为 {@link Date}
     *
     * @param time 带有时区偏移量的日期时间
     * @return 日期
     */
    public static Date toDate(OffsetDateTime time) {
        Assert.notNull(time, "time cannot be null");
        return Date.from(time.toInstant());
    }

    /**
     * 将 {@link LocalDateTime} 转换为 {@link Date}
     *
     * @param time 日期时间
     * @return 日期
     */
    public static Date toDate(LocalDateTime time) {
        Assert.notNull(time, "time cannot be null");
        return Date.from(time.atZone(ZONE_ID).toInstant());
    }

    /**
     * 将毫秒级时间戳转换为 {@link Date}
     *
     * @param millisecond 毫秒级时间戳
     * @return 日期
     */
    public static Date toDate(long millisecond) {
        return Date.from(Instant.ofEpochMilli(millisecond));
    }

    /**
     * 将 {@link LocalDateTime} 转换为13位时间戳
     *
     * @param time 日期时间
     * @return 13位时间戳
     */
    public static long toTimestamp(ZonedDateTime time) {
        Assert.notNull(time, "time cannot be null");
        return time.toInstant().toEpochMilli();
    }

    /**
     * 将 {@link LocalDateTime} 转换为13位时间戳
     *
     * @param time 日期时间
     * @return 13位时间戳
     */
    public static long toTimestamp(OffsetDateTime time) {
        Assert.notNull(time, "time cannot be null");
        return time.toInstant().toEpochMilli();
    }

    /**
     * 将 {@link LocalDateTime} 转换为13位时间戳
     *
     * @param time 日期时间
     * @return 13位时间戳
     */
    public static long toTimestamp(LocalDateTime time) {
        Assert.notNull(time, "time cannot be null");
        return time.atZone(ZONE_ID).toInstant().toEpochMilli();
    }

    /**
     * 将 {@link Date} 转换为13位时间戳
     *
     * @param date 日期
     * @return 13位时间戳
     */
    public static long toTimestamp(Date date) {
        Assert.notNull(date, "date cannot be null");
        return date.toInstant().toEpochMilli();
    }

    /**
     * 获取当前日期时间字符串格式，使用默认格式化规则
     *
     * @return 当前日期时间格式化后的字符串
     */
    public static String getDatetime() {
        return DEFAULT_DATE_TIME_FORMATTER.format(LocalDateTime.now());
    }

    /**
     * 获取当前日期时间字符串格式，需指定格式化规则
     *
     * @param format 格式化规则
     * @return 当前日期时间格式化后的字符串
     */
    public static String getDatetime(String format) {
        Assert.notEmpty(format, "format cannot be empty");
        return DateTimeFormatter.ofPattern(format).withZone(ZONE_ID).format(LocalDateTime.now());
    }

    /**
     * 获取当前日期字符串格式，使用默认格式化规则
     *
     * @return 当前日期格式化后的字符串
     */
    public static String getDate() {
        return DEFAULT_DATE_FORMATTER.format(LocalDate.now());
    }

    /**
     * 获取当前日期字符串格式，需指定格式化规则
     *
     * @param format 格式化规则
     * @return 当前日期格式化后的字符串
     */
    public static String getDate(String format) {
        Assert.notEmpty(format, "format cannot be empty");
        return DateTimeFormatter.ofPattern(format).withZone(ZONE_ID).format(LocalDate.now());
    }

    /**
     * 获取当前时间字符串格式，使用默认格式化规则
     *
     * @return 当前时间格式化后的字符串
     */
    public static String getTime() {
        return DEFAULT_TIME_FORMATTER.format(LocalTime.now());
    }

    /**
     * 获取当前时间字符串格式，需指定格式化规则
     *
     * @param format 格式化规则
     * @return 当前时间格式化后的字符串
     */
    public static String getTime(String format) {
        Assert.notEmpty(format, "format cannot be empty");
        return DateTimeFormatter.ofPattern(format).withZone(ZONE_ID).format(LocalTime.now());
    }

    /**
     * 获取当前时间戳
     *
     * @return 时间戳
     * @see System#currentTimeMillis()
     */
    public static long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取日期的年、月、日等属性
     *
     * @param date 日期
     * @param attr 属性 {@link Calendar}
     * @return 属性值
     * @see Calendar
     * @see GregorianCalendar#get(int)
     */
    public static int getDateAttr(Date date, int attr) {
        Assert.notNull(date, "date cannot be null");
        return GregorianCalendar.from(date.toInstant().atZone(ZONE_ID)).get(attr);
    }

    /**
     * 获取时间戳的年、月、日等属性
     *
     * @param instant 时间戳
     * @param attr    属性 {@link Calendar}
     * @return 属性值
     * @see Calendar
     * @see GregorianCalendar#get(int)
     */
    public static int getTimestampAttr(Instant instant, int attr) {
        Assert.notNull(instant, "instant cannot be null");
        return GregorianCalendar.from(instant.atZone(ZONE_ID)).get(attr);
    }

    /**
     * 获取毫秒级时间戳的年、月、日等属性
     *
     * @param millisecond 毫秒级时间戳
     * @param attr        属性 {@link Calendar}
     * @return 属性值
     * @see Calendar
     * @see GregorianCalendar#get(int)
     */
    public static int getTimestampAttr(long millisecond, int attr) {
        return GregorianCalendar.from(Instant.ofEpochMilli(millisecond).atZone(ZONE_ID)).get(attr);
    }

    /**
     * 解析字符串为带时区的日期时间，使用默认格式化规则 {@link #DEFAULT_DATE_TIME_STR}
     *
     * @param text 日期时间字符串
     * @return 带时区的日期时间
     */
    public static ZonedDateTime parseZonedDateTime(CharSequence text) {
        Assert.notNull(text, "text cannot be empty");
        return ZonedDateTime.of(LocalDateTime.parse(text, DEFAULT_DATE_TIME_FORMATTER), ZONE_ID);
    }

    /**
     * 解析字符串为带时区偏移量的日期时间，使用默认格式化规则 {@link #DEFAULT_DATE_TIME_STR}
     *
     * @param text 日期时间字符串
     * @return 带时区偏移量的日期时间
     */
    public static OffsetDateTime parseOffsetDateTime(CharSequence text) {
        Assert.notNull(text, "text cannot be empty");
        return LocalDateTime.parse(text, DEFAULT_DATE_TIME_FORMATTER).atZone(ZONE_ID).toOffsetDateTime();
    }

    /**
     * 解析字符串为本地日期时间，使用默认格式化规则 {@link #DEFAULT_DATE_TIME_STR}
     *
     * @param text 日期时间字符串
     * @return 本地日期时间
     */
    public static LocalDateTime parseLocalDateTime(CharSequence text) {
        Assert.notNull(text, "text cannot be empty");
        return LocalDateTime.parse(text, DEFAULT_DATE_TIME_FORMATTER);
    }

    /**
     * 解析字符串为本地日期，使用默认格式化规则 {@link #DEFAULT_DATE_STR}
     *
     * @param text 日期字符串
     * @return 本地日期
     */
    public static LocalDate parseLocalDate(CharSequence text) {
        Assert.notNull(text, "text cannot be empty");
        return LocalDate.parse(text, DEFAULT_DATE_FORMATTER);
    }

    /**
     * 解析字符串为本地时间，使用默认格式化规则 {@link #DEFAULT_TIME_STR}
     *
     * @param text 时间字符串
     * @return 本地时间
     */
    public static LocalTime parseLocalTime(CharSequence text) {
        Assert.notNull(text, "text cannot be empty");
        return LocalTime.parse(text, DEFAULT_TIME_FORMATTER);
    }

    /**
     * 解析字符串为时间戳，使用默认格式化规则 {@link #DEFAULT_DATE_TIME_STR}
     *
     * @param text 日期时间字符串
     * @return 时间戳
     */
    public static Instant parseInstant(CharSequence text) {
        Assert.notNull(text, "text cannot be empty");
        return LocalDateTime.parse(text, DEFAULT_DATE_TIME_FORMATTER).atZone(ZONE_ID).toInstant();
    }

    /**
     * 解析字符串为日期，使用默认格式化规则 {@link #DEFAULT_DATE_TIME_STR}
     *
     * @param text 日期时间字符串
     * @return 日期
     */
    public static Date parseDate(CharSequence text) {
        Assert.notNull(text, "text cannot be empty");
        return Date.from(LocalDateTime.parse(text, DEFAULT_DATE_TIME_FORMATTER).atZone(ZONE_ID).toInstant());
    }

}
