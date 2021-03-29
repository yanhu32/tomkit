package tomkit.core.bean.init;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

/**
 * @author yh
 * @since 2021/1/12
 */
enum InitJavaType {
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
    ZONED_DATE_TIME(ZonedDateTime.class),
    OFFSET_DATE_TIME(OffsetDateTime.class),
    ;

    private final Class<?>[] classes;

    InitJavaType(Class<?> cls) {
        this.classes = new Class[]{cls};
    }

    InitJavaType(Class<?> cls0, Class<?> cls1) {
        this.classes = new Class[]{cls0, cls1};
    }

    public static Optional<InitJavaType> get(Class<?> clazz) {
        return Arrays.stream(InitJavaType.values())
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
