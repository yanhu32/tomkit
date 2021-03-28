package tomkit.core.lang;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.nio.charset.StandardCharsets.*;

/**
 * @author yh
 * @since 2021/3/26
 */
public class Charsets {

    public static final Charset SYSTEM_DEFAULT_CHARSET = Charset.defaultCharset();

    private static final SortedMap<String, Charset> STANDARD_CHARSET_MAP;

    static {
        final SortedMap<String, Charset> standardCharsetMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        standardCharsetMap.put(ISO_8859_1.name(), ISO_8859_1);
        standardCharsetMap.put(US_ASCII.name(), US_ASCII);
        standardCharsetMap.put(UTF_16.name(), UTF_16);
        standardCharsetMap.put(UTF_16BE.name(), UTF_16BE);
        standardCharsetMap.put(UTF_16LE.name(), UTF_16LE);
        standardCharsetMap.put(UTF_8.name(), UTF_8);
        STANDARD_CHARSET_MAP = Collections.unmodifiableSortedMap(standardCharsetMap);
    }

    /**
     * 构造一个从规范字符集名称到每个Java平台实现所需的字符集对象的有序映射
     *
     * @return 从规范字符集名称到字符集对象的不可变、不区分大小写的映射
     * @see Charset#availableCharsets()
     */
    public static SortedMap<String, Charset> requiredCharsets() {
        return STANDARD_CHARSET_MAP;
    }

    /**
     * 判断传入字符编码是否为null，返回传入的字符编码或指定的默认字符编码
     *
     * @param charset        字符编码
     * @param defaultCharset 默认字符编码
     * @return 传入的字符编码或默认字符编码
     */
    public static Charset defaultIfNull(final Charset charset, final Charset defaultCharset) {
        return null != charset ? charset : defaultCharset;
    }

    /**
     * 判断传入字符编码名称是否为null，返回传入的字符编码或指定的默认字符编码
     *
     * @param charsetName    字符编码名称
     * @param defaultCharset 默认字符编码
     * @return 传入的字符编码或默认字符编码
     */
    public static Charset defaultIfNull(final String charsetName, final Charset defaultCharset) {
        return null != charsetName ? Charset.forName(charsetName) : defaultCharset;
    }

    /**
     * 判断传入字符编码是否为null，返回传入的字符编码或系统默认字符编码
     *
     * @param charset 字符编码
     * @return 传入的字符编码或默认字符编码
     */
    public static Charset defaultIfNull(final Charset charset) {
        return null != charset ? charset : SYSTEM_DEFAULT_CHARSET;
    }

    /**
     * 判断传入字符编码名称是否为null，返回传入的字符编码或系统默认字符编码
     *
     * @param charsetName 字符编码名称
     * @return 传入的字符编码或默认字符编码
     */
    public static Charset defaultIfNull(final String charsetName) {
        return null != charsetName ? Charset.forName(charsetName) : SYSTEM_DEFAULT_CHARSET;
    }

    /**
     * 判断传入字符编码是否为null，返回传入的字符编码或UTF-8编码
     *
     * @param charset 字符编码
     * @return 传入的字符编码或默认字符编码
     */
    public static Charset utf8IfNull(final Charset charset) {
        return null != charset ? charset : UTF_8;
    }

    /**
     * 判断传入字符编码名称是否为null，返回传入的字符编码或UTF-8编码
     *
     * @param charsetName 字符编码名称
     * @return 传入的字符编码或默认字符编码
     */
    public static Charset utf8IfNull(final String charsetName) {
        return null != charsetName ? Charset.forName(charsetName) : UTF_8;
    }

    /**
     * 判断给定字符编码名称是否合法
     *
     * @param charsetName 字符编码名称
     * @return 字符编码名称是否合法
     */
    public static boolean isCharset(String charsetName) {
        if (null == charsetName) {
            return false;
        }
        try {
            Charset.forName(charsetName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
