package tomkit.core.lang;

/**
 * 枚举工具类
 *
 * @author yh
 * @since 2021/4/2
 */
public final class Enums {

    private Enums() {
    }

    /**
     * 判断给定枚举名称和指定字符串是否相等
     *
     * @param e
     * @param name
     * @return
     */
    public static boolean equals(Enum<?> e, String name) {
        return null != e && e.name().equals(name);
    }

    /**
     * 判断给定枚举名称和指定字符串是否相等，忽略大小写
     *
     * @param e
     * @param name
     * @return
     */
    public static boolean equalsIgnoreCase(Enum<?> e, String name) {
        return null != e && e.name().equalsIgnoreCase(name);
    }

    /**
     * 将字符串转换为指定枚举类
     *
     * @param name
     * @param enumType
     * @param <T>
     * @return
     */
    public static <T extends Enum<T>> T valueOf(String name, Class<T> enumType) {
        return Enum.valueOf(enumType, name);
    }

}
