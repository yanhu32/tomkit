package tomkit.core.function;

/**
 * 字符串转换器
 *
 * @author yh
 * @since 2021/1/12
 */
@FunctionalInterface
public interface StringConverter<O> {
    /**
     * 字符串转换为其他类型
     *
     * @param s 字符串值
     * @return 转换后的对象
     */
    O convert(String s);

}
