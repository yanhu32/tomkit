package tomkit.core.bean.init;


import tomkit.core.function.StringConverter;

import java.io.Serializable;

/**
 * 默认转换器
 *
 * @author yh
 * @since 2021/1/12
 */
final class AutoConverter implements StringConverter<Serializable> {
    /**
     * 不做任何事情
     *
     * @param s 字符串值
     * @return 转换后的值
     */
    @Override
    public Serializable convert(String s) {
        return null;
    }

}
