package tomkit.core.lang;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author yh
 * @since 2021/1/29
 */
public final class Assert {

    private Assert() {
    }

    public static String nullMessage(String name) {
        return name + " cannot be null";
    }

    /**
     * 断言一个布尔表达式，如果表达式为 {@code false} 则抛出一个 {@link IllegalStateException} 异常
     *
     * @param expression 布尔表达式
     * @param message    断言失败时使用的异常消息
     * @throws IllegalStateException 如果表达式为 {@code false}
     */
    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * 断言一个布尔表达式，如果表达式为 {@code false} 则抛出一个 {@link IllegalStateException} 异常
     *
     * @param expression      布尔表达式
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalStateException 如果表达式为 {@code false}
     */
    public static void state(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalStateException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言一个布尔表达式，如果表达式为 {@code false} 则抛出一个 {@link IllegalArgumentException} 异常
     *
     * @param expression 布尔表达式
     * @param message    断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果表达式为 {@code false}
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个布尔表达式，如果表达式为 {@code false} 则抛出一个 {@link IllegalArgumentException} 异常
     *
     * @param expression      布尔表达式
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果表达式为 {@code false}
     */
    public static void isTrue(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言一个布尔表达式，如果表达式为 {@code true} 则抛出一个 {@link IllegalArgumentException} 异常
     *
     * @param expression 布尔表达式
     * @param message    断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果表达式为 {@code true}
     */
    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个布尔表达式，如果表达式为 {@code true} 则抛出一个 {@link IllegalArgumentException} 异常
     *
     * @param expression      布尔表达式
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果表达式为 {@code true}
     */
    public static void isFalse(boolean expression, Supplier<String> messageSupplier) {
        if (expression) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言一个对象为 {@code null}
     *
     * @param object  要检查的对象
     * @param message 断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果对象不为 {@code null}
     */
    public static void isNull(Object object, String message) {
        if (null != object) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个对象为 {@code null}
     *
     * @param object          要检查的对象
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果对象不为 {@code null}
     */
    public static void isNull(Object object, Supplier<String> messageSupplier) {
        if (null != object) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言一个对象不为 {@code null}
     *
     * @param object  要检查的对象
     * @param message 断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果对象为 {@code null}
     */
    public static void notNull(Object object, String message) {
        if (null == object) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个对象不为 {@code null}
     *
     * @param object          要检查的对象
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果对象为 {@code null}
     */
    public static void notNull(Object object, Supplier<String> messageSupplier) {
        if (null == object) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言给定的字符串不为空
     *
     * @param text    要检查的字符串
     * @param message 断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果text为空
     * @see StringKit#hasLength
     */
    public static void hasLength(String text, String message) {
        if (!StringKit.hasLength(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言给定字符串不为空
     *
     * @param text            要检查的字符串
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果text为空
     * @see StringKit#hasLength
     */
    public static void hasLength(String text, Supplier<String> messageSupplier) {
        if (!StringKit.hasLength(text)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言给定字符串包含有效的文本内容
     *
     * @param text    要检查的字符串
     * @param message 断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果文本不包含有效的文本内容
     * @see StringKit#hasText
     */
    public static void hasText(String text, String message) {
        if (!StringKit.hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言给定字符串包含有效的文本内容
     *
     * @param text            要检查的字符串
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果文本不包含有效的文本内容
     * @see StringKit#hasText
     */
    public static void hasText(String text, Supplier<String> messageSupplier) {
        if (!StringKit.hasText(text)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言给定的文本不包含给定的子字符串
     *
     * @param textToSearch 要搜索的文本
     * @param substring    要在文本中查找的子字符串
     * @param message      断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果文本包含子字符串
     */
    public static void notContains(String textToSearch, String substring, String message) {
        if (StringKit.hasLength(textToSearch) && StringKit.hasLength(substring) &&
                textToSearch.contains(substring)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言给定的文本不包含给定的子字符串
     *
     * @param textToSearch    要搜索的文本
     * @param substring       要在文本中查找的子字符串
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果文本包含子字符串
     */
    public static void notContains(String textToSearch, String substring, Supplier<String> messageSupplier) {
        if (StringKit.hasLength(textToSearch) && StringKit.hasLength(substring) &&
                textToSearch.contains(substring)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言一个对象为空
     *
     * @param object  要检查的对象
     * @param message 断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果对象不为空
     */
    public static void isEmpty(Object object, String message) {
        if (!ObjectKit.isEmpty(object)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个对象为空
     *
     * @param object          要检查的对象
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果对象不为空
     */
    public static void isEmpty(Object object, Supplier<String> messageSupplier) {
        if (!ObjectKit.isEmpty(object)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言一个对象不为空
     *
     * @param object  要检查的对象
     * @param message 断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果对象为空
     */
    public static void notEmpty(Object object, String message) {
        if (ObjectKit.isEmpty(object)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个对象不为空
     *
     * @param object          要检查的对象
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果对象为空
     */
    public static void notEmpty(Object object, Supplier<String> messageSupplier) {
        if (ObjectKit.isEmpty(object)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言数组不为空
     *
     * @param array   要检查的数组
     * @param message 断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果数组为空
     */
    public static void notEmpty(Object[] array, String message) {
        if (ObjectKit.isEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言数组不为空
     *
     * @param array           要检查的数组
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果数组为空
     */
    public static void notEmpty(Object[] array, Supplier<String> messageSupplier) {
        if (ObjectKit.isEmpty(array)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言数组不包含 {@code null} 元素
     *
     * @param array   要检查的数组
     * @param message 断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果数组包含 {@code null} 元素
     */
    public static void noNullElements(Object[] array, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }

    /**
     * 断言数组不包含 {@code null} 元素
     *
     * @param array           要检查的数组
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果数组包含 {@code null} 元素
     */
    public static void noNullElements(Object[] array, Supplier<String> messageSupplier) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new IllegalArgumentException(nullSafeGet(messageSupplier));
                }
            }
        }
    }

    /**
     * 断言集合不为空
     *
     * @param collection 要检查的集合
     * @param message    断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果集合为空
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (ObjectKit.isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言集合不为空
     *
     * @param collection      要检查的集合
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果集合为空
     */
    public static void notEmpty(Collection<?> collection, Supplier<String> messageSupplier) {
        if (ObjectKit.isEmpty(collection)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言集合不包含 {@code null} 元素
     *
     * @param collection 要检查的集合
     * @param message    断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果集合包含 {@code null} 元素
     * @since 5.2
     */
    public static void noNullElements(Collection<?> collection, String message) {
        if (collection != null) {
            for (Object element : collection) {
                if (element == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }

    /**
     * 断言集合不包含 {@code null} 元素
     *
     * @param collection      要检查的集合
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果集合包含 {@code null} 元素
     * @since 5.2
     */
    public static void noNullElements(Collection<?> collection, Supplier<String> messageSupplier) {
        if (collection != null) {
            for (Object element : collection) {
                if (element == null) {
                    throw new IllegalArgumentException(nullSafeGet(messageSupplier));
                }
            }
        }
    }

    /**
     * 断言一个Map不为空
     *
     * @param map     要检查的Map
     * @param message 断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果Map为空
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        if (ObjectKit.isEmpty(map)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个Map不为空
     *
     * @param map             要检查的Map
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果Map为空
     */
    public static void notEmpty(Map<?, ?> map, Supplier<String> messageSupplier) {
        if (ObjectKit.isEmpty(map)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言所提供的对象是所提供类的实例
     *
     * @param type    要检查的类型
     * @param obj     要检查的对象
     * @param message 断言失败时使用的异常消息
     *                如果它是空的，或者以":"或";"或"，"或"."结尾，则会附加一条完整的异常消息。
     *                如果它以空格结束，则会附加有问题的子类型的名称。在任何其他情况下，一个带
     *                空格的“:”和有问题的子类型的名称将被追加。
     * @throws IllegalArgumentException 如果对象不是类型的实例
     */
    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, message);
        }
    }

    /**
     * 断言所提供的对象是所提供类的实例
     *
     * @param type            要检查的类型
     * @param obj             要检查的对象
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果对象不是类型的实例
     */
    public static void isInstanceOf(Class<?> type, Object obj, Supplier<String> messageSupplier) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言所提供的对象是所提供类的实例
     *
     * @param type 要检查的类型
     * @param obj  要检查的对象
     * @throws IllegalArgumentException 如果对象不是类型的实例
     */
    public static void isInstanceOf(Class<?> type, Object obj) {
        isInstanceOf(type, obj, "");
    }

    /**
     * 断言 {@code superType.isAssignableFrom(subType)} 为 {@code true}.
     *
     * @param superType 要检查的超类类型
     * @param subType   要检查的子类类型
     * @param message   断言失败时使用的异常消息
     *                  如果它是空的，或者以":"或";"或"，"或"."结尾，则会附加一条完整的异常消息。
     *                  如果它以空格结束，则会附加有问题的子类型的名称。在任何其他情况下，一个带
     *                  空格的“:”和有问题的子类型的名称将被追加。
     * @throws IllegalArgumentException 如果类是不可分配的
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        notNull(superType, "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, message);
        }
    }

    /**
     * 断言 {@code superType.isAssignableFrom(subType)} 为 {@code true}.
     *
     * @param superType       要检查的超类类型
     * @param subType         要检查的子类类型
     * @param messageSupplier 如果断言失败，则使用的异常消息的提供者
     * @throws IllegalArgumentException 如果类是不可分配的
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, Supplier<String> messageSupplier) {
        notNull(superType, "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, nullSafeGet(messageSupplier));
        }
    }

    /**
     * 断言 {@code superType.isAssignableFrom(subType)} 为 {@code true}.
     * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
     *
     * @param superType 要检查的超类类型
     * @param subType   要检查的子类类型
     * @throws IllegalArgumentException 如果类是不可分配的
     */
    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "");
    }


    private static void instanceCheckFailed(Class<?> type, Object obj, String msg) {
        String className = (obj != null ? obj.getClass().getName() : "null");
        String result = "";
        boolean defaultMessage = true;
        if (StringKit.hasLength(msg)) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, className);
                defaultMessage = false;
            }
        }
        if (defaultMessage) {
            result = result + ("Object of class [" + className + "] must be an instance of " + type);
        }
        throw new IllegalArgumentException(result);
    }

    private static void assignableCheckFailed(Class<?> superType, Class<?> subType, String msg) {
        String result = "";
        boolean defaultMessage = true;
        if (StringKit.hasLength(msg)) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, subType);
                defaultMessage = false;
            }
        }
        if (defaultMessage) {
            result = result + (subType + " is not assignable to " + superType);
        }
        throw new IllegalArgumentException(result);
    }

    private static boolean endsWithSeparator(String msg) {
        return (msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith("."));
    }

    private static String messageWithTypeName(String msg, Object typeName) {
        return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
    }


    private static String nullSafeGet(Supplier<String> messageSupplier) {
        return (messageSupplier != null ? messageSupplier.get() : null);
    }

}

