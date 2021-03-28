package tomkit.core.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 行迭代器
 * <pre>
 * try(LineIterator it = IOStreams.lineIterator(stream, charset)) {
 *   while (it.hasNext()) {
 *     String line = it.nextLine();
 *     /// do something with line
 *   }
 * }
 * </pre>
 *
 * @author yh
 * @since 2021/3/28
 */
public class LineIterator implements Iterator<String>, Closeable {

    /**
     * 被读的阅读器
     */
    private final BufferedReader bufferedReader;
    /**
     * 当前行
     */
    private String cachedLine;
    /**
     * 迭代器是否已被完全读取
     */
    private boolean finished = false;

    /**
     * 构造一个阅读器的行迭代器
     *
     * @param reader 要读的阅读器
     * @throws IllegalArgumentException 如果reader为null
     */
    public LineIterator(final Reader reader) throws IllegalArgumentException {
        if (reader == null) {
            throw new IllegalArgumentException("Reader must not be null");
        }
        if (reader instanceof BufferedReader) {
            bufferedReader = (BufferedReader) reader;
        } else {
            bufferedReader = new BufferedReader(reader);
        }
    }

    //-----------------------------------------------------------------------

    /**
     * 判断{@link Reader}阅读器是否有下一行
     *
     * @return 如果有下一行返回{@code true}，否则返回{@code false}
     * @throws IllegalStateException 如果发生I/O错误
     */
    @Override
    public boolean hasNext() {
        if (cachedLine != null) {
            return true;
        } else if (finished) {
            return false;
        } else {
            try {
                while (true) {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        finished = true;
                        return false;
                    } else if (isValidLine(line)) {
                        cachedLine = line;
                        return true;
                    }
                }
            } catch (final IOException ioe) {
                IOStreams.close(this, ioe::addSuppressed);
                throw new IllegalStateException(ioe);
            }
        }
    }

    /**
     * 验证返回行是否有效
     *
     * @param line 要验证的行
     * @return true表示有效，false表示从迭代器中移除
     */
    protected boolean isValidLine(final String line) {
        return true;
    }

    /**
     * 返回包装的{@link Reader}阅读器中的下一行
     *
     * @return 输入的下一行
     * @throws NoSuchElementException 如果没有要返回的行
     */
    @Override
    public String next() {
        return nextLine();
    }

    /**
     * 返回包装的{@link Reader}阅读器中的下一行
     *
     * @return 输入的下一行
     * @throws NoSuchElementException 如果没有要返回的行
     */
    public String nextLine() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more lines");
        }
        final String currentLine = cachedLine;
        cachedLine = null;
        return currentLine;
    }

    /**
     * 关闭底层的{@link Reader}
     * 如果只希望处理较大文件的前几行，则此方法非常有用。如果不关闭迭代器，则{@link Reader}将保持打开状态。
     * 可以安全地多次调用此方法
     *
     * @throws IOException 如果关闭底层的{@link Reader}失败
     */
    @Override
    public void close() throws IOException {
        finished = true;
        cachedLine = null;
        bufferedReader.close();
    }

    /**
     * 未实现
     *
     * @throws UnsupportedOperationException always
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove unsupported on LineIterator");
    }

}
