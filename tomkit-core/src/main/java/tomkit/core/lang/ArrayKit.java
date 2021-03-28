package tomkit.core.lang;

/**
 * 数组工具类
 *
 * @author yh
 * @since 2021/3/26
 */
public class ArrayKit {

    public static <T> boolean isEmpty(T[] array) {
        return null == array || array.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return null != array && array.length != 0;
    }

    public static boolean isEmpty(char[] array) {
        return null == array || array.length == 0;
    }

    public static boolean isNotEmpty(char[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(byte[] array) {
        return null == array || array.length == 0;
    }

    public static boolean isNotEmpty(byte[] array) {
        return !isEmpty(array);
    }


}
