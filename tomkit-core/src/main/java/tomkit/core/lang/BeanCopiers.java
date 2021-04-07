package tomkit.core.lang;


import net.sf.cglib.beans.BeanCopier;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yh
 * @version 2019/8/30
 */
public class BeanCopiers {

    /**
     * 属性全拷贝
     *
     * @param source
     * @param target
     */
    public static void copy(Object source, Object target) {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }

    public static <A, B> B map(A a, Class<B> clazz) {
        B b = null;
        if (null != a && null != clazz) {
            try {
                b = clazz.newInstance();
                copy(a, b);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    public static <A, B> List<B> map(Collection<A> collection, Class<B> clazz) {
        if (Collections.isEmpty(collection)) {
            return java.util.Collections.emptyList();
        }
        return collection.stream().map(a -> map(a, clazz)).collect(Collectors.toList());
    }

}
