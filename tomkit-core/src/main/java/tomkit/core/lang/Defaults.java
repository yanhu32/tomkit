package tomkit.core.lang;

/**
 * 这个类为JLS（Java 语言规范）定义的所有Java类型提供默认值
 *
 * @author yh
 * @since 2021/4/7
 */
public final class Defaults {
    private Defaults() {
    }

    /**
     * 返回JLS定义的{@code type}的默认值
     *
     * @param <T>  JLS定义的类型
     * @param type JLS定义的类型的Class
     * @return 默认值
     */
    @SuppressWarnings("unchecked")
    public static <T> T defaultValue(Class<T> type) {
        java.util.Objects.requireNonNull(type);
        if (type == boolean.class) {
            return (T) Boolean.FALSE;
        } else if (type == char.class) {
            return (T) Character.valueOf('\0');
        } else if (type == byte.class) {
            return (T) Byte.valueOf((byte) 0);
        } else if (type == short.class) {
            return (T) Short.valueOf((short) 0);
        } else if (type == int.class) {
            return (T) Integer.valueOf(0);
        } else if (type == long.class) {
            return (T) Long.valueOf(0L);
        } else if (type == float.class) {
            return (T) Float.valueOf(0f);
        } else if (type == double.class) {
            return (T) Double.valueOf(0d);
        } else {
            return null;
        }
    }
}