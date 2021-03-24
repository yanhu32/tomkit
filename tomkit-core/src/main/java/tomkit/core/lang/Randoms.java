package tomkit.core.lang;

import tomkit.core.function.CharSupplier;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author yh
 * @since 2021/3/23
 */
public final class Randoms {

    static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'};

    private Randoms() {
    }

    public static int nextInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int nextInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    public static int nextInt(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }

    public static long nextLong() {
        return ThreadLocalRandom.current().nextLong();
    }

    public static long nextLong(long bound) {
        return ThreadLocalRandom.current().nextLong(bound);
    }

    public static long nextLong(long origin, long bound) {
        return ThreadLocalRandom.current().nextLong(origin, bound);
    }

    public static double nextDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    public static double nextDouble(double bound) {
        return ThreadLocalRandom.current().nextDouble(bound);
    }

    public static double nextDouble(double origin, double bound) {
        return ThreadLocalRandom.current().nextDouble(origin, bound);
    }

    /**
     * 获取随机数字字符
     *
     * @return
     */
    public static char nextDigit() {
        return CHARS[nextInt(10)];
    }

    /**
     * 获取随机字母字符
     *
     * @return
     */
    public static char nextLetter() {
        return CHARS[nextInt(10, CHARS.length)];
    }

    /**
     * 获取随机数字和字母字符
     *
     * @return
     */
    public static char nextChar() {
        return CHARS[nextInt(CHARS.length)];
    }

    /**
     * 获取随机基本汉字字符
     *
     * @return
     */
    public static char nextHanzi() {
        return (char) nextInt(0x4E00, 0x9FA5 + 1);
    }

    /**
     * 获取指定长度的随机数字字符串
     *
     * @param len 随机串长度，必须大于0
     * @return
     */
    public static String randomDigit(int len) {
        return randomStr(len, Randoms::nextDigit);
    }

    /**
     * 获取指定长度的随机字母字符串
     *
     * @param len 随机串长度，必须大于0
     * @return
     */
    public static String randomLetter(int len) {
        return randomStr(len, Randoms::nextLetter);
    }

    /**
     * 获取指定长度的随机数字和字母字符串
     *
     * @param len 随机串长度，必须大于0
     * @return
     */
    public static String randomStr(int len) {
        return randomStr(len, Randoms::nextChar);
    }

    /**
     * 获取指定长度的随机基本汉字字符串
     *
     * @param len
     * @return
     */
    public static String randomHanzi(int len) {
        return randomStr(len, Randoms::nextHanzi);
    }

    /**
     * 根据给定字符生成规则生成指定长度随机字符串
     *
     * @param len
     * @param supplier
     * @return
     */
    public static String randomStr(int len, CharSupplier supplier) {
        Assert.state(len > 0, "len必须大于0");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(supplier.get());
        }
        return builder.toString();
    }

}
