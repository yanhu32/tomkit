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
     * @param e    指定枚举
     * @param name 指定字符串
     * @return 是否相等
     */
    public static boolean equals(Enum<?> e, String name) {
        return null != e && e.name().equals(name);
    }

    /**
     * 判断给定枚举名称和指定字符串是否相等，忽略大小写
     *
     * @param e    指定枚举
     * @param name 指定字符串
     * @return 是否相等，忽略大小写
     */
    public static boolean equalsIgnoreCase(Enum<?> e, String name) {
        return null != e && e.name().equalsIgnoreCase(name);
    }

    /**
     * 将字符串转换为指定枚举类
     *
     * @param name     枚举名
     * @param enumType 枚举类型Class
     * @param <T>      枚举类型
     * @return 枚举类
     */
    public static <T extends Enum<T>> T valueOf(String name, Class<T> enumType) {
        return Enum.valueOf(enumType, name);
    }

    /**
     * 判断给的枚举数组内是否存在名称与给的字符串相同
     *
     * @param enums 枚举数组
     * @param name  字符串
     * @return 是否存在名称相同
     */
    public static boolean include(Enum<?>[] enums, String name) {
        for (Enum<?> e : enums) {
            if (equals(e, name)) {
                return true;
            }
        }
        return false;
    }
}
