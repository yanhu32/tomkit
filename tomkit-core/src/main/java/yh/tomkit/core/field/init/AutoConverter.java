package yh.tomkit.core.field.init;


import java.io.Serializable;

/**
 * 默认转换器
 *
 * @author yh
 * @since 2021/1/12
 */
final class AutoConverter implements Converter<Serializable> {
    /**
     * 不做任何事情
     *
     * @param s
     * @return
     */
    @Override
    public Serializable convert(String s) {
        return null;
    }

}
