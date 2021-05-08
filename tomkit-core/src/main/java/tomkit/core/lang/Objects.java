package tomkit.core.lang;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.*;
import java.util.function.Supplier;

/**
 * 对象工具类
 *
 * @author yh
 * @since 2021/1/28
 */
public final class Objects {

    private static final int INITIAL_HASH = 7;
    private static final int MULTIPLIER = 31;

    private static final String EMPTY_STRING = "";
    private static final String NULL_STRING = "null";
    private static final String ARRAY_START = "{";
    private static final String ARRAY_END = "}";
    private static final String EMPTY_ARRAY = ARRAY_START + ARRAY_END;
    private static final String ARRAY_ELEMENT_SEPARATOR = ", ";

    /**
     * 不可实例化
     */
    private Objects() {
    }

    /**
     * 判断对象是否为 {@code null}
     *
     * @param obj a object
     * @return 是否为 {@code null}
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * <p>判断当前对象是否不为null</p>
     *
     * <pre>
     *     Objects.isNotNull(null)  = false
     *     Objects.isNotNull(1)     = true
     * </pre>
     *
     * @param obj a Object
     * @return 入参不为null返回true，否则返回false
     */
    public static boolean isNotNull(Object obj) {
        return null != obj;
    }

    /**
     * 判断所有入参对象是否全为 {@code null}
     *
     * @param objs Object array
     * @return 是否全为 {@code null}
     */
    public static boolean isAllNull(Object... objs) {
        for (Object obj : objs) {
            if (null != obj) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断所有入参对象是否全为 {@code null}
     *
     * @param objs 对象集合
     * @return 是否全为 {@code null}
     */
    public static boolean isAllNull(Iterable<Object> objs) {
        for (Object obj : objs) {
            if (null != obj) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断所有入参对象是否全不为 {@code null}
     *
     * @param objs 对象数组
     * @return 是否全不为 {@code null}
     */
    public static boolean isAllNotNull(Object... objs) {
        for (Object obj : objs) {
            if (null == obj) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断所有入参对象是否全不为 {@code null}
     *
     * @param objs 对象集合
     * @return 是否全不为 {@code null}
     */
    public static boolean isAllNotNull(Iterable<Object> objs) {
        for (Object obj : objs) {
            if (null == obj) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否有为 {@code null} 的对象
     *
     * @param objs 对象数组
     * @return 是否有为 {@code null}
     */
    public static boolean hasNull(Object... objs) {
        for (Object obj : objs) {
            if (null == obj) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否有为 {@code null} 的对象
     *
     * @param objs 对象集合
     * @return 是否有为 {@code null}
     */
    public static boolean hasNull(Iterable<Object> objs) {
        for (Object obj : objs) {
            if (null == obj) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否有不为 {@code null} 的对象
     *
     * @param objs 对象数组
     * @return 是否有不为 {@code null}
     */
    public static boolean hasNotNull(Object... objs) {
        for (Object obj : objs) {
            if (null != obj) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否存在不为 {@code null} 的对象
     *
     * @param objs 对象集合
     * @return 是否存在不为 {@code null}
     */
    public static boolean hasNotNull(Iterable<Object> objs) {
        for (Object obj : objs) {
            if (null != obj) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象是否为 {@code null} 或空对象
     * 支持字符串、集合、数组、Optional等类型
     * <ul>
     *     <li>null</li>
     *     <li>空字符序列</li>
     *     <li>空Optional</li>
     *     <li>空集合</li>
     *     <li>空Map</li>
     *     <li>空数组</li>
     * </ul>
     *
     * <pre>
     *     Objects.isEmpty(null)                    = true
     *     Objects.isEmpty("")                      = true
     *     Objects.isEmpty(Optional.empty)          = true
     *     Objects.isEmpty(Collections.emptyList()) = true
     *     Objects.isEmpty(Collections.emptyMap())  = true
     *     Objects.isEmpty(new String[0])           = true
     * </pre>
     *
     * @param obj o object
     * @return 是否为 {@code null} 或空对象
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Optional) {
            return !((Optional<?>) obj).isPresent();
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }

        // else
        return false;
    }

    /**
     * 判断对象是否不为 {@code null} 或空对象
     * 支持字符串、集合、数组、Optional等类型
     *
     * @param obj a object
     * @return 是否不为 {@code null} 或空对象
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 判断入参对象是否全为 {@code null} 或空对象
     * 支持字符串、集合、数组、Optional等类型
     *
     * @param objs 对象数组
     * @return 是否全为 {@code null} 或空对象
     */
    public static boolean isAllEmpty(Object... objs) {
        for (Object obj : objs) {
            if (isNotEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断入参对象是否全为 {@code null} 或空对象
     * 支持字符串、集合、数组、Optional等类型
     *
     * @param objs 对象集合
     * @return 是否全为 {@code null} 或空对象
     */
    public static boolean isAllEmpty(Iterable<Object> objs) {
        for (Object obj : objs) {
            if (isNotEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断入参对象是否全不为 {@code null} 或空对象
     * 支持字符串、集合、数组、Optional等类型
     *
     * @param objs 对象数组
     * @return 是否全不为 {@code null} 或空对象
     */
    public static boolean isAllNotEmpty(Object... objs) {
        for (Object obj : objs) {
            if (isEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断入参对象是否全不为 {@code null} 或空对象
     * 支持字符串、集合、数组、Optional等类型
     *
     * @param objs 对象集合
     * @return 是否全不为 {@code null} 或空对象
     */
    public static boolean isAllNotEmpty(Iterable<Object> objs) {
        for (Object obj : objs) {
            if (isEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断入参对象是否存在 {@code null} 或空对象
     * 支持字符串、集合、数组、Optional等类型
     *
     * @param objs 对象数组
     * @return 是否存在 {@code null} 或空对象
     */
    public static boolean hasEmpty(Object... objs) {
        for (Object obj : objs) {
            if (isEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断入参对象是否存在 {@code null} 或空对象
     * 支持字符串、集合、数组、Optional等类型
     *
     * @param objs 对象集合
     * @return 是否存在 {@code null} 或空对象
     */
    public static boolean hasEmpty(Iterable<Object> objs) {
        for (Object obj : objs) {
            if (isEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断入参对象是否存在不为 {@code null} 或空对象
     * 支持字符串、集合、数组、Optional等类型
     *
     * @param objs 对象数组
     * @return 是否存在不为 {@code null} 或空对象
     */
    public static boolean hasNotEmpty(Object... objs) {
        for (Object obj : objs) {
            if (isNotEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断入参对象是否存在不为 {@code null} 或空对象
     * 支持字符串、集合、数组、Optional等类型
     *
     * @param objs 对象集合
     * @return 是否存在不为 {@code null} 或空对象
     */
    public static boolean hasNotEmpty(Iterable<Object> objs) {
        for (Object obj : objs) {
            if (isNotEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两个对象是否相等
     *
     * @param a a object
     * @param b a object
     * @return 是否相等
     */
    public static boolean equals(Object a, Object b) {
        return java.util.Objects.equals(a, b);
    }

    /**
     * 判断两个对象是否不相等，两个对象都可以为null
     *
     * @param a a object
     * @param b a object
     * @return 两个对象是否不相等
     */
    public static boolean notEquals(Object a, Object b) {
        return !equals(a, b);
    }

    /**
     * 判断两个对象是否深层相等，此方法适用于任意深度的嵌套数组
     *
     * @param a a object
     * @param b a object
     * @return 是否深层相等
     */
    public static boolean deepEquals(Object a, Object b) {
        return java.util.Objects.deepEquals(a, b);
    }

    /**
     * 计算对象hashCode值
     *
     * @param o o object
     * @return 对象hashCode值
     */
    public static int hashCode(Object o) {
        return java.util.Objects.hashCode(o);
    }

    /**
     * 计算对象hash值
     *
     * @param values 对象数组
     * @return 对象哈希值
     */
    public static int hash(Object... values) {
        return java.util.Objects.hash(values);
    }

    /**
     * 返回对象的toString值
     *
     * @param o a object
     * @return 对象toString值
     */
    public static String toString(Object o) {
        return java.util.Objects.toString(o);
    }

    /**
     * 返回对象的toString值，如果当前为 {@code null} 则返回默认值
     *
     * @param o           a object
     * @param nullDefault 默认值
     * @return 对象toString值
     */
    public static String toString(Object o, String nullDefault) {
        return java.util.Objects.toString(o, nullDefault);
    }

    /**
     * 根据自定义实现的Comparator接口行为比较两个大小
     *
     * @param a   a object
     * @param b   a object
     * @param c   比较器
     * @param <T> 对象类型
     * @return 比较结果
     */
    public static <T> int compare(T a, T b, Comparator<? super T> c) {
        return java.util.Objects.compare(a, b, c);
    }

    /**
     * 检查对象是否为 {@code null} ，如果为 {@code null} 则抛出空指针异常
     *
     * @param obj a object
     * @param <T> 对象类型
     * @return 原始对象
     */
    public static <T> T requireNonNull(T obj) {
        return java.util.Objects.requireNonNull(obj);
    }

    /**
     * 检查对象是否为 {@code null} ，如果为 {@code null} 则抛出空指针异常，可指定异常message
     *
     * @param obj     a object
     * @param message message
     * @param <T>     T
     * @return 原始对象
     */
    public static <T> T requireNonNull(T obj, String message) {
        return java.util.Objects.requireNonNull(obj, message);
    }

    /**
     * 检查对象是否为 {@code null} ，如果为 {@code null} 则抛出空指针异常，可指定异常message
     *
     * @param obj             a object
     * @param messageSupplier 异常消息的提供者
     * @param <T>             对象类型
     * @return 原对象或生产的新对象
     */
    public static <T> T requireNonNull(T obj, Supplier<String> messageSupplier) {
        return java.util.Objects.requireNonNull(obj, messageSupplier);
    }

    /**
     * 判断对象是否为数组类型
     *
     * @param obj a object
     * @return 对象是否为数组
     */
    public static boolean isArray(Object obj) {
        return (obj != null && obj.getClass().isArray());
    }

    /**
     * 如果当前对象为 {@code null} 则返回默认值
     *
     * <pre>
     *     Objects.defaultIfNull(null, null)      = null
     *     Objects.defaultIfNull(null, "")        = ""
     *     Objects.defaultIfNull(null, "zz")      = "zz"
     *     Objects.defaultIfNull("abc", *)        = "abc"
     *     Objects.defaultIfNull(Boolean.TRUE, *) = Boolean.TRUE
     * </pre>
     *
     * @param value        a object
     * @param defaultValue 默认值
     * @return 返回对象或默认值
     */
    public static <T> T defaultIfNull(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * 如果当前对象为 {@code null} 则返回默认值
     *
     * @param value        a object
     * @param defaultValue 默认值
     * @return 返回对象或默认值
     */
    public static <T> T defaultIfNull(T value, Supplier<T> defaultValue) {
        return value != null ? value : defaultValue.get();
    }

    /**
     * 如果当前对象为 {@code} 或空对象则返回默认值
     *
     * <pre>
     *     Objects.defaultIfEmpty(null, null)       = null
     *     Objects.defaultIfEmpty(null, "")         = ""
     *     Objects.defaultIfEmpty("", "zz")         = "zz"
     *     Objects.defaultIfEmpty("abc", *)         = "abc"
     *     Objects.defaultIfEmpty(Boolean.TRUE, *)  = Boolean.TRUE
     * </pre>
     *
     * @param value        a object
     * @param defaultValue 默认值
     * @return 当前对象或默认值
     * @see #isEmpty(Object)
     */
    public static <T> T defaultIfEmpty(T value, T defaultValue) {
        return !isEmpty(value) ? value : defaultValue;
    }

    /**
     * 如果当前对象为 {@code} 或空对象则返回默认值
     *
     * @param value        a object
     * @param defaultValue 默认值
     * @return 当前对象或默认值
     * @see #isEmpty(Object)
     */
    public static <T> T defaultIfEmpty(T value, Supplier<T> defaultValue) {
        return !isEmpty(value) ? value : defaultValue.get();
    }

    /**
     * 判断布尔包装类是否为 {@code true}
     *
     * @param b a boolean
     * @return 是否为 {@code true}
     */
    public static boolean isTrue(Boolean b) {
        return null != b && b;
    }

    /**
     * 判断布尔包装类是否不为 {@code true}
     *
     * @param b a boolean
     * @return 是否不为 {@code true}
     */
    public static boolean isNotTrue(Boolean b) {
        return null == b || !b;
    }

    /**
     * 判断布尔包装类是否为 {@code false}
     *
     * @param b a boolean
     * @return 是否为 {@code false}
     */
    public static boolean isFalse(Boolean b) {
        return null != b && !b;
    }

    /**
     * 判断布尔包装类是否不为 {@code false}
     *
     * @param b a boolean
     * @return 是否不为 {@code false}
     */
    public static boolean isNotFalse(Boolean b) {
        return null == b || b;
    }

    /**
     * 判断异常是否属于 {@link RuntimeException} 或 {@link Error}
     *
     * @param ex a throwable
     * @return 是否属于 {@link RuntimeException} 或 {@link Error}
     */
    public static boolean isCheckedException(Throwable ex) {
        return !(ex instanceof RuntimeException || ex instanceof Error);
    }

    /**
     * 检查给定数组是否包含给定元素
     *
     * @param array   对象数组
     * @param element 对象
     * @return 数组是否包含给定对象
     */
    public static boolean containsElement(Object[] array, Object element) {
        if (array == null) {
            return false;
        }
        for (Object arrayEle : array) {
            if (nullSafeEquals(arrayEle, element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查给定枚举常量数组是否包含给定名称的常量，区分大小写
     *
     * @param enumValues 枚举数组
     * @param constant   常量名称
     * @return 是否包含
     */
    public static boolean containsConstant(Enum<?>[] enumValues, String constant) {
        return containsConstant(enumValues, constant, false);
    }

    /**
     * 检查给定枚举常量数组是否包含给定名称的常量
     *
     * @param enumValues    枚举数组
     * @param constant      字符常量
     * @param caseSensitive 是否区分大小写
     * @return 是否包含
     */
    public static boolean containsConstant(Enum<?>[] enumValues, String constant, boolean caseSensitive) {
        for (Enum<?> candidate : enumValues) {
            if (caseSensitive ? candidate.toString().equals(constant) :
                    candidate.toString().equalsIgnoreCase(constant)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 不分大小写替代 {@link Enum#valueOf(Class, String)}
     *
     * @param enumValues 枚举数组
     * @param constant   字符常量
     * @param <E>        枚举类型
     * @return 枚举对象
     */
    public static <E extends Enum<?>> E caseInsensitiveValueOf(E[] enumValues, String constant) {
        for (E candidate : enumValues) {
            if (candidate.toString().equalsIgnoreCase(constant)) {
                return candidate;
            }
        }
        throw new IllegalArgumentException("Constant [" + constant + "] does not exist in enum type " +
                enumValues.getClass().getComponentType().getName());
    }

    /**
     * 将给定对象添加到给定数组中，返回一个新的数组
     *
     * @param array 对象数组
     * @param obj   指定对象
     * @param <A>   数组类型
     * @param <O>   指定对象类型
     * @return 新数组
     */
    public static <A, O extends A> A[] arrayAddItem(A[] array, O obj) {
        Class<?> compType = Object.class;
        if (array != null) {
            compType = array.getClass().getComponentType();
        } else if (obj != null) {
            compType = obj.getClass();
        }
        int newArrLength = (array != null ? array.length + 1 : 1);
        @SuppressWarnings("unchecked")
        A[] newArr = (A[]) Array.newInstance(compType, newArrLength);
        if (array != null) {
            System.arraycopy(array, 0, newArr, 0, array.length);
        }
        newArr[newArr.length - 1] = obj;
        return newArr;
    }

    /**
     * 判断给定的对象是否相等，支持数组内容对比
     *
     * @param o1 a object
     * @param o2 a object
     * @return 对象是否相等
     * @see Object#equals(Object)
     * @see java.util.Arrays#equals
     */
    public static boolean nullSafeEquals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        if (o1.equals(o2)) {
            return true;
        }
        if (o1.getClass().isArray() && o2.getClass().isArray()) {
            return arrayEquals(o1, o2);
        }
        return false;
    }

    /**
     * 比较两个对象（数组）是否相等
     *
     * @param array1 a array object
     * @param array2 a array object
     * @return 是否相等
     * @see java.util.Arrays#equals
     */
    private static boolean arrayEquals(Object array1, Object array2) {
        if (array1 instanceof Object[] && array2 instanceof Object[]) {
            return Arrays.equals((Object[]) array1, (Object[]) array2);
        }
        if (array1 instanceof boolean[] && array2 instanceof boolean[]) {
            return Arrays.equals((boolean[]) array1, (boolean[]) array2);
        }
        if (array1 instanceof byte[] && array2 instanceof byte[]) {
            return Arrays.equals((byte[]) array1, (byte[]) array2);
        }
        if (array1 instanceof char[] && array2 instanceof char[]) {
            return Arrays.equals((char[]) array1, (char[]) array2);
        }
        if (array1 instanceof double[] && array2 instanceof double[]) {
            return Arrays.equals((double[]) array1, (double[]) array2);
        }
        if (array1 instanceof float[] && array2 instanceof float[]) {
            return Arrays.equals((float[]) array1, (float[]) array2);
        }
        if (array1 instanceof int[] && array2 instanceof int[]) {
            return Arrays.equals((int[]) array1, (int[]) array2);
        }
        if (array1 instanceof long[] && array2 instanceof long[]) {
            return Arrays.equals((long[]) array1, (long[]) array2);
        }
        if (array1 instanceof short[] && array2 instanceof short[]) {
            return Arrays.equals((short[]) array1, (short[]) array2);
        }
        return false;
    }

    /**
     * 返回给定对象的哈希码，支持数组类型
     *
     * @param obj a object
     * @return 对象哈希码
     * @see Object#hashCode()
     * @see #nullSafeHashCode(Object[])
     * @see #nullSafeHashCode(boolean[])
     * @see #nullSafeHashCode(byte[])
     * @see #nullSafeHashCode(char[])
     * @see #nullSafeHashCode(double[])
     * @see #nullSafeHashCode(float[])
     * @see #nullSafeHashCode(int[])
     * @see #nullSafeHashCode(long[])
     * @see #nullSafeHashCode(short[])
     */
    public static int nullSafeHashCode(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj.getClass().isArray()) {
            if (obj instanceof Object[]) {
                return nullSafeHashCode((Object[]) obj);
            }
            if (obj instanceof boolean[]) {
                return nullSafeHashCode((boolean[]) obj);
            }
            if (obj instanceof byte[]) {
                return nullSafeHashCode((byte[]) obj);
            }
            if (obj instanceof char[]) {
                return nullSafeHashCode((char[]) obj);
            }
            if (obj instanceof double[]) {
                return nullSafeHashCode((double[]) obj);
            }
            if (obj instanceof float[]) {
                return nullSafeHashCode((float[]) obj);
            }
            if (obj instanceof int[]) {
                return nullSafeHashCode((int[]) obj);
            }
            if (obj instanceof long[]) {
                return nullSafeHashCode((long[]) obj);
            }
            if (obj instanceof short[]) {
                return nullSafeHashCode((short[]) obj);
            }
        }
        return obj.hashCode();
    }

    /**
     * 返回指定数组内容的哈希码
     *
     * @param array object数组
     * @return 数组内容哈希码
     */
    public static int nullSafeHashCode(Object[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (Object element : array) {
            hash = MULTIPLIER * hash + nullSafeHashCode(element);
        }
        return hash;
    }

    /**
     * 返回指定数组内容的哈希码
     *
     * @param array boolean数组
     * @return 数组内容哈希码
     */
    public static int nullSafeHashCode(boolean[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (boolean element : array) {
            hash = MULTIPLIER * hash + Boolean.hashCode(element);
        }
        return hash;
    }

    /**
     * 返回指定数组内容的哈希码
     *
     * @param array byte数组
     * @return 数组内容哈希码
     */
    public static int nullSafeHashCode(byte[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (byte element : array) {
            hash = MULTIPLIER * hash + element;
        }
        return hash;
    }

    /**
     * 返回指定数组内容的哈希码
     *
     * @param array char数组
     * @return 数组内容哈希码
     */
    public static int nullSafeHashCode(char[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (char element : array) {
            hash = MULTIPLIER * hash + element;
        }
        return hash;
    }

    /**
     * 返回指定数组内容的哈希码
     *
     * @param array double数组
     * @return 数组内容哈希码
     */
    public static int nullSafeHashCode(double[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (double element : array) {
            hash = MULTIPLIER * hash + Double.hashCode(element);
        }
        return hash;
    }

    /**
     * 返回指定数组内容的哈希码
     *
     * @param array float数组
     * @return 数组内容哈希码
     */
    public static int nullSafeHashCode(float[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (float element : array) {
            hash = MULTIPLIER * hash + Float.hashCode(element);
        }
        return hash;
    }

    /**
     * 返回指定数组内容的哈希码
     *
     * @param array int数组
     * @return 数组内容哈希码
     */
    public static int nullSafeHashCode(int[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (int element : array) {
            hash = MULTIPLIER * hash + element;
        }
        return hash;
    }

    /**
     * 返回指定数组内容的哈希码
     *
     * @param array long数组
     * @return 数组内容哈希码
     */
    public static int nullSafeHashCode(long[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (long element : array) {
            hash = MULTIPLIER * hash + Long.hashCode(element);
        }
        return hash;
    }

    /**
     * 返回指定数组内容的哈希码
     *
     * @param array short数组
     * @return 数组内容哈希码
     */
    public static int nullSafeHashCode(short[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (short element : array) {
            hash = MULTIPLIER * hash + element;
        }
        return hash;
    }

    /**
     * 返回对象的完整的字符串形式
     *
     * @param obj a object
     * @return 对象字符串形式
     */
    public static String identityToString(Object obj) {
        if (obj == null) {
            return EMPTY_STRING;
        }
        return obj.getClass().getName() + "@" + getIdentityHexString(obj);
    }

    /**
     * 返回对象哈希码的十六进制字符串
     *
     * @param obj a object
     * @return 哈希码十六进制
     */
    public static String getIdentityHexString(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    /**
     * 返回对象字符串值，null返回空字符
     *
     * @param obj a object
     * @return 字符串值
     */
    public static String getDisplayString(Object obj) {
        if (obj == null) {
            return EMPTY_STRING;
        }
        return nullSafeToString(obj);
    }

    /**
     * 返回对象类名
     *
     * @param obj o object
     * @param <T> 对象类型
     * @return 对象类名
     */
    public static <T> String nullSafeClassName(T obj) {
        return (obj != null ? obj.getClass().getName() : NULL_STRING);
    }

    /**
     * 返回指定对象（数组）的字符串表示形式
     *
     * @param obj a object
     * @return 对象字符串形式
     * @see Object#hashCode()
     * @see #nullSafeToString(Object[])
     * @see #nullSafeToString(boolean[])
     * @see #nullSafeToString(byte[])
     * @see #nullSafeToString(char[])
     * @see #nullSafeToString(double[])
     * @see #nullSafeToString(float[])
     * @see #nullSafeToString(int[])
     * @see #nullSafeToString(long[])
     * @see #nullSafeToString(short[])
     */
    public static String nullSafeToString(Object obj) {
        if (obj == null) {
            return NULL_STRING;
        }
        if (obj instanceof CharSequence) {
            return obj.toString();
        }
        if (obj instanceof Object[]) {
            return nullSafeToString((Object[]) obj);
        }
        if (obj instanceof boolean[]) {
            return nullSafeToString((boolean[]) obj);
        }
        if (obj instanceof byte[]) {
            return nullSafeToString((byte[]) obj);
        }
        if (obj instanceof char[]) {
            return nullSafeToString((char[]) obj);
        }
        if (obj instanceof double[]) {
            return nullSafeToString((double[]) obj);
        }
        if (obj instanceof float[]) {
            return nullSafeToString((float[]) obj);
        }
        if (obj instanceof int[]) {
            return nullSafeToString((int[]) obj);
        }
        if (obj instanceof long[]) {
            return nullSafeToString((long[]) obj);
        }
        if (obj instanceof short[]) {
            return nullSafeToString((short[]) obj);
        }
        String str = obj.toString();
        return (str != null ? str : EMPTY_STRING);
    }

    /**
     * 返回Object数组的字符串形式
     *
     * @param array object数组
     * @return 数组字符串形式
     */
    public static String nullSafeToString(Object[] array) {
        if (array == null) {
            return NULL_STRING;
        }
        if (array.length == 0) {
            return EMPTY_ARRAY;
        }
        StringJoiner stringJoiner = new StringJoiner(ARRAY_ELEMENT_SEPARATOR, ARRAY_START, ARRAY_END);
        for (Object o : array) {
            stringJoiner.add(String.valueOf(o));
        }
        return stringJoiner.toString();
    }

    /**
     * 返回boolean数组的字符串形式
     *
     * @param array boolean数组
     * @return 数组字符串形式
     */
    public static String nullSafeToString(boolean[] array) {
        if (array == null) {
            return NULL_STRING;
        }
        if (array.length == 0) {
            return EMPTY_ARRAY;
        }
        StringJoiner stringJoiner = new StringJoiner(ARRAY_ELEMENT_SEPARATOR, ARRAY_START, ARRAY_END);
        for (boolean b : array) {
            stringJoiner.add(String.valueOf(b));
        }
        return stringJoiner.toString();
    }

    /**
     * 返回byte数组的字符串形式
     *
     * @param array byte数组
     * @return 数组字符串形式
     */
    public static String nullSafeToString(byte[] array) {
        if (array == null) {
            return NULL_STRING;
        }
        if (array.length == 0) {
            return EMPTY_ARRAY;
        }
        StringJoiner stringJoiner = new StringJoiner(ARRAY_ELEMENT_SEPARATOR, ARRAY_START, ARRAY_END);
        for (byte b : array) {
            stringJoiner.add(String.valueOf(b));
        }
        return stringJoiner.toString();
    }

    /**
     * 返回char数组的字符串形式
     *
     * @param array char数组
     * @return 数组字符串形式
     */
    public static String nullSafeToString(char[] array) {
        if (array == null) {
            return NULL_STRING;
        }
        if (array.length == 0) {
            return EMPTY_ARRAY;
        }
        StringJoiner stringJoiner = new StringJoiner(ARRAY_ELEMENT_SEPARATOR, ARRAY_START, ARRAY_END);
        for (char c : array) {
            stringJoiner.add('\'' + String.valueOf(c) + '\'');
        }
        return stringJoiner.toString();
    }

    /**
     * 返回double数组的字符串形式
     *
     * @param array double数组
     * @return 数组字符串形式
     */
    public static String nullSafeToString(double[] array) {
        if (array == null) {
            return NULL_STRING;
        }
        if (array.length == 0) {
            return EMPTY_ARRAY;
        }
        StringJoiner stringJoiner = new StringJoiner(ARRAY_ELEMENT_SEPARATOR, ARRAY_START, ARRAY_END);
        for (double d : array) {
            stringJoiner.add(String.valueOf(d));
        }
        return stringJoiner.toString();
    }

    /**
     * 返回float数组的字符串形式
     *
     * @param array float数组
     * @return 数组字符串形式
     */
    public static String nullSafeToString(float[] array) {
        if (array == null) {
            return NULL_STRING;
        }
        if (array.length == 0) {
            return EMPTY_ARRAY;
        }
        StringJoiner stringJoiner = new StringJoiner(ARRAY_ELEMENT_SEPARATOR, ARRAY_START, ARRAY_END);
        for (float f : array) {
            stringJoiner.add(String.valueOf(f));
        }
        return stringJoiner.toString();
    }

    /**
     * 返回int数组的字符串形式
     *
     * @param array int数组
     * @return 数组字符串形式
     */
    public static String nullSafeToString(int[] array) {
        if (array == null) {
            return NULL_STRING;
        }
        if (array.length == 0) {
            return EMPTY_ARRAY;
        }
        StringJoiner stringJoiner = new StringJoiner(ARRAY_ELEMENT_SEPARATOR, ARRAY_START, ARRAY_END);
        for (int i : array) {
            stringJoiner.add(String.valueOf(i));
        }
        return stringJoiner.toString();
    }

    /**
     * 返回long数组的字符串形式
     *
     * @param array long数组
     * @return 数组字符串形式
     */
    public static String nullSafeToString(long[] array) {
        if (array == null) {
            return NULL_STRING;
        }
        if (array.length == 0) {
            return EMPTY_ARRAY;
        }
        StringJoiner stringJoiner = new StringJoiner(ARRAY_ELEMENT_SEPARATOR, ARRAY_START, ARRAY_END);
        for (long l : array) {
            stringJoiner.add(String.valueOf(l));
        }
        return stringJoiner.toString();
    }

    /**
     * 返回short数组的字符串形式
     *
     * @param array short数组
     * @return 数组字符串形式
     */
    public static String nullSafeToString(short[] array) {
        if (array == null) {
            return NULL_STRING;
        }
        if (array.length == 0) {
            return EMPTY_ARRAY;
        }
        StringJoiner stringJoiner = new StringJoiner(ARRAY_ELEMENT_SEPARATOR, ARRAY_START, ARRAY_END);
        for (short s : array) {
            stringJoiner.add(String.valueOf(s));
        }
        return stringJoiner.toString();
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

}
