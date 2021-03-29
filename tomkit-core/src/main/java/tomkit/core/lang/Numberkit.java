package tomkit.core.lang;

import java.util.function.BiPredicate;

/**
 * 数字工具类
 *
 * @author yh
 * @since 2021/3/23
 */
public final class Numberkit {

    private Numberkit() {
    }

    /**
     * 根据指定规则比较a和b，a和b都不为{@code null}
     *
     * @param a         第一个数字
     * @param b         第二个数字
     * @param predicate 比较规则
     * @return 比较结果
     */
    public static boolean compare(Number a, Number b, BiPredicate<Number, Number> predicate) {
        if (null == a || null == b) {
            return false;
        }
        return predicate.test(a, b);
    }

}
