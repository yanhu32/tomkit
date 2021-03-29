package tomkit.core.lang;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 字符串操作辅助类
 *
 * @author yh
 * @since 2021/2/4
 */
public final class StringkitHelper {

    private StringkitHelper() {
    }

    public static Builder builder(String value) {
        return new Builder(value);
    }

    /**
     * 通过字节数组实例化字符串，使用utf-8编码
     *
     * @param bytes 字节数组
     * @return 字符串构建器
     */
    public static Builder builder(final byte[] bytes) {
        return new Builder(Stringkit.toString(bytes));
    }

    /**
     * 通过字节数组实例化字符串
     *
     * @param bytes   字节数组
     * @param charset 编码
     * @return 字符串构建器
     */
    public static Builder builder(final byte[] bytes, final Charset charset) {
        return new Builder(Stringkit.toString(bytes, charset));
    }

    public static class Builder {

        private String value;

        private Builder(String value) {
            this.value = value;
        }

        public Optional<String> optional() {
            return Optional.ofNullable(value);
        }

        public String get() {
            return value;
        }

        /*---------------------------- 中间操作 ----------------------------*/

        /**
         * 返回双引号的字符串
         *
         * @return
         */
        public Builder doubleQuote() {
            value = Stringkit.doubleQuote(value);
            return this;
        }

        /**
         * 返回单引号的字符串
         *
         * @return
         */
        public Builder quote() {
            value = Stringkit.quote(value);
            return this;
        }

        /**
         * 两侧添加指定字符
         *
         * @param tag
         * @return
         */
        public Builder twoFlanks(final String tag) {
            value = Stringkit.twoFlanks(value, tag);
            return this;
        }

