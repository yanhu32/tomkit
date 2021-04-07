package tomkit.core.lang;

import java.util.Collection;

/**
 * 集合工具类
 *
 * @author yh
 * @since 2021/3/26
 */
public final class Collections {

    private Collections() {
    }

    /**
     * 判断集合是否为null或空
     *
     * @param collection 要判断的集合
     * @param <E>        集合类型
     * @return 是否为null或空
     */
    public static <E> boolean isEmpty(Collection<E> collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * 判断集合是否不为null或空
     *
     * @param collection 要判断的集合
     * @param <E>        集合类型
     * @return 是否不为null或空
     */
    public static <E> boolean isNotEmpty(Collection<E> collection) {
        return null != collection && !collection.isEmpty();
    }

}
