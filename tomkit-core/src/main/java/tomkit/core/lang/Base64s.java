package tomkit.core.lang;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * base64工具类
 *
 * @author yh
 * @since 2021/3/25
 */
public final class Base64s {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private Base64s() {
    }

    /**
     * base64编码
     *
     * @param src 字节数组
     * @return 编码后字节数组
     */
    public static byte[] encode(byte[] src) {
        if (src.length == 0) {
            return new byte[0];
        }
        return Base64.getEncoder().encode(src);
    }

    /**
     * base64编码
     *
     * @param src 字符串
     * @return 编码后字节数组
     */
    public static byte[] encode(String src) {
        return encode(src.getBytes(DEFAULT_CHARSET));
    }

    /**
     * base64编码
     *
     * @param src     字符串
     * @param charset 字符编码
     * @return 编码后字节数组
     */
    public static byte[] encode(String src, Charset charset) {
        return encode(src.getBytes(charset));
    }

    /**
     * base64解码
     *
     * @param src 字节数组
     * @return 解码后字节数组
     */
    public static byte[] decode(byte[] src) {
        if (src.length == 0) {
            return new byte[0];
        }
        return Base64.getDecoder().decode(src);
    }

    /**
     * base64解码
     *
     * @param src 字符串
     * @return 解码后字节数组
     */
    public static byte[] decode(String src) {
        return decode(src.getBytes(DEFAULT_CHARSET));
    }

    /**
     * base64解码
     *
     * @param src     字符串
     * @param charset 字符编码
     * @return 解码后字节数组
     */
    public static byte[] decode(String src, Charset charset) {
        return decode(src.getBytes(charset));
    }

    /**
     * base64编码
     *
     * @param src 字节数组
     * @return 编码后字符串
     */
    public static String encodeToString(byte[] src) {
        return new String(encode(src), DEFAULT_CHARSET);
    }

    /**
     * base64编码
     *
     * @param src     字符数组
     * @param charset 字符编码
     * @return 编码后字符串
     */
    public static String encodeToString(byte[] src, Charset charset) {
        return new String(encode(src), charset);
    }

    /**
     * base64编码
     *
     * @param src 字符串
     * @return 解码后字符串
     */
    public static String encodeToString(String src) {
        return new String(encode(src.getBytes(DEFAULT_CHARSET)), DEFAULT_CHARSET);
    }

    /**
     * base64编码
     *
     * @param src     字符串
     * @param charset 字符格式
     * @return 解码后字符串
     */
    public static String encodeToString(String src, Charset charset) {
        return new String(encode(src.getBytes(charset)), charset);
    }

    /**
     * base64解码
     *
     * @param src 字节数组
     * @return 解码后字符串
     */
    public static String decodeToString(byte[] src) {
        return new String(decode(src), DEFAULT_CHARSET);
    }

    /**
     * base64解码
     *
     * @param src     字节数组
     * @param charset 字符编码
     * @return 解码后字符串
     */
    public static String decodeToString(byte[] src, Charset charset) {
        return new String(decode(src), charset);
    }

    /**
     * base64解码
     *
     * @param src 字符串
     * @return 解码后字符串
     */
    public static String decodeToString(String src) {
        return new String(decode(src.getBytes(DEFAULT_CHARSET)), DEFAULT_CHARSET);
    }

    /**
     * base64解码
     *
     * @param src     字符串
     * @param charset 字符编码
     * @return 解码后字符串
     */
    public static String decodeToString(String src, Charset charset) {
        return new String(decode(src.getBytes(charset)), charset);
    }

    /**
     * url base64编码
     *
     * @param src 字节数组
     * @return 解码后字节数组
     */
    public static byte[] encodeUrlSafe(byte[] src) {
        if (src.length == 0) {
            return src;
        }
        return Base64.getUrlEncoder().encode(src);
    }

    /**
     * url base64解码
     *
     * @param src 字节数组
     * @return 解码后字节数组
     */
    public static byte[] decodeUrlSafe(byte[] src) {
        if (src.length == 0) {
            return src;
        }
        return Base64.getUrlDecoder().decode(src);
    }

