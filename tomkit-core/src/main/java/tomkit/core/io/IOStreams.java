package tomkit.core.io;

import tomkit.core.lang.Arrays;
import tomkit.core.lang.Collections;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * I/O流工具类
 *
 * @author yh
 * @since 2021/3/27
 */
public final class IOStreams {

    /**
     * 复制方法中使用的默认缓冲区大小
     */
    public static final int DEFAULT_BUFFER_SIZE = 1024 * 8;

    /**
     * 系统目录分隔符
     */
    public static final char DIR_SEPARATOR = File.separatorChar;

    /**
     * Unix系统目录分隔符
     */
    public static final char DIR_SEPARATOR_UNIX = '/';

    /**
     * Windows系统目录分隔符
     */
    public static final char DIR_SEPARATOR_WINDOWS = '\\';

    /**
     * 表示文件(流)结束符
     */
    public static final int EOF = -1;

    /**
     * 系统行分隔符
     */
    public static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Unix系统行分隔符
     */
    public static final String LINE_SEPARATOR_UNIX = "\n";

    /**
     * Windows系统行分隔符
     */
    public static final String LINE_SEPARATOR_WINDOWS = "\r\n";

    private IOStreams() {
    }

    /* ------------------------------------------------------ buffer ------------------------------------------------------ */

    /**
     * 如果InputStream已经是{@link BufferedInputStream}，则返回给定的InputStream，否则根据给定
     * InputStream创建一个BufferedInputStream返回
     *
     * @param inputStream 要包装或返回的InputStream(非null)
     * @return 给定的InputStream或包装InputStream的新 {@link BufferedInputStream}
     * @throws NullPointerException 如果输入参数为null
     */
    @SuppressWarnings("resource")
    public static BufferedInputStream buffer(final InputStream inputStream) {
        Objects.requireNonNull(inputStream, "inputStream");

        return inputStream instanceof BufferedInputStream ?
                (BufferedInputStream) inputStream : new BufferedInputStream(inputStream);
    }

    /**
     * 如果InputStream已经是{@link BufferedInputStream}，则返回给定的InputStream，否则根据给定
     * InputStream创建一个BufferedInputStream返回
     *
     * @param inputStream 要包装或返回的InputStream(非null)
     * @param size        创建新BufferedInputStream的缓冲区大小
     * @return 给定的InputStream或包装InputStream的新 {@link BufferedInputStream}
     * @throws NullPointerException 如果输入参数为null
     */
    @SuppressWarnings("resource")
    public static BufferedInputStream buffer(final InputStream inputStream, final int size) {
        Objects.requireNonNull(inputStream, "inputStream");

        return inputStream instanceof BufferedInputStream ?
                (BufferedInputStream) inputStream : new BufferedInputStream(inputStream, size);
    }

    /**
     * 如果OutputStream已经是{@link BufferedOutputStream}，则返回给定的OutputStream，否则根据给定
     * OutputStream创建一个BufferedOutputStream返回
     *
     * @param outputStream 要包装或返回的OutputStream(非null)
     * @return 给定的OutputStream或包装OutputStream的新 {@link BufferedOutputStream}
     * @throws NullPointerException 如果输入参数为null
     */
    @SuppressWarnings("resource")
    public static BufferedOutputStream buffer(final OutputStream outputStream) {
        Objects.requireNonNull(outputStream, "outputStream");

        return outputStream instanceof BufferedOutputStream ?
                (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream);
    }

    /**
     * 如果OutputStream已经是{@link BufferedOutputStream}，则返回给定的OutputStream，否则根据给定
     * OutputStream创建一个BufferedOutputStream返回
     *
     * @param outputStream 要包装或返回的OutputStream(非null)
     * @param size         创建新BufferedOutputStream的缓冲区大小
     * @return 给定的OutputStream或包装OutputStream的新 {@link BufferedOutputStream}
     * @throws NullPointerException 如果输入参数为null
     */
    @SuppressWarnings("resource")
    public static BufferedOutputStream buffer(final OutputStream outputStream, final int size) {
        Objects.requireNonNull(outputStream, "outputStream");

        return outputStream instanceof BufferedOutputStream ?
                (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream, size);
    }

    /**
     * 如果Reader已经是{@link BufferedReader}，则返回给定的Reader，否则根据给定
     * Reader创建一个BufferedReader返回
     *
     * @param reader 要包装或返回的Reader(非null)
     * @return 给定的Reader或包装Reader的新 {@link BufferedReader}
     * @throws NullPointerException 如果输入参数为null
     */
    public static BufferedReader buffer(final Reader reader) {
        Objects.requireNonNull(reader, "reader");

        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    /**
     * 如果Reader已经是{@link BufferedReader}，则返回给定的Reader，否则根据给定
     * Reader创建一个BufferedReader返回
     *
     * @param reader 要包装或返回的Reader(非null)
     * @param size   创建新BufferedReader的缓冲区大小
     * @return 给定的Reader或包装Reader的新 {@link BufferedReader}
     * @throws NullPointerException 如果输入参数为null
     */
    public static BufferedReader buffer(final Reader reader, final int size) {
        Objects.requireNonNull(reader, "reader");

        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, size);
    }

