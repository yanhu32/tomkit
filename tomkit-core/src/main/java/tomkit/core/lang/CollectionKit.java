package tomkit.core.lang;

import java.util.Collection;

/**
 * 集合工具类
 *
 * @author yh
 * @since 2021/3/26
 */
public class CollectionKit {

    public static <E> boolean isEmpty(Collection<E> collection) {
        return null == collection || collection.isEmpty();
    }

    public static <E> boolean isNotEmpty(Collection<E> collection) {
        return null != collection && !collection.isEmpty();
    }

}
