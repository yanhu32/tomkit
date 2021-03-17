package tomkit.core.function;

import java.util.function.Supplier;

/**
 * 字符提供者
 *
 * @author yh
 * @since 2021/2/4
 */
@FunctionalInterface
public interface CharSupplier {
    /**
     * 获取一个字符，避免使用 {@link Supplier<Character>} 进行自动拆箱
     *
     * @return 字符
     */
    char get();

}
