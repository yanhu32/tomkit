package tomkit.core.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 *
 * @author yh
 * @since 2021/3/25
 */
public final class Filekit {

    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    private static final String EMPTY_STRING = "";

    private static final int NOT_FOUND = -1;

    /**
     * 扩展分隔符
     */
    public static final char EXTENSION_SEPARATOR = '.';

    /**
     * 扩展分隔符字符串
     */
    public static final String EXTENSION_SEPARATOR_STR = "" + EXTENSION_SEPARATOR;

    /**
     * Unix分隔符
     */
    private static final char UNIX_SEPARATOR = '/';

    /**
     * Windows分隔符
     */
    private static final char WINDOWS_SEPARATOR = '\\';

    /**
     * 当前系统分隔符
     */
    private static final char SYSTEM_SEPARATOR = File.separatorChar;

    /**
     * 与系统分隔符相反的分隔符
     */
    private static final char OTHER_SEPARATOR;

    static {
        if (isSystemWindows()) {
            OTHER_SEPARATOR = UNIX_SEPARATOR;
        } else {
            OTHER_SEPARATOR = WINDOWS_SEPARATOR;
        }
    }

    private Filekit() {
    }

    //-----------------------------------------------------------------------

    /**
     * 确定是否正在使用Windows文件系统
     *
     * @return true if the system is Windows
     */
    static boolean isSystemWindows() {
        return SYSTEM_SEPARATOR == WINDOWS_SEPARATOR;
    }

    //-----------------------------------------------------------------------

    /**
     * 检查字符是否为分隔符
     *
     * @param ch 要检查的字符
     * @return 如果是分隔符，则为true
     */
    private static boolean isSeparator(final char ch) {
        return ch == UNIX_SEPARATOR || ch == WINDOWS_SEPARATOR;
    }

    /**
     * 复制源文件内容到目标文件
     *
     * @param in  源文件路径
     * @param out 目标文件路径
     * @return 复制的字节数
     * @throws IOException 发生I/O异常时
     */
    public static long copy(Path in, Path out) throws IOException {
        return Files.size(Files.copy(in, out));
    }

    /**
     * 复制源文件内容到目标文件，支持续写
     *
     * @param in     源文件路径
     * @param out    目标文件路径
     * @param append 是否续写到目标文件
     * @return 复制的字节数
     * @throws IOException 发生I/O异常时
     */
    public static long copy(Path in, Path out, boolean append) throws IOException {
        // 创建目标文件父目录
        createParentDirectories(out);
        try (InputStream inputStream = Files.newInputStream(in);
             OutputStream outputStream = new FileOutputStream(out.toFile(), append)) {
            return IOStreamkit.copy(inputStream, outputStream);
        }
    }

    /**
     * 将给定输入流的内容复制到给定文件内
     * 不对输入流做关闭处理
     *
     * @param in  输入流
     * @param out 目标文件路径
     * @return 复制的字节数
     * @throws IOException 发生I/O错误时
     */
    public static long copy(InputStream in, Path out) throws IOException {
        // 创建目标文件父目录
        createParentDirectories(out);
        try (OutputStream outputStream = Files.newOutputStream(out)) {
            return IOStreamkit.copy(in, outputStream);
        }
    }

    /**
     * 将给定文件内容复制给输出流
     * 不对输出流做关闭处理
     *
     * @param in  输入文件路径
     * @param out 输出流
     * @return 复制的字节数
     * @throws IOException 发生I/O异常时
     */
    public static long copy(Path in, OutputStream out) throws IOException {
        try (InputStream inputStream = Files.newInputStream(in)) {
            return IOStreamkit.copy(inputStream, out);
        }
    }

    /**
     * 将阅读器内容复制给目标文件
     * 不对流做关闭处理
     *
     * @param reader 阅读器
     * @param out    目标文件路径
     * @return 复制的字符数
     * @throws IOException 发生I/O错误时
     */
    public static long copy(Reader reader, Path out) throws IOException {
        // 创建目标文件父目录
        createParentDirectories(out);
        try (Writer writer = new OutputStreamWriter(Files.newOutputStream(out))) {
            return IOStreamkit.copy(reader, writer);
        }
    }

