package tomkit.core.function;

import java.util.function.Supplier;

/**
 * @author yh
 * @since 2021/4/7
 */
public class Suppliers {

    /**
     * Resolve the given {@code Supplier}, getting its result or immediately
     * returning {@code null} if the supplier itself was {@code null}.
     *
     * @param supplier the supplier to resolve
     * @return the supplier's result, or {@code null} if none
     */
    public static <T> T resolve(Supplier<T> supplier) {
        return (supplier != null ? supplier.get() : null);
    }

}
