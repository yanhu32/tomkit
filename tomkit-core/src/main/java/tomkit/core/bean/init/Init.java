package tomkit.core.bean.init;

import tomkit.core.function.StringConverter;

import java.io.Serializable;
import java.lang.annotation.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;

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
     * 八大基本类型及其包装类、{@link String}、{@link BigDecimal}、{@link BigInteger}、{@link LocalDate}、{@link LocalTime}、
     * {@link LocalDateTime}、{@link ZonedDateTime}、{@link OffsetDateTime} 类型默认支持解析，可以不用指定转换器，当默认类型转换
     * 不满足需求时可以指定自定义转换器；除此之外其他类型必须实现并指定对应的转换器。
     *
     * @return {@link StringConverter} 转换器实现类
     */
    Class<? extends StringConverter<? extends Serializable>> converter() default AutoConverter.class;

    /**
     * 日期时间格式化字符串
     *
     * @return 格式化字符串
     */
    String format() default "";

}