    /**
     * 将阅读器内容复制给目标文件
     * 不对流做关闭处理
     *
     * @param reader  阅读器
     * @param out     目标文件路径
     * @param charset 写入时的字符编码
     * @return 复制的字符数
     * @throws IOException 发生I/O错误时
     */
    public static long copy(Reader reader, Path out, Charset charset) throws IOException {
        // 创建目标文件父目录
        createParentDirectories(out);
        try (Writer writer = new OutputStreamWriter(Files.newOutputStream(out), charset)) {
            return IOStreamkit.copy(reader, writer);
        }
    }


    /**
     * 将文件内容复制给读写器
     * 不对流做关闭处理
     *
     * @param in     源文件路径
     * @param writer 读写器
     * @return 复制的字符数
     * @throws IOException 发生I/O错误时
     */
    public static long copy(Path in, Writer writer) throws IOException {
        try (Reader reader = new InputStreamReader(Files.newInputStream(in))) {
            return IOStreamkit.copy(reader, writer);
        }
    }

    /**
     * 将文件内容复制给读写器
     * 不对流做关闭处理
     *
     * @param in      源文件路径
     * @param writer  读写器
     * @param decoder 读取时的字符编码
     * @return 复制的字符数
     * @throws IOException 发生I/O错误时
     */
    public static long copy(Path in, Writer writer, Charset decoder) throws IOException {
        try (Reader reader = new InputStreamReader(Files.newInputStream(in), decoder)) {
            return IOStreamkit.copy(reader, writer);
        }
    }

    /**
     * 获取文件路径下的所有文件
     *
     * @param dir 文件路径
     * @return 搜索到的文件路径
     * @throws IOException 如果发生I/O错误
     */
    public static List<Path> searchFiles(Path dir) throws IOException {
        List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                result.add(entry);
            }
        } catch (DirectoryIteratorException ex) {
            // I/O error encounted during the iteration, the cause is an IOException
            throw ex.getCause();
        }
        return result;
    }

    /**
     * 根据通配符搜索匹配的文件路径列表
     *
     * @param dir  文件路径
     * @param glob 通配符
     * @return 搜索到的文件路径
     * @throws IOException 如果发生I/O错误
     */
    public static List<Path> searchFiles(Path dir, String glob) throws IOException {
        List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, glob)) {
            for (Path entry : stream) {
                result.add(entry);
            }
        } catch (DirectoryIteratorException ex) {
            // I/O error encounted during the iteration, the cause is an IOException
            throw ex.getCause();
        }
        return result;
    }

    /**
     * 创建文件路径父目录
     *
     * @param path 文件路径
     */
    public static void createParentDirectories(Path path) throws IOException {
        if (Files.notExists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
    }

    /**
     * 创建文件
     *
     * @param path 文件路径
     * @throws IOException 发生I/O异常时
     */
    public static void createFile(Path path) throws IOException {
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
    }

    /**
     * 创建文件夹
     *
     * @param path 文件夹
     * @throws IOException 如果发生I/O错误
     */
    public static void createDirectories(Path path) throws IOException {
        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }
    }

    /**
     * 判断给定的文件或目录是否为空
     *
     * @param path 指定要查询的文件或目录
     * @return 给定的文件或目录是否为空
     * @throws IOException 如果发生I/O错误
     */
    public static boolean isEmpty(final Path path) throws IOException {
        return Files.isDirectory(path) ? isEmptyDirectory(path) : isEmptyFile(path);
    }

    /**
     * 判断给定的目录是否为空
     *
     * @param directory 指定要查询的目录
     * @return 给定的目录是否为空
     * @throws IOException 如果发生I/O错误
     */
    public static boolean isEmptyDirectory(final Path directory) throws IOException {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory)) {
            if (directoryStream.iterator().hasNext()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断给定文件是否为空
     *
     * @param file 指定要查询的文件
     * @return 给定文件是否为空
     * @throws IOException 如果发生I/O错误
     */
    public static boolean isEmptyFile(final Path file) throws IOException {
        return Files.size(file) <= 0;
    }

}
