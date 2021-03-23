package tomkit.core.lang;

import java.util.function.BiPredicate;

/**
 * 整型工具类
 *
 * @author yh
 * @since 2021/3/23
 */
public class Integers {
    /**
     * a是否等于b
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean eq(int a, int b) {
        return a == b;
    }

    /**
     * a是否小于b
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean lt(int a, int b) {
        return a < b;
    }

    /**
     * a是否大于b
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean gt(int a, int b) {
        return a > b;
    }

    /**
     * a是否小于等于b
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean lte(int a, int b) {
        return a <= b;
    }

    /**
     * a是否大于等于b
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean gte(int a, int b) {
        return a >= b;
    }

    /**
     * a是否等于b，a和b都不为{@code null}
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean eq(Integer a, Integer b) {
        return compare(a, b, Integer::equals);
    }

    /**
     * a是否小于b，a和b都不为{@code null}
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean lt(Integer a, Integer b) {
        return compare(a, b, (x, y) -> x < y);
    }

    /**
     * a是否大于b，a和b都不为{@code null}
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean gt(Integer a, Integer b) {
        return compare(a, b, (x, y) -> x > y);
    }

    /**
     * a是否小于等于b，a和b都不为{@code null}
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean lte(Integer a, Integer b) {
        return compare(a, b, (x, y) -> x <= y);
    }

    /**
     * a是否大于等于b，a和b都不为{@code null}
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean gte(Integer a, Integer b) {
        return compare(a, b, (x, y) -> x >= y);
    }

    /**
     * 根据指定规则比较a和b，a和b都不为{@code null}
     *
     * @param a
     * @param b
     * @param predicate
     * @return
     */
    public static boolean compare(Integer a, Integer b, BiPredicate<Integer, Integer> predicate) {
        if (null == a || null == b) {
            return false;
        }
        return predicate.test(a, b);
    }

    public static String toString(Integer value) {
        if (null == value) {
            return "null";
        }
        return Integer.toString(value);
    }

}
