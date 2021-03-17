package tomkit.core.bean.init;


import tomkit.core.lang.Strings;
import tomkit.core.function.StringConverter;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author yh
 * @since 2021/1/12
 */
public final class InitHelper {
    /**
     * 日期格式
     */
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 时间格式
     */
    private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    /**
     * 日期时间格式
     */
    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 初始化对象属性值
     *
     * @param target 目标对象
     * @param <T>    目标对象类型
     * @return 原对象
     */
    public static <T> T init(T target) {
        Class<?> clazz = target.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), clazz);
                Method method = descriptor.getReadMethod();
                // 属性值为null时才设置默认值
                if (null == method.invoke(target)) {
                    Object value = getDefaultValue(field);
                    if (null != value) {
                        descriptor.getWriteMethod().invoke(target, value);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return target;
    }

    /**
     * 获取注解指定的默认值
     *
     * @param field 字段
     * @return 注解指定的默认值
     */
    private static Object getDefaultValue(Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Init def = field.getAnnotation(Init.class);
        if (null != def) {
            String value = def.value();
            Class<? extends StringConverter<? extends Serializable>> converter = def.converter();
            if (converter == AutoConverter.class) {
                String format = def.format();
                JavaType javaType = JavaType.get(field.getType()).orElse(JavaType.VOID);
                DateTimeFormatter formatter;
                switch (javaType) {

                    case BOOLEAN:
                        return Boolean.valueOf(value);

                    case BYTE:
                        return Byte.valueOf(value);

                    case SHORT:
                        return Short.valueOf(value);

                    case INT:
                        return Integer.valueOf(value);

                    case LONG:
                        return Long.valueOf(value);

                    case FLOAT:
                        return Float.valueOf(value);

                    case DOUBLE:
                        return Double.valueOf(value);

                    case CHAR:
                        return value.charAt(0);

                    case STRING:

                        return value;
                    case BIG_DECIMAL:
                        return new BigDecimal(value);

                    case BIG_INTEGER:
                        return new BigInteger(value);

                    case DATE:
                        formatter = DateTimeFormatter.ofPattern(Strings.defaultIfEmpty(format, DEFAULT_DATE_TIME_FORMAT));
                        return Date.from(LocalDateTime.parse(value, formatter).atZone(ZoneId.systemDefault()).toInstant());

                    case LOCAL_DATE:
                        return LocalDate.parse(value, DateTimeFormatter.ofPattern(Strings.defaultIfEmpty(format, DEFAULT_DATE_FORMAT)));

                    case LOCAL_TIME:
                        return LocalTime.parse(value, DateTimeFormatter.ofPattern(Strings.defaultIfEmpty(format, DEFAULT_TIME_FORMAT)));

                    case LOCAL_DATE_TIME:
                        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(Strings.defaultIfEmpty(format, DEFAULT_DATE_TIME_FORMAT)));

                    case ZONED_DATE_TIME:
                        formatter = DateTimeFormatter.ofPattern(Strings.defaultIfEmpty(format, DEFAULT_DATE_TIME_FORMAT));
                        return ZonedDateTime.of(LocalDateTime.parse(value, formatter), ZoneId.systemDefault());

                    case OFFSET_DATE_TIME:
                        formatter = DateTimeFormatter.ofPattern(Strings.defaultIfEmpty(format, DEFAULT_DATE_TIME_FORMAT));
                        return ZonedDateTime.of(LocalDateTime.parse(value, formatter), ZoneId.systemDefault()).toOffsetDateTime();
                    default:
                }
            } else {
                return converter.getDeclaredConstructor().newInstance().convert(value);
            }
        }
        return null;
    }

}
