package tomkit.core.lang;

import tomkit.core.function.CharSupplier;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机生成器工具类
 *
 * @author yh
 * @since 2021/3/23
 */
public final class Randomkit {

    static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'};

    private Randomkit() {
    }

    /**
     * 获取随机整型
     *
     * @return 随机整型
     */
    public static int nextInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    /**
     * 获取随机整型
     *
     * @param bound 最大上限（不包含）
     * @return 随机整型
     */
    public static int nextInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    /**
     * 获取随机整型
     *
     * @param origin 下限（包含）
     * @param bound  上限（不包含）
     * @return 随机整型
     */
    public static int nextInt(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }

    /**
     * 获取随机长整型
     *
     * @return 随机长整型
     */
    public static long nextLong() {
        return ThreadLocalRandom.current().nextLong();
    }

    /**
     * 获取随机长整型
     *
     * @param bound 上限（不包含）
     * @return 随机长整型
     */
    public static long nextLong(long bound) {
        return ThreadLocalRandom.current().nextLong(bound);
    }

    /**
     * 获取随机长整型
     *
     * @param origin 下限（包含）
     * @param bound  上限（不包含）
     * @return 随机长整型
     */
    public static long nextLong(long origin, long bound) {
        return ThreadLocalRandom.current().nextLong(origin, bound);
    }

    /**
     * 获取随机浮点数
     *
     * @return 随机浮点数
     */
    public static double nextDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    /**
     * 获取随机浮点数
     *
     * @param bound 上限（不包含）
     * @return 随机浮点数
     */
    public static double nextDouble(double bound) {
        return ThreadLocalRandom.current().nextDouble(bound);
    }

    /**
     * 获取随机浮点数
     *
     * @param origin 下限（包含）
     * @param bound  上限（不包含）
     * @return 随机浮点数
     */
    public static double nextDouble(double origin, double bound) {
        return ThreadLocalRandom.current().nextDouble(origin, bound);
    }

    /**
     * 获取随机数字字符
     *
     * @return 随机数字
     */
    public static char nextDigit() {
        return CHARS[nextInt(10)];
    }

    /**
     * 获取随机字母字符
     *
     * @return 随机字母
     */
    public static char nextLetter() {
        return CHARS[nextInt(10, CHARS.length)];
    }

    /**
     * 获取随机数字和字母字符
     *
     * @return 随机字符
     */
    public static char nextChar() {
        return CHARS[nextInt(CHARS.length)];
    }

    /**
     * 获取随机基本汉字字符
     *
     * @return 随机基本汉字
     */
    public static char nextHanzi() {
        return (char) nextInt(0x4E00, 0x9FA5 + 1);
    }

    /**
     * 获取指定长度的随机数字字符串
     *
     * @param len 随机串长度，必须大于0
     * @return 随机数字串
     */
    public static String randomDigit(int len) {
        return randomStr(len, Randomkit::nextDigit);
    }

    /**
     * 获取指定长度的随机字母字符串
     *
     * @param len 随机串长度，必须大于0
     * @return 随机字母串
     */
    public static String randomLetter(int len) {
        return randomStr(len, Randomkit::nextLetter);
    }

    /**
     * 获取指定长度的随机数字和字母字符串
     *
     * @param len 随机串长度，必须大于0
     * @return 随机字符串
     */
    public static String randomStr(int len) {
        return randomStr(len, Randomkit::nextChar);
    }

    /**
     * 获取指定长度的随机基本汉字字符串
     *
     * @param len 随机串长度，必须大于0
     * @return 随机汉字串
     */
    public static String randomHanzi(int len) {
        return randomStr(len, Randomkit::nextHanzi);
    }

    /**
     * 根据给定字符生成规则生成指定长度随机字符串
     *
     * @param len      随机串长度
     * @param supplier 字符生成器
     * @return 随机串
     */
    public static String randomStr(int len, CharSupplier supplier) {
        Assert.isTrue(len > 0, "len必须大于0");

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(supplier.get());
        }
        return builder.toString();
    }

}
