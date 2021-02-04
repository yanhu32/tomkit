package yh.tomkit.core.field.init;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

/**
 * @author yh
 * @since 2021/1/12
 */
enum JavaType {
    /**
     *
     */
    VOID(void.class, Void.class),
    BOOLEAN(boolean.class, Boolean.class),
    BYTE(byte.class, Byte.class),
    SHORT(short.class, Short.class),
    INT(int.class, Integer.class),
    LONG(long.class, Long.class),
    FLOAT(float.class, Float.class),
    DOUBLE(double.class, Double.class),
    CHAR(char.class, Character.class),
    STRING(String.class),
    BIG_DECIMAL(BigDecimal.class),
    BIG_INTEGER(BigInteger.class),
    DATE(Date.class),
    LOCAL_DATE(LocalDate.class),
    LOCAL_TIME(LocalTime.class),
    LOCAL_DATE_TIME(LocalDateTime.class),
    ;

    private final Class<?>[] classes;

    JavaType(Class<?> cls) {
        this.classes = new Class[]{cls};
    }

    JavaType(Class<?> cls0, Class<?> cls1) {
        this.classes = new Class[]{cls0, cls1};
    }

    public static Optional<JavaType> get(Class<?> clazz) {
        return Arrays.stream(JavaType.values())
                .filter(type -> type.has(clazz))
                .findAny();
    }

    private <T> boolean has(Class<T> clazz) {
        for (Class<?> cls : classes) {
            if (cls == clazz) {
                return true;
            }
        }
        return false;
    }

}