    /**
     * url base64编码
     *
     * @param src 字节数组
     * @return 解码后字节数组
     */
    public static byte[] encodeUrlSafe(String src) {
        return encodeUrlSafe(src.getBytes(DEFAULT_CHARSET));
    }

    /**
     * url base64编码
     *
     * @param src     字节数组
     * @param charset 字符编码
     * @return 解码后字节数组
     */
    public static byte[] encodeUrlSafe(String src, Charset charset) {
        return encodeUrlSafe(src.getBytes(charset));
    }

    /**
     * url base64解码
     *
     * @param src 编码字符串
     * @return 解码后字节数组
     */
    public static byte[] decodeUrlSafe(String src) {
        return decodeUrlSafe(src.getBytes(DEFAULT_CHARSET));
    }

    /**
     * url base64解码
     *
     * @param src     编码字符串
     * @param charset 字符编码
     * @return 解码后字节数组
     */
    public static byte[] decodeUrlSafe(String src, Charset charset) {
        return decodeUrlSafe(src.getBytes(charset));
    }

    /**
     * url base编码
     *
     * @param src 字节数组
     * @return 编码后字符串
     */
    public static String encodeUrlSafeToString(byte[] src) {
        return new String(encodeUrlSafe(src), DEFAULT_CHARSET);
    }

    /**
     * url base编码
     *
     * @param src 字符串
     * @return 编码后字符串
     */
    public static String encodeUrlSafeToString(String src) {
        return new String(encodeUrlSafe(src.getBytes(DEFAULT_CHARSET)), DEFAULT_CHARSET);
    }

    /**
     * url base编码
     *
     * @param src     字符串
     * @param charset 字符编码
     * @return 编码后字符串
     */
    public static String encodeUrlSafeToString(String src, Charset charset) {
        return new String(encodeUrlSafe(src.getBytes(charset)), charset);
    }

    /**
     * url base64解码
     *
     * @param src 编码字节数组
     * @return 解码后字符串
     */
    public static String decodeUrlSafeToString(byte[] src) {
        return new String(decodeUrlSafe(src), DEFAULT_CHARSET);
    }

    /**
     * url base64解码
     *
     * @param src 字符串
     * @return 解码后字符串
     */
    public static String decodeUrlSafeToString(String src) {
        return new String(decodeUrlSafe(src.getBytes(DEFAULT_CHARSET)), DEFAULT_CHARSET);
    }

    /**
     * url base64解码
     *
     * @param src     字符串
     * @param charset 字符编码
     * @return 解码后字符串
     */
    public static String decodeUrlSafeToString(String src, Charset charset) {
        return new String(decodeUrlSafe(src.getBytes(charset)), charset);
    }

    /**
     * 对输出流进行base64编码包装
     * <p>
     * 输出流的close方法调用时会填充
     * <pre>
     *     String src = "0123456789";
     *     try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
     *          OutputStream os = Base64s.encodeWarp(outputStream)) {
     *         os.write(src.getBytes(StandardCharsets.UTF_8));
     *         // 当源字符串长度不能被3整除时，调用包装流close方法后才会填充最后几位，可查看{@link Base64.EncOutputStream#close()}方法的实现
     *         os.close();
     *         System.out.println(outputStream.toString());   // MDEyMzQ1Njc4OQ==
     *     }
     * </pre>
     *
     * @param outputStream 输出流
     * @return 编码包装后的输出流
     * @see Base64.Encoder#wrap(OutputStream)
     * @see Base64.EncOutputStream#close()
     */
    public static OutputStream encodeWarp(OutputStream outputStream) {
        return Base64.getEncoder().wrap(outputStream);
    }

    /**
     * 对输入流进行base64解码包装
     * <pre>
     *     String text = "MDEyMzQ1Njc4OQ==";
     *     try (InputStream in = new ByteArrayInputStream(text.getBytes())) {
     *         System.out.println(IOStreams.copyToString(Base64s.decodeWarp(in)));  // 0123465789
     *     }
     * </pre>
     *
     * @param inputStream 输入流
     * @return 解码包装后的输入流
     * @see Base64.Decoder#wrap(InputStream)
     */
    public static InputStream decodeWarp(InputStream inputStream) {
        return Base64.getDecoder().wrap(inputStream);
    }

}
