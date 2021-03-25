package tomkit.core.io;

import tomkit.core.error.TomkitException;
import tomkit.core.lang.Assert;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private IOStreams() {
    }

    /**
     * 将给定输入流的内容复制到一个新的字节数组中
     * <p>
     * 不对输入流做关闭处理
     *
     * @param in 要复制的流
     * @return 已复制的新字节数组
     * @throws IOException 发生I/O错误时
     */
    public static byte[] copyToByteArray(InputStream in) throws IOException {
        Assert.notNull(in, "输入流不能为空");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE)) {
            copy(in, out);
            return out.toByteArray();
        }
    }

    /**
     * 将给定字符输入流的内容复制到一个新的字符数组中
     * <p>
     * 不对输入流做关闭处理
     *
     * @param reader 要复制的流
     * @return 已复制的新字符数组
     * @throws IOException 发生I/O错误时
     */
    public static char[] copyToCharArray(Reader reader) throws IOException {
        Assert.notNull(reader, "字符输入流不能为空");

        return copyToString(reader).toCharArray();
    }

    /**
     * 将给定输入流的内容复制到一个字符串中，使用UTF-8编码
     * <p>
     * 不对输入流做关闭处理
     *
     * @param in 要复制的流
     * @return 被复制的字符串
     * @throws IOException 发生I/O错误时
     */
    public static String copyToString(InputStream in) throws IOException {
        return copyToString(in, DEFAULT_CHARSET);
    }

    /**
     * 将给定输入流的内容复制到一个字符串中
     * <p>
     * 不对输入流做关闭处理
     *
     * @param in      要复制的流
     * @param charset 用来解码字节的 {@link Charset}
     * @return 被复制的字符串
     * @throws IOException 发生I/O错误时
     */
    public static String copyToString(InputStream in, Charset charset) throws IOException {
        Assert.notNull(in, "输入流不能为空");
        Assert.notNull(charset, "字符编码不能为空");

        return copyToString(new BufferedReader(new InputStreamReader(in, charset)));
    }

    /**
     * 将给定字符输入流的内容复制到一个字符串中
     * <p>
     * 不对输入流做关闭处理
     *
     * @param reader 要复制的流
     * @return 被复制的字符串
     * @throws IOException 发生I/O错误时
     */
    public static String copyToString(Reader reader) throws IOException {
        Assert.notNull(reader, "字符输入流不能为空");

        StringBuilder builder = new StringBuilder(BUFFER_SIZE);
        char[] buffer = new char[BUFFER_SIZE];
        int readLen;
        while ((readLen = reader.read(buffer)) != -1) {
            builder.append(buffer, 0, readLen);
        }
        return builder.toString();
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
        int n;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
            count += n;
        }
        out.flush();
        return count;
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

        if (!Files.mkdirsParentFile(outFile)) {
            throw new TomkitException("创建文件父目录失败 file:" + outFile.getAbsolutePath());
        }
        try (OutputStream out = new FileOutputStream(outFile)) {
            return copy(in, out);
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
        Assert.notNull(inFile, "源文件不能为空");
        Assert.notNull(out, "输出流不能为空");
        Assert.state(inFile.exists(), "源文件不存在");
        Assert.state(inFile.canRead(), "源文件不可读");
        Assert.state(inFile.isFile(), "源文件必传为文件类型");

        try (InputStream in = new BufferedInputStream(new FileInputStream(inFile))) {
            return copy(in, out);
        }
    }

    /**
     * 将给定字节数组的内容复制到给定的输出流
     * <p>不对输出流做关闭处理
     *
     * @param data 要从中复制的字节数组
     * @param out  要复制到的输出流
     * @throws IOException 发生I/O错误时
     */
    public static void copy(byte[] data, OutputStream out) throws IOException {
        Assert.notNull(data, "输入字节数组不能为空");
        Assert.notNull(out, "输出流不能为空");

        out.write(data);
        out.flush();
    }

    /**
     * 将给定字符串的内容复制到给定的输出流
     * <p>不对流做关闭处理
     *
     * @param data    要复制的字符串
     * @param charset 字节编码
     * @param out     要复制到的输出流
     * @throws IOException 发生I/O错误时
     */
    public static void copy(String data, Charset charset, OutputStream out) throws IOException {
        Assert.notNull(data, "输入字符串不能为空");
        Assert.notNull(charset, "字符编码不能为空");
        Assert.notNull(out, "输出流不能为空");

        Writer writer = new OutputStreamWriter(out, charset);
        writer.write(data);
        writer.flush();
    }

    /**
     * 将给定字符串的内容复制到给定的输出流，使用UTF-8编码
     * <p>不对流做关闭处理
     *
     * @param in  要复制的字符串
     * @param out 要复制到的输出流
     * @throws IOException 发生I/O错误时
     */
    public static void copy(String in, OutputStream out) throws IOException {
        copy(in, DEFAULT_CHARSET, out);
    }

    /**
     * 将字符输入流内容复制给字符输出流
     * <p>
     * 不对流做关闭处理
     *
     * @param reader     字符输入流
     * @param writer     字符输出流
     * @param bufferSize 复制使用的字符数组大小
     * @return 复制的字符数
     * @throws IOException 发生I/O错误时
     */
    public static long copy(Reader reader, Writer writer, int bufferSize) throws IOException {
        Assert.notNull(reader, "字符输入流不能为空");
        Assert.notNull(writer, "字符输出流不能为空");

        final char[] buffer = new char[bufferSize];
        long count = 0;
        int n;
        while ((n = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, n);
            count += n;
        }
        writer.flush();
        return count;
    }

    /**
     * 将字符输入流内容复制给字符输出流
     * <p>
     * 不对流做关闭处理
     *
     * @param reader 字符输入流
     * @param writer 字符输出流
     * @return 复制的字符数
     * @throws IOException 发生I/O错误时
     */
    public static long copy(Reader reader, Writer writer) throws IOException {
        return copy(reader, writer, BUFFER_SIZE / 2);
    }

    /**
     * 将字符输入流内容复制给目标文件
     *
     * @param reader  字符输入流
     * @param outFile 目标文件
     * @return 复制的字符数
     * @throws IOException 发生I/O错误时
     */
    public static long copy(Reader reader, File outFile) throws IOException {
        Assert.notNull(reader, "字符输入流不能为空");
        Assert.notNull(outFile, "目标文件不能为空");

        if (!Files.mkdirsParentFile(outFile)) {
            throw new TomkitException("创建文件父目录失败 file:" + outFile.getAbsolutePath());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            return copy(reader, writer);
        }
    }

    /**
     * 将文件内容复制给字符输出流
     *
     * @param inFile 源文件
     * @param writer 字符输出流
     * @return 复制的字符数
     * @throws IOException 发生I/O错误时
     */
    public static long copy(File inFile, Writer writer) throws IOException {
        Assert.notNull(inFile, "源文件不能为空");
        Assert.notNull(writer, "字符输出流不能为空");
        Assert.state(inFile.exists(), "源文件不存在");
        Assert.state(inFile.canRead(), "源文件不可读");
        Assert.state(inFile.isFile(), "源文件必传为文件类型");

        try (BufferedReader reader = new BufferedReader(new FileReader(inFile))) {
            return copy(reader, writer);
        }
    }

    /**
     * 将字符数组复制给字符输出流
     *
     * @param data   字符数组
     * @param writer 字符输出流
     * @throws IOException 发生I/O错误时
     */
    public static void copy(char[] data, Writer writer) throws IOException {
        Assert.notNull(data, "字符数组不能为空");
        Assert.notNull(writer, "字符输出流不能为空");

        writer.write(data);
        writer.flush();
    }

    /**
     * 将字符串复制给字符输出流
     *
     * @param data   字符串
     * @param writer 字符输出流
     * @throws IOException 发生I/O错误时
     */
    public static void copy(String data, Writer writer) throws IOException {
        Assert.notNull(data, "字符数组不能为空");
        Assert.notNull(writer, "字符输出流不能为空");

        writer.write(data);
        writer.flush();
    }

    /**
     * 将给定输入流的内容范围复制到给定输出流
     * <p>如果指定的范围超过了输入流的长度，则复制到流的末尾，并返回实际复制的字节数
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

        long bytesToCopy = end - start + 1;
        byte[] buffer = new byte[(int) Math.min(IOStreams.BUFFER_SIZE, bytesToCopy)];
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
     * 排干给定输入流的剩余内容
     * <p>不对流做关闭处理
     *
     * @param in 要排干的输入流
     * @return 读取的字节数
     * @throws IOException 发生I/O错误时
     */
    public static long drain(InputStream in) throws IOException {
        Assert.notNull(in, "输入流不能为空");

        byte[] buffer = new byte[BUFFER_SIZE];
        int readLen;
        int count = 0;
        while ((readLen = in.read(buffer)) != -1) {
            count += readLen;
        }
        return count;
    }

}
