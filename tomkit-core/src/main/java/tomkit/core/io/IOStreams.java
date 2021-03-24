package tomkit.core.io;

import tomkit.core.lang.Assert;

import java.io.*;
import java.nio.charset.Charset;

/**
 * I/O流工具类
 *
 * @author yh
 * @since 2021/1/29
 */
public final class IOStreams {

    /**
     * 复制字节时使用的默认缓冲区大小
     */
    public static final int BUFFER_SIZE = 4096;
    /**
     * 空的字节数组
     */
    private static final byte[] EMPTY_CONTENT = new byte[0];

    private IOStreams() {
    }

    /**
     * 将给定输入流的内容复制到一个新的字节数组中
     * <p>不对输入流做关闭处理
     *
     * @param in 要复制的流(可能是{@code null}或空)
     * @return 已复制到(可能为空)的新字节数组
     * @throws IOException 发生I/O错误时
     */
    public static byte[] copyToByteArray(InputStream in) throws IOException {
        if (in == null) {
            return new byte[0];
        }

        try (ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE)) {
            copy(in, out);
            return out.toByteArray();
        }
    }

    /**
     * 将给定输入流的内容复制到一个字符串中
     * <p>不对输入流做关闭处理
     *
     * @param in      要复制的流(可能是{@code null}或空)
     * @param charset 用来解码字节的 {@link Charset}
     * @return 被复制到(可能为空)的字符串
     * @throws IOException 发生I/O错误时
     */
    public static String copyToString(InputStream in, Charset charset) throws IOException {
        if (in == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder(BUFFER_SIZE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, charset));
        char[] buffer = new char[BUFFER_SIZE];
        int readLen;
        while ((readLen = reader.read(buffer)) != -1) {
            builder.append(buffer, 0, readLen);
        }
        return builder.toString();
    }

    /**
     * 将给定的字节输出流的内容复制到字符串中
     * <p>不对输出流做关闭处理
     *
     * @param outputStream 要复制到字符串中的 {@code ByteArrayOutputStream}
     * @param charset      用来解码字节的 {@link Charset}
     * @return 被复制到(可能为空)的字符串
     */
    public static String copyToString(ByteArrayOutputStream outputStream, Charset charset) {
        Assert.notNull(outputStream, "字节输出流不能为空");
        Assert.notNull(charset, "字符编码不能为空");

        return new String(outputStream.toByteArray(), charset);
    }

    /**
     * 将给定字节数组的内容复制到给定的输出流
     * <p>不对输出流做关闭处理
     *
     * @param in  要从中复制的字节数组
     * @param out 要复制到的输出流
     * @throws IOException 发生I/O错误时
     */
    public static void copy(byte[] in, OutputStream out) throws IOException {
        Assert.notNull(in, "输入字节数组不能为空");
        Assert.notNull(out, "输出流不能为空");

        out.write(in);
        out.flush();
    }

    /**
     * 将给定字符串的内容复制到给定的输出流
     * <p>不对流做关闭处理
     *
     * @param in      要复制的字符串
     * @param charset 字节编码
     * @param out     要复制到的输出流
     * @throws IOException 发生I/O错误时
     */
    public static void copy(String in, Charset charset, OutputStream out) throws IOException {
        Assert.notNull(in, "输入字符串不能为空");
        Assert.notNull(charset, "字符编码不能为空");
        Assert.notNull(out, "输出流不能为空");

        Writer writer = new OutputStreamWriter(out, charset);
        writer.write(in);
        writer.flush();
    }

    /**
     * 将给定输入流的内容复制到给定输出流
     * <p>
     * 不对流做关闭处理
     *
     * @param in  要复制的输入流
     * @param out 要复制到的输出流
     * @return 复制的字节数
     * @throws IOException 发生I/O错误时
     */
    public static long copy(InputStream in, OutputStream out) throws IOException {
        return copy(in, out, BUFFER_SIZE);
    }

    /**
     * 将给定输入流的内容复制到给定输出流
     * <p>
     * 不对流做关闭处理
     *
     * @param in         要复制的输入流
     * @param out        要复制到的输出流
     * @param bufferSize 用于复制的字节数组长度
     * @return 复制的字节数
     * @throws IOException 发生I/O错误时
     */
    public static long copy(InputStream in, OutputStream out, int bufferSize) throws IOException {
        Assert.notNull(in, "输入流不能为空");
        Assert.notNull(out, "输出流不能为空");

        final byte[] buffer = new byte[bufferSize];
        long count = 0;
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
            count += read;
        }
        out.flush();
        return count;
    }

    /**
     * 将给定输入流的内容复制到给定文件内
     * <p>不对输入流做关闭处理
     *
     * @param in      输入流
     * @param outFile 目标文件
     * @return 复制的字节数
     * @throws IOException 发生I/O错误时
     */
    public static long copy(InputStream in, File outFile) throws IOException {
        Assert.notNull(in, "输入流不能为空");
        Assert.notNull(outFile, "目标文件不能为空");

        createParentFile(outFile);

        try (OutputStream out = new FileOutputStream(outFile)) {
            return copy(in, out);
        }
    }

    /**
     * 创建文件父目录
     *
     * @param file 给定文件
     */
    private static void createParentFile(File file) {
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            throw new RuntimeException("创建文件父目录失败 file:" + file.getAbsolutePath());
        }
    }

    /**
     * 将给定文件内容复制给输出流
     * <p>
     * 不对输出流做关闭处理
     *
     * @param inFile 输入文件
     * @param out    输出流
     * @return 复制的字节数
     * @throws IOException 发生I/O异常时
     */
    public static long copy(File inFile, OutputStream out) throws IOException {
        Assert.notNull(inFile, "输入文件不能为空");
        Assert.notNull(out, "输出流不能为空");

        if (!inFile.exists()) {
            throw new RuntimeException("输入文件不存在 file:" + inFile.getAbsolutePath());
        }

        try (InputStream in = new FileInputStream(inFile)) {
            return copy(in, out);
        }
    }

    /**
     * 将字符输入流复制给字符输出流
     * <p>
     * 不对流做关闭处理
     *
     * @param reader
     * @param writer
     * @return
     * @throws IOException
     */
    public static long copy(Reader reader, Writer writer) throws IOException {
        return copy(reader, writer, BUFFER_SIZE / 2);
    }

    /**
     * 将字符输入流复制给字符输出流
     * <p>
     * 不对流做关闭处理
     *
     * @param reader
     * @param writer
     * @param bufferSize
     * @return
     * @throws IOException
     */
    public static long copy(Reader reader, Writer writer, int bufferSize) throws IOException {
        Assert.notNull(reader, "字符输入流不能为空");
        Assert.notNull(writer, "字符输出流不能为空");

        final char[] buffer = new char[bufferSize];
        long count = 0;
        int n;
        while (-1 != (n = reader.read(buffer))) {
            writer.write(buffer, 0, n);
            count += n;
        }
        writer.flush();
        return count;
    }

    /**
     * 将给定InputStream的内容范围复制到给定OutputStream
     * <p>如果指定的范围超过了InputStream的长度，则复制到流的末尾，并返回实际复制的字节数
     * <p>不对流做关闭处理
     *
     * @param in    要复制的InputStream
     * @param out   要复制到的输出流
     * @param start 开始复制的位置
     * @param end   结束复制的位置
     * @return 复制的字节数
     * @throws IOException 发生I/O错误时
     */
    public static long copyRange(InputStream in, OutputStream out, long start, long end) throws IOException {
        Assert.notNull(in, "输入流不能为空");
        Assert.notNull(out, "输出流不能为空");

        long skipped = in.skip(start);
        if (skipped < start) {
            throw new IOException("Skipped only " + skipped + " bytes out of " + start + " required");
        }

        BufferedInputStream bin = new BufferedInputStream(in);
        BufferedOutputStream bout = new BufferedOutputStream(out);

        long bytesToCopy = end - start + 1;
        byte[] buffer = new byte[(int) Math.min(IOStreams.BUFFER_SIZE, bytesToCopy)];
        while (bytesToCopy > 0) {
            int bytesRead = bin.read(buffer);
            if (bytesRead == -1) {
                break;
            } else if (bytesRead <= bytesToCopy) {
                bout.write(buffer, 0, bytesRead);
                bytesToCopy -= bytesRead;
            } else {
                bout.write(buffer, 0, (int) bytesToCopy);
                bytesToCopy = 0;
            }
        }
        return (end - start + 1 - bytesToCopy);
    }

    /**
     * 排干给定InputStream的剩余内容
     * <p>不对流做关闭处理
     *
     * @param in 要排干的输入流
     * @return 读取的字节数
     * @throws IOException 发生I/O错误时
     */
    public static int drain(InputStream in) throws IOException {
        Assert.notNull(in, "输入流不能为空");

        byte[] buffer = new byte[BUFFER_SIZE];
        int readLen;
        int count = 0;
        while ((readLen = in.read(buffer)) != -1) {
            count += readLen;
        }
        return count;
    }

    /**
     * 返回一个有效的空输入流
     *
     * @return 一个基于空字节数组的 {@link ByteArrayInputStream}
     */
    public static InputStream emptyInput() {
        return new ByteArrayInputStream(EMPTY_CONTENT);
    }

}
