package yh.tomkit.core.field.init;

/**
 * 转换器
 *
 * @author yh
 * @since 2021/1/12
 */
@FunctionalInterface
public interface Converter<O> {
    /**
     * 字符串转换为其他类型
     *
     * @param s 字符串
     * @return 转换后的对象
     */
    O convert(String s);

}
