package tomkit.core.lang;

import java.util.*;

/**
 * 数组工具类
 *
 * @author yh
 * @since 2021/3/26
 */
public final class Arrays {

    private Arrays() {
    }

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

    /**
     * 空数组
     *
     * @return 空数组
     */
    public static byte[] emptyBytes() {
        return new byte[0];
    }

    /**
     * 空数组
     *
     * @return 空数组
     */
    public static short[] emptyShorts() {
        return new short[0];
    }

    /**
     * 空数组
     *
     * @return 空数组
     */
    public static int[] emptyInts() {
        return new int[0];
    }

    /**
     * 空数组
     *
     * @return 空数组
     */
    public static long[] emptyLongs() {
        return new long[0];
    }

    /**
     * 空数组
     *
     * @return 空数组
     */
    public static float[] emptyFloats() {
        return new float[0];
    }

    /**
     * 空数组
     *
     * @return 空数组
     */
    public static double[] emptyDoubles() {
        return new double[0];
    }

    /**
     * 空数组
     *
     * @return 空数组
     */
    public static char[] emptyChars() {
        return new char[0];
    }

    /**
     * 空数组
     *
     * @return 空数组
     */
    public static boolean[] emptyBooleans() {
        return new boolean[0];
    }

    /**
     * 返回当前数组，当前数组为{@code null}时返回默认数组
     *
     * @param value        当前数组
     * @param defaultValue 默认数组
     * @param <T>          数组类型
     * @return 当前数组或默认数组
     */
    public static <T> T[] defaultIfNull(T[] value, T[] defaultValue) {
        return null != value ? value : defaultValue;
    }

    /**
     * 返回当前数组，当前数组为{@code null}时返回默认数组
     *
     * @param value        当前数组
     * @param defaultValue 默认数组
     * @return 当前数组或默认数组
     */
    public static byte[] defaultIfNull(byte[] value, byte[] defaultValue) {
        return null != value ? value : defaultValue;
    }

    /**
     * 返回当前数组，当前数组为{@code null}时返回默认数组
     *
     * @param value        当前数组
     * @param defaultValue 默认数组
     * @return 当前数组或默认数组
     */
    public static short[] defaultIfNull(short[] value, short[] defaultValue) {
        return null != value ? value : defaultValue;
    }

    /**
     * 返回当前数组，当前数组为{@code null}时返回默认数组
     *
     * @param value        当前数组
     * @param defaultValue 默认数组
     * @return 当前数组或默认数组
     */
    public static int[] defaultIfNull(int[] value, int[] defaultValue) {
        return null != value ? value : defaultValue;
    }

    /**
     * 返回当前数组，当前数组为{@code null}时返回默认数组
     *
     * @param value        当前数组
     * @param defaultValue 默认数组
     * @return 当前数组或默认数组
     */
    public static long[] defaultIfNull(long[] value, long[] defaultValue) {
        return null != value ? value : defaultValue;
    }

    /**
     * 返回当前数组，当前数组为{@code null}时返回默认数组
     *
     * @param value        当前数组
     * @param defaultValue 默认数组
     * @return 当前数组或默认数组
     */
    public static float[] defaultIfNull(float[] value, float[] defaultValue) {
        return null != value ? value : defaultValue;
    }

    /**
     * 返回当前数组，当前数组为{@code null}时返回默认数组
     *
     * @param value        当前数组
     * @param defaultValue 默认数组
     * @return 当前数组或默认数组
     */
    public static double[] defaultIfNull(double[] value, double[] defaultValue) {
        return null != value ? value : defaultValue;
    }

    /**
     * 返回当前数组，当前数组为{@code null}时返回默认数组
     *
     * @param value        当前数组
     * @param defaultValue 默认数组
     * @return 当前数组或默认数组
     */
    public static char[] defaultIfNull(char[] value, char[] defaultValue) {
        return null != value ? value : defaultValue;
    }

    /**
     * 返回当前数组，当前数组为{@code null}时返回默认数组
     *
     * @param value        当前数组
     * @param defaultValue 默认数组
     * @return 当前数组或默认数组
     */
    public static boolean[] defaultIfNull(boolean[] value, boolean[] defaultValue) {
        return null != value ? value : defaultValue;
    }

