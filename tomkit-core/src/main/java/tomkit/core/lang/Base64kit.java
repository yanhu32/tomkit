package tomkit.core.lang;

import tomkit.core.io.IOStreams;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * base64工具类
 *
 * @author yh
 * @since 2021/3/25
 */
public final class Base64kit {

    private Base64kit() {
    }

    /**
     * base64编码
     *
     * @param src 字节数组
     * @return 编码后字节数组
     */
    public static byte[] encode(final byte[] src) {
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
    public static byte[] encode(final String src) {
        return encode(src.getBytes(Charsets.SYSTEM_DEFAULT_CHARSET));
    }

    /**
     * base64编码
     *
     * @param src     字符串
     * @param charset 字符编码
     * @return 编码后字节数组
     */
    public static byte[] encode(final String src, final Charset charset) {
        return encode(src.getBytes(charset));
    }

    /**
     * 将输入流内容进行base64编码
     *
     * @param inputStream 输入流
     * @return 流内容的base64编码
     * @throws IOException 如果发生I/O错误
     */
    public static byte[] encode(final InputStream inputStream) throws IOException {
        return encode(IOStreams.toByteArray(inputStream));
    }

    /**
     * 将阅读器内容进行base46编码
     *
     * @param reader  阅读器
     * @param charset 字符编码
     * @return 流内容的base64编码
     * @throws IOException 如果发生I/O错误
     */
    public static byte[] encode(final Reader reader, final Charset charset) throws IOException {
        return encode(IOStreams.toByteArray(reader, charset));
    }

    /**
     * base64解码
     *
     * @param src 字节数组
     * @return 解码后字节数组
     */
    public static byte[] decode(final byte[] src) {
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
    public static byte[] decode(final String src) {
        return decode(src.getBytes(Charsets.SYSTEM_DEFAULT_CHARSET));
    }

    /**
     * base64解码
     *
     * @param src     字符串
     * @param charset 字符编码
     * @return 解码后字节数组
     */
    public static byte[] decode(final String src, final Charset charset) {
        return decode(src.getBytes(charset));
    }

    /**
     * 将输入流内容进行base64解码
     *
     * @param inputStream 输入流
     * @return 流内容的base64解码
     * @throws IOException 如果发生I/O错误
     */
    public static byte[] decode(final InputStream inputStream) throws IOException {
        return decode(IOStreams.toByteArray(inputStream));
    }

    /**
     * 将阅读器内容进行base46解码
     *
     * @param reader  阅读器
     * @param charset 字符编码
     * @return 流内容的base64解码
     * @throws IOException 如果发生I/O错误
     */
    public static byte[] decode(final Reader reader, final Charset charset) throws IOException {
        return decode(IOStreams.toByteArray(reader, charset));
    }

    /**
     * base64编码
     *
     * @param src 字节数组
     * @return 编码后字符串
     */
    public static String encodeToString(final byte[] src) {
        return new String(encode(src), Charsets.SYSTEM_DEFAULT_CHARSET);
    }

    /**
     * base64编码
     *
     * @param src     字符数组
     * @param charset 字符编码
     * @return 编码后字符串
     */
    public static String encodeToString(final byte[] src, final Charset charset) {
        return new String(encode(src), charset);
    }

    /**
     * base64编码
     *
     * @param src 字符串
     * @return 解码后字符串
     */
    public static String encodeToString(final String src) {
        return new String(encode(src.getBytes(Charsets.SYSTEM_DEFAULT_CHARSET)), Charsets.SYSTEM_DEFAULT_CHARSET);
    }

    /**
     * base64编码
     *
     * @param src     字符串
     * @param charset 字符编码
     * @return 解码后字符串
     */
    public static String encodeToString(final String src, final Charset charset) {
        return new String(encode(src.getBytes(charset)), charset);
    }

    /**
     * 将输入流内容进行base64编码
     *
     * @param inputStream 输入流
     * @param charset     字符编码
     * @return 流内容的base64编码
     * @throws IOException 如果发生I/O错误
     */
    public static String encodeToString(final InputStream inputStream, final Charset charset) throws IOException {
        return new String(encode(IOStreams.toByteArray(inputStream)), charset);
    }

    /**
     * 将阅读器内容进行base46编码
     *
     * @param reader  阅读器
     * @param charset 字符编码
     * @return 流内容的base64编码
     * @throws IOException 如果发生I/O错误
     */
    public static String encodeToString(final Reader reader, final Charset charset) throws IOException {
        return new String(encode(IOStreams.toByteArray(reader, charset)), charset);
    }

    /**
     * base64解码
     *
     * @param src 字节数组
     * @return 解码后字符串
     */
    public static String decodeToString(final byte[] src) {
        return new String(decode(src), Charsets.SYSTEM_DEFAULT_CHARSET);
    }

    /**
     * base64解码
     *
     * @param src     字节数组
     * @param charset 字符编码
     * @return 解码后字符串
     */
    public static String decodeToString(final byte[] src, final Charset charset) {
        return new String(decode(src), charset);
    }

    /**
     * base64解码
     *
     * @param src 字符串
     * @return 解码后字符串
     */
    public static String decodeToString(final String src) {
        return new String(decode(src.getBytes(Charsets.SYSTEM_DEFAULT_CHARSET)), Charsets.SYSTEM_DEFAULT_CHARSET);
    }

    /**
     * base64解码
     *
     * @param src     字符串
     * @param charset 字符编码
     * @return 解码后字符串
     */
    public static String decodeToString(final String src, final Charset charset) {
        return new String(decode(src.getBytes(charset)), charset);
    }

    /**
     * 将输入流内容进行base64解码
     *
     * @param inputStream 输入流
     * @param charset     字符编码
     * @return 流内容的base64解码
     * @throws IOException 如果发生I/O错误
     */
    public static String decodeToString(final InputStream inputStream, final Charset charset) throws IOException {
        return new String(decode(IOStreams.toByteArray(inputStream)), charset);
    }

    /**
     * 将阅读器内容进行base46解码
     *
     * @param reader  阅读器
     * @param charset 字符编码
     * @return 流内容的base64解码
     * @throws IOException 如果发生I/O错误
     */
    public static String decodeToString(final Reader reader, final Charset charset) throws IOException {
        return new String(decode(IOStreams.toByteArray(reader, charset)), charset);
    }

    /**
     * 将字节数组的base64编码内容转换为输入流
     *
     * @param src 字节数组
     * @return base64编码内容的输入流
     */
    public static InputStream encodeToInputStream(byte[] src) {
        return new ByteArrayInputStream(encode(src));
    }

    /**
     * 将字符串的base64编码内容转换为输入流
     *
     * @param src     字符串
     * @param charset 字符编码
     * @return base64编码内容的输入流
     */
    public static InputStream encodeToInputStream(String src, Charset charset) {
        return new ByteArrayInputStream(encode(src.getBytes(charset)));
    }

    /**
     * 将字节数组的base64解码内容转换为输入流
     *
     * @param src 字节数组
     * @return base64解码内容的输入流
     */
    public static InputStream decodeToInputStream(byte[] src) {
        return new ByteArrayInputStream(decode(src));
    }

    /**
     * 将字符串的base64解码内容转换为输入流
     *
     * @param src     字符串
     * @param charset 字符编码
     * @return base64解码内容的输入流
     */
    public static InputStream decodeToInputStream(String src, Charset charset) {
        return new ByteArrayInputStream(decode(src.getBytes(charset)));
    }

    /**
     * 将字节数组的base64编码内容写入输出流
     *
     * @param src          字节数组
     * @param outputStream 输出流
     * @throws IOException 如果发生I/O错误
     */
    public static void encodeToOutputStream(byte[] src, OutputStream outputStream) throws IOException {
        outputStream.write(encode(src));
        outputStream.flush();
    }

    /**
     * 将字符串的base64编码内容写入输出流
     *
     * @param src          字符串
     * @param charset      字符编码
     * @param outputStream 输出流
     * @throws IOException 如果发生I/O错误
     */
    public static void encodeToOutputStream(String src, Charset charset, OutputStream outputStream) throws IOException {
        outputStream.write(encode(src.getBytes(charset)));
        outputStream.flush();
    }

    /**
     * 将字节数组的base64解码内容写入输出流
     *
     * @param src          字节数组
     * @param outputStream 输出流
     * @throws IOException 如果发生I/O错误
     */
    public static void decodeToOutputStream(byte[] src, OutputStream outputStream) throws IOException {
        outputStream.write(encode(src));
        outputStream.flush();
    }

    /**
     * 将字符串的base64解码内容写入输出流
     *
     * @param src          字符串
     * @param charset      字符编码
     * @param outputStream 输出流
     * @throws IOException 如果发生I/O错误
     */
    public static void decodeToOutputStream(String src, Charset charset, OutputStream outputStream) throws IOException {
        outputStream.write(encode(src.getBytes(charset)));
        outputStream.flush();
    }

    /**
     * url base64编码
     *
     * @param src 字节数组
     * @return 解码后字节数组
     */
    public static byte[] encodeUrlSafe(final byte[] src) {
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
    public static byte[] decodeUrlSafe(final byte[] src) {
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
    public static byte[] encodeUrlSafe(final String src) {
        return encodeUrlSafe(src.getBytes(Charsets.SYSTEM_DEFAULT_CHARSET));
    }

    /**
     * url base64编码
     *
     * @param src     字节数组
     * @param charset 字符编码
     * @return 解码后字节数组
     */
    public static byte[] encodeUrlSafe(final String src, final Charset charset) {
        return encodeUrlSafe(src.getBytes(charset));
    }

    /**
     * url base64解码
     *
     * @param src 编码字符串
     * @return 解码后字节数组
     */
    public static byte[] decodeUrlSafe(final String src) {
        return decodeUrlSafe(src.getBytes(Charsets.SYSTEM_DEFAULT_CHARSET));
    }

    /**
     * url base64解码
     *
     * @param src     编码字符串
     * @param charset 字符编码
     * @return 解码后字节数组
     */
    public static byte[] decodeUrlSafe(final String src, final Charset charset) {
        return decodeUrlSafe(src.getBytes(charset));
    }

    /**
     * url base编码
     *
     * @param src 字节数组
     * @return 编码后字符串
     */
    public static String encodeUrlSafeToString(final byte[] src) {
        return new String(encodeUrlSafe(src), Charsets.SYSTEM_DEFAULT_CHARSET);
    }

    /**
     * url base编码
     *
     * @param src 字符串
     * @return 编码后字符串
     */
    public static String encodeUrlSafeToString(final String src) {
        return new String(encodeUrlSafe(src.getBytes(Charsets.SYSTEM_DEFAULT_CHARSET)), Charsets.SYSTEM_DEFAULT_CHARSET);
    }

    /**
     * url base编码
     *
     * @param src     字符串
     * @param charset 字符编码
     * @return 编码后字符串
     */
    public static String encodeUrlSafeToString(final String src, final Charset charset) {
        return new String(encodeUrlSafe(src.getBytes(charset)), charset);
    }

    /**
     * url base64解码
     *
     * @param src 编码字节数组
     * @return 解码后字符串
     */
    public static String decodeUrlSafeToString(final byte[] src) {
        return new String(decodeUrlSafe(src), Charsets.SYSTEM_DEFAULT_CHARSET);
    }

    /**
     * url base64解码
     *
     * @param src 字符串
     * @return 解码后字符串
     */
    public static String decodeUrlSafeToString(final String src) {
        return new String(decodeUrlSafe(src.getBytes(Charsets.SYSTEM_DEFAULT_CHARSET)), Charsets.SYSTEM_DEFAULT_CHARSET);
    }

    /**
     * url base64解码
     *
     * @param src     字符串
     * @param charset 字符编码
     * @return 解码后字符串
     */
    public static String decodeUrlSafeToString(final String src, final Charset charset) {
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
     *         // 当源字符串长度不能被3整除时，调用包装流close方法后才会填充最后几位，可查看{@code Base64.EncOutputStream#close()}方法的实现
     *         os.close();
     *         System.out.println(outputStream.toString());   // MDEyMzQ1Njc4OQ==
     *     }
     * </pre>
     *
     * @param outputStream 输出流
     * @return 编码包装后的输出流
     * @see Base64.Encoder#wrap(OutputStream)
     */
    public static OutputStream encodeWarp(final OutputStream outputStream) {
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
    public static InputStream decodeWarp(final InputStream inputStream) {
        return Base64.getDecoder().wrap(inputStream);
    }

}
