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