    /**
     * 如果Writer已经是{@link BufferedWriter}，则返回给定的Writer，否则根据给定
     * Writer创建一个BufferedWriter返回
     *
     * @param writer 要包装或返回的Writer(非null)
     * @return 给定的Writer或包装Writer的新 {@link BufferedWriter}
     * @throws NullPointerException 如果输入参数为null
     */
    public static BufferedWriter buffer(final Writer writer) {
        Objects.requireNonNull(writer, "writer");

        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer);
    }

    /**
     * 如果Writer已经是{@link BufferedWriter}，则返回给定的Writer，否则根据给定
     * Writer创建一个BufferedWriter返回
     *
     * @param writer 要包装或返回的Writer(非null)
     * @param size   创建新BufferedWriter的缓冲区大小
     * @return 给定的Writer或包装Writer的新 {@link BufferedWriter}
     * @throws NullPointerException 如果输入参数为null
     */
    public static BufferedWriter buffer(final Writer writer, final int size) {
        Objects.requireNonNull(writer, "writer");

        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer, size);
    }

    /* ------------------------------------------------------ copy ------------------------------------------------------ */

    /**
     * 从{@link InputStream}输入流复制字节到{@link OutputStream}输出流
     * 这个方法使用提供的缓冲区，所以不需要使用{@link BufferedInputStream}
     * 不对流做关闭操作
     *
     * @param input  读取的输入流{@link InputStream}
     * @param output 写入的输出流{@link OutputStream}
     * @return 复制的字节数
     * @throws NullPointerException 如果input或output为null
     * @throws IOException          如果出现I/O错误
     */
    public static long copy(final InputStream input, final OutputStream output)
            throws IOException {
        return copy(input, output, new byte[DEFAULT_BUFFER_SIZE]);
    }

    /**
     * 从{@link InputStream}输入流复制字节到{@link OutputStream}输出流
     * 这个方法使用提供的缓冲区，所以不需要使用{@link BufferedInputStream}
     * 不对流做关闭操作
     *
     * @param input      读取的输入流{@link InputStream}
     * @param output     写入的输出流{@link OutputStream}
     * @param bufferSize 用于复制的缓冲区大小
     * @return 复制的字节数
     * @throws NullPointerException 如果input或output为null
     * @throws IOException          如果出现I/O错误
     */
    public static long copy(final InputStream input, final OutputStream output,
                            final int bufferSize) throws IOException {
        return copy(input, output, new byte[bufferSize]);
    }

    /**
     * 从{@link InputStream}输入流复制字节到{@link OutputStream}输出流
     * 这个方法使用提供的缓冲区，所以不需要使用{@link BufferedInputStream}
     * 不对流做关闭操作
     *
     * @param input  读取的输入流{@link InputStream}
     * @param output 写入的输出流{@link OutputStream}
     * @param buffer 用于复制的缓冲区
     * @return 复制的字节数
     * @throws NullPointerException 如果input、output或buffer为null
     * @throws IOException          如果出现I/O错误
     */
    public static long copy(final InputStream input, final OutputStream output,
                            final byte[] buffer) throws IOException {
        Objects.requireNonNull(input, "input");
        Objects.requireNonNull(output, "output");
        Objects.requireNonNull(buffer, "buffer");

        long count = 0;
        int n;
        while ((n = input.read(buffer)) != EOF) {
            output.write(buffer, 0, n);
            count += n;
        }
        output.flush();
        return count;
    }

    /**
     * 从{@link InputStream}输入流复制字节到{@link OutputStream}输出流
     * 这个方法使用提供的缓冲区，所以不需要使用{@link BufferedInputStream}
     * 默认缓冲区大小为{@link #DEFAULT_BUFFER_SIZE}.
     * 不对流做关闭操作
     *
     * @param input       要读的输入流{@link InputStream}
     * @param output      要写的输出流{@link OutputStream}
     * @param inputOffset 在复制前从输入中跳过的字符数，负值将被忽略
     * @param length      要复制的字符数，负值为复制全部
     * @return 复制的字节数
     * @throws NullPointerException 如果input或output为null
     * @throws IOException          如果出现I/O错误
     */
    public static long copy(final InputStream input, final OutputStream output, final long inputOffset,
                            final long length) throws IOException {
        return copy(input, output, inputOffset, length, new byte[DEFAULT_BUFFER_SIZE]);
    }

    /**
     * 从{@link InputStream}输入流复制字节到{@link OutputStream}输出流
     * 这个方法使用提供的缓冲区，所以不需要使用{@link BufferedInputStream}
     * 默认缓冲区大小为{@link #DEFAULT_BUFFER_SIZE}.
     * 不对流做关闭操作
     *
     * @param input  要读的输入流{@link InputStream}
     * @param output 要写的输出流{@link OutputStream}
     * @param offset 在复制前从输入中跳过的字符数，负值将被忽略
     * @param length 要复制的字符数，负值为复制全部
     * @param buffer 用于复制的缓冲区
     * @return 复制的字节数
     * @throws NullPointerException 如果input或output为null
     * @throws IOException          如果出现I/O错误
     */
    public static long copy(final InputStream input, final OutputStream output, final long offset,
                            final long length, final byte[] buffer) throws IOException {
        Objects.requireNonNull(input, "input");
        Objects.requireNonNull(output, "output");
        Objects.requireNonNull(buffer, "buffer");

        long skipped = input.skip(offset);
        if (skipped < offset) {
            throw new IOException("Skipped only " + skipped + " bytes out of " + offset + " required");
        }
        if (length == 0) {
            return 0;
        }

        final int bufferLength = buffer.length;
        int bytesToRead = bufferLength;
        if (length > 0 && length < bufferLength) {
            bytesToRead = (int) length;
        }
        int read;
        long totalRead = 0;
        while (bytesToRead > 0 && EOF != (read = input.read(buffer, 0, bytesToRead))) {
            output.write(buffer, 0, read);
            totalRead += read;
            if (length > 0) { // 如果读不完，只调整长度
                // 注意，由于buffer.length是一个整数，所以必须进行类型转换
                bytesToRead = (int) Math.min(length - totalRead, bufferLength);
            }
        }
        output.flush();
        return totalRead;
    }

    /**
     * 从{@link Reader}阅读器复制字符到{@link Writer}写入器
     * 这个方法使用提供的缓冲区，所以不需要使用{@link BufferedReader}
     *
     * @param reader 阅读器{@link Reader}
     * @param writer 写入器{@link Writer}
     * @return 复制的字符数
     * @throws NullPointerException 如果reader或writer为null
     * @throws IOException          如果出现I/O错误
     */
    public static long copy(final Reader reader, final Writer writer)
            throws IOException {
        return copy(reader, writer, new char[DEFAULT_BUFFER_SIZE]);
    }

    /**
     * 从{@link Reader}阅读器复制字符到{@link Writer}写入器
     * 这个方法使用提供的缓冲区，所以不需要使用{@link BufferedReader}
     *
     * @param reader     阅读器{@link Reader}
     * @param writer     写入器{@link Writer}
     * @param bufferSize 复制使用的缓存区大小
     * @return 复制的字符数
     * @throws NullPointerException 如果reader或writer为null
     * @throws IOException          如果出现I/O错误
     */
    public static long copy(final Reader reader, final Writer writer, final int bufferSize)
            throws IOException {
        return copy(reader, writer, new char[bufferSize]);
    }

    /**
     * 从{@link Reader}阅读器复制字符到{@link Writer}写入器
     * 这个方法使用提供的缓冲区，所以不需要使用{@link BufferedReader}
     *
     * @param reader 阅读器{@link Reader}
     * @param writer 写入器{@link Writer}
     * @param buffer 用于复制的缓冲区
     * @return 复制的字符数
     * @throws NullPointerException 如果reader、writer或buffer为null
     * @throws IOException          如果出现I/O错误
     */
    public static long copy(final Reader reader, final Writer writer, final char[] buffer)
            throws IOException {
        Objects.requireNonNull(reader, "reader");
        Objects.requireNonNull(writer, "writer");
        Objects.requireNonNull(buffer, "buffer");

        long count = 0;
        int n;
        while ((n = reader.read(buffer)) != EOF) {
            writer.write(buffer, 0, n);
            count += n;
        }
        writer.flush();
        return count;
    }

    /**
     * 从{@link Reader}阅读器复制字符到{@link Writer}写入器
     * 这个方法使用提供的缓冲区，所以不需要使用{@link BufferedReader}
     * 默认缓冲区大小为{@link #DEFAULT_BUFFER_SIZE}
     *
     * @param reader      阅读器{@link Reader}
     * @param writer      写入器{@link Writer}
     * @param inputOffset 在复制前从输入中跳过的字符数，负值将被忽略
     * @param length      要复制的字符数，负值为复制全部
     * @return 复制的字符数
     * @throws NullPointerException 如果reader或writer为null
     * @throws IOException          如果出现I/O错误
     */
    public static long copy(final Reader reader, final Writer writer, final long inputOffset, final long length)
            throws IOException {
        return copy(reader, writer, inputOffset, length, new char[DEFAULT_BUFFER_SIZE]);
    }

    /**
     * 从{@link Reader}阅读器复制字符到{@link Writer}写入器
     * 这个方法使用提供的缓冲区，所以不需要使用{@link BufferedReader}
     *
     * @param reader 阅读器{@link Reader}
     * @param writer 写入器{@link Writer}
     * @param offset 在复制前从输入中跳过的字符数，负值将被忽略
     * @param length 要复制的字符数，负值为复制全部
     * @param buffer 用于复制的缓冲区
     * @return 复制的字符数
     * @throws NullPointerException 如果reader、writer或buffer为null
     * @throws IOException          如果出现I/O错误
     */
    public static long copy(final Reader reader, final Writer writer, final long offset, final long length,
                            final char[] buffer) throws IOException {
        Objects.requireNonNull(reader, "reader");
        Objects.requireNonNull(writer, "writer");
        Objects.requireNonNull(buffer, "buffer");

        long skipped = reader.skip(offset);
        if (skipped < offset) {
            throw new IOException("Skipped only " + skipped + " bytes out of " + offset + " required");
        }
        if (length == 0) {
            return 0;
        }

        int bytesToRead = buffer.length;
        if (length > 0 && length < buffer.length) {
            bytesToRead = (int) length;
        }
        int read;
        long totalRead = 0;
        while (bytesToRead > 0 && EOF != (read = reader.read(buffer, 0, bytesToRead))) {
            writer.write(buffer, 0, read);
            totalRead += read;
            if (length > 0) { // 如果读不完，只调整长度
                // 注意，由于buffer.length是一个整数，所以必须进行类型转换
                bytesToRead = (int) Math.min(length - totalRead, buffer.length);
            }
        }
        writer.flush();
        return totalRead;
    }

    /**
     * 从{@link Reader}阅读器复制字符到{@link Appendable}
     * <p>
     * 这个方法在内部缓冲输入，所以不需要使用{@link BufferedReader}
     * <p>
     *
     * @param reader     阅读器{@link Reader}
     * @param appendable 可写入的{@link Appendable}
     * @return 复制的字符数
     * @throws NullPointerException 如果reader或appendable为null
     * @throws IOException          如果出现I/O错误
     */
    public static long copy(final Reader reader, final Appendable appendable) throws IOException {
        return copy(reader, appendable, CharBuffer.allocate(DEFAULT_BUFFER_SIZE));
    }

    /**
     * 从{@link Reader}阅读器复制字符到{@link Appendable}
     * <p>
     * 这个方法在内部缓冲输入，所以不需要使用{@link BufferedReader}
     * <p>
     *
     * @param reader     阅读器{@link Reader}
     * @param appendable 可写入的{@link Appendable}
     * @param buffer     用于复制的缓冲区
     * @return 复制的字符数
     * @throws NullPointerException 如果reader、appendable或buffer为null
     * @throws IOException          如果出现I/O错误
     */
    public static long copy(final Reader reader, final Appendable appendable, final CharBuffer buffer) throws IOException {
        Objects.requireNonNull(reader, "reader");
        Objects.requireNonNull(appendable, "appendable");
        Objects.requireNonNull(buffer, "buffer");

        long count = 0;
        int n;
        while ((n = reader.read(buffer)) != EOF) {
            buffer.flip();
            appendable.append(buffer, 0, n);
            count += n;
        }
        return count;
    }

    /**
     * 从{@link InputStream}输入流复制字节到{@link Writer}写入器
     * 这个方法使用提供的缓冲区，所以不需要使用{@link BufferedInputStream}
     * 不对流做关闭操作
     *
     * @param input        读取的输入流{@link InputStream}
     * @param writer       写入器{@link Writer}
     * @param inputCharset 字符编码
     * @throws NullPointerException 如果input、writer或inputCharset为null
     * @throws IOException          如果出现I/O错误
     */
    public static void copy(final InputStream input, final Writer writer, final Charset inputCharset)
            throws IOException {
        Objects.requireNonNull(inputCharset, "inputCharset");

        final InputStreamReader reader = new InputStreamReader(input, inputCharset);
        copy(reader, writer);
    }

    /**
     * 从{@link Reader}阅读器复制字节到{@link OutputStream}输出流
     * 这个方法使用提供的缓冲区，所以不需要使用{@link BufferedReader}
     * 不对流做关闭操作
     *
     * @param reader        阅读器{@link Reader}
     * @param output        输出流{@link OutputStream}
     * @param outputCharset 字符编码
     * @throws NullPointerException 如果reader、output或outputCharset为null
     * @throws IOException          如果出现I/O错误
     */
    public static void copy(final Reader reader, final OutputStream output, final Charset outputCharset)
            throws IOException {
        final OutputStreamWriter out = new OutputStreamWriter(output, outputCharset);
        copy(reader, out);
        out.flush();
    }

    /* ------------------------------------------------------ copyToString ------------------------------------------------------ */

    /**
     * 将{@link InputStream}输入流的内容转换为字符串，使用给定字符编码
     *
     * @param input   输入流{@link InputStream}
     * @param charset 字符编码
     * @return 内容的字符串
     * @throws IOException 如果出现I/O错误
     */
    public static String copyToString(final InputStream input, final Charset charset) throws IOException {
        Objects.requireNonNull(input, "input");
        Objects.requireNonNull(charset, "charset");

        final InputStreamReader reader = new InputStreamReader(input, charset);
        return copyToString(reader);
    }

    /**
     * 将{@link Reader}阅读器的内容转换为字符串，使用给定字符编码
     *
     * @param reader 阅读器{@link Reader}
     * @return 内容的字符串
     * @throws IOException 如果出现I/O错误
     */
    public static String copyToString(final Reader reader) throws IOException {
        Objects.requireNonNull(reader, "reader");

        StringBuilder builder = new StringBuilder(DEFAULT_BUFFER_SIZE);
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        int readLen;
        while ((readLen = reader.read(buffer)) != EOF) {
            builder.append(buffer, 0, readLen);
        }
        return builder.toString();
    }

    /**
     * 获取给定URI上的内容
     *
     * @param uri     URI源
     * @param charset 字符编码
     * @return 内容的字符串
     * @throws IOException 如果出现I/O错误
     */
    public static String copyToString(final URI uri, final Charset charset) throws IOException {
        return copyToString(uri.toURL(), charset);
    }

    /**
     * 获取给定URL上的内容
     *
     * @param url     URL源
     * @param charset 字符编码
     * @return 内容的字符串
     * @throws IOException 如果出现I/O错误
     */
    public static String copyToString(final URL url, final Charset charset) throws IOException {
        Objects.requireNonNull(url, "url");
        Objects.requireNonNull(charset, "charset");

        URLConnection con = url.openConnection();
        try {
            return copyToString(con, charset);
        } finally {
            close(con);
        }
    }

    /**
     * 获取给定URLConnection上的内容，使用指定字符编码
     *
     * @param connection 要读取的{@link URLConnection}
     * @param charset    字符编码
     * @return 内容的字符串
     * @throws IOException 如果出现I/O错误
     */
    public static String copyToString(final URLConnection connection, final Charset charset) throws IOException {
        return copyToString(connection, Duration.ofSeconds(10), Duration.ofSeconds(10), charset);
    }

    /**
     * 获取给定URLConnection上的内容，使用指定字符编码
     *
     * @param connection     要读取的{@link URLConnection}
     * @param charset        字符编码
     * @param connectTimeout 连接超时时间
     * @param readTimeout    读取超时时间
     * @return 内容的字符串
     * @throws IOException 如果出现I/O错误
     */
    public static String copyToString(final URLConnection connection, Duration connectTimeout,
                                      Duration readTimeout, final Charset charset) throws IOException {
        // 连接超时时间，单位毫秒
        connection.setConnectTimeout((int) connectTimeout.toMillis());
        // 读取超时时间，单位毫秒
        connection.setReadTimeout((int) readTimeout.toMillis());
        try (InputStream inputStream = connection.getInputStream()) {
            return copyToString(inputStream, charset);
        }
    }
    /* ------------------------------------------------------ toByteArray ------------------------------------------------------ */

    /**
     * 读取{@link InputStream}输入流的内容为一个字节数组
     *
     * @param input 读取的输入流{@link InputStream}
     * @return 内容的字节数组
     * @throws NullPointerException 如果input为null
     * @throws IOException          如果出现I/O错误
     */
    public static byte[] toByteArray(final InputStream input) throws IOException {
        Objects.requireNonNull(input, "input");

        try (final ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            copy(input, output);
            return output.toByteArray();
        }
    }

    /**
     * 获取{@link Reader}阅读器的内容为一个字节数组，使用指定字符编码
     *
     * @param reader  阅读器{@link Reader}
     * @param charset 字符编码
     * @return 内容的字节数组
     * @throws IOException 如果出现I/O错误
     */
    public static byte[] toByteArray(final Reader reader, final Charset charset) throws IOException {
        Objects.requireNonNull(reader, "reader");
        Objects.requireNonNull(charset, "charset");

        try (final ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            copy(reader, output, charset);
            return output.toByteArray();
        }
    }

    /**
     * 获取{@link URI}的内容为一个字节数组
     *
     * @param uri 要读取的{@link URI}
     * @return 内容的字节数组
     * @throws IOException 如果发生I/O异常
     */
    public static byte[] toByteArray(final URI uri) throws IOException {
        Objects.requireNonNull(uri, "uri");

        return toByteArray(uri.toURL());
    }

    /**
     * 获取{@link URL}的内容为一个字节数组
     *
     * @param url 要读取的{@link URL}
     * @return 内容的字节数组
     * @throws IOException 如果发生I/O异常
     */
    public static byte[] toByteArray(final URL url) throws IOException {
        Objects.requireNonNull(url, "url");

        URLConnection con = url.openConnection();
        try {
            return toByteArray(con);
        } finally {
            close(con);
        }
    }

    /**
     * 获取{@link URLConnection}的内容为一个字节数组
     *
     * @param connection 要读取的{@link URLConnection}
     * @return 内容的字节数组
     * @throws IOException 如果发生I/O异常
     */
    public static byte[] toByteArray(final URLConnection connection) throws IOException {
        return toByteArray(connection, Duration.ofSeconds(10), Duration.ofSeconds(10));
    }

    /**
     * 获取{@link URLConnection}的内容为一个字节数组
     *
     * @param connection     要读取的{@link URLConnection}
     * @param connectTimeout 连接超时时间
     * @param readTimeout    读取超时时间
     * @return 内容的字节数组
     * @throws IOException 如果发生I/O异常
     */
    public static byte[] toByteArray(final URLConnection connection, Duration connectTimeout,
                                     Duration readTimeout) throws IOException {
        // 连接超时时间，单位毫秒
        connection.setConnectTimeout((int) connectTimeout.toMillis());
        // 读取超时时间，单位毫秒
        connection.setReadTimeout((int) readTimeout.toMillis());
        try (InputStream inputStream = connection.getInputStream()) {
            return toByteArray(inputStream);
        }
    }

    /* ------------------------------------------------------ toCharArray ------------------------------------------------------ */

    /**
     * 获取{@link InputStream}的内容作为字符数组，使用指定字符编码
     *
     * @param input   要读取的{@link InputStream}
     * @param charset 字符编码
     * @return 内容的字符数组
     * @throws IOException 如果出现I/O错误
     */
    public static char[] toCharArray(final InputStream input, final Charset charset)
            throws IOException {
        try (final CharArrayWriter output = new CharArrayWriter()) {
            copy(input, output, charset);
            return output.toCharArray();
        }
    }

    /**
     * 获取{@link Reader}的内容作为字符数组
     *
     * @param input 要读取的{@link Reader}
     * @return 内容的字符数组
     * @throws IOException 如果出现I/O错误
     */
    public static char[] toCharArray(final Reader input) throws IOException {
        try (final CharArrayWriter writer = new CharArrayWriter()) {
            copy(input, writer);
            return writer.toCharArray();
        }
    }

    /**
     * 获取{@link URI}的内容为一个字符数组，使用指定字符编码
     *
     * @param uri     要读取的{@link URI}
     * @param charset 字符编码
     * @return 内容的字符数组
     * @throws IOException 如果发生I/O异常
     */
    public static char[] toCharArray(final URI uri, Charset charset) throws IOException {
        Objects.requireNonNull(uri, "uri");

        return toCharArray(uri.toURL(), charset);
    }

    /**
     * 获取{@link URL}的内容为一个字符数组，使用指定字符编码
     *
     * @param url     要读取的{@link URL}
     * @param charset 字符编码
     * @return 内容的字符数组
     * @throws IOException 如果发生I/O异常
     */
    public static char[] toCharArray(final URL url, Charset charset) throws IOException {
        Objects.requireNonNull(url, "url");

        URLConnection con = url.openConnection();
        try {
            return toCharArray(con, charset);
        } finally {
            close(con);
        }
    }

    /**
     * 获取{@link URLConnection}的内容为一个字符数组，使用指定字符编码
     *
     * @param connection 要读取的{@link URLConnection}
     * @param charset    字符编码
     * @return 内容的字符数组
     * @throws IOException 如果发生I/O异常
     */
    public static char[] toCharArray(final URLConnection connection, Charset charset) throws IOException {
        return toCharArray(connection, Duration.ofSeconds(10), Duration.ofSeconds(10), charset);
    }

    /**
     * 获取{@link URLConnection}的内容为一个字符数组，使用指定字符编码
     *
     * @param connection     要读取的{@link URLConnection}
     * @param charset        字符编码
     * @param connectTimeout 连接超时时间
     * @param readTimeout    读取超时时间
     * @return 内容的字符数组
     * @throws IOException 如果发生I/O异常
     */
    public static char[] toCharArray(final URLConnection connection, Duration connectTimeout,
                                     Duration readTimeout, Charset charset) throws IOException {
        // 连接超时时间，单位毫秒
        connection.setConnectTimeout((int) connectTimeout.toMillis());
        // 读取超时时间，单位毫秒
        connection.setReadTimeout((int) readTimeout.toMillis());
        try (InputStream inputStream = connection.getInputStream()) {
            return toCharArray(inputStream, charset);
        }
    }

    /* ------------------------------------------------------ readLines ------------------------------------------------------ */

    /**
     * 获取{@link InputStream}的内容作为字符串列表，每行为列表一项，使用指定字符编码
     *
     * @param input   要读取的{@link InputStream}
     * @param charset 字符编码
     * @return 内容的字符串列表
     * @throws IOException 如果出现I/O错误
     */
    public static List<String> readLines(final InputStream input, final Charset charset) throws IOException {
        final InputStreamReader reader = new InputStreamReader(input, charset);
        return readLines(reader);
    }

    /**
     * 获取{@link Reader}的内容作为字符串列表，每行为列表一项
     *
     * @param reader 要读取的{@link Reader}
     * @return 内容的字符串列表
     * @throws IOException 如果出现I/O错误
     */
    @SuppressWarnings("resource")
    public static List<String> readLines(final Reader reader) throws IOException {
        final BufferedReader br = buffer(reader);
        final List<String> list = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        return list;
    }

    /**
     * 获取{@link URI}的内容作为字符串列表，每行为列表一项，使用指定字符编码
     *
     * @param uri     要读取的{@link URI}
     * @param charset 字符编码
     * @return 内容的字符串列表
     * @throws IOException 如果出现I/O错误
     */
    public static List<String> readLines(final URI uri, Charset charset) throws IOException {
        return readLines(uri.toURL(), charset);
    }

    /**
     * 获取{@link URL}的内容作为字符串列表，每行为列表一项，使用指定字符编码
     *
     * @param url     要读取的{@link URL}
     * @param charset 字符编码
     * @return 内容的字符串列表
     * @throws IOException 如果出现I/O错误
     */
    public static List<String> readLines(final URL url, Charset charset) throws IOException {
        URLConnection con = url.openConnection();
        try {
            return readLines(con, charset);
        } finally {
            close(con);
        }
    }

    /**
     * 获取{@link URLConnection}的内容作为字符串列表，每行为列表一项，使用指定字符编码
     *
     * @param connection 要读取的{@link URLConnection}
     * @param charset    字符编码
     * @return 内容的字符串列表
     * @throws IOException 如果出现I/O错误
     */
    public static List<String> readLines(final URLConnection connection, Charset charset) throws IOException {
        return readLines(connection, Duration.ofSeconds(10), Duration.ofSeconds(10), charset);
    }

    /**
     * 获取{@link URLConnection}的内容作为字符串列表，每行为列表一项，使用指定字符编码
     *
     * @param connection     要读取的{@link URLConnection}
     * @param charset        字符编码
     * @param connectTimeout 连接超时时间
     * @param readTimeout    读取超时时间
     * @return 内容的字符串列表
     * @throws IOException 如果出现I/O错误
     */
    public static List<String> readLines(final URLConnection connection, Duration connectTimeout,
                                         Duration readTimeout, Charset charset) throws IOException {
        // 连接超时时间，单位毫秒
        connection.setConnectTimeout((int) connectTimeout.toMillis());
        // 读取超时时间，单位毫秒
        connection.setReadTimeout((int) readTimeout.toMillis());
        try (InputStream inputStream = connection.getInputStream()) {
            return readLines(inputStream, charset);
        }
    }

    /* ------------------------------------------------------ lineIterator ------------------------------------------------------ */

    /**
     * 返回{@link InputStream}输入流中的行迭代器{@link LineIterator}，使用指定的字符编码
     *
     * <pre>
     * try(LineIterator it = IOStreams.lineIterator(stream, charset)) {
     *   while (it.hasNext()) {
     *     String line = it.nextLine();
     *     /// do something with line
     *   }
     * }
     * </pre>
     *
     * @param input   输入流{@link InputStream}
     * @param charset 字符编码
     * @return 读取器中行的迭代器
     * @throws IllegalArgumentException 如果input或charset为null
     * @throws IOException              如果出现I/O错误
     */
    public static LineIterator lineIterator(final InputStream input, final Charset charset) throws IOException {
        Objects.requireNonNull(input, "input");
        Objects.requireNonNull(charset, "charset");

        return new LineIterator(new InputStreamReader(input, charset));
    }

    /* ------------------------------------------------------ skip ------------------------------------------------------ */

    /**
     * 跳过请求的字节数
     *
     * @param input  要跳过的{@link InputStream}
     * @param toSkip 要跳过的字节数
     * @return 实际跳过字节数
     * @throws IOException 如果读取{@link InputStream}有问题
     * @see InputStream#skip(long)
     */
    public static long skip(final InputStream input, final long toSkip) throws IOException {
        Objects.requireNonNull(input, "input");

        return input.skip(toSkip);
    }

    /**
     * 跳过请求的字节数
     *
     * @param input  要跳过的{@link ReadableByteChannel}
     * @param toSkip 要跳过的字节数
     * @return 实际跳过字节数
     * @throws IOException 如果读取{@link ReadableByteChannel}有问题
     */
    public static long skip(final ReadableByteChannel input, final long toSkip) throws IOException {
        Objects.requireNonNull(input, "input");

        if (toSkip <= 0) {
            return 0;
        }
        final ByteBuffer skipByteBuffer = ByteBuffer.allocate((int) Math.min(toSkip, DEFAULT_BUFFER_SIZE));
        long remain = toSkip;
        while (remain > 0) {
            skipByteBuffer.position(0);
            skipByteBuffer.limit((int) Math.min(remain, DEFAULT_BUFFER_SIZE));
            final int n = input.read(skipByteBuffer);
            if (n == EOF) {
                break;
            }
            remain -= n;
        }
        return toSkip - remain;
    }

    /**
     * 跳过请求的字节数
     *
     * @param reader 要挑过的流
     * @param toSkip 要跳过的字节数
     * @return 实际跳过字节数
     * @throws IOException              如果流不可读
     * @throws IllegalArgumentException if toSkip is negative
     * @throws EOFException             if the number of characters skipped was incorrect
     * @see Reader#skip(long)
     */
    public static long skip(final Reader reader, final long toSkip) throws IOException {
        Objects.requireNonNull(reader, "reader");

        return reader.skip(toSkip);
    }

    /* ------------------------------------------------------ close ------------------------------------------------------ */

    /**
     * 将给定的{@link Closeable}进行关闭操作
     *
     * @param closeable 要关闭的资源，可以为null
     * @throws IOException 如果出现I/O错误
     */
    public static void close(final Closeable closeable) throws IOException {
        if (closeable != null) {
            closeable.close();
        }
    }

    /**
     * 将给定的{@link Closeable}进行关闭操作
     *
     * @param closeables 要关闭的资源，可以为null
     * @throws IOException 如果出现I/O错误
     */
    public static void close(final Closeable... closeables) throws IOException {
        if (closeables != null) {
            for (final Closeable closeable : closeables) {
                close(closeable);
            }
        }
    }

    /**
     * 关闭给定的{@link Closeable}，同时由给定的{@link Consumer}消费IOException
     *
     * @param closeable 要关闭的资源，可以为null
     * @param consumer  消费由{@link Closeable#close()}抛出的IOException
     */
    public static void close(final Closeable closeable, final Consumer<IOException> consumer) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (final IOException e) {
                if (consumer != null) {
                    consumer.accept(e);
                }
            }
        }
    }

    /**
     * 关闭URLConnection
     *
     * @param conn 要关闭的连接
     */
    public static void close(final URLConnection conn) {
        if (conn instanceof HttpURLConnection) {
            ((HttpURLConnection) conn).disconnect();
        }
    }

    /* ------------------------------------------------------ drain ------------------------------------------------------ */

    /**
     * 消耗InputStream输入流的内容并忽略内容
     * 缓冲区大小为{@link #DEFAULT_BUFFER_SIZE}
     *
     * @param input 要读取的输入流
     * @return 复制的字节数，如果input为null则返回0
     * @throws IOException 如果出现I/O错误
     */
    public static long drain(final InputStream input) throws IOException {
        Objects.requireNonNull(input, "input");

        long count = 0;
        int n;
        byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
        while ((n = input.read(bytes)) != EOF) {
            count += n;
        }
        return count;
    }

    /**
     * 消耗Reader阅读器的内容并忽略内容
     * 缓冲区大小为{@link #DEFAULT_BUFFER_SIZE}
     *
     * @param reader 要读取的阅读器
     * @return 复制的字节数，如果input为null则返回0
     * @throws IOException 如果出现I/O错误
     */
    public static long drain(final Reader reader) throws IOException {
        Objects.requireNonNull(reader, "reader");

        long count = 0;
        int n;
        char[] chars = new char[DEFAULT_BUFFER_SIZE];
        while ((n = reader.read(chars)) != EOF) {
            count += n;
        }
        return count;
    }
    /* ------------------------------------------------------ contentEquals ------------------------------------------------------ */

    /**
     * 比较两个流的内容是否相等
     *
     * @param input1 第一个流
     * @param input2 第二个流
     * @return 如果流的内容相等则为true，否则为false
     * @throws IOException 如果出现I/O错误
     */
    @SuppressWarnings("resource")
    public static boolean contentEquals(final InputStream input1, final InputStream input2) throws IOException {
        if (input1 == input2) {
            return true;
        }
        if (input1 == null ^ input2 == null) {
            return false;
        }

        final BufferedInputStream bin1 = buffer(input1);
        final BufferedInputStream bin2 = buffer(input2);

        int ch = bin1.read();
        while (EOF != ch) {
            final int ch2 = bin2.read();
            if (ch != ch2) {
                return false;
            }
            ch = bin1.read();
        }
        return bin2.read() == EOF;
    }

    /**
     * 比较两个阅读器的内容是否相等
     *
     * @param input1 第一个阅读器
     * @param input2 第二个阅读器
     * @return 如果阅读器的内容相等则为true，否则为false
     * @throws IOException 如果出现I/O错误
     */
    @SuppressWarnings("resource")
    public static boolean contentEquals(final Reader input1, final Reader input2) throws IOException {
        if (input1 == input2) {
            return true;
        }
        if (input1 == null ^ input2 == null) {
            return false;
        }

        final BufferedReader br1 = buffer(input1);
        final BufferedReader br2 = buffer(input2);

        int ch = br1.read();
        while (EOF != ch) {
            final int ch2 = br2.read();
            if (ch != ch2) {
                return false;
            }
            ch = br1.read();
        }
        return br2.read() == EOF;
    }

    /**
     * 比较两个阅读器的内容是否相等，忽略EOL字符
     *
     * @param input1 第一个阅读器
     * @param input2 第二个阅读器
     * @return 如果阅读器的内容相等（忽略EOL）则为true，否则为false
     * @throws IOException 如果出现I/O错误
     */
    @SuppressWarnings("resource")
    public static boolean contentEqualsIgnoreEOL(final Reader input1, final Reader input2) throws IOException {
        if (input1 == input2) {
            return true;
        }
        if (input1 == null ^ input2 == null) {
            return false;
        }

        final BufferedReader br1 = buffer(input1);
        final BufferedReader br2 = buffer(input2);

        String line1 = br1.readLine();
        String line2 = br2.readLine();
        while (line1 != null && line1.equals(line2)) {
            line1 = br1.readLine();
            line2 = br2.readLine();
        }
        return Objects.equals(line1, line2);
    }

    /* ------------------------------------------------------ read ------------------------------------------------------ */

    /**
     * 从输入流中读取字节
     *
     * @param input  要读取的{@link InputStream}
     * @param buffer 保存内容的字节数组
     * @return 实际读取的长度；如果达到EOF，可能会比要求的少
     * @throws IOException 如果发生I/O错误
     */
    public static int read(final InputStream input, final byte[] buffer) throws IOException {
        return read(input, buffer, 0, buffer.length);
    }

    /**
     * 从输入流中读取字节
     *
     * @param input  要读取的{@link InputStream}
     * @param buffer 保存内容的字节数组
     * @param offset 进入缓冲区的初始偏移量
     * @param length 要读取的长度，必须大于等于0
     * @return 实际读取的长度；如果达到EOF，可能会比要求的少
     * @throws IOException 如果发生I/O错误
     */
    public static int read(final InputStream input, final byte[] buffer, final int offset, final int length)
            throws IOException {
        if (length < 0) {
            throw new IllegalArgumentException("Length must not be negative: " + length);
        }
        int remaining = length;
        while (remaining > 0) {
            final int location = length - remaining;
            final int count = input.read(buffer, offset + location, remaining);
            if (EOF == count) { // EOF
                break;
            }
            remaining -= count;
        }
        return length - remaining;
    }

    /**
     * 从{@link ReadableByteChannel}读取字节
     *
     * @param input  要读取的字节通道
     * @param buffer 保存内容的字符数组
     * @return 实际读取的长度；如果达到EOF，可能会比要求的少
     * @throws IOException 如果发生I/O错误
     */
    public static int read(final ReadableByteChannel input, final ByteBuffer buffer) throws IOException {
        final int length = buffer.remaining();
        while (buffer.remaining() > 0) {
            final int count = input.read(buffer);
            if (EOF == count) { // EOF
                break;
            }
        }
        return length - buffer.remaining();
    }

    /**
     * 从输入字符流中读取字符
     * 这种实现保证在放弃之前读取尽可能多的字符;对于{@link Reader}的子类可能并不总是这样
     *
     * @param input  要读取的{@link Reader}
     * @param buffer 保存内容的字符数组
     * @return 实际读取的长度；如果达到EOF，可能会比要求的少
     * @throws IOException 如果发生I/O错误
     */
    public static int read(final Reader input, final char[] buffer) throws IOException {
        return read(input, buffer, 0, buffer.length);
    }

    /**
     * 从输入字符流中读取字符
     * 这种实现保证在放弃之前读取尽可能多的字符;对于{@link Reader}的子类可能并不总是这样
     *
     * @param input  要读取的{@link Reader}
     * @param buffer 保存内容的字符数组
     * @param offset 进入缓冲区的初始偏移量
     * @param length 要读取的长度，必须是大于等于0
     * @return 实际读取的长度；如果达到EOF，可能会比要求的少
     * @throws IOException 如果发生I/O错误
     */
    public static int read(final Reader input, final char[] buffer, final int offset, final int length)
            throws IOException {
        if (length < 0) {
            throw new IllegalArgumentException("Length must not be negative: " + length);
        }
        int remaining = length;
        while (remaining > 0) {
            final int location = length - remaining;
            final int count = input.read(buffer, offset + location, remaining);
            if (EOF == count) { // EOF
                break;
            }
            remaining -= count;
        }
        return length - remaining;
    }
    /* ------------------------------------------------------ write ------------------------------------------------------ */

    /**
     * 将字节数组写入{@link OutputStream}
     *
     * @param data   要写入的字节数组，在输出期间不要修改，忽略null
     * @param output {@link OutputStream}写入
     * @throws NullPointerException 如果output为null
     * @throws IOException          如果出现I/O错误
     */
    public static void write(final byte[] data, final OutputStream output)
            throws IOException {
        if (data != null) {
            output.write(data);
            output.flush();
        }
    }

    /**
     * 将字节数组写入{@link Writer}，使用指定字符编码
     *
     * @param data    要写入的字节数组，在输出期间不要修改，忽略null
     * @param output  {@link Writer}写入
     * @param charset 字符编码
     * @throws NullPointerException 如果output或charset为null
     * @throws IOException          如果出现I/O错误
     */
    public static void write(final byte[] data, final Writer output, final Charset charset)
            throws IOException {
        Objects.requireNonNull(output, "output");
        Objects.requireNonNull(charset, "charset");

        if (data != null) {
            output.write(new String(data, charset));
            output.flush();
        }
    }

    /**
     * 将字符数组写入{@link OutputStream}，使用指定字符编码
     *
     * @param data    要写入的字符数组，在输出期间不要修改，忽略null
     * @param output  {@link OutputStream}写入
     * @param charset 字符编码
     * @throws NullPointerException 如果output或charset为null
     * @throws IOException          如果出现I/O错误
     */
    public static void write(final char[] data, final OutputStream output, final Charset charset)
            throws IOException {
        Objects.requireNonNull(output, "output");
        Objects.requireNonNull(charset, "charset");

        if (data != null) {
            output.write(new String(data).getBytes(charset));
            output.flush();
        }
    }

    /**
     * 将字节数组写入{@link Writer}
     *
     * @param data   要写入的字符数组，在输出期间不要修改，忽略null
     * @param output {@link Writer}写入
     * @throws NullPointerException if output is null
     * @throws IOException          如果出现I/O错误
     */
    public static void write(final char[] data, final Writer output)
            throws IOException {
        Objects.requireNonNull(output, "output");

        if (data != null) {
            output.write(data);
            output.flush();
        }
    }

    /**
     * 将字符序列输出到{@link OutputStream}，使用指定字符编码
     *
     * @param data    要输出的字符序列，忽略null值
     * @param output  {@link OutputStream}输出
     * @param charset 字符编码
     * @throws NullPointerException 如果output或charset为null
     * @throws IOException          如果出现I/O错误
     */
    public static void write(final CharSequence data, final OutputStream output, final Charset charset)
            throws IOException {
        Objects.requireNonNull(output, "output");
        Objects.requireNonNull(charset, "charset");

        if (data != null) {
            output.write(data.toString().getBytes(charset));
            output.flush();
        }
    }

    /**
     * 将字符序列写入到{@link Writer}
     *
     * @param data   要输出的字符序列，忽略null值
     * @param output {@link Writer}写入
     * @throws NullPointerException 如果output为null
     * @throws IOException          如果出现I/O错误
     */
    public static void write(final CharSequence data, final Writer output)
            throws IOException {
        Objects.requireNonNull(output, "output");

        if (data != null) {
            output.write(data.toString());
            output.flush();
        }
    }

    /* ------------------------------------------------------ writeChunked ------------------------------------------------------ */

    /**
     * 将{@code char[]}中的字符以块的方式输出到{@link OutputStream}，为了编写非常大的字节
     * 数组，可能会导致过多的内存使用
     *
     * @param data   要输出的字节数组，在输出期间不要修改，null会被忽略
     * @param output {@link OutputStream}输出
     * @throws NullPointerException 如果output为null
     * @throws IOException          如果出现I/O错误
     */
    public static void writeChunked(final byte[] data, final OutputStream output)
            throws IOException {
        Objects.requireNonNull(output, "output");

        if (Arrays.isNotEmpty(data)) {
            int length = data.length;
            int offset = 0;
            while (length > 0) {
                final int chunk = Math.min(length, DEFAULT_BUFFER_SIZE);
                output.write(data, offset, chunk);
                length -= chunk;
                offset += chunk;
            }
            output.flush();
        }
    }

    /**
     * 将{@code char[]}中的字符以块的方式写入{@link Writer}，为了编写非常大的字符
     * 数组，可能会导致过多的内存使用
     *
     * @param data   要写入的字符数组，在输出期间不要修改，null会被忽略
     * @param output {@link Writer}写入
     * @throws NullPointerException 如果output为null
     * @throws IOException          如果出现I/O错误
     */
    public static void writeChunked(final char[] data, final Writer output)
            throws IOException {
        Objects.requireNonNull(output, "output");

        if (Arrays.isNotEmpty(data)) {
            int length = data.length;
            int offset = 0;
            while (length > 0) {
                final int chunk = Math.min(length, DEFAULT_BUFFER_SIZE);
                output.write(data, offset, chunk);
                length -= chunk;
                offset += chunk;
            }
            output.flush();
        }
    }

    /* ------------------------------------------------------ writeLines ------------------------------------------------------ */

    /**
     * 将集合中每一项的{@code toString()}值逐行写入{@link OutputStream}，使用指定字符编码和系统默认行结束符
     *
     * @param lines   要写的行，为null的项产生空行
     * @param output  输出流，不能为null，不进行关闭操作
     * @param charset 字符编码
     * @throws IOException 如果出现I/O错误
     */
    public static void writeLines(final Collection<?> lines, final OutputStream output, Charset charset)
            throws IOException {
        writeLines(lines, LINE_SEPARATOR, output, charset);
    }

    /**
     * 将集合中每一项的{@code toString()}值逐行写入{@link OutputStream}，使用指定的字符编码和指定的行结束符
     *
     * @param lines         要写的行，为null的项产生空行
     * @param lineSeparator 要使用的行分隔符
     * @param output        输出流，不进行关闭操作
     * @param charset       要使用的字符编码
     * @throws IOException 如果出现I/O错误
     */
    public static void writeLines(final Collection<?> lines, String lineSeparator, final OutputStream output,
                                  Charset charset)
            throws IOException {
        Objects.requireNonNull(lineSeparator, "lineSeparator");
        Objects.requireNonNull(output, "output");
        Objects.requireNonNull(charset, "charset");

        if (Collections.isEmpty(lines)) {
            return;
        }

        for (final Object line : lines) {
            if (line != null) {
                output.write(line.toString().getBytes(charset));
            }
            output.write(lineSeparator.getBytes(charset));
        }
        output.flush();
    }


    /**
     * 将集合中每一项的{@code toString}值逐行写入{@code Writer}，使用系统默认行分隔符
     *
     * @param lines  要写的行，为null的项产生空行
     * @param writer 写入器，不能为null，不进行关闭操作
     * @throws IOException 如果出现I/O错误
     */
    public static void writeLines(final Collection<?> lines, final Writer writer)
            throws IOException {
        writeLines(lines, LINE_SEPARATOR, writer);
    }

    /**
     * 将集合中每一项的{@code toString}值逐行写入{@code Writer}，使用指定的行分隔符
     *
     * @param lines         要写的行，为null的项产生空行
     * @param lineSeparator 要使用的行分隔符
     * @param writer        写入器，不进行关闭操作
     * @throws IOException 如果出现I/O错误
     */
    public static void writeLines(final Collection<?> lines, String lineSeparator, final Writer writer)
            throws IOException {
        Objects.requireNonNull(lineSeparator, "lineSeparator");
        Objects.requireNonNull(writer, "writer");

        if (Collections.isEmpty(lines)) {
            return;
        }

        for (final Object line : lines) {
            if (line != null) {
                writer.write(line.toString());
            }
            writer.write(lineSeparator);
        }
        writer.flush();
    }

    /* ------------------------------------------------------ toInputStream ------------------------------------------------------ */

    /**
     * 将指定的字符串转换为以字节编码的输入流，使用指定字符编码
     *
     * @param input   要转换的字符串
     * @param charset 字符编码
     * @return 字节数组输入流
     */
    public static InputStream toInputStream(final String input, final Charset charset) {
        return new ByteArrayInputStream(input.getBytes(charset));
    }

    /**
     * 将指定的字符序列转换为以字节编码的输入流，使用指定字符编码
     *
     * @param input   要转换的字符序列
     * @param charset 字符编码
     * @return 字节数组输入流
     */
    public static InputStream toInputStream(final CharSequence input, final Charset charset) {
        return new ByteArrayInputStream(input.toString().getBytes(charset));
    }

    /* ------------------------------------------------------ resourceToXXX ------------------------------------------------------ */

    /**
     * 获取指向给定类路径资源的URL
     * <p>
     * 给定的{@code name}应该是绝对路径的
     * </p>
     *
     * @param name 所需资源的名称
     * @return 资源的URL
     * @throws IOException 如果出现I/O错误
     */
    public static URL resourceToURL(final String name) throws IOException {
        return resourceToURL(name, null);
    }

    /**
     * 获取指向给定类路径资源的URL
     * <p>
     * 给定的{@code name}应该是绝对路径的
     * </p>
     *
     * @param name        所需资源的名称
     * @param classLoader 资源解析委托的类加载器
     * @return 资源的URL
     * @throws IOException 如果出现I/O错误
     */
    public static URL resourceToURL(final String name, final ClassLoader classLoader) throws IOException {
        final URL resource = classLoader == null ? IOStreams.class.getResource(name) : classLoader.getResource(name);
        if (resource == null) {
            throw new IOException("Resource not found: " + name);
        }
        return resource;
    }

    /**
     * 使用指定的字符编码以字符串形式获取类路径资源的内容
     * <p>
     * 给定的{@code name}应该是绝对路径的
     * </p>
     *
     * @param name    所需资源的名称
     * @param charset 字符编码
     * @return 资源的字符串
     * @throws IOException 如果出现I/O错误
     */
    public static String resourceToString(final String name, final Charset charset) throws IOException {
        return resourceToString(name, charset, null);
    }

    /**
     * 使用指定的字符编码以字符串形式获取类路径资源的内容
     * <p>
     * 给定的{@code name}应该是绝对路径的
     * </p>
     *
     * @param name        所需资源的名称
     * @param charset     字符编码
     * @param classLoader 资源解析委托的类加载器
     * @return 资源的字符串
     * @throws IOException 如果出现I/O错误
     */
    public static String resourceToString(final String name, final Charset charset, final ClassLoader classLoader) throws IOException {
        return copyToString(resourceToURL(name, classLoader), charset);
    }

    /**
     * 以字节数组的形式获取类路径资源的内容
     * <p>
     * 给定的{@code name}应该是绝对路径的
     * </p>
     *
     * @param name 所需资源的名称
     * @return 资源的字节数组
     * @throws IOException 如果出现I/O错误
     */
    public static byte[] resourceToByteArray(final String name) throws IOException {
        return resourceToByteArray(name, null);
    }

    /**
     * 以字节数组的形式获取类路径资源的内容
     * <p>
     * 给定的{@code name}应该是绝对路径的
     * </p>
     *
     * @param name        所需资源的名称
     * @param classLoader 资源解析委托的类加载器
     * @return 资源的字节数组
     * @throws IOException 如果出现I/O错误
     */
    public static byte[] resourceToByteArray(final String name, final ClassLoader classLoader) throws IOException {
        return toByteArray(resourceToURL(name, classLoader));
    }

}