        /**
         * 去掉开头和结尾的空格 {@code '\u0020'}
         *
         * @return
         */
        public Builder trim() {
            value = Stringkit.trim(value);
            return this;
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
         */
        public Builder strip() {
            value = Stringkit.strip(value);
            return this;
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
         */
        public Builder stripAll() {
            value = Stringkit.stripAll(value);
            return this;
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
         */
        public Builder stripStart() {
            value = Stringkit.stripStart(value);
            return this;
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
         */
        public Builder stripEnd() {
            value = Stringkit.stripEnd(value);
            return this;
        }

        /**
         * 返回传入的字符串，如果为 {@code null} 则返回空串
         */
        public Builder defaultStr() {
            value = Stringkit.defaultStr(value);
            return this;
        }

        /**
         * 返回传入的字符串，如果为 {@code null} 则返回默认值
         */
        public Builder defaultIfNull(final String defaultStr) {
            value = Stringkit.defaultIfNull(value, defaultStr);
            return this;
        }

        /**
         * 返回传入的字符串，如果为 {@code null} 或 {@code ""} 则返回默认值
         */
        public Builder defaultIfEmpty(final String defaultStr) {
            value = Stringkit.defaultIfEmpty(value, defaultStr);
            return this;
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
         */
        public Builder defaultIfBlank(String defaultStr) {
            value = Stringkit.defaultIfBlank(value, defaultStr);
            return this;
        }

        /**
         * 获取字符串中最左边的len个字符
         */
        public Builder left(final int len) {
            value = Stringkit.left(value, len);
            return this;
        }

        /**
         * 获取字符串中最右边的len个字符
         */
        public Builder right(final int len) {
            value = Stringkit.right(value, len);
            return this;
        }

        /**
         * 获取字符串中从pos开始len个字符
         */
        public Builder mid(final int pos, final int len) {
            value = Stringkit.mid(value, pos, len);
            return this;
        }

        /**
         * 替换字符
         */
        public Builder replace(final char target, final char replacement) {
            value = Stringkit.replace(value, target, replacement);
            return this;
        }

        /**
         * 替换字符序列
         */
        public Builder replace(final CharSequence target, final CharSequence replacement) {
            value = Stringkit.replace(value, target, replacement);
            return this;
        }

        /**
         * 替换满足指定正则的字符序列
         */
        public Builder replaceAll(final String regex, final String replacement) {
            value = Stringkit.replaceAll(value, regex, replacement);
            return this;
        }

        /**
         * 替换符合的第一个字符序列
         */
        public Builder replaceFirst(final String regex, final String replacement) {
            value = Stringkit.replaceFirst(value, regex, replacement);
            return this;
        }

        /**
         * 去除指定字符序列
         */
        public Builder remove(final CharSequence str) {
            value = Stringkit.remove(value, str);
            return this;
        }

        /**
         * 去除满足指定正则的字符序列
         */
        public Builder removeAll(final String regex) {
            value = Stringkit.removeAll(value, regex);
            return this;
        }

        /**
         * 去除第一个满足指定正则的字符序列
         */
        public Builder removeFirst(final String regex) {
            value = Stringkit.removeFirst(value, regex);
            return this;
        }

        /**
         * 去除最后一个满足指定的字符序列
         */
        public Builder removeLast(final String str) {
            value = Stringkit.removeLast(value, str);
            return this;
        }

        /**
         * 截取最后一个"."后的部分
         */
        public Builder suffix() {
            value = Stringkit.suffix(value);
            return this;
        }

        /**
         * 获取最后一个指定字符后的部分
         */
        public Builder suffix(final char separator) {
            value = Stringkit.suffix(value, separator);
            return this;
        }


        /**
         * 重复一个字符串多次生成一个新字符串
         */
        public Builder repeat(int repeat) {
            value = Stringkit.repeat(value, repeat);
            return this;
        }

        /**
         * 右侧填充空格
         */
        public Builder rpad(final int size) {
            value = Stringkit.rpad(value, size);
            return this;
        }

        /**
         * 右侧填充字符
         */
        public Builder rpad(final int size, final char padChar) {
            value = Stringkit.rpad(value, size, padChar);
            return this;
        }

        /**
         * 右侧填充字符串
         */
        public Builder rpad(final int size, final String padStr) {
            value = Stringkit.rpad(value, size, padStr);
            return this;
        }


        /**
         * 左侧填充空格
         *
         * @param source
         * @param size
         * @return
         */
        public Builder lpad(String source, int size) {
            value = Stringkit.lpad(value, size);
            return this;
        }

        /**
         * 左侧填充字符
         *
         * @param size
         * @param padChar
         * @return
         */
        public Builder lpad(final int size, final char padChar) {
            value = Stringkit.lpad(value, size, padChar);
            return this;
        }

        /**
         * 左侧填充字符串
         *
         * @param size
         * @param padStr
         * @return
         */
        public Builder lpad(final int size, final String padStr) {
            value = Stringkit.lpad(value, size, padStr);
            return this;
        }

        /**
         * 转大写
         *
         * @return
         */
        public Builder upperCase() {
            value = Stringkit.upperCase(value);
            return this;
        }

        /**
         * 转大写
         */
        public Builder upperCase(Locale locale) {
            value = Stringkit.upperCase(value, locale);
            return this;
        }

        /**
         * 转小写
         */
        public Builder lowerCase() {
            value = Stringkit.lowerCase(value);
            return this;
        }

        /**
         * 转小写
         */
        public Builder lowerCase(final Locale locale) {
            value = Stringkit.lowerCase(value, locale);
            return this;
        }

        /**
         * 大小写互换，大写转小写，小写转大写
         */
        public Builder swapCase() {
            value = Stringkit.swapCase(value);
            return this;
        }

        /**
         * 首字母大写
         */
        public Builder capitalize() {
            value = Stringkit.capitalize(value);
            return this;
        }

        /**
         * 首字母小写
         */
        public Builder uncapitalize() {
            value = Stringkit.uncapitalize(value);
            return this;
        }

        /**
         * 下划线命名转驼峰命名，字符串长度需大于2
         */
        public Builder underscoreToCamelCase() {
            value = Stringkit.underscoreToCamelCase(value);
            return this;
        }

        /**
         * 驼峰命名转下划线命名，字符串长度需大于2
         */
        public Builder camelToUnderscoreCase() {
            value = Stringkit.camelToUnderscoreCase(value);
            return this;
        }

        /**
         * 反转字符串
         */
        public Builder reverse() {
            value = Stringkit.reverse(value);
            return this;
        }

        /**
         * 省略字符串
         */
        public Builder abbreviate(final int maxWidth) {
            value = Stringkit.abbreviate(value, maxWidth);
            return this;
        }

        /**
         * 返回规范化的空格参数字符串，删除开头和结尾的空格，然后用单个空格替换空白字符序列。
         */
        public Builder normalizeSpace() {
            value = Stringkit.normalizeSpace(value);
            return this;
        }

        /*---------------------------- 终止操作 ----------------------------*/

        /**
         * 字符序列是否为 {@code null}
         */
        public boolean isNull() {
            return value == null;
        }

        /**
         * 字符序列是否非 {@code null}
         */
        public boolean isNotNull(final CharSequence source) {
            return value != null;
        }

        /**
         * 判断字符序列是否为 {@code null} 或 {@code ""}
         */
        public boolean isEmpty() {
            return Stringkit.isEmpty(value);
        }

        /**
         * 判断字符序列是否不为 {@code null} 或 {@code ""}
         */
        public boolean isNotEmpty() {
            return Stringkit.isNotEmpty(value);
        }

        /**
         * 判断字符序列是否为 {@code null}, {@code ""} 或空格 ({@code SPACE_SEPARATOR},
         * {@code LINE_SEPARATOR}, {@code PARAGRAPH_SEPARATOR})
         */
        public boolean isSpace() {
            return Stringkit.isSpace(value);
        }

        /**
         * 判断字符序列是否不为 {@code null}, {@code ""} 或空格 ({@code SPACE_SEPARATOR},
         * {@code LINE_SEPARATOR}, {@code PARAGRAPH_SEPARATOR})
         */
        public boolean isNotSpace() {
            return Stringkit.isNotSpace(value);
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
         */
        public boolean isBlank() {
            return Stringkit.isBlank(value);
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
         */
        public boolean isNotBlank() {
            return Stringkit.isNotBlank(value);
        }

        /**
         * 判断字符序列是否存在非空白字符
         */
        public boolean hasText() {
            return Stringkit.hasText(value);
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
         */
        public boolean hasWhitespace() {
            return Stringkit.hasWhitespace(value);
        }

        /**
         * 判断字符序列长度是否大于
         */
        public boolean hasLength() {
            return Stringkit.hasLength(value);
        }

        /**
         * 判断是否符合给定谓语表达式
         */
        public boolean test(final Predicate<String> predicate) {
            return Stringkit.test(value, predicate);
        }

        /**
         * 判断目标字符串是否包含指定字符序列
         */
        public boolean contains(final CharSequence x) {
            return Stringkit.contains(value, x);
        }

        /**
         * 是否含有指定字符
         */
        public boolean contains(final char c) {
            return Stringkit.contains(value, c);
        }

        /**
         * 是否匹配正则表达式
         */
        public boolean matches(final String regex) {
            return Stringkit.matches(value, regex);
        }

        /**
         * 以...开始
         */
        public boolean startsWith(final String prefix) {
            return Stringkit.startsWith(value, prefix);
        }

        /**
         * 以...开始（不区分大小写）
         */
        public boolean startsWithIgnoreCase(final String prefix) {
            return Stringkit.startsWithIgnoreCase(value, prefix);
        }

        /**
         * 以...结尾
         */
        public boolean endsWith(final String suffix) {
            return Stringkit.endsWith(value, suffix);
        }

        /**
         * 以...结束（不区分大小写）
         */
        public boolean endsWithIgnoreCase(final String suffix) {
            return Stringkit.endsWithIgnoreCase(value, suffix);
        }

        /**
         * 判断a是否等于b
         */
        public boolean equals(final CharSequence b) {
            return Stringkit.equals(value, b);
        }

        /**
         * 判断a是否等于b，不区分大小写
         */
        public boolean equalsIgnoreCase(final String b) {
            return Stringkit.equalsIgnoreCase(value, b);
        }

        /**
         * 判断a是否不等于b
         */
        public boolean notEquals(final String b) {
            return Stringkit.notEquals(value, b);
        }

        /**
         * 判断a大于b
         */
        public boolean gt(final String b) {
            return Stringkit.gt(value, b);
        }

        /**
         * 判断a小于b
         */
        public boolean lt(final String b) {
            return Stringkit.lt(value, b);
        }

        /**
         * 判断a的长度等于b的长度
         */
        public boolean lengthEq(final CharSequence b) {
            return Stringkit.lengthEq(value, b);
        }

        /**
         * 判断a的长度大于b的长度
         */
        public boolean lengthGt(final CharSequence b) {
            return Stringkit.lengthGt(value, b);
        }

        /**
         * 判断a的长度大于等于b的长度
         */
        public boolean lengthGte(final CharSequence b) {
            return Stringkit.lengthGte(value, b);
        }

        /**
         * 判断a的长度小于b的长度
         */
        public boolean lengthLt(final CharSequence b) {
            return Stringkit.lengthLt(value, b);
        }

        /**
         * 判断a的长度小于等于b的长度
         */
        public boolean lengthLte(final CharSequence b) {
            return Stringkit.lengthLte(value, b);
        }

        /**
         * 字符串转换其他对象
         */
        public <T> Optional<T> map(final Function<String, T> fun) {
            return Stringkit.map(value, fun);
        }

        /**
         * 字符串转换数字
         */
        public <N extends Number> Optional<N> toNumber(final Function<String, N> fun) {
            return Stringkit.toNumber(value, fun);
        }

        /**
         * 将字符串转为字节数组，使用utf-8编码
         */
        public ByteBuffer getByteBuffer() {
            return Stringkit.getByteBuffer(value);
        }

        /**
         * 将字符串转为字节数组
         */
        public ByteBuffer getByteBuffer(final Charset charset) {
            return Stringkit.getByteBuffer(value, charset);
        }

        /**
         * 将字符串转为字节数组，使用utf-8编码
         */
        public byte[] getBytes() {
            return Stringkit.getBytes(value);
        }

        /**
         * 将字符串转为字节数组
         */
        public byte[] getBytes(final Charset charset) {
            return Stringkit.getBytes(value, charset);
        }

        /**
         * 字符串长度
         */
        public int length() {
            return Stringkit.length(value);
        }

        /**
         * 子字符串出现的次数
         */
        public int countMatches(final String sub) {
            return Stringkit.countMatches(value, sub);
        }

        /**
         * 检查字符串是否以指定字符串数组中的任何一个结尾
         */
        public boolean endsWithAny(final String[] searchStrings) {
            return Stringkit.endsWithAny(value, searchStrings);
        }

        /**
         * 判断字符序列的所有字符是否满足给定判断条件
         */
        public boolean isCharPredicate(final Predicate<Character> predicate) {
            return Stringkit.isCharPredicate(value, predicate);
        }

        /**
         * 是否只包含小写字符
         */
        public boolean isLowerCase() {
            return Stringkit.isLowerCase(value);
        }

        /**
         * 是否只包含大写字符
         */
        public boolean isUpperCase() {
            return Stringkit.isUpperCase(value);
        }

        /**
         * 检查字符串是否只包含unicode字母
         */
        public boolean isLetter() {
            return Stringkit.isLetter(value);
        }

        /**
         * 检查字符串是否只包含unicode数字，小数点不是unicode数字并返回 {@code false}
         */
        public boolean isDigit() {
            return Stringkit.isDigit(value);
        }

        /**
         * 检查字符串是否只包含unicode字母或数字
         */
        public boolean isLetterOrDigit() {
            return Stringkit.isLetterOrDigit(value);
        }

        /**
         * 检查字符串是否只包含unicode空格
         */
        public boolean isSpaceChar() {
            return Stringkit.isSpaceChar(value);
        }

        /**
         * 检查字符串是否只包含ASCII可打印字符
         */
        public boolean isAsciiPrintable() {
            return Stringkit.isAsciiPrintable(value);
        }

        /**
         * 检查字符串是否只包含ASCII控制字符
         */
        public boolean isAsciiControl() {
            return Stringkit.isAsciiControl(value);
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
         */
        public boolean isWhitespace() {
            return Stringkit.isWhitespace(value);
        }

        /**
         * 判断字符序列内所有字符是否都在Unicode中有定义
         */
        public boolean isDefined() {
            return Stringkit.isDefined(value);
        }

    }

}
