package tomkit.core.lang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理类
 * 用法：
 * <code>
 * final Code code = new AroundProxyHandler<Code>(new Java())
 *      .before((c, m, v) -> log.info("执行前"))
 *      .after((c, m, v) -> log.info("执行后"))
 *      .newInstance();
 * code.exec();
 * </code>
 *
 * @author yh
 * @since 2021/8/16
 */
public class AroundProxyHandler<T> implements InvocationHandler {

    private final T origin;
    private Around<T> before;
    private Around<T> after;

    public <S extends T> AroundProxyHandler(S origin) {
        this.origin = origin;
    }

    public AroundProxyHandler<T> before(Around<T> before) {
        this.before = before;
        return this;
    }

    public AroundProxyHandler<T> after(Around<T> after) {
        this.after = after;
        return this;
    }

    @SuppressWarnings("unchecked")
    public T newInstance() {
        return (T) Proxy.newProxyInstance(origin.getClass().getClassLoader(), origin.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (null != before) {
            before.apply(origin, method, args);
        }
        try {
            return method.invoke(origin, args);
        } finally {
            if (null != after) {
                after.apply(origin, method, args);
            }
        }
    }

    @FunctionalInterface
    public interface Around<T> {
        void apply(T origin, Method method, Object[] args);
    }
}
