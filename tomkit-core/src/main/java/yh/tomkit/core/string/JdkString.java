package yh.tomkit.core.string;

import java.util.Locale;

/**
 * jdk string
 *
 * @author yh
 * @since 2021/1/29
 */
abstract class JdkString implements StringInterface {

    /**
     * 根据delimiter作为分隔符将elements所有字符序列拼接在一起
     *
     * <pre>{@code
     *     String message = String.join("-", "Java", "is", "cool");
     *     // message returned is: "Java-is-cool"
     * }</pre>
     *
     * @param delimiter 分隔每个元素的分隔符
     * @param elements  需要拼接的字符序列集合
     * @return 拼接后的字符串
     * @see String#join(CharSequence, CharSequence...)
     * @see java.util.StringJoiner
     */
    public static String join(CharSequence delimiter, CharSequence... elements) {
        return String.join(delimiter, elements);
    }

    /**
     * 根据delimiter作为分隔符将elements所有字符序列拼接在一起
     *
     * <pre>{@code
     *     List<String> strings = new LinkedList<>();
     *     strings.add("Java");strings.add("is");
     *     strings.add("cool");
     *     String message = String.join(" ", strings);
     *     //message returned is: "Java is cool"
     *
     *     Set<String> strings = new LinkedHashSet<>();
     *     strings.add("Java"); strings.add("is");
     *     strings.add("very"); strings.add("cool");
     *     String message = String.join("-", strings);
     *     //message returned is: "Java-is-very-cool"
     * }</pre>
     *
     * @param delimiter 分隔每个元素的分隔符
     * @param elements  需要拼接的字符序列集合
     * @return 拼接后的字符串
     * @see String#join(CharSequence, CharSequence...)
     * @see java.util.StringJoiner
     */
    public static String join(CharSequence delimiter, Iterable<? extends CharSequence> elements) {
        return String.join(delimiter, elements);
    }

    /**
     * 格式化字符串
     *
     * @param format 格式
     * @param args   占位符参数
     * @return 格式化后字符串
     */
    public static String format(String format, Object... args) {
        return String.format(format, args);
    }

    /**
     * 格式化字符串
     *
     * @param l      地区
     * @param format 格式
     * @param args   占位符参数
     * @return 格式化后字符串
     */
    public static String format(Locale l, String format, Object... args) {
        return String.format(l, format, args);
    }

    /**
     * 返回字符串值
     *
     * @param obj 任意对象
     * @return 字符串值
     */
    public static String valueOf(Object obj) {
        return String.valueOf(obj);
    }

    /**
     * 返回字符串值
     *
     * @param data 字符数组
     * @return 字符串值
     */
    public static String valueOf(char[] data) {
        return String.valueOf(data);
    }

    /**
     * 返回字符串值
     *
     * @param data   字符数组
     * @param offset 偏移量
     * @param count  长度
     * @return 字符串值
     */
    public static String valueOf(char[] data, int offset, int count) {
        return String.valueOf(data, offset, count);
    }

    /**
     * 拷贝字符串值
     *
     * @param data   字符数组
     * @param offset 偏移量
     * @param count  长度
     * @return 字符串值
     */
    public static String copyValueOf(char[] data, int offset, int count) {
        return String.copyValueOf(data, offset, count);
    }

    /**
     * 拷贝字符串值
     *
     * @param data 字符数组
     * @return 字符串值
     */
    public static String copyValueOf(char[] data) {
        return String.copyValueOf(data);
    }

    /**
     * 返回字符串值
     *
     * @param b 布尔
     * @return 字符串值
     */
    public static String valueOf(boolean b) {
        return b ? "true" : "false";
    }

    /**
     * 返回字符串值
     *
     * @param c 字符
     * @return 字符串值
     */
    public static String valueOf(char c) {
        return String.valueOf(c);
    }

    /**
     * 返回字符串值
     *
     * @param i 整型
     * @return 字符串值
     */
    public static String valueOf(int i) {
        return String.valueOf(i);
    }

    /**
     * 返回字符串值
     *
     * @param l 长整型
     * @return 字符串值
     */
    public static String valueOf(long l) {
        return String.valueOf(l);
    }

    /**
     * 返回字符串值
     *
     * @param f 浮点数
     * @return 字符串值
     */
    public static String valueOf(float f) {
        return String.valueOf(f);
    }

    /**
     * 返回字符串值
     *
     * @param d 双精度浮点数
     * @return 字符串值
     */
    public static String valueOf(double d) {
        return String.valueOf(d);
    }

}
