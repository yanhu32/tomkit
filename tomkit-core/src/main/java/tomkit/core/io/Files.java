package tomkit.core.io;

import tomkit.core.error.TomkitException;
import tomkit.core.lang.Assert;

import java.io.*;

/**
 * 文件工具类
 *
 * @author yh
 * @since 2021/3/25
 */
public final class Files {

    private Files() {
    }

    /**
     * 复制源文件内容到目标文件
     *
     * @param in  源文件
     * @param out 目标文件
     * @return 复制的字节数
     * @throws IOException 发生I/O异常时
     */
    public static long copy(File in, File out) throws IOException {
        return copy(in, out, false);
    }

    /**
     * 复制源文件内容到目标文件，支持续写
     *
     * @param in     源文件
     * @param out    目标文件
     * @param append 是否续写到目标文件
     * @return 复制的字节数
     * @throws IOException 发生I/O异常时
     */
    public static long copy(File in, File out, boolean append) throws IOException {
        Assert.notNull(in, "源文件不能为空");
        Assert.notNull(out, "目标文件不能为空");
        Assert.state(in.exists(), "源文件不存在");
        Assert.state(in.canRead(), "源文件不可读");
        Assert.state(in.isFile(), "源文件必传为文件类型");

        if (!mkdirsParentFile(out)) {
            throw new TomkitException("创建文件父目录失败 file:" + out.getAbsolutePath());
        }

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(in));
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(out, append))) {
            return IOStreams.copy(inputStream, outputStream);
        }
    }

    /**
     * 将给定输入流的内容复制到给定文件内
     * 不对输入流做关闭处理
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
            throw new TomkitException("创建文件父目录失败");
        }
        try (OutputStream out = new FileOutputStream(outFile)) {
            return IOStreams.copy(in, out);
        }
    }

    /**
     * 将给定文件内容复制给输出流
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
            return IOStreams.copy(in, out);
        }
    }

    /**
     * 将阅读器内容复制给目标文件
     * 不对流做关闭处理
     *
     * @param reader  阅读器
     * @param outFile 目标文件
     * @return 复制的字符数
     * @throws IOException 发生I/O错误时
     */
    public static long copy(Reader reader, File outFile) throws IOException {
        Assert.notNull(reader, "阅读器不能为空");
        Assert.notNull(outFile, "目标文件不能为空");

        if (!Files.mkdirsParentFile(outFile)) {
            throw new TomkitException("创建文件父目录失败");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            return IOStreams.copy(reader, writer);
        }
    }

    /**
     * 将文件内容复制给读写器
     * 不对流做关闭处理
     *
     * @param inFile 源文件
     * @param writer 读写器
     * @return 复制的字符数
     * @throws IOException 发生I/O错误时
     */
    public static long copy(File inFile, Writer writer) throws IOException {
        Assert.notNull(inFile, "源文件不能为空");
        Assert.notNull(writer, "读写器不能为空");
        Assert.state(inFile.exists(), "源文件不存在");
        Assert.state(inFile.canRead(), "源文件不可读");
        Assert.state(inFile.isFile(), "源文件必传为文件类型");

        try (BufferedReader reader = new BufferedReader(new FileReader(inFile))) {
            return IOStreams.copy(reader, writer);
        }
    }

    /**
     * 创建父文件夹
     *
     * @param file 文件
     * @return 创建结果
     */
    public static boolean mkdirsParentFile(File file) {
        Assert.notNull(file, "文件不能为空");
        Assert.state(file.isAbsolute(), "文件路径必须为绝对路径");

        File parentFile = file.getParentFile();
        return parentFile.exists() || parentFile.mkdirs();
    }

    /**
     * 创建文件
     *
     * @param file 文件
     * @return 创建结果
     * @throws IOException 发送I/O异常时
     */
    public static boolean createNewFile(File file) throws IOException {
        Assert.notNull(file, "文件不能为空");
        Assert.state(file.isAbsolute(), "文件路径必须为绝对路径");

        if (file.exists()) {
            return true;
        }
        return mkdirsParentFile(file) && file.createNewFile();
    }

    /**
     * 创建文件夹
     *
     * @param file 文件夹
     * @return 创建结果
     */
    public static boolean mkdirs(File file) {
        Assert.notNull(file, "文件不能为空");
        Assert.state(file.isAbsolute(), "文件路径必须为绝对路径");

        return file.exists() || file.mkdirs();
    }

}
