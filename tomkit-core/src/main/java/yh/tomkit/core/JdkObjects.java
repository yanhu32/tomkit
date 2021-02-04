package yh.tomkit.core;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * jdk objects
 */
abstract class JdkObjects {

    /**
     * 判断两个对象是否相等
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(Object a, Object b) {
        return Objects.equals(a, b);
    }

    /**
     * 判断两个对象是否深层相等，此方法适用于任意深度的嵌套数组
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean deepEquals(Object a, Object b) {
        return Objects.deepEquals(a, b);
    }

    /**
     * 计算对象hashCode值
     *
     * @param o
     * @return 对象hashCode值
     */
    public static int hashCode(Object o) {
        return Objects.hashCode(o);
    }

    /**
     * 计算对象hash值
     *
     * @param values
     * @return
     */
    public static int hash(Object... values) {
        return Objects.hash(values);
    }

    /**
     * 返回对象的toString值
     *
     * @param o
     * @return
     */
    public static String toString(Object o) {
        return Objects.toString(o);
    }

    /**
     * 返回对象的toString值，如果当前为null则返回默认值
     *
     * @param o
     * @param nullDefault
     * @return
     */
    public static String toString(Object o, String nullDefault) {
        return Objects.toString(o, nullDefault);
    }

    /**
     * 根据自定义实现的Comparator接口行为比较两个大小
     *
     * @param a
     * @param b
     * @param c
     * @param <T>
     * @return
     */
    public static <T> int compare(T a, T b, Comparator<? super T> c) {
        return Objects.compare(a, b, c);
    }

    /**
     * 检查对象是否为null，如果为null则抛出空指针异常
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> T requireNonNull(T obj) {
        return Objects.requireNonNull(obj);
    }

    /**
     * 检查对象是否为null，如果为null则抛出空指针异常，可指定异常message
     *
     * @param obj
     * @param message
     * @param <T>
     * @return
     */
    public static <T> T requireNonNull(T obj, String message) {
        return Objects.requireNonNull(obj, message);
    }

    /**
     * 检查对象是否为null，如果为null则抛出空指针异常，可指定异常message
     *
     * @param obj
     * @param messageSupplier
     * @param <T>
     * @return
     */
    public static <T> T requireNonNull(T obj, Supplier<String> messageSupplier) {
        return Objects.requireNonNull(obj, messageSupplier);
    }

    /**
     * 判断对象是否为null
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 判断对象是否不为null
     *
     * @param obj
     * @return
     */
    public static boolean nonNull(Object obj) {
        return obj != null;
    }

}
