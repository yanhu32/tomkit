package tomkit.core.lang;

import tomkit.core.function.CharSupplier;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 字符串工具类
 *
 * @author yh
 * @since 2021/1/29
 */
public final class Strings {

    /**
     * 空字符串
     */
    private static final String EMPTY = "";

    /**
     * 下划线
     */
    private static final char UNDERLINE = '_';

    /**
     * 双引号
     */
    private static final char DOUBLE_QUOTE = '\"';

    /**
     * 单引号（英文）
     */
    private static final char QUOTE = '\'';

    /**
     * 空格字符
     */
    private static final char SPACE = ' ';

    /**
     * 空格
     */
    private static final String SPACE_STR = " ";

    /**
     * 省略号
     */
    private static final String ELLIPSIS = "...";

    /**
     * 表示搜索失败的索引
     */
    private static final int INDEX_NOT_FOUND = -1;

    /**
     * 填充常数可以扩展到的最大大小
     */
    private static final int PAD_LIMIT = 8192;

    private Strings() {
        throw new AssertionError("Strings cannot be instantiated!");
    }

    /**
     * 字符序列是否为 {@code null}
     *
     * <pre class="code">
     *     Strings.isNull(null) = true
     *     Strings.isNull("")   = false
     * </pre>
     *
     * @param source 字符序列
     * @return {@code true} source为 {@code null}, {@code false} source不为 {@code null}
     */
    public static boolean isNull(final CharSequence source) {
        return source == null;
    }

