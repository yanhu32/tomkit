package tomkit.core.io;

import tomkit.core.lang.Assert;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author yh
 * @since 2021/1/29
 */
public class Streams {
    /**
     * 复制字节时使用的默认缓冲区大小
     */
    public static final int BUFFER_SIZE = 4096;
    /**
     * 空的字节数组
     */
    private static final byte[] EMPTY_CONTENT = new byte[0];

    /**
     * 将给定 {@link InputStream} 的内容复制到一个新的字节数组中
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
     * 将给定 {@link InputStream} 的内容复制到一个字符串中
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

        StringBuilder out = new StringBuilder(BUFFER_SIZE);
        InputStreamReader reader = new InputStreamReader(in, charset);
        char[] buffer = new char[BUFFER_SIZE];
        int charsRead;
        while ((charsRead = reader.read(buffer)) != -1) {
            out.append(buffer, 0, charsRead);
        }
        return out.toString();
    }

    /**
     * 将给定的 {@link ByteArrayOutputStream} 的内容复制到 {@link String} 中
     * <p>不对输出流做关闭处理
     *
     * @param outputStream 要复制到字符串中的 {@code ByteArrayOutputStream}
     * @param charset      用来解码字节的 {@link Charset}
     * @return 被复制到(可能为空)的字符串
     */
    public static String copyToString(ByteArrayOutputStream outputStream, Charset charset) {
        Assert.notNull(outputStream, "No ByteArrayOutputStream specified");
        Assert.notNull(charset, "No Charset specified");
        return new String(outputStream.toByteArray(), charset);
    }

    /**
     * 将给定字节数组的内容复制到给定的OutputStream
     * <p>不对输出流做关闭处理
     *
     * @param in  要从中复制的字节数组
     * @param out 要复制到的输出流
     * @throws IOException 发生I/O错误时
     */
    public static void copy(byte[] in, OutputStream out) throws IOException {
        Assert.notNull(in, "No input byte array specified");
        Assert.notNull(out, "No OutputStream specified");

        out.write(in);
        out.flush();
    }

    /**
     * 将给定字符串的内容复制到给定的OutputStream
     * <p>不对流做关闭处理
     *
     * @param in      要复制的字符串
     * @param charset 字节编码
     * @param out     要复制到的输出流
     * @throws IOException 发生I/O错误时
     */
    public static void copy(String in, Charset charset, OutputStream out) throws IOException {
        Assert.notNull(in, "No input String specified");
        Assert.notNull(charset, "No Charset specified");
        Assert.notNull(out, "No OutputStream specified");

        Writer writer = new OutputStreamWriter(out, charset);
        writer.write(in);
        writer.flush();
    }

    /**
     * 将给定 {@link InputStream} 的内容复制到给定 {@link OutputStream}
     * <p>不对流做关闭处理
     *
     * @param in  要复制的输入流
     * @param out 要复制到的输出流
     * @return 复制的字节数
     * @throws IOException 发生I/O错误时
     */
    public static int copy(InputStream in, OutputStream out) throws IOException {
        Assert.notNull(in, "No InputStream specified");
        Assert.notNull(out, "No OutputStream specified");

        int byteCount = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            byteCount += bytesRead;
        }
        out.flush();
        return byteCount;
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
        Assert.notNull(in, "No InputStream specified");
        Assert.notNull(out, "No OutputStream specified");

        long skipped = in.skip(start);
        if (skipped < start) {
            throw new IOException("Skipped only " + skipped + " bytes out of " + start + " required");
        }

        long bytesToCopy = end - start + 1;
        byte[] buffer = new byte[(int) Math.min(Streams.BUFFER_SIZE, bytesToCopy)];
        while (bytesToCopy > 0) {
            int bytesRead = in.read(buffer);
            if (bytesRead == -1) {
                break;
            } else if (bytesRead <= bytesToCopy) {
                out.write(buffer, 0, bytesRead);
                bytesToCopy -= bytesRead;
            } else {
                out.write(buffer, 0, (int) bytesToCopy);
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
        Assert.notNull(in, "No InputStream specified");
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        int byteCount = 0;
        while ((bytesRead = in.read(buffer)) != -1) {
            byteCount += bytesRead;
        }
        return byteCount;
    }

    /**
     * 返回一个有效的空 {@link InputStream}.
     *
     * @return 一个基于空字节数组的 {@link ByteArrayInputStream}
     */
    public static InputStream emptyInput() {
        return new ByteArrayInputStream(EMPTY_CONTENT);
    }

    /**
     * 返回给定的 {@link InputStream} 的变体，其中调用 {@link InputStream#close()} 不起作用
     *
     * @param in 要装饰的输入流
     * @return 忽略close调用的InputStream版本
     */
    public static InputStream nonClosing(InputStream in) {
        Assert.notNull(in, "No InputStream specified");
        return new NonClosingInputStream(in);
    }

    /**
     * 返回给定的 {@link OutputStream} 的变体，其中调用 {@link OutputStream#close()} 不起作用
     *
     * @param out 要装饰的输出流
     * @return 忽略close调用的OutputStream版本
     */
    public static OutputStream nonClosing(OutputStream out) {
        Assert.notNull(out, "No OutputStream specified");
        return new NonClosingOutputStream(out);
    }


    private static class NonClosingInputStream extends FilterInputStream {

        public NonClosingInputStream(InputStream in) {
            super(in);
        }

        @Override
        public void close() throws IOException {
        }
    }


    private static class NonClosingOutputStream extends FilterOutputStream {

        public NonClosingOutputStream(OutputStream out) {
            super(out);
        }

        @Override
        public void write(byte[] b, int off, int let) throws IOException {
            // 为了提高性能，必须重写这个方法
            this.out.write(b, off, let);
        }

        @Override
        public void close() throws IOException {
        }
    }

}
