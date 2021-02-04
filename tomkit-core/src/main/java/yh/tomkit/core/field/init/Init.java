package yh.tomkit.core.field.init;

import java.io.Serializable;
import java.lang.annotation.*;

/**
 * 默认值
 *
 * @author yh
 * @since 2021/1/12
 */
@Inherited
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Init {
    /**
     * 属性默认值
     *
     * @return 默认值
     */
    String value();

    /**
     * 转换器，将 String 转换为其他类型
     * <p>
     * 八大基本类型及其包装类、String、BigDecimal、BigInteger、LocalDate、LocalTime、LocalDateTime 类型默认支持解析，
     * 可以不用指定转换器，当默认类型转换不满足需求时可以指定自定义转换器；除此之外其他类型必须实现并指定对应的转换器。
     *
     * @return Converter
     */
    Class<? extends Converter<? extends Serializable>> converter() default AutoConverter.class;

    /**
     * 日期格式化字符串
     *
     * @return 格式化字符串
     */
    String format() default "";

}
