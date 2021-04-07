package tomkit.core.lang;

import java.util.*;

/**
 * 数组工具类
 *
 * @author yh
 * @since 2021/3/26
 */
public class Arrays {

    /* ------------------------------------------- isEmpty ------------------------------------------- */

    /**
     * 数组是否为{@code null}或空
     *
     * @param array 泛型数组
     * @param <T>   泛型
     * @return 是否为null或空
     */
    public static <T> boolean isEmpty(T[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 数组是否为{@code null}或空
     *
     * @param array 字节数组
     * @return 是否为null或空
     */
    public static boolean isEmpty(byte[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 数组是否为{@code null}或空
     *
     * @param array 短整型数组
     * @return 是否为null或空
     */
    public static boolean isEmpty(short[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 数组是否为{@code null}或空
     *
     * @param array 整型数组
     * @return 是否为null或空
     */
    public static boolean isEmpty(int[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 数组是否为{@code null}或空
     *
     * @param array 长整型数组
     * @return 是否为null或空
     */
    public static boolean isEmpty(long[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 数组是否为{@code null}或空
     *
     * @param array 浮点数数组
     * @return 是否为null或空
     */
    public static boolean isEmpty(float[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 数组是否为{@code null}或空
     *
     * @param array 双精度浮点数数组
     * @return 是否为null或空
     */
    public static boolean isEmpty(double[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 数组是否为{@code null}或空
     *
     * @param array 字符数组
     * @return 是否为null或空
     */
    public static boolean isEmpty(char[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 数组是否为{@code null}或空
     *
     * @param array 布尔数组
     * @return 是否为null或空
     */
    public static boolean isEmpty(boolean[] array) {
        return null == array || array.length == 0;
    }

    /* ------------------------------------------- isNotEmpty ------------------------------------------- */

    /**
     * 数组是否不为{@code null}或空
     *
     * @param array 泛型数组
     * @param <T>   泛型
     * @return 是否不为null或空
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return null != array && array.length != 0;
    }

    /**
     * 数组是否不为{@code null}或空
     *
     * @param array 字节数组
     * @return 是否不为null或空
     */
    public static boolean isNotEmpty(byte[] array) {
        return !isEmpty(array);
    }

    /**
     * 数组是否不为{@code null}或空
     *
     * @param array 短整型数组
     * @return 是否不为null或空
     */
    public static boolean isNotEmpty(short[] array) {
        return !isEmpty(array);
    }

    /**
     * 数组是否不为{@code null}或空
     *
     * @param array 整型数组
     * @return 是否不为null或空
     */
    public static boolean isNotEmpty(int[] array) {
        return !isEmpty(array);
    }

    /**
     * 数组是否不为{@code null}或空
     *
     * @param array 长整型数组
     * @return 是否不为null或空
     */
    public static boolean isNotEmpty(long[] array) {
        return !isEmpty(array);
    }

    /**
     * 数组是否不为{@code null}或空
     *
     * @param array 浮点数数组
     * @return 是否不为null或空
     */
    public static boolean isNotEmpty(float[] array) {
        return !isEmpty(array);
    }

    /**
     * 数组是否不为{@code null}或空
     *
     * @param array 双精度浮点数数组
     * @return 是否不为null或空
     */
    public static boolean isNotEmpty(double[] array) {
        return !isEmpty(array);
    }

    /**
     * 数组是否不为{@code null}或空
     *
     * @param array 字符数组
     * @return 是否不为null或空
     */
    public static boolean isNotEmpty(char[] array) {
        return !isEmpty(array);
    }

    /**
     * 数组是否不为{@code null}或空
     *
     * @param array 布尔数组
     * @return 是否不为null或空
     */
    public static boolean isNotEmpty(boolean[] array) {
        return !isEmpty(array);
    }

    /**
     * 将可变长参数转换为Set集合
     *
     * @param a   可变长参数
     * @param <T> 参数类型
     * @return Set集合
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> Set<T> asSet(T... a) {
        return new HashSet<>(java.util.Arrays.asList(a));
    }


}