    /**
     * 判断传入的所有字符序列是否都为 {@code null}
     *
     * <pre class="code">
     *     Strings.isAllNull(null, null) = true
     *     Strings.isAllNull(null, "")   = false
     * </pre>
     *
     * @param sources 字符序列列表
     * @return 是否所有字符序列都为 {@code null}
     */
    public static boolean isAllNull(final CharSequence... sources) {
        for (CharSequence source : sources) {
            if (null != source) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符序列是否非 {@code null}
     *
     * <pre class="code">
     *     Strings.isNotNull(null) = false
     *     Strings.isNotNull("")   = true
     * </pre>
     *
     * @param source 字符序列
     * @return 是否非 {@code null}
     */
    public static boolean isNotNull(final CharSequence source) {
        return source != null;
    }

    /**
     * 判断传入的字符序列是否都非 {@code null}
     *
     * <pre class="code">
     *     Strings.isAllNotNull(null, null) = false
     *     Strings.isAllNotNull(null, "")   = false
     *     Strings.isAllNotNull("", "")     = true
     * </pre>
     *
     * @param sources 字符序列列表
     * @return 字符序列是否都非 {@code null}
     */
    public static boolean isAllNotNull(final CharSequence... sources) {
        for (CharSequence source : sources) {
            if (null == source) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断传入的字符序列里是否含有 {@code null}
     *
     * <pre class="code">
     *     Strings.hasNull(null, null) = true
     *     Strings.hasNull(null, "")   = true
     *     Strings.hasNull("", "")     = false
     * </pre>
     *
     * @param sources 字符序列列表
     * @return 字符序列里是否含有 {@code null}
     */
    public static boolean hasNull(final CharSequence... sources) {
        for (CharSequence source : sources) {
            if (null == source) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断传入的字符序列里是否含非 {@code null}
     *
     * <pre class="code">
     *     Strings.hasNotNull(null, null) = false
     *     Strings.hasNotNull(null, "")   = true
     *     Strings.hasNotNull("", "")     = true
     * </pre>
     *
     * @param sources 字符序列列表
     * @return 字符序列里是否含非 {@code null}
     */
    public static boolean hasNotNull(final CharSequence... sources) {
        for (CharSequence source : sources) {
            if (null != source) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符序列是否为 {@code null} 或 {@code ""}
     *
     * <pre class="code">
     *     Strings.isEmpty(null) = true
     *     Strings.isEmpty("")   = true
     *     Strings.isEmpty("1")  = false
     * </pre>
     *
     * @param source 字符序列
     * @return 是否为 {@code null} 或 {@code ""}
     */
    public static boolean isEmpty(final CharSequence source) {
        return (null == source || source.length() == 0);
    }

    /**
     * 判断传入字符序列是否都为 {@code null} 或 {@code ""}
     *
     * <pre class="code">
     *     Strings.isAllEmpty(null, null) = true
     *     Strings.isAllEmpty(null, "")   = true
     *     Strings.isAllEmpty("", "1")    = false
     * </pre>
     *
     * @param sources 字符序列列表
     * @return 字符序列是否都为 {@code null} 或 {@code ""}
     * @see #isNotEmpty(CharSequence)
     */
    public static boolean isAllEmpty(final CharSequence... sources) {
        for (CharSequence source : sources) {
            if (isNotEmpty(source)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符序列是否不为 {@code null} 或 {@code ""}
     *
     * <pre class="code">
     *     Strings.isNotEmpty(null) = false
     *     Strings.isNotEmpty("")   = false
     *     Strings.isNotEmpty("1")  = true
     * </pre>
     *
     * @param source 字符序列
     * @return 是否不为 {@code null} 或 {@code ""}
     * @see #isEmpty(CharSequence)
     */
    public static boolean isNotEmpty(final CharSequence source) {
        return (null != source && source.length() > 0);
    }

    /**
     * 判断传入字符序列是否都不为 {@code null} 或 {@code ""}
     *
     * <pre class="code">
     *     Strings.isAllNotEmpty(null, null) = false
     *     Strings.isAllNotEmpty(null, "")   = false
     *     Strings.isAllNotEmpty("", "1")    = false
     *     Strings.isAllNotEmpty("1", "2")   = true
     * </pre>
     *
     * @param sources 字符序列列表
     * @return 是否都不为 {@code null} 或 {@code ""}
     */
    public static boolean isAllNotEmpty(final CharSequence... sources) {
        for (CharSequence source : sources) {
            if (isEmpty(source)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断传入字符序列是否存在 {@code null} 或 {@code ""}
     *
     * <pre class="code">
     *     Strings.hasEmpty(null, null) = true
     *     Strings.hasEmpty(null, "")   = true
     *     Strings.hasEmpty("", "1")    = true
     *     Strings.hasEmpty("1", "2")   = false
     * </pre>
     *
     * @param sources 字符序列列表
     * @return 是否存在 {@code null} 或 {@code ""}
     */
    public static boolean hasEmpty(final CharSequence... sources) {
        for (CharSequence source : sources) {
            if (isEmpty(source)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断传入字符序列是否存在不为 {@code null} 或 {@code ""}
     *
     * <pre class="code">
     *     Strings.hasNotEmpty(null, null) = false
     *     Strings.hasNotEmpty(null, "")   = false
     *     Strings.hasNotEmpty("", "1")    = true
     * </pre>
     *
     * @param sources 字符序列列表
     * @return 是否存在不为 {@code null} 或 {@code ""}
     */
    public static boolean hasNotEmpty(final CharSequence... sources) {
        for (CharSequence source : sources) {
            if (!isEmpty(source)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符序列是否为 {@code null}, {@code ""} 或空格 ({@code SPACE_SEPARATOR},
     * {@code LINE_SEPARATOR}, {@code PARAGRAPH_SEPARATOR})
     *
     * <pre class="code">
     *     Strings.isSpace(null)   = true
     *     Strings.isSpace("")     = true
     *     Strings.isSpace("  ")   = true
     *     Strings.isSpace("\n\t") = false
     *     Strings.isSpace("a")    = false
     * </pre>
     *
     * @param sequence 字符序列
     * @return 是否为 {@code null}, {@code ""} 或空格
     */
    public static boolean isSpace(final CharSequence sequence) {
        if (isEmpty(sequence)) {
            return true;
        }

        int length = sequence.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isSpaceChar(sequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符序列是否不为 {@code null}, {@code ""} 或空格 ({@code SPACE_SEPARATOR},
     * {@code LINE_SEPARATOR}, {@code PARAGRAPH_SEPARATOR})
     *
     * <pre class="code">
     *     Strings.isNotSpace(null)   = false
     *     Strings.isNotSpace("")     = false
     *     Strings.isNotSpace("  ")   = false
     *     Strings.isNotSpace("\n\t") = true
     *     Strings.isNotSpace("a")    = true
     * </pre>
     *
     * @param sequence 字符序列
     * @return 是否不为 {@code null}, {@code ""} 或空格
     * @see #isSpace(CharSequence)
     */
    public static boolean isNotSpace(final CharSequence sequence) {
        return !isSpace(sequence);
    }

    /**
     * 判断字符序列是否为 {@code null}, {@code ""} 或空白字符
     * <p>
     * 空白字符:
     * <ul>
     * <li> Unicode空白字符 ({@code SPACE_SEPARATOR},{@code LINE_SEPARATOR},
     *      or {@code PARAGRAPH_SEPARATOR})
     *      但不是一个不间断的空格 ({@code '\u00A0'}, {@code '\u2007'}, {@code '\u202F'}).
     * <li> {@code '\t'}, U+0009 HORIZONTAL TABULATION.
     * <li> {@code '\n'}, U+000A LINE FEED.
     * <li> {@code '\u000B'}, U+000B VERTICAL TABULATION.
     * <li> {@code '\f'}, U+000C FORM FEED.
     * <li> {@code '\r'}, U+000D CARRIAGE RETURN.
     * <li> {@code '\u001C'}, U+001C FILE SEPARATOR.
     * <li> {@code '\u001D'}, U+001D GROUP SEPARATOR.
     * <li> {@code '\u001E'}, U+001E RECORD SEPARATOR.
     * <li> {@code '\u001F'}, U+001F UNIT SEPARATOR.
     * </ul>
     *
     * <pre class="code">
     *     Strings.isBlank(null)    = true
     *     Strings.isBlank("")      = true
     *     Strings.isBlank(" ")     = true
     *     Strings.isBlank(" \t\n") = true
     *     Strings.isBlank("1")     = false
     * </pre>
     *
     * @param source 字符序列
     * @return 是否为 {@code null}, {@code ""} 或空白字符
     * @see #hasText(CharSequence)
     * @see Character#isWhitespace(char)
     */
    public static boolean isBlank(final CharSequence source) {
        return !hasText(source);
    }

    /**
     * 判断字符序列是否不为 {@code null}, {@code ""} 或空白字符
     * <p>
     * 空白字符:
     * <ul>
     * <li> Unicode空白字符 ({@code SPACE_SEPARATOR},{@code LINE_SEPARATOR},
     *      or {@code PARAGRAPH_SEPARATOR})
     *      但不是一个不间断的空格 ({@code '\u00A0'}, {@code '\u2007'}, {@code '\u202F'}).
     * <li> {@code '\t'}, U+0009 HORIZONTAL TABULATION.
     * <li> {@code '\n'}, U+000A LINE FEED.
     * <li> {@code '\u000B'}, U+000B VERTICAL TABULATION.
     * <li> {@code '\f'}, U+000C FORM FEED.
     * <li> {@code '\r'}, U+000D CARRIAGE RETURN.
     * <li> {@code '\u001C'}, U+001C FILE SEPARATOR.
     * <li> {@code '\u001D'}, U+001D GROUP SEPARATOR.
     * <li> {@code '\u001E'}, U+001E RECORD SEPARATOR.
     * <li> {@code '\u001F'}, U+001F UNIT SEPARATOR.
     * </ul>
     *
     * <pre class="code">
     *     Strings.isNotBlank(null)    = false
     *     Strings.isNotBlank("")      = false
     *     Strings.isNotBlank(" ")     = false
     *     Strings.isNotBlank(" \t\n") = false
     *     Strings.isNotBlank("1")     = true
     * </pre>
     *
     * @param source 字符序列
     * @return 是否为非空白字符
     * @see #hasText(CharSequence)
     * @see #isBlank(CharSequence)
     * @see Character#isWhitespace(char)
     */
    public static boolean isNotBlank(final CharSequence source) {
        return hasText(source);
    }

    /**
     * 判断字符序列是否存在非空白字符
     *
     * <p>source为 {@code null} 或 {@code ""} 时返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.hasText(null)    = false
     *     Strings.hasText("")      = false
     *     Strings.hasText(" ")     = false
     *     Strings.hasText(" \t\n") = false
     *     Strings.hasText("1")     = true
     * </pre>
     *
     * @param source 字符序列
     * @return 是否存在非空白字符
     * @see Character#isWhitespace
     */
    public static boolean hasText(final CharSequence source) {
        if (isEmpty(source)) {
            return false;
        }

        int length = source.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(source.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符序列是否含有空白字符
     *
     * <p>source为 {@code null} 或 {@code ""} 时返回 {@code false}</p>
     * <p>
     * 空白字符:
     * <ul>
     * <li> Unicode空白字符 ({@code SPACE_SEPARATOR},{@code LINE_SEPARATOR},
     *      or {@code PARAGRAPH_SEPARATOR})
     *      但不是一个不间断的空格 ({@code '\u00A0'}, {@code '\u2007'}, {@code '\u202F'}).
     * <li> {@code '\t'}, U+0009 HORIZONTAL TABULATION.
     * <li> {@code '\n'}, U+000A LINE FEED.
     * <li> {@code '\u000B'}, U+000B VERTICAL TABULATION.
     * <li> {@code '\f'}, U+000C FORM FEED.
     * <li> {@code '\r'}, U+000D CARRIAGE RETURN.
     * <li> {@code '\u001C'}, U+001C FILE SEPARATOR.
     * <li> {@code '\u001D'}, U+001D GROUP SEPARATOR.
     * <li> {@code '\u001E'}, U+001E RECORD SEPARATOR.
     * <li> {@code '\u001F'}, U+001F UNIT SEPARATOR.
     * </ul>
     *
     * <pre class="code">
     *     Strings.hasWhitespace(null)    = false
     *     Strings.hasWhitespace("")      = false
     *     Strings.hasWhitespace(" ")     = true
     *     Strings.hasWhitespace(" \t\n") = true
     *     Strings.hasWhitespace("1")     = false
     * </pre>
     *
     * @param source a string
     * @return 是否含有空白字符
     * @see Character#isWhitespace(char)
     */
    public static boolean hasWhitespace(final CharSequence source) {
        if (isEmpty(source)) {
            return false;
        }

        int length = source.length();
        for (int i = 0; i < length; i++) {
            if (Character.isWhitespace(source.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符序列长度是否大于
     *
     * <p>source为 {@code null} 或 {@code ""} 时返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.hasLength(null) = false
     *     Strings.hasLength("")   = false
     *     Strings.hasLength(" ")  = true
     * </pre>
     *
     * @param source 字符序列
     * @return 长度是否大于0
     */
    public static boolean hasLength(final CharSequence source) {
        return (source != null && source.length() > 0);
    }

    private static final ReentrantLock UUID_LOCK = new ReentrantLock();

    /**
     * 生成无连接符uuid
     *
     * <p>生成的uuid字符串会去除'-'</p>
     *
     * <pre class="code">
     *     Strings.uuid() = 99c68bcb50b849c99bbe41628e6aec32
     * </pre>
     *
     * @return 无连接符uuid
     * @see UUID#randomUUID()
     * @see UUID#toString()
     * @see String#replace(CharSequence, CharSequence)
     */
    public static String uuid() {
        UUID_LOCK.lock();
        try {
            return UUID.randomUUID().toString().replace("-", "");
        } finally {
            UUID_LOCK.unlock();
        }
    }

    /**
     * 生成带连接符uuid
     *
     * <pre class="code">
     *     Strings.uuidL() = 134bed0f-0275-4452-a9a2-710981fea61e
     * </pre>
     *
     * @return 带连接符uuid
     * @see UUID#randomUUID()
     * @see UUID#toString()
     */
    public static String uuidL() {
        UUID_LOCK.lock();
        try {
            return UUID.randomUUID().toString();
        } finally {
            UUID_LOCK.unlock();
        }
    }

    /**
     * 生成随机字符串
     *
     * @param len      字符串长度
     * @param supplier 字符生成器
     * @return 随机字符串
     * @see CharSupplier#get()
     */
    public static String randomStr(final int len, final CharSupplier supplier) {
        Assert.state(len > 0, "len has to be greater than 0");
        Assert.notNull(supplier, "supplier cannot be null");

        StringBuilder builder = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            builder.append(supplier.get());
        }
        return builder.toString();
    }

    /**
     * 随机生成可见字符的字符串
     *
     * @param len 随机串长度
     * @return 随机串
     * @see CharSupplier#get()
     * @see #randomStr(int, CharSupplier)
     */
    public static String randomStr(final int len) {
        Assert.state(len > 0, "len has to be greater than 0");

        ThreadLocalRandom random = ThreadLocalRandom.current();
        return randomStr(len, () -> (char) (32 + random.nextInt(95)));
    }

    /**
     * 随机生成包含字母的字符串
     *
     * @param len 随机串长度
     * @return 随机串
     * @see CharSupplier#get()
     * @see #randomStr(int, CharSupplier)
     */
    public static String randomLetter(final int len) {
        Assert.state(len > 0, "len has to be greater than 0");

        ThreadLocalRandom random = ThreadLocalRandom.current();
        CharSupplier supplier = () -> (char) ((random.nextBoolean() ? 65 : 97)
                + random.nextInt(26));
        return randomStr(len, supplier);
    }

    /**
     * 随机生成包含数字的字符串
     *
     * @param len 随机串长度
     * @return 随机串
     * @see CharSupplier#get()
     * @see #randomStr(int, CharSupplier)
     */
    public static String randomDigit(final int len) {
        Assert.state(len > 0, "len has to be greater than 0");

        ThreadLocalRandom random = ThreadLocalRandom.current();
        return randomStr(len, () -> (char) (48 + random.nextInt(10)));
    }

    /**
     * 随机生成包含字母和数字的字符串
     *
     * @param len 随机串长度
     * @return 随机串
     * @see CharSupplier#get()
     * @see #randomStr(int, CharSupplier)
     */
    public static String randomLetterDigit(final int len) {
        Assert.state(len > 0, "len has to be greater than 0");

        ThreadLocalRandom random = ThreadLocalRandom.current();
        CharSupplier supplier = () -> {
            switch (random.nextInt(3)) {
                case 0:
                    return (char) (65 + random.nextInt(26));
                case 1:
                    return (char) (97 + random.nextInt(26));
                default:
                    return (char) (48 + random.nextInt(10));
            }
        };
        return randomStr(len, supplier);
    }

    /**
     * 返回双引号的字符串
     *
     * <pre class="code">
     *     Strings.doubleQuote(null) = "null"
     *     Strings.doubleQuote("")   = ""
     *     Strings.doubleQuote("1")  = "1"
     * </pre>
     *
     * @param source a string
     * @return "source"
     */
    public static String doubleQuote(final String source) {
        return DOUBLE_QUOTE + source + DOUBLE_QUOTE;
    }

    /**
     * 返回单引号的字符串
     *
     * <pre class="code">
     *     Strings.quote(null) = 'null'
     *     Strings.quote("")   = ''
     *     Strings.quote("1")  = '1'
     * </pre>
     *
     * @param source a string
     * @return 'source'
     */
    public static String quote(final String source) {
        return QUOTE + source + QUOTE;
    }

    /**
     * 两侧添加指定字符
     *
     * <pre class="code">
     *     Strings.twoFlanks("a", "-") = -a-
     * </pre>
     *
     * @param source a string
     * @param tag    tag
     * @return tag + source + tag
     */
    public static String twoFlanks(final String source, final String tag) {
        return tag + source + tag;
    }

    /**
     * 判断是否符合给定谓语表达式
     *
     * <p>source或predicate为 {@code null} 时返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.test("a", String::isEmpty) = false
     * </pre>
     *
     * @param source    a string
     * @param predicate 一个参数的谓词
     * @return boolean value
     */
    public static boolean test(final String source, final Predicate<String> predicate) {
        Assert.notNull(predicate, "predicate cannot be null");
        return predicate.test(source);
    }

    /**
     * 字符串转换为其他对象
     * 当source不为 {@code null} 时调用 {@link Function#apply(Object)}
     *
     * <pre class="code">
     *     Strings.map(null, Integer::valueOf) = Optional.empty
     *     Strings.map("11", Integer::valueOf) = Optional[2]
     * </pre>
     *
     * @param source   a string
     * @param function 转换接口
     * @param <T>      任意类型
     * @return 指定类型
     * @see Function#apply(Object)
     */
    public static <T> Optional<T> map(final String source, final Function<String, T> function) {
        Assert.notNull(function, "function cannot be null");
        return Optional.ofNullable(null != source ? function.apply(source) : null);
    }

    /**
     * 字符串转换为数字 {@link Number} 子类
     * 当source不为 {@code null} 时调用 {@link Function#apply(Object)}
     *
     * <pre class="code">
     *     Strings.toNumber(null, Integer::new) = Optional.empty
     *     Strings.toNumber("1", Integer::new)  = Optional[1]
     * </pre>
     *
     * @param source   a string
     * @param function 转换接口
     * @param <N>      {@link Number} 子类
     * @return 数字
     * @see Number
     * @see Function#apply(Object)
     */
    public static <N extends Number> Optional<N> toNumber(final String source, final Function<String, N> function) {
        Assert.notNull(function, "function cannot be null");
        return Optional.ofNullable(null != source ? function.apply(source) : null);
    }

    /**
     * 判断目标字符串是否包含指定字符序列
     *
     * <p>source或x为 {@code null} 或 {@code ""} 时返回 {@code false}
     * source的长度需大于等于x的长度</p>
     *
     * <pre class="code">
     *     Strings.contains(null, null)  = false
     *     Strings.contains(null, "1")   = false
     *     Strings.contains("1", "1111") = false
     *     Strings.contains("1111", "2") = false
     *     Strings.contains("1111", "1") = true
     * </pre>
     *
     * @param source a string
     * @param x      指定字符序列
     * @return 是否包含指定字符序列
     * @see String#contains(CharSequence)
     */
    public static boolean contains(final String source, final CharSequence x) {
        return (lengthGte(source, x) && source.contains(x));
    }

    /**
     * 是否含有指定字符
     *
     * <p>source为 {@code null} 或 {@code ""} 时返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.contains(null, '1')   = false
     *     Strings.contains("1111", '2') = false
     *     Strings.contains("1", '1')    = true
     *     Strings.contains("1111", '1') = true
     * </pre>
     *
     * @param source a string
     * @param c      a char
     * @return 是否包含指定字符
     */
    public static boolean contains(final String source, final char c) {
        if (isEmpty(source)) {
            return false;
        }

        int length = source.length();
        for (int i = 0; i < length; i++) {
            if (source.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否匹配正则表达式
     *
     * <p>source或regex为 {@code null} 或 {@code ""} 时返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.matches("abc", "^[a-z]+$") = true
     *     Strings.matches("abc123", "\\d+")  = false
     * </pre>
     *
     * @param source a string
     * @param regex  正则表达式
     * @return 是否匹配
     * @see String#matches(String)
     */
    public static boolean matches(final String source, final String regex) {
        return (isAllNotEmpty(source, regex) && source.matches(regex));
    }

    /**
     * 以...开始
     *
     * <p>source或prefix为 {@code null} 或 {@code ""} 时返回 {@code false}
     * source的长度需大于等于prefix的长度</p>
     *
     * <pre class="code">
     *     Strings.startsWith(null, "a")  = false
     *     Strings.startsWith("abc", "b") = false
     *     Strings.startsWith("abc", "a") = true
     * </pre>
     *
     * @param source a string
     * @param prefix 前缀
     * @return boolean value
     */
    public static boolean startsWith(final String source, final String prefix) {
        return (lengthGte(source, prefix) && source.startsWith(prefix));
    }

    /**
     * 以...开始（不区分大小写）
     *
     * <p>source或prefix为 {@code null} 或 {@code ""} 时返回 {@code false}
     * source的长度需大于等于prefix的长度</p>
     *
     * <pre class="code">
     *     Strings.startsWithIgnoreCase("abc", "b") = false
     *     Strings.startsWithIgnoreCase("abc", "a") = true
     *     Strings.startsWithIgnoreCase("Abc", "a") = true
     * </pre>
     *
     * @param source a string
     * @param prefix 前缀
     * @return boolean value
     */
    public static boolean startsWithIgnoreCase(final String source, final String prefix) {
        return (lengthGte(source, prefix) && source.regionMatches(true, 0, prefix, 0, prefix.length()));
    }

    /**
     * 以...结尾
     *
     * <p>source或suffix为 {@code null} 或 {@code ""} 时返回 {@code false}
     * source的长度需大于等于suffix的长度</p>
     *
     * <pre class="code">
     *     Strings.endsWith(null, "a")  = false
     *     Strings.endsWith("abc", "b") = false
     *     Strings.endsWith("abc", "c") = true
     * </pre>
     *
     * @param source a string
     * @param suffix 后缀
     * @return boolean value
     */
    public static boolean endsWith(final String source, final String suffix) {
        return (lengthGte(source, suffix) && source.endsWith(suffix));
    }

    /**
     * 以...结束（不区分大小写）
     *
     * <p>source或suffix为 {@code null} 或 {@code ""} 时返回 {@code false},
     * source的长度需大于等于suffix的长度</p>
     *
     * <pre class="code">
     *     Strings.endsWithIgnoreCase("abc", "b") = false
     *     Strings.endsWithIgnoreCase("abc", "c") = true
     *     Strings.endsWithIgnoreCase("abC", "c") = true
     * </pre>
     *
     * @param source a string
     * @param suffix 后缀
     * @return boolean value
     */
    public static boolean endsWithIgnoreCase(final String source, final String suffix) {
        return (lengthGte(source, suffix) && source.regionMatches(true, source.length() - suffix.length(), suffix, 0, suffix.length()));
    }

    /**
     * 判断a是否等于b
     *
     * <p>a和b同时为 {@code null} 时返回 {@code true}</p>
     *
     * <pre class="code">
     *     Strings.equals(null, null) = true
     *     Strings.equals("ab", null) = false
     *     Strings.equals("ab", "a")  = false
     *     Strings.equals("ab", "ab") = true
     * </pre>
     *
     * @param a a string
     * @param b a string
     * @return a是否等于b
     * @see String#equals(Object)
     */
    public static boolean equals(final CharSequence a, final CharSequence b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }

    /**
     * 判断a是否等于b，不区分大小写
     *
     * <p>a和b同时为 {@code null} 时返回 {@code true}</p>
     *
     * <pre class="code">
     *     Strings.equals(null, null) = true
     *     Strings.equals("ab", null) = false
     *     Strings.equals("ab", "a")  = false
     *     Strings.equals("ab", "ab") = true
     *     Strings.equals("AB", "ab") = true
     * </pre>
     *
     * @param a a string
     * @param b a string
     * @return a是否等于b
     * @see String#equalsIgnoreCase(String)
     */
    public static boolean equalsIgnoreCase(final String a, final String b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.equalsIgnoreCase(b);
    }

    /**
     * 判断a是否不等于b
     *
     * <pre class="code">
     *     Strings.notEquals(null, null) = false
     *     Strings.notEquals("ab", null) = true
     *     Strings.notEquals("ab", "a")  = true
     *     Strings.notEquals("ab", "ab") = false
     * </pre>
     *
     * @param a a string
     * @param b a string
     * @return a是否不等于b
     * @see #equals(CharSequence, CharSequence)
     */
    public static boolean notEquals(final String a, final String b) {
        return !equals(a, b);
    }

    /**
     * 判断a是否大于b
     *
     * <p>a或b为 {@code null} 时返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.gt(null, null)  = false
     *     Strings.gt("ab", "a")   = true
     *     Strings.gt("ab", "abc") = false
     * </pre>
     *
     * @param a a string
     * @param b a string
     * @return 是否大于
     */
    public static boolean gt(final String a, final String b) {
        return (isAllNotNull(a, b) && a.compareTo(b) > 0);
    }

    /**
     * 判断a是否小于b
     *
     * <p>a或b为 {@code null} 时返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.gt(null, null)  = false
     *     Strings.gt("ab", "a")   = false
     *     Strings.gt("ab", "abc") = true
     * </pre>
     *
     * @param a a string
     * @param b a string
     * @return 是否小于
     */
    public static boolean lt(final String a, final String b) {
        return (isAllNotNull(a, b) && a.compareTo(b) < 0);
    }

    /**
     * 判断a的长度等于b的长度
     *
     * <p>a或b为 {@code null} 时返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.lengthEq("ab", null)  = false
     *     Strings.lengthEq("ab", "a")   = false
     *     Strings.lengthEq("ab", "ac")  = true
     *     Strings.lengthEq("ab", "ace") = false
     * </pre>
     *
     * @param a a string
     * @param b a string
     * @return 长度是否等于
     */
    public static boolean lengthEq(final CharSequence a, final CharSequence b) {
        return (null != a && null != b && a.length() == b.length());
    }

    /**
     * 判断a的长度大于b的长度
     *
     * <p>a或b为 {@code null} 时返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.lengthGt("ab", null)  = false
     *     Strings.lengthGt("ab", "a")   = true
     *     Strings.lengthGt("ab", "ac")  = false
     *     Strings.lengthGt("ab", "ace") = false
     * </pre>
     *
     * @param a a string
     * @param b a string
     * @return 长度是否大于
     */
    public static boolean lengthGt(final CharSequence a, final CharSequence b) {
        return (null != a && null != b && a.length() > b.length());
    }

    /**
     * 判断a的长度大于等于b的长度
     *
     * <p>a或b为 {@code null} 时返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.lengthGte("ab", null)  = false
     *     Strings.lengthGte("ab", "a")   = true
     *     Strings.lengthGte("ab", "ac")  = true
     *     Strings.lengthGte("ab", "ace") = false
     * </pre>
     *
     * @param a a string
     * @param b a string
     * @return 长度是否大于等于
     */
    public static boolean lengthGte(final CharSequence a, final CharSequence b) {
        return (null != a && null != b && a.length() >= b.length());
    }

    /**
     * 判断a的长度小于b的长度
     *
     * <p>a或b为 {@code null} 时返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.lengthLt("ab", null)  = false
     *     Strings.lengthLt("ab", "a")   = false
     *     Strings.lengthLt("ab", "ac")  = false
     *     Strings.lengthLt("ab", "ace") = true
     * </pre>
     *
     * @param a a string
     * @param b a string
     * @return 长度是否小于
     */
    public static boolean lengthLt(final CharSequence a, final CharSequence b) {
        return (null != a && null != b && a.length() < b.length());
    }

    /**
     * 判断a的长度小于等于b的长度
     *
     * <p>a或b为 {@code null} 时返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.lengthLte("ab", null)  = false
     *     Strings.lengthLte("ab", "a")   = false
     *     Strings.lengthLte("ab", "ac")  = true
     *     Strings.lengthLte("ab", "ace") = true
     * </pre>
     *
     * @param a a string
     * @param b a string
     * @return 长度是否大于等于
     */
    public static boolean lengthLte(final CharSequence a, final CharSequence b) {
        return (null != a && null != b && a.length() <= b.length());
    }

    /**
     * 根据delimiter作为分隔符将elements所有字符序列拼接在一起
     *
     * <pre>
     *     Strings.join(",", null, "b")    = "null,b"
     *     Strings.join(",", "1", "b")     = "1,b"
     *     Strings.join("", "1", "b")      = "1b"
     *     Strings.join(",", "1", "", "2") = "1,,2"
     * </pre>
     *
     * @param delimiter 分隔每个元素的分隔符
     * @param elements  需要拼接的字符序列集合
     * @return 拼接后的字符串
     * @see String#join(CharSequence, CharSequence...)
     * @see java.util.StringJoiner
     */
    public static String join(CharSequence delimiter, CharSequence... elements) {
        Assert.notNull(delimiter, "delimiter cannot be null");
        Assert.notNull(elements, "elements cannot be null");
        return String.join(delimiter, elements);
    }

    /**
     * 根据delimiter作为分隔符将elements所有字符序列拼接在一起
     *
     * @param delimiter 分隔每个元素的分隔符
     * @param elements  需要拼接的字符序列集合
     * @return 拼接后的字符串
     * @see String#join(CharSequence, CharSequence...)
     * @see java.util.StringJoiner
     */
    public static String join(CharSequence delimiter, Iterable<? extends CharSequence> elements) {
        Assert.notNull(delimiter, "delimiter cannot be null");
        Assert.notNull(elements, "elements cannot be null");
        return String.join(delimiter, elements);
    }

    /**
     * 格式化字符串
     *
     * <pre>
     *     Strings.format(null)         = java.lang.IllegalArgumentException: format cannot be null
     *     Strings.format("")           = ""
     *     Strings.format("%dAB")       = java.util.MissingFormatArgumentException: Format specifier '%d'
     *     Strings.format("%dAB", null) = "nullAB"
     *     Strings.format("%dAB", 1)    = "1AB"
     * </pre>
     *
     * @param format 格式
     * @param args   占位符参数
     * @return 格式化后字符串
     */
    public static String format(String format, Object... args) {
        Assert.notNull(format, "format cannot be null");
        return String.format(format, args);
    }

    /**
     * 格式化字符串
     *
     * @param locale 地区
     * @param format 格式
     * @param args   占位符参数
     * @return 格式化后字符串
     */
    public static String format(Locale locale, String format, Object... args) {
        Assert.notNull(locale, "locale cannot be null");
        Assert.notNull(format, "format cannot be null");
        return String.format(locale, format, args);
    }

    /**
     * 去掉开头和结尾的空格 {@code '\u0020'}
     *
     * <pre class="code">
     *     Strings.trim(null)   = null
     *     Strings.trim("")     = ""
     *     Strings.trim(" ")    = ""
     *     Strings.trim("ab")   = "ab"
     *     Strings.trim(" ab ") = "ab"
     * </pre>
     *
     * @param source a string
     * @return 去除两侧空格后的字符串
     * @see String#trim()
     */
    public static String trim(final String source) {
        return isNotEmpty(source) ? source.trim() : source;
    }

    /**
     * 去掉开头和结尾的空白字符
     * <p>空白字符:
     * <ul>
     * <li> Unicode空白字符 ({@code SPACE_SEPARATOR},{@code LINE_SEPARATOR},
     *      or {@code PARAGRAPH_SEPARATOR})
     *      但不是一个不间断的空格 ({@code '\u00A0'}, {@code '\u2007'}, {@code '\u202F'}).
     * <li> {@code '\t'}, U+0009 HORIZONTAL TABULATION.
     * <li> {@code '\n'}, U+000A LINE FEED.
     * <li> {@code '\u000B'}, U+000B VERTICAL TABULATION.
     * <li> {@code '\f'}, U+000C FORM FEED.
     * <li> {@code '\r'}, U+000D CARRIAGE RETURN.
     * <li> {@code '\u001C'}, U+001C FILE SEPARATOR.
     * <li> {@code '\u001D'}, U+001D GROUP SEPARATOR.
     * <li> {@code '\u001E'}, U+001E RECORD SEPARATOR.
     * <li> {@code '\u001F'}, U+001F UNIT SEPARATOR.
     * </ul>
     *
     * <pre class="code">
     *     Strings.strip(null)     = null
     *     Strings.strip("")       = ""
     *     Strings.strip(" ")      = ""
     *     Strings.strip("ab")     = "ab"
     *     Strings.strip(" ab\n ") = "ab"
     * </pre>
     *
     * @param source a string
     * @return 去除两侧空白字符后的字符串
     * @see Character#isWhitespace(char)
     */
    public static String strip(final String source) {
        if (isEmpty(source)) {
            return source;
        }

        int beginIndex = 0;
        int endIndex = source.length() - 1;
        // 开头
        while (beginIndex <= endIndex && Character.isWhitespace(source.charAt(beginIndex))) {
            beginIndex++;
        }
        // 结尾
        while (endIndex > beginIndex && Character.isWhitespace(source.charAt(endIndex))) {
            endIndex--;
        }

        return source.substring(beginIndex, endIndex + 1);
    }

    /**
     * 去掉所有的空白字符
     * <p>
     * 空白字符:
     * <ul>
     * <li> Unicode空白字符 ({@code SPACE_SEPARATOR},{@code LINE_SEPARATOR},
     *      or {@code PARAGRAPH_SEPARATOR})
     *      但不是一个不间断的空格 ({@code '\u00A0'}, {@code '\u2007'}, {@code '\u202F'}).
     * <li> {@code '\t'}, U+0009 HORIZONTAL TABULATION.
     * <li> {@code '\n'}, U+000A LINE FEED.
     * <li> {@code '\u000B'}, U+000B VERTICAL TABULATION.
     * <li> {@code '\f'}, U+000C FORM FEED.
     * <li> {@code '\r'}, U+000D CARRIAGE RETURN.
     * <li> {@code '\u001C'}, U+001C FILE SEPARATOR.
     * <li> {@code '\u001D'}, U+001D GROUP SEPARATOR.
     * <li> {@code '\u001E'}, U+001E RECORD SEPARATOR.
     * <li> {@code '\u001F'}, U+001F UNIT SEPARATOR.
     * </ul>
     *
     * <pre class="code">
     *     Strings.stripAll(null)      = null
     *     Strings.stripAll("")        = ""
     *     Strings.stripAll(" ")       = ""
     *     Strings.stripAll("ab a")    = "aba"
     *     Strings.stripAll(" ab\n a") = "aba"
     * </pre>
     *
     * @param source a string
     * @return 去掉所有的空白字符的字符串
     * @see Character#isWhitespace(char)
     */
    public static String stripAll(final String source) {
        if (isEmpty(source)) {
            return source;
        }

        int length = source.length();
        StringBuilder builder = new StringBuilder(source.length());
        for (int i = 0; i < length; i++) {
            char c = source.charAt(i);
            if (!Character.isWhitespace(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * 去掉开头的空白字符
     * <p>
     * 空白字符:
     * <ul>
     * <li> Unicode空白字符 ({@code SPACE_SEPARATOR},{@code LINE_SEPARATOR},
     *      or {@code PARAGRAPH_SEPARATOR})
     *      但不是一个不间断的空格 ({@code '\u00A0'}, {@code '\u2007'}, {@code '\u202F'}).
     * <li> {@code '\t'}, U+0009 HORIZONTAL TABULATION.
     * <li> {@code '\n'}, U+000A LINE FEED.
     * <li> {@code '\u000B'}, U+000B VERTICAL TABULATION.
     * <li> {@code '\f'}, U+000C FORM FEED.
     * <li> {@code '\r'}, U+000D CARRIAGE RETURN.
     * <li> {@code '\u001C'}, U+001C FILE SEPARATOR.
     * <li> {@code '\u001D'}, U+001D GROUP SEPARATOR.
     * <li> {@code '\u001E'}, U+001E RECORD SEPARATOR.
     * <li> {@code '\u001F'}, U+001F UNIT SEPARATOR.
     * </ul>
     *
     * <pre class="code">
     *     Strings.stripStart(null)    = null
     *     Strings.stripStart("")      = ""
     *     Strings.stripStart(" ")     = ""
     *     Strings.stripStart("ab a")  = "ab a"
     *     Strings.stripStart(" ab\n") = "ab\n"
     * </pre>
     *
     * @param source a string
     * @return 去掉开头的空白字符的字符串
     * @see Character#isWhitespace(char)
     */
    public static String stripStart(final String source) {
        if (isEmpty(source)) {
            return source;
        }

        int beginIdx = 0;
        while (beginIdx < source.length() && Character.isWhitespace(source.charAt(beginIdx))) {
            beginIdx++;
        }
        return source.substring(beginIdx);
    }

    /**
     * 去掉结尾的空白字符
     * <p>
     * 空白字符:
     * <ul>
     * <li> Unicode空白字符 ({@code SPACE_SEPARATOR},{@code LINE_SEPARATOR},
     *      or {@code PARAGRAPH_SEPARATOR})
     *      但不是一个不间断的空格 ({@code '\u00A0'}, {@code '\u2007'}, {@code '\u202F'}).
     * <li> {@code '\t'}, U+0009 HORIZONTAL TABULATION.
     * <li> {@code '\n'}, U+000A LINE FEED.
     * <li> {@code '\u000B'}, U+000B VERTICAL TABULATION.
     * <li> {@code '\f'}, U+000C FORM FEED.
     * <li> {@code '\r'}, U+000D CARRIAGE RETURN.
     * <li> {@code '\u001C'}, U+001C FILE SEPARATOR.
     * <li> {@code '\u001D'}, U+001D GROUP SEPARATOR.
     * <li> {@code '\u001E'}, U+001E RECORD SEPARATOR.
     * <li> {@code '\u001F'}, U+001F UNIT SEPARATOR.
     * </ul>
     *
     * <pre class="code">
     *     Strings.stripEnd(null)    = null
     *     Strings.stripEnd("")      = ""
     *     Strings.stripEnd(" ")     = ""
     *     Strings.stripEnd("ab a")  = "ab a"
     *     Strings.stripEnd(" ab\n") = " ab"
     * </pre>
     *
     * @param source a string
     * @return 去掉结尾的空白字符的字符串
     * @see Character#isWhitespace(char)
     */
    public static String stripEnd(final String source) {
        if (isEmpty(source)) {
            return source;
        }

        int endIdx = source.length() - 1;
        while (endIdx >= 0 && Character.isWhitespace(source.charAt(endIdx))) {
            endIdx--;
        }
        return source.substring(0, endIdx + 1);
    }

    /**
     * 返回传入的字符串，如果为 {@code null} 则返回空串
     *
     * <pre class="code">
     *     Strings.defaultStr(null) = ""
     *     Strings.defaultStr("")   = ""
     *     Strings.defaultStr("a")  = "a"
     * </pre>
     *
     * @param source a string
     * @return a string
     */
    public static String defaultStr(final String source) {
        return (null == source ? EMPTY : source);
    }

    /**
     * 返回传入的字符串，如果为 {@code null} 则返回默认值
     *
     * <pre class="code">
     *     Strings.defaultIfNull(null, "v") = "v"
     *     Strings.defaultIfNull("", "v")   = ""
     *     Strings.defaultIfNull("a", "v")  = "a"
     * </pre>
     *
     * @param source     a string
     * @param defaultStr default string
     * @return a string
     */
    public static String defaultIfNull(final String source, final String defaultStr) {
        return (null == source ? defaultStr : source);
    }

    /**
     * 返回传入的字符串，如果为 {@code null} 或 {@code ""} 则返回默认值
     *
     * <pre class="code">
     *     Strings.defaultIfEmpty(null, "v") = "v"
     *     Strings.defaultIfEmpty("", "v")   = "v"
     *     Strings.defaultIfEmpty("\n", "v") = "\n"
     *     Strings.defaultIfEmpty("a", "v")  = "a"
     * </pre>
     *
     * @param source     a string
     * @param defaultStr default string
     * @return a string
     */
    public static String defaultIfEmpty(final String source, final String defaultStr) {
        return (isEmpty(source) ? defaultStr : source);
    }

    /**
     * 目标字符串不为 {@code null}, {@code ""} 或空白字符时返回原字符串，否则返回给定默认字符串
     * <p>
     * 空白字符:
     * <ul>
     * <li> Unicode空白字符 ({@code SPACE_SEPARATOR},{@code LINE_SEPARATOR},
     *      or {@code PARAGRAPH_SEPARATOR})
     *      但不是一个不间断的空格 ({@code '\u00A0'}, {@code '\u2007'}, {@code '\u202F'}).
     * <li> {@code '\t'}, U+0009 HORIZONTAL TABULATION.
     * <li> {@code '\n'}, U+000A LINE FEED.
     * <li> {@code '\u000B'}, U+000B VERTICAL TABULATION.
     * <li> {@code '\f'}, U+000C FORM FEED.
     * <li> {@code '\r'}, U+000D CARRIAGE RETURN.
     * <li> {@code '\u001C'}, U+001C FILE SEPARATOR.
     * <li> {@code '\u001D'}, U+001D GROUP SEPARATOR.
     * <li> {@code '\u001E'}, U+001E RECORD SEPARATOR.
     * <li> {@code '\u001F'}, U+001F UNIT SEPARATOR.
     * </ul>
     *
     * <pre class="code">
     *     Strings.defaultIfBlank(null, "v") = "v"
     *     Strings.defaultIfBlank("", "v")   = "v"
     *     Strings.defaultIfBlank(" ", "v")  = "v"
     *     Strings.defaultIfBlank("\n", "v") = "v"
     *     Strings.defaultIfBlank("a", "v")  = "a"
     * </pre>
     *
     * @param source     a string
     * @param defaultStr default string
     * @return a string
     */
    public static String defaultIfBlank(String source, String defaultStr) {
        return (isBlank(source) ? defaultStr : source);
    }

    /**
     * 获取字符串中最左边的len个字符
     *
     * <p>source为 {@code null} 时返回 {@code null}, len小于等于0时返回 {@code ""}</p>
     *
     * <pre class="code">
     *     Strings.right(null, *)  = null
     *     Strings.right(*, -ve)   = ""
     *     Strings.right("", *)    = ""
     *     Strings.right("abc", 0) = ""
     *     Strings.right("abc", 2) = "ab"
     *     Strings.right("abc", 4) = "abc"
     * </pre>
     *
     * @param source a string
     * @param len    长度
     * @return 最左边的len个字符
     */
    public static String left(final String source, final int len) {
        if (isEmpty(source)) {
            return source;
        }

        if (len <= 0) {
            return EMPTY;
        }

        if (source.length() <= len) {
            return source;
        }
        return source.substring(0, len);
    }

    /**
     * 获取字符串中最右边的len个字符
     *
     * <p>source为 {@code null} 时返回 {@code null}, len小于等于0时返回 {@code ""}</p>
     *
     * <pre class="code">
     *     Strings.right(null, *)  = null
     *     Strings.right(*, -ve)   = ""
     *     Strings.right("", *)    = ""
     *     Strings.right("abc", 0) = ""
     *     Strings.right("abc", 2) = "bc"
     *     Strings.right("abc", 4) = "abc"
     * </pre>
     *
     * @param source a string
     * @param len    长度
     * @return 最右边的len个字符
     */
    public static String right(final String source, final int len) {
        if (isEmpty(source)) {
            return source;
        }

        if (len <= 0) {
            return EMPTY;
        }

        if (source.length() <= len) {
            return source;
        }
        return source.substring(source.length() - len);
    }

    /**
     * 获取字符串中从pos开始len个字符
     *
     * <p>source为 {@code null} 时返回 {@code null}, len小于等于0时返回 {@code ""}</p>
     *
     * <pre class="code">
     *     Strings.mid(null, *, *)   = null
     *     Strings.mid(*, *, -ve)    = ""
     *     Strings.mid("", 0, *)     = ""
     *     Strings.mid("abc", 0, 2)  = "ab"
     *     Strings.mid("abc", 0, 4)  = "abc"
     *     Strings.mid("abc", 2, 4)  = "c"
     *     Strings.mid("abc", 2, 0)  = ""
     *     Strings.mid("abc", 4, 2)  = ""
     *     Strings.mid("abc", -2, 2) = "ab"
     * </pre>
     *
     * @param source a string
     * @param pos    开始偏移量
     * @param len    长度
     * @return 截取字符串
     */
    public static String mid(final String source, int pos, final int len) {
        if (isEmpty(source)) {
            return source;
        }

        if (len < 0 || pos > source.length()) {
            return EMPTY;
        }

        if (pos < 0) {
            pos = 0;
        }
        if (source.length() <= (pos + len)) {
            return source.substring(pos);
        }
        return source.substring(pos, pos + len);
    }

    /**
     * 替换字符
     *
     * <p>source不为 {@code null} 或 {@code ""} 时才能执行替换</p>
     *
     * <pre class="code">
     *     Strings.replace(null, 'A', 'B')  = null
     *     Strings.replace("ACA", 'A', 'B') = "BCB"
     * </pre>
     *
     * @param source      源字符串
     * @param target      需要替换的字符
     * @param replacement 新值
     * @return 替换后的字符串
     */
    public static String replace(final String source, final char target, final char replacement) {
        return isNotEmpty(source) ? source.replace(target, replacement) : source;
    }

    /**
     * 替换字符序列
     *
     * <p>source, target, replacement不为 {@code null} 时才执行替换</p>
     *
     * <pre class="code">
     *     Strings.replace(null, "A", "B")  = null
     *     Strings.replace("A", null, "B")  = "A"
     *     Strings.replace("A", "A", null)  = "A"
     *     Strings.replace("ACA", "A", "B") = "BCB"
     * </pre>
     *
     * @param source      源字符串
     * @param target      需要替换的字符序列
     * @param replacement 新值
     * @return 替换后的字符串
     */
    public static String replace(final String source, final CharSequence target, final CharSequence replacement) {
        return isAllNotNull(source, target, replacement) ? source.replace(target, replacement) : source;
    }

    /**
     * 替换满足指定正则的字符序列
     *
     * <p>source, regex, replacement不为 {@code null} 时才执行替换</p>
     *
     * <pre class="code">
     *     Strings.replaceAll(null, "A", "B")       = null
     *     Strings.replaceAll("A", null, "B")       = "A"
     *     Strings.replaceAll("A", "A", null)       = "A"
     *     Strings.replaceAll("A1B2C", "\\d+", "-") = "A-B-C"
     * </pre>
     *
     * @param source      源字符串
     * @param regex       需要替换的正则
     * @param replacement 新值
     * @return 替换后的字符串
     */
    public static String replaceAll(final String source, final String regex, final String replacement) {
        return isAllNotNull(source, regex, replacement) ? source.replaceAll(regex, replacement) : source;
    }

    /**
     * 替换符合的第一个字符序列
     *
     * <p>source, regex, replacement不为 {@code null} 时才执行替换</p>
     *
     * <pre class="code">
     *     Strings.replaceFirst(null, "A", "B")       = null
     *     Strings.replaceFirst("A", null, "B")       = "A"
     *     Strings.replaceFirst("A", "A", null)       = "A"
     *     Strings.replaceFirst("A1B2C", "\\d+", "-") = "A-B2C"
     * </pre>
     *
     * @param source      源字符串
     * @param regex       正则
     * @param replacement 新字符集
     * @return 替换后的字符串
     */
    public static String replaceFirst(final String source, final String regex, final String replacement) {
        return isAllNotNull(source, regex, replacement) ? source.replaceFirst(regex, replacement) : source;
    }

    /**
     * 去除指定字符序列
     *
     * <p>source, str不为 {@code null} 或 {@code ""} 时才执行去除</p>
     *
     * <pre class="code">
     *     Strings.remove(null, "A")  = null
     *     Strings.remove("A", null)  = "A"
     *     Strings.remove("AAC", "A") = "C"
     * </pre>
     *
     * @param source 源字符串
     * @param str    需要去除的字符序列
     * @return string
     */
    public static String remove(final String source, final CharSequence str) {
        return isAllNotEmpty(source, str) ? source.replace(str, EMPTY) : source;
    }

    /**
     * 去除满足指定正则的字符序列
     *
     * <p>source, regex不为 {@code null} 或 {@code ""} 时才执行去除</p>
     *
     * <pre class="code">
     *     Strings.removeAll(null, "A")      = null
     *     Strings.removeAll("A", null)      = "A"
     *     Strings.removeAll("A1B2", "\\d+") = "AB"
     * </pre>
     *
     * @param source 源字符串
     * @param regex  需要去除的正则
     * @return string
     */
    public static String removeAll(final String source, final String regex) {
        return isAllNotEmpty(source, regex) ? source.replaceAll(regex, EMPTY) : source;
    }

    /**
     * 去除第一个满足指定正则的字符序列
     *
     * <p>source, regex不为 {@code null} 或 {@code ""} 时才执行去除</p>
     *
     * <pre class="code">
     *     Strings.removeFirst(null, "A")      = null
     *     Strings.removeFirst("A", null)      = "A"
     *     Strings.removeFirst("A1B2", "\\d+") = "AB2"
     * </pre>
     *
     * @param source 源字符串
     * @param regex  需要去除的正则
     * @return string
     */
    public static String removeFirst(final String source, final String regex) {
        return isAllNotEmpty(source, regex) ? source.replaceFirst(regex, EMPTY) : source;
    }

    /**
     * 去除最后一个满足指定的字符序列
     *
     * <p>source, str不为 {@code null} 或 {@code ""} 时才执行去除</p>
     *
     * <pre class="code">
     *     Strings.removeLast(null, "A")      = null
     *     Strings.removeLast("A", null)      = "A"
     *     Strings.removeLast("A1B2", "\\d+") = "AB"
     * </pre>
     *
     * @param source 源字符串
     * @param str    需要去除字符
     * @return string
     */
    public static String removeLast(final String source, final String str) {
        String newStr = source;
        if (isAllNotEmpty(source, str)) {
            int sourceLen = source.length();
            int strLen = str.length();

            int idx = source.lastIndexOf(str);

            if (idx > 0) {
                if (sourceLen > strLen) {
                    newStr = source.substring(0, idx) + source.substring(idx + strLen);
                } else {
                    newStr = source.substring(0, idx);
                }
            } else if (idx == 0) {
                if (sourceLen > strLen) {
                    newStr = source.substring(idx + strLen);
                } else {
                    newStr = "";
                }
            }
            // else idx < 0
        }
        return newStr;
    }

    /**
     * 截取最后一个"."后的部分
     *
     * <p>qualifiedName为 {@code null} 或 {@code ""} 时直接返回qualifiedName</p>
     *
     * <pre class="code">
     *     Strings.suffix(null)    = null
     *     Strings.suffix("")      = ""
     *     Strings.suffix("a.b.c") = ".c"
     * </pre>
     *
     * @param qualifiedName a string
     * @return 后缀
     */
    public static String suffix(final String qualifiedName) {
        return suffix(qualifiedName, '.');
    }

    /**
     * 获取最后一个指定字符后的部分
     *
     * <p>qualifiedName为 {@code null} 或 {@code ""} 时直接返回qualifiedName</p>
     *
     * <pre class="code">
     *     Strings.suffix(null, '.')    = null
     *     Strings.suffix("", '.')      = ""
     *     Strings.suffix("a.b.c", '.') = ".c"
     * </pre>
     *
     * @param qualifiedName a string
     * @param separator     分隔符
     * @return 后缀
     */
    public static String suffix(final String qualifiedName, final char separator) {
        return isNotEmpty(qualifiedName) ?
                qualifiedName.substring(qualifiedName.lastIndexOf(separator)) :
                qualifiedName;
    }

    /**
     * 将字符串转为字节数组，使用系统默认编码
     *
     * <p>source为 {@code null} 时返回 {@code null}</p>
     *
     * @param source a string
     * @return ByteBuffer
     */
    public static ByteBuffer getByteBuffer(final String source) {
        return getByteBuffer(source, Charset.defaultCharset());
    }

    /**
     * 将字符串转为字节数组
     *
     * <p>source为 {@code null} 时返回 {@code null}</p>
     *
     * @param source  a string
     * @param charset 编码
     * @return ByteBuffer
     */
    public static ByteBuffer getByteBuffer(final String source, final Charset charset) {
        if (source == null) {
            return null;
        }
        if (charset != null) {
            return ByteBuffer.wrap(source.getBytes(charset));
        }
        return ByteBuffer.wrap(source.getBytes());
    }

    /**
     * 将字符串转为字节数组，使用utf-8编码
     *
     * <p>source为 {@code null} 时返回 {@code null}</p>
     *
     * @param source a string
     * @return ByteBuffer
     */
    public static ByteBuffer getByteBufferUtf8(final String source) {
        return getByteBuffer(source, StandardCharsets.UTF_8);
    }

    /**
     * 将字符串转为字节数组，使用系统默认编码
     *
     * <p>source为 {@code null} 时返回 {@code null}</p>
     *
     * @param source a string
     * @return byte[]
     */
    public static byte[] getBytes(final String source) {
        return getBytes(source, Charset.defaultCharset());
    }

    /**
     * 将字符串转为字节数组
     *
     * <p>source为 {@code null} 时返回 {@code null}</p>
     *
     * @param source  a string
     * @param charset 编码
     * @return 字节数组
     */
    public static byte[] getBytes(final String source, final Charset charset) {
        if (null == source) {
            return null;
        }
        return source.getBytes(charset);
    }

    /**
     * 将字符串转为字节数组，使用utf-8编码
     *
     * <p>source为 {@code null} 时返回 {@code null}</p>
     *
     * @param source a string
     * @return 字节数组
     */
    public static byte[] getBytesUtf8(final String source) {
        return getBytes(source, StandardCharsets.UTF_8);
    }

    /**
     * 通过字节数组实例化字符串，使用当前系统默认编码
     *
     * <p>bytes为 {@code null} 时返回 {@code null}</p>
     *
     * @param bytes 字节数组
     * @return string
     */
    public static String newString(final byte[] bytes) {
        return newString(bytes, Charset.defaultCharset());
    }

    /**
     * 通过字节数组实例化字符串
     *
     * <p>bytes为 {@code null} 时返回 {@code null}</p>
     *
     * @param bytes   字节数组
     * @param charset 编码
     * @return string
     */
    public static String newString(final byte[] bytes, final Charset charset) {
        if (null == bytes) {
            return null;
        }
        return new String(bytes, charset);
    }

    /**
     * 通过字节数组实例化字符串，使用utf-8编码
     *
     * <p>bytes为 {@code null} 时返回 {@code null}</p>
     *
     * @param bytes 字节数组
     * @return string
     */
    public static String newStringUtf8(final byte[] bytes) {
        return newString(bytes, StandardCharsets.UTF_8);
    }

    /**
     * 重复一个字符串多次生成一个新字符串
     *
     * <p>source为 {@code null} 或 {@code ""} 时直接返回source, repeat小于等于0时返回 {@code ""}</p>
     *
     * <pre class="code">
     *     Strings.repeat(null, 2)  = null
     *     Strings.repeat("", 2)    = ""
     *     Strings.repeat("ab", -1) = ""
     *     Strings.repeat("ab", 0)  = ""
     *     Strings.repeat("ab", 2)  = "abab"
     * </pre>
     *
     * @param source a string
     * @param repeat 重复次数
     * @return 重复的字符串
     * @see #padding(int, char)
     */
    public static String repeat(final String source, final int repeat) {
        if (isEmpty(source)) {
            return source;
        }
        if (repeat <= 0) {
            return EMPTY;
        }
        int inputLength = source.length();
        if (repeat == 1 || inputLength == 0) {
            return source;
        }
        if (inputLength == 1 && repeat <= PAD_LIMIT) {
            return padding(repeat, source.charAt(0));
        }

        int outputLength = inputLength * repeat;
        switch (inputLength) {
            case 1:
                char ch = source.charAt(0);
                char[] output1 = new char[outputLength];
                for (int i = repeat - 1; i >= 0; i--) {
                    output1[i] = ch;
                }
                return new String(output1);
            case 2:
                char ch0 = source.charAt(0);
                char ch1 = source.charAt(1);
                char[] output2 = new char[outputLength];
                for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                return new String(output2);
            default:
                StringBuilder buf = new StringBuilder(outputLength);
                for (int i = 0; i < repeat; i++) {
                    buf.append(source);
                }
                return buf.toString();
        }
    }

    /**
     * 使用指定分隔符重复到给定长度填充。
     *
     * <pre class="code">
     *     Strings.padding(0, 'e')  = ""
     *     Strings.padding(3, 'e')  = "eee"
     *     Strings.padding(-2, 'e') = IndexOutOfBoundsException
     * </pre>
     *
     * @param repeat  重复次数
     * @param padChar 重复字符
     * @return a string
     * @throws IndexOutOfBoundsException if <code>repeat &lt; 0</code>
     * @see #repeat(String, int)
     */
    private static String padding(final int repeat, final char padChar) throws IndexOutOfBoundsException {
        if (repeat < 0) {
            throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
        }
        final char[] buf = new char[repeat];
        Arrays.fill(buf, padChar);
        return new String(buf);
    }

    /**
     * 右侧填充空格
     *
     * <pre class="code">
     *     Strings.rpad(null, 2)  = null
     *     Strings.rpad("", 2)    = ""
     *     Strings.rpad("ab", -1) = "ab"
     *     Strings.rpad("ab", 2)  = "ab"
     *     Strings.rpad("ab", 3)  = "ab "
     * </pre>
     *
     * @param source a string
     * @param size   字符串最大长度
     * @return 填充后的字符串
     */
    public static String rpad(final String source, final int size) {
        return rpad(source, size, SPACE);
    }

    /**
     * 右侧填充字符
     *
     * <pre class="code">
     *     Strings.rpad(null, 2, 'z')  = null
     *     Strings.rpad("", 2, 'z')    = "zz"
     *     Strings.rpad("ab", -1, 'z') = "ab"
     *     Strings.rpad("ab", 2, 'z')  = "ab"
     *     Strings.rpad("ab", 3, 'z')  = "abz"
     * </pre>
     *
     * @param source  a string
     * @param size    字符串最大长度
     * @param padChar 填充的字符
     * @return 填充后的字符串
     */
    public static String rpad(final String source, final int size, final char padChar) {
        if (null == source) {
            return null;
        }
        int pads = size - source.length();
        if (pads <= 0) {
            return source;
        }
        if (pads > PAD_LIMIT) {
            return rpad(source, size, String.valueOf(padChar));
        }
        return source.concat(padding(pads, padChar));
    }

    /**
     * 右侧填充字符串
     *
     * <pre class="code">
     *     Strings.rpad(null, 2, "zy")   = null
     *     Strings.rpad("", 2, "zy")     = "zy"
     *     Strings.rpad("abc", -1, "zy") = "abc"
     *     Strings.rpad("abc", 3, "zy")  = "abc"
     *     Strings.rpad("abc", 6, "zy")  = "abczyz"
     * </pre>
     *
     * @param source a string
     * @param size   字符串最大长度
     * @param padStr 填充的字符串
     * @return 填充后的字符串
     */
    public static String rpad(final String source, final int size, String padStr) {
        if (null == source) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = SPACE_STR;
        }
        int padLen = padStr.length();
        int strLen = source.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return source;
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return rpad(source, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return source.concat(padStr);
        } else if (pads < padLen) {
            return source.concat(padStr.substring(0, pads));
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return source.concat(new String(padding));
        }
    }


    /**
     * 左侧填充空格
     *
     * <pre class="code">
     *     Strings.lpad(null, 2)  = null
     *     Strings.lpad("", 2)    = ""
     *     Strings.lpad("ab", -1) = "ab"
     *     Strings.lpad("ab", 2)  = "ab"
     *     Strings.lpad("ab", 3)  = " ab"
     * </pre>
     *
     * @param source a string
     * @param size   字符串最大长度
     * @return 填充后的字符串
     */
    public static String lpad(final String source, final int size) {
        return lpad(source, size, SPACE);
    }

    /**
     * 左侧填充字符
     *
     * <pre class="code">
     *     Strings.lpad(null, 2, 'z')  = null
     *     Strings.lpad("", 2, 'z')    = "zz"
     *     Strings.lpad("ab", -1, 'z') = "ab"
     *     Strings.lpad("ab", 2, 'z')  = "ab"
     *     Strings.lpad("ab", 3, 'z')  = "zab"
     * </pre>
     *
     * @param source  a string
     * @param size    字符串最大长度
     * @param padChar 填充的字符
     * @return 填充后的字符串
     */
    public static String lpad(final String source, final int size, final char padChar) {
        if (source == null) {
            return null;
        }
        int pads = size - source.length();
        if (pads <= 0) {
            return source; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return lpad(source, size, String.valueOf(padChar));
        }
        return padding(pads, padChar).concat(source);
    }

    /**
     * 左侧填充字符串
     *
     * <pre class="code">
     *     Strings.lpad(null, 2, "zy")   = null
     *     Strings.lpad("", 2, "zy")     = "zy"
     *     Strings.lpad("abc", -1, "zy") = "abc"
     *     Strings.lpad("abc", 3, "zy")  = "abc"
     *     Strings.lpad("abc", 6, "zy")  = "zyzabc"
     * </pre>
     *
     * @param source a string
     * @param size   字符串最大长度
     * @param padStr 填充的字符串
     * @return 填充后的字符串
     */
    public static String lpad(final String source, final int size, String padStr) {
        if (source == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = SPACE_STR;
        }
        int padLen = padStr.length();
        int strLen = source.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return source; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return lpad(source, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(source);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(source);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(source);
        }
    }

    /**
     * 字符串长度
     *
     * <p>source为 {@code null} 时返回 {@code 0}</p>
     *
     * <pre class="code">
     *     Strings.length(null) = 0
     *     Strings.length("")   = 0
     *     Strings.length("aa") = 2
     * </pre>
     *
     * @param source a string
     * @return 字符串长度
     */
    public static int length(final String source) {
        return (null == source ? 0 : source.length());
    }

    /**
     * 转大写
     *
     * <p>source为 {@code null} 或 {@code ""} 时直接返回source</p>
     *
     * <pre class="code">
     *     Strings.upperCase(null)  = null
     *     Strings.upperCase("")    = ""
     *     Strings.upperCase("aBc") = "ABC"
     * </pre>
     *
     * @param source a string
     * @return 大写字符串
     */
    public static String upperCase(final String source) {
        if (isEmpty(source)) {
            return source;
        }
        return source.toUpperCase();
    }

    /**
     * 转大写
     *
     * <p>source为 {@code null} 或 {@code ""} 时直接返回source</p>
     *
     * <pre class="code">
     *     Strings.upperCase(null, Locale.ENGLISH)  = null
     *     Strings.upperCase("", Locale.ENGLISH)    = ""
     *     Strings.upperCase("aBc", Locale.ENGLISH) = "ABC"
     * </pre>
     *
     * @param source a string
     * @param locale 地区
     * @return 大写字符串
     */
    public static String upperCase(final String source, final Locale locale) {
        if (isEmpty(source)) {
            return source;
        }
        if (null != locale) {
            return source.toUpperCase(locale);
        } else {
            return source.toUpperCase();
        }
    }

    /**
     * 转小写
     *
     * <p>source为 {@code null} 或 {@code ""} 时直接返回source</p>
     *
     * <pre class="code">
     *     Strings.lowerCase(null)  = null
     *     Strings.lowerCase("")    = ""
     *     Strings.lowerCase("aBc") = "abc"
     * </pre>
     *
     * @param source a string
     * @return 小写字符串
     */
    public static String lowerCase(final String source) {
        if (isEmpty(source)) {
            return source;
        }
        return source.toLowerCase();
    }

    /**
     * 转小写
     *
     * <p>source为 {@code null} 或 {@code ""} 时直接返回source</p>
     *
     * <pre class="code">
     *     Strings.lowerCase(null, Locale.ENGLISH)  = null
     *     Strings.lowerCase("", Locale.ENGLISH)    = ""
     *     Strings.lowerCase("aBc", Locale.ENGLISH) = "abc"
     * </pre>
     *
     * @param source a string
     * @param locale 地区
     * @return 小写字符串
     */
    public static String lowerCase(final String source, final Locale locale) {
        if (isEmpty(source)) {
            return source;
        }
        if (null != locale) {
            return source.toLowerCase(locale);
        } else {
            return source.toLowerCase();
        }
    }

    /**
     * 大小写互换，大写转小写，小写转大写
     *
     * <p>source为 {@code null} 或 {@code ""} 时直接返回source</p>
     *
     * <pre class="code">
     *     Strings.swapCase(null)                 = null
     *     Strings.swapCase("")                   = ""
     *     Strings.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
     * </pre>
     *
     * @param source a string
     * @return 大小写互换后的字符串
     */
    public static String swapCase(final String source) {
        if (isEmpty(source)) {
            return source;
        }
        int length = source.length();
        StringBuilder buffer = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            char ch = source.charAt(i);
            if (Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                ch = Character.toUpperCase(ch);
            }
            buffer.append(ch);
        }
        return buffer.toString();
    }

    /**
     * 首字母大写
     *
     * <p>source为 {@code null} 或 {@code ""} 时直接返回source</p>
     *
     * <pre class="code">
     *     Strings.capitalize(null)  = null
     *     Strings.capitalize("")    = ""
     *     Strings.capitalize("cat") = "Cat"
     *     Strings.capitalize("cAt") = "CAt"
     * </pre>
     *
     * @param source a string
     * @return string
     */
    public static String capitalize(final String source) {
        return changeFirstCharacterCase(source, true);
    }

    /**
     * 首字母小写
     *
     * <p>source为 {@code null} 或 {@code ""} 时直接返回source</p>
     *
     * <pre class="code">
     *     Strings.uncapitalize(null)  = null
     *     Strings.uncapitalize("")    = ""
     *     Strings.uncapitalize("Cat") = "cat"
     *     Strings.uncapitalize("CAt") = "cAt"
     * </pre>
     *
     * @param source a string
     * @return string
     */
    public static String uncapitalize(String source) {
        return changeFirstCharacterCase(source, false);
    }

    private static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (!hasLength(str)) {
            return str;
        }

        char baseChar = str.charAt(0);
        char updatedChar;
        if (capitalize) {
            updatedChar = Character.toUpperCase(baseChar);
        } else {
            updatedChar = Character.toLowerCase(baseChar);
        }
        if (baseChar == updatedChar) {
            return str;
        }

        char[] chars = str.toCharArray();
        chars[0] = updatedChar;
        return new String(chars, 0, chars.length);
    }

    /**
     * 下划线命名转驼峰命名，字符串长度需大于2
     *
     * <p>source为 {@code null} 或 {@code ""} 时直接返回source</p>
     *
     * <pre class="code">
     *     Strings.underscoreToCamelCase(null)   = null
     *     Strings.underscoreToCamelCase("")     = ""
     *     Strings.underscoreToCamelCase("_ab")  = "_ab"
     *     Strings.underscoreToCamelCase("ab_")  = "ab_"
     *     Strings.underscoreToCamelCase("a_b")  = "aB"
     *     Strings.underscoreToCamelCase("a1_b") = "a1B"
     * </pre>
     *
     * @param source 下划线命名字符串
     * @return 驼峰命名字符串
     */
    public static String underscoreToCamelCase(final String source) {
        if (isEmpty(source)) {
            return source;
        }
        StringBuilder builder = new StringBuilder(source);
        for (int i = 1; i < builder.length() - 1; i++) {
            char before = builder.charAt(i - 1);
            char current = builder.charAt(i);
            char after = builder.charAt(i + 1);
            if (isUnderscoreCaseRequired(before, current, after)) {
                builder.delete(i, i + 2);
                builder.insert(i, Character.toUpperCase(after));
            }
        }
        return builder.toString();
    }

    private static boolean isUnderscoreCaseRequired(final char before, final char current, final char after) {
        return ((Character.isLowerCase(before) || Chars.isAsciiNumeric(before)) && current == UNDERLINE && Character.isLowerCase(after));
    }

    /**
     * 驼峰命名转下划线命名，字符串长度需大于2
     *
     * <p>source为 {@code null} 或 {@code ""} 时直接返回source</p>
     *
     * <pre class="code">
     *     Strings.camelToUnderscoreCase(null)   = null
     *     Strings.camelToUnderscoreCase("")     = ""
     *     Strings.camelToUnderscoreCase("Abc")  = "Abc"
     *     Strings.camelToUnderscoreCase("abC")  = "abC"
     *     Strings.camelToUnderscoreCase("ABc")  = "ABc"
     *     Strings.camelToUnderscoreCase("aBC")  = "aBC"
     *     Strings.camelToUnderscoreCase("aBc")  = "a_bc"
     *     Strings.camelToUnderscoreCase("a1Bc") = "a1_bc"
     * </pre>
     *
     * @param source 驼峰命名字符串
     * @return 下划线命名字符串
     */
    public static String camelToUnderscoreCase(final String source) {
        if (isEmpty(source)) {
            return source;
        }
        StringBuilder builder = new StringBuilder(source);
        for (int i = 1; i < builder.length() - 1; i++) {
            char before = builder.charAt(i - 1);
            char current = builder.charAt(i);
            char after = builder.charAt(i + 1);
            if (isCamelCaseRequired(before, current, after)) {
                builder.insert(i++, UNDERLINE);
                builder.deleteCharAt(i);
                builder.insert(i, Character.toLowerCase(current));
            }
        }
        return builder.toString();
    }

    private static boolean isCamelCaseRequired(final char before, final char current, final char after) {
        return ((Character.isLowerCase(before) || Chars.isAsciiNumeric(before)) && Character.isUpperCase(current) && Character.isLowerCase(after));
    }

    /**
     * 子字符串出现的次数
     *
     * <pre class="code">
     *     Strings.countMatches(null, "A")      = 0
     *     Strings.countMatches("A", null)      = 0
     *     Strings.countMatches("", "A")        = 0
     *     Strings.countMatches("AAA_AA", "AB") = 0
     *     Strings.countMatches("AAA_AA", "AA") = 2
     * </pre>
     *
     * @param source a string
     * @param sub    子字符串
     * @return 出现的次数
     */
    public static int countMatches(final String source, final String sub) {
        if (hasEmpty(source, sub)) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = source.indexOf(sub, idx)) != INDEX_NOT_FOUND) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    /**
     * 反转字符串
     *
     * <pre class="code">
     *     Strings.reverse(null)  = null
     *     Strings.reverse("")    = ""
     *     Strings.reverse("bat") = "tab"
     * </pre>
     *
     * @param source a string
     * @return 反转后的字符串
     * @see StringBuilder#reverse()
     */
    public static String reverse(final String source) {
        if (isEmpty(source)) {
            return source;
        }
        return new StringBuilder(source).reverse().toString();
    }

    /**
     * 省略字符串
     *
     * <pre class="code">
     *     Strings.abbreviate(null, 4)     = null
     *     Strings.abbreviate("", 4)       = ""
     *     Strings.abbreviate("abcdef", 4) = "a..."
     *     Strings.abbreviate("abcdef", 5) = "ab..."
     *     Strings.abbreviate("abcdef", 7) = "abcdef"
     *     Strings.abbreviate("abcdef", 3) = IllegalArgumentException
     * </pre>
     *
     * @param source   a string
     * @param maxWidth 字符串最大长度，大于等于4
     * @return 省略后字符串
     */
    public static String abbreviate(final String source, final int maxWidth) {
        int minMaxWidth = 4;
        if (maxWidth < minMaxWidth) {
            throw new IllegalArgumentException("Minimum abbreviation width is 4");
        }
        if (isEmpty(source) || (source.length() <= maxWidth)) {
            return source;
        }
        return source.substring(0, maxWidth - 3) + ELLIPSIS;
    }

    /**
     * 返回规范化的空格参数字符串，删除开头和结尾的空格，然后用单个空格替换空白字符序列。
     *
     * <pre class="code">
     *     Strings.normalizeSpace(null)                  = null
     *     Strings.normalizeSpace("")                    = ""
     *     Strings.normalizeSpace(" \n\r\t")             = ""
     *     Strings.normalizeSpace(" This\t \nis a test") = "This is a test"
     * </pre>
     *
     * @param source a string
     * @return 规范化后的字符串
     * @see Character#isWhitespace(char)
     */
    public static String normalizeSpace(String source) {
        if (isEmpty(source)) {
            return source;
        }
        source = strip(source);
        StringBuilder b = new StringBuilder(source.length());
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if (Character.isWhitespace(c)) {
                if (i > 0 && !Character.isWhitespace(source.charAt(i - 1))) {
                    b.append(SPACE);
                }
            } else {
                b.append(c);
            }
        }
        return b.toString();
    }

    /**
     * 检查字符串是否以指定字符串数组中的任何一个结尾
     *
     * <pre class="code">
     *     Strings.endsWithAny(null, null)                                 = false
     *     Strings.endsWithAny(null, new String[] {"xyz"})                 = false
     *     Strings.endsWithAny("abc.cn", null)                             = false
     *     Strings.endsWithAny("abc.cn", new String[] {""})                = true
     *     Strings.endsWithAny("abc.cn", new String[] {"cn"})              = true
     *     Strings.endsWithAny("abc.cn", new String[] {null, "cn", "com"}) = true
     * </pre>
     *
     * @param source        a string
     * @param searchStrings String[]
     * @return boolean
     */
    public static boolean endsWithAny(final String source, final String[] searchStrings) {
        if (isEmpty(source) || (null == searchStrings || searchStrings.length == 0)) {
            return false;
        }
        for (String searchString : searchStrings) {
            if (null != searchString && source.endsWith(searchString)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符序列的所有字符是否满足给定判断条件
     *
     * <p>字符序列为 {@code null} 和 {@code ""} 返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.isCharPredicate(null, Character::isLowerCase)  = false
     *     Strings.isCharPredicate("", Character::isLowerCase)    = false
     *     Strings.isCharPredicate("ABC", Character::isLowerCase) = true
     * </pre>
     *
     * @param sequence  字符序列
     * @param predicate 谓语表达式
     * @return {@code true} 满足 {@code false} 不满足
     */
    public static boolean isCharPredicate(final CharSequence sequence, final Predicate<Character> predicate) {
        if (isEmpty(sequence)) {
            return false;
        }
        int length = sequence.length();
        for (int i = 0; i < length; i++) {
            if (!predicate.test(sequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否只包含小写字符
     *
     * <p>字符序列为 {@code null} 和 {@code ""} 返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.isLowerCase(null)  = false
     *     Strings.isLowerCase("")    = false
     *     Strings.isLowerCase("  ")  = false
     *     Strings.isLowerCase("abc") = true
     *     Strings.isLowerCase("abC") = false
     * </pre>
     *
     * @param source a string
     * @return boolean
     * @see #isCharPredicate(CharSequence, Predicate)
     * @see Character#isLowerCase(char)
     */
    public static boolean isLowerCase(final CharSequence source) {
        return isCharPredicate(source, Character::isLowerCase);
    }

    /**
     * 是否只包含大写字符
     *
     * <p>字符序列为 {@code null} 和 {@code ""} 返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.isUpperCase(null)  = false
     *     Strings.isUpperCase("")    = false
     *     Strings.isUpperCase("  ")  = false
     *     Strings.isUpperCase("ABC") = true
     *     Strings.isUpperCase("aBC") = false
     * </pre>
     *
     * @param source a string
     * @return boolean
     * @see #isCharPredicate(CharSequence, Predicate)
     * @see Character#isUpperCase(char)
     */
    public static boolean isUpperCase(final CharSequence source) {
        return isCharPredicate(source, Character::isUpperCase);
    }

    /**
     * 检查字符串是否只包含unicode字母
     *
     * <p>字符序列为 {@code null} 和 {@code ""} 返回 {@code false}</p>
     *
     * <ul>
     * <li> {@code UPPERCASE_LETTER}
     * <li> {@code LOWERCASE_LETTER}
     * <li> {@code TITLECASE_LETTER}
     * <li> {@code MODIFIER_LETTER}
     * <li> {@code OTHER_LETTER}
     * </ul>
     *
     * <pre class="code">
     *     Strings.isLetter(null)  = false
     *     Strings.isLetter("")    = false
     *     Strings.isLetter(" ")   = false
     *     Strings.isLetter("Ab2") = false
     *     Strings.isLetter("Ab-") = false
     *     Strings.isLetter("Abc") = true
     * </pre>
     *
     * @param source a string
     * @return boolean
     * @see #isCharPredicate(CharSequence, Predicate)
     * @see Character#isLetter(char)
     */
    public static boolean isLetter(final CharSequence source) {
        return isCharPredicate(source, Character::isLetter);
    }

    /**
     * 检查字符串是否只包含unicode数字，小数点不是unicode数字并返回 {@code false}
     *
     * <p>字符序列为 {@code null} 和 {@code ""} 返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.isDigit(null)   = false
     *     Strings.isDigit("")     = false
     *     Strings.isDigit("  ")   = false
     *     Strings.isDigit("123")  = true
     *     Strings.isDigit("12 3") = false
     *     Strings.isDigit("ab2c") = false
     *     Strings.isDigit("12-3") = false
     *     Strings.isDigit("12.3") = false
     * </pre>
     *
     * @param source a string
     * @return boolean
     * @see #isCharPredicate(CharSequence, Predicate)
     * @see Character#isDigit(char)
     */
    public static boolean isDigit(final CharSequence source) {
        return isCharPredicate(source, Character::isDigit);
    }

    /**
     * 检查字符串是否只包含unicode字母或数字
     *
     * <p>字符序列为 {@code null} 和 {@code ""} 返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.isLetterOrDigit(null)   = false
     *     Strings.isLetterOrDigit("")     = false
     *     Strings.isLetterOrDigit(" ")    = false
     *     Strings.isLetterOrDigit("Ab c") = false
     *     Strings.isLetterOrDigit("Ab-c") = false
     *     Strings.isLetterOrDigit("Abc")  = true
     *     Strings.isLetterOrDigit("1234") = true
     *     Strings.isLetterOrDigit("Ab2c") = true
     * </pre>
     *
     * @param source a string
     * @return boolean
     * @see #isCharPredicate(CharSequence, Predicate)
     * @see Character#isLetterOrDigit(char)
     */
    public static boolean isLetterOrDigit(final CharSequence source) {
        return isCharPredicate(source, Character::isLetterOrDigit);
    }

    /**
     * 检查字符串是否只包含unicode空格
     *
     * <p>字符序列为 {@code null} 和 {@code ""} 返回 {@code false}</p>
     * <ul>
     * <li> {@code SPACE_SEPARATOR}
     * <li> {@code LINE_SEPARATOR}
     * <li> {@code PARAGRAPH_SEPARATOR}
     * </ul>
     *
     * <pre class="code">
     *     Strings.isSpaceChar(null)   = false
     *     Strings.isSpaceChar("")     = false
     *     Strings.isSpaceChar("  ")   = true
     *     Strings.isSpaceChar("123")  = false
     *     Strings.isSpaceChar("Abc")  = false
     * </pre>
     *
     * @param source a string
     * @return boolean
     * @see Character#isLetterOrDigit(char)
     * @see Character#isSpaceChar(char)
     */
    public static boolean isSpaceChar(final CharSequence source) {
        return isCharPredicate(source, Character::isSpaceChar);
    }

    /**
     * 检查字符串是否只包含ASCII可打印字符
     *
     * <p>字符序列为 {@code null} 和 {@code ""} 返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.isAsciiPrintable(null)                   = false
     *     Strings.isAsciiPrintable("")                     = false
     *     Strings.isAsciiPrintable(" ")                    = true
     *     Strings.isAsciiPrintable("Ceki")                 = true
     *     Strings.isAsciiPrintable("ab2c")                 = true
     *     Strings.isAsciiPrintable("!ab-c~")               = true
     *     Strings.isAsciiPrintable("\u0020")               = true
     *     Strings.isAsciiPrintable("\u0021")               = true
     *     Strings.isAsciiPrintable("\u007e")               = true
     *     Strings.isAsciiPrintable("\u007f")               = false
     *     Strings.isAsciiPrintable("Ceki G\u00fclc\u00fc") = false
     * </pre>
     *
     * @param source a string
     * @return boolean
     * @see #isCharPredicate(CharSequence, Predicate)
     * @see Chars#isAsciiPrintable(char)
     */
    public static boolean isAsciiPrintable(final CharSequence source) {
        return isCharPredicate(source, Chars::isAsciiPrintable);
    }

    /**
     * 检查字符串是否只包含ASCII控制字符
     *
     * <p>字符序列为 {@code null} 和 {@code ""} 返回 {@code false}</p>
     *
     * <pre class="code">
     *     Strings.isAsciiControl(null)     = false
     *     Strings.isAsciiControl("")       = false
     *     Strings.isAsciiControl(" ")      = false
     *     Strings.isAsciiControl("Ab2c")   = false
     *     Strings.isAsciiControl("!ab-c~") = false
     *     Strings.isAsciiControl("\u0000") = true
     *     Strings.isAsciiControl("\u007E") = false
     *     Strings.isAsciiControl("\u007F") = true
     * </pre>
     *
     * @param source a string
     * @return boolean
     * @see #isCharPredicate(CharSequence, Predicate)
     * @see Chars#isAsciiControl(char)
     */
    public static boolean isAsciiControl(final CharSequence source) {
        return isCharPredicate(source, Chars::isAsciiControl);
    }

    /**
     * 检查字符串是否只包含空白字符
     *
     * <p>字符序列为 {@code null} 和 {@code ""} 返回 {@code false}</p>
     * <p>
     * 空白字符:
     * <ul>
     * <li> Unicode空白字符 ({@code SPACE_SEPARATOR},{@code LINE_SEPARATOR},
     *      or {@code PARAGRAPH_SEPARATOR})
     *      但不是一个不间断的空格 ({@code '\u00A0'}, {@code '\u2007'}, {@code '\u202F'}).
     * <li> {@code '\t'}, U+0009 HORIZONTAL TABULATION.
     * <li> {@code '\n'}, U+000A LINE FEED.
     * <li> {@code '\u000B'}, U+000B VERTICAL TABULATION.
     * <li> {@code '\f'}, U+000C FORM FEED.
     * <li> {@code '\r'}, U+000D CARRIAGE RETURN.
     * <li> {@code '\u001C'}, U+001C FILE SEPARATOR.
     * <li> {@code '\u001D'}, U+001D GROUP SEPARATOR.
     * <li> {@code '\u001E'}, U+001E RECORD SEPARATOR.
     * <li> {@code '\u001F'}, U+001F UNIT SEPARATOR.
     * </ul>
     *
     * <pre class="code">
     *     Strings.isWhitespace(null)   = false
     *     Strings.isWhitespace("")     = false
     *     Strings.isWhitespace(" \n")  = true
     *     Strings.isWhitespace("abc")  = false
     *     Strings.isWhitespace("ab2c") = false
     *     Strings.isWhitespace("ab-c") = false
     * </pre>
     *
     * @param source a string
     * @return boolean
     * @see #isCharPredicate(CharSequence, Predicate)
     * @see Character#isWhitespace(char)
     */
    public static boolean isWhitespace(final CharSequence source) {
        return isCharPredicate(source, Character::isWhitespace);
    }

    /**
     * 判断字符序列内所有字符是否都在Unicode中有定义
     * {@link Character#isDefined(char)}
     *
     * @param source 字符序列
     * @return boolean
     * @see #isCharPredicate(CharSequence, Predicate)
     * @see Character#isDefined(char)
     */
    public static boolean isDefined(final CharSequence source) {
        return isCharPredicate(source, Character::isDefined);
    }

}