    /**
     * 获取两个数组长度的最小值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @param <T>    数组类型
     * @return 长度最小值
     */
    public static <T> int minLen(T[] array1, T[] array2) {
        return Math.min(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最小值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最小值
     */
    public static int minLen(byte[] array1, byte[] array2) {
        return Math.min(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最小值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最小值
     */
    public static int minLen(short[] array1, short[] array2) {
        return Math.min(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最小值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最小值
     */
    public static int minLen(int[] array1, int[] array2) {
        return Math.min(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最小值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最小值
     */
    public static int minLen(long[] array1, long[] array2) {
        return Math.min(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最小值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最小值
     */
    public static int minLen(float[] array1, float[] array2) {
        return Math.min(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最小值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最小值
     */
    public static int minLen(double[] array1, double[] array2) {
        return Math.min(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最小值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最小值
     */
    public static int minLen(char[] array1, char[] array2) {
        return Math.min(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最小值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最小值
     */
    public static int minLen(boolean[] array1, boolean[] array2) {
        return Math.min(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最大值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @param <T>    数组类型
     * @return 长度最大值
     */
    public static <T> int maxLen(T[] array1, T[] array2) {
        return Math.max(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最大值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最大值
     */
    public static int maxLen(byte[] array1, byte[] array2) {
        return Math.max(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最大值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最大值
     */
    public static int maxLen(short[] array1, short[] array2) {
        return Math.max(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最大值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最大值
     */
    public static int maxLen(int[] array1, int[] array2) {
        return Math.max(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最大值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最大值
     */
    public static int maxLen(long[] array1, long[] array2) {
        return Math.max(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最大值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最大值
     */
    public static int maxLen(float[] array1, float[] array2) {
        return Math.max(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最大值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最大值
     */
    public static int maxLen(double[] array1, double[] array2) {
        return Math.max(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最大值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最大值
     */
    public static int maxLen(char[] array1, char[] array2) {
        return Math.max(array1.length, array2.length);
    }

    /**
     * 获取两个数组长度的最大值
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 长度最大值
     */
    public static int maxLen(boolean[] array1, boolean[] array2) {
        return Math.max(array1.length, array2.length);
    }

    /**
     * 获取两个数组中长度较小的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @param <T>    数组类型
     * @return 较小数组
     */
    public static <T> T[] min(T[] array1, T[] array2) {
        return array1.length > array2.length ? array2 : array1;
    }

    /**
     * 获取两个数组中长度较小的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较小数组
     */
    public static byte[] min(byte[] array1, byte[] array2) {
        return array1.length > array2.length ? array2 : array1;
    }

    /**
     * 获取两个数组中长度较小的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较小数组
     */
    public static short[] min(short[] array1, short[] array2) {
        return array1.length > array2.length ? array2 : array1;
    }

    /**
     * 获取两个数组中长度较小的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较小数组
     */
    public static int[] min(int[] array1, int[] array2) {
        return array1.length > array2.length ? array2 : array1;
    }

    /**
     * 获取两个数组中长度较小的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较小数组
     */
    public static long[] min(long[] array1, long[] array2) {
        return array1.length > array2.length ? array2 : array1;
    }

    /**
     * 获取两个数组中长度较小的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较小数组
     */
    public static float[] min(float[] array1, float[] array2) {
        return array1.length > array2.length ? array2 : array1;
    }

    /**
     * 获取两个数组中长度较小的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较小数组
     */
    public static double[] min(double[] array1, double[] array2) {
        return array1.length > array2.length ? array2 : array1;
    }

    /**
     * 获取两个数组中长度较小的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较小数组
     */
    public static char[] min(char[] array1, char[] array2) {
        return array1.length > array2.length ? array2 : array1;
    }

    /**
     * 获取两个数组中长度较小的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较小数组
     */
    public static boolean[] min(boolean[] array1, boolean[] array2) {
        return array1.length > array2.length ? array2 : array1;
    }

    /**
     * 获取两个数组中长度较大的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @param <T>    数组类型
     * @return 较大数组
     */
    public static <T> T[] max(T[] array1, T[] array2) {
        return array1.length >= array2.length ? array1 : array2;
    }

    /**
     * 获取两个数组中长度较大的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较大数组
     */
    public static byte[] max(byte[] array1, byte[] array2) {
        return array1.length >= array2.length ? array1 : array2;
    }

    /**
     * 获取两个数组中长度较大的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较大数组
     */
    public static short[] max(short[] array1, short[] array2) {
        return array1.length >= array2.length ? array1 : array2;
    }

    /**
     * 获取两个数组中长度较大的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较大数组
     */
    public static int[] max(int[] array1, int[] array2) {
        return array1.length >= array2.length ? array1 : array2;
    }

    /**
     * 获取两个数组中长度较大的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较大数组
     */
    public static long[] max(long[] array1, long[] array2) {
        return array1.length >= array2.length ? array1 : array2;
    }

    /**
     * 获取两个数组中长度较大的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较大数组
     */
    public static float[] max(float[] array1, float[] array2) {
        return array1.length >= array2.length ? array1 : array2;
    }

    /**
     * 获取两个数组中长度较大的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较大数组
     */
    public static double[] max(double[] array1, double[] array2) {
        return array1.length >= array2.length ? array1 : array2;
    }

    /**
     * 获取两个数组中长度较大的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较大数组
     */
    public static char[] max(char[] array1, char[] array2) {
        return array1.length >= array2.length ? array1 : array2;
    }

    /**
     * 获取两个数组中长度较大的，长度相对时返回第一个数组
     *
     * @param array1 数组1
     * @param array2 数组2
     * @return 较大数组
     */
    public static boolean[] max(boolean[] array1, boolean[] array2) {
        return array1.length >= array2.length ? array1 : array2;
    }

}
