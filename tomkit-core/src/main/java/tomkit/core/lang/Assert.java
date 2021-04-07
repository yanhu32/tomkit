package tomkit.core.lang;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 断言
 *
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
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression a boolean expression
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression a boolean expression
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个布尔表达式，如果表达式为 {@code false} 则抛出一个 {@link IllegalStateException} 异常
     *
     * @param expression 布尔表达式
     * @throws IllegalStateException 如果表达式为 {@code false}
     */
    public static void state(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
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
     * 断言一个布尔表达式，如果表达式为 {@code true} 则抛出一个 {@link IllegalArgumentException} 异常
     *
     * @param expression 布尔表达式
     * @throws IllegalArgumentException 如果表达式为 {@code false}
     */
    public static void ifTrue(boolean expression) {
        if (expression) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 断言一个布尔表达式，如果表达式为 {@code true} 则抛出一个 {@link IllegalArgumentException} 异常
     *
     * @param expression 布尔表达式
     * @param message    断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果表达式为 {@code false}
     */
    public static void ifTrue(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个布尔表达式，如果表达式为 {@code false} 则抛出一个 {@link IllegalArgumentException} 异常
     *
     * @param expression 布尔表达式
     * @throws IllegalArgumentException 如果表达式为 {@code false}
     */
    public static void ifFalse(boolean expression) {
        if (expression) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 断言一个布尔表达式，如果表达式为 {@code false} 则抛出一个 {@link IllegalArgumentException} 异常
     *
     * @param expression 布尔表达式
     * @param message    断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果表达式为 {@code false}
     */
    public static void ifFalse(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
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
     * 断言一个布尔表达式，如果表达式为 {@code true} 则抛出一个 {@link IllegalArgumentException} 异常
     *
     * @param expression 布尔表达式
     * @throws IllegalArgumentException 如果表达式为 {@code true}
     */
    public static void isFalse(boolean expression) {
        if (expression) {
            throw new IllegalArgumentException();
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
     * 断言一个对象为 {@code null}
     *
     * @param object 要检查的对象
     * @throws IllegalArgumentException 如果对象不为 {@code null}
     */
    public static void isNull(Object object) {
        if (null != object) {
            throw new IllegalArgumentException();
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
     * 断言一个对象不为 {@code null}
     *
     * @param object 要检查的对象
     * @throws IllegalArgumentException 如果对象为 {@code null}
     */
    public static void notNull(Object object) {
        if (null == object) {
            throw new IllegalArgumentException();
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
     * 断言给定的字符串不为空
     *
     * @param text 要检查的字符串
     * @throws IllegalArgumentException 如果text为空
     * @see Strings#hasLength
     */
    public static void hasLength(String text) {
        if (!Strings.hasLength(text)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 断言给定的字符串不为空
     *
     * @param text    要检查的字符串
     * @param message 断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果text为空
     * @see Strings#hasLength
     */
    public static void hasLength(String text, String message) {
        if (!Strings.hasLength(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言给定字符串包含有效的文本内容
     *
     * @param text 要检查的字符串
     * @throws IllegalArgumentException 如果文本不包含有效的文本内容
     * @see Strings#hasText
     */
    public static void hasText(String text) {
        if (!Strings.hasText(text)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 断言给定字符串包含有效的文本内容
     *
     * @param text    要检查的字符串
     * @param message 断言失败时使用的异常消息
     * @throws IllegalArgumentException 如果文本不包含有效的文本内容
     * @see Strings#hasText
     */
    public static void hasText(String text, String message) {
        if (!Strings.hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言给定的文本不包含给定的子字符串
     *
     * @param textToSearch 要搜索的文本
     * @param substring    要在文本中查找的子字符串
     * @throws IllegalArgumentException 如果文本包含子字符串
     */
    public static void notContains(String textToSearch, String substring) {
        if (Strings.hasLength(textToSearch) && Strings.hasLength(substring) &&
                textToSearch.contains(substring)) {
            throw new IllegalArgumentException();
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
        if (Strings.hasLength(textToSearch) && Strings.hasLength(substring) &&
                textToSearch.contains(substring)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个对象为空
     *
     * @param object 要检查的对象
     * @throws IllegalArgumentException 如果对象不为空
     */
    public static void isEmpty(Object object) {
        if (!Objects.isEmpty(object)) {
            throw new IllegalArgumentException();
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
        if (!Objects.isEmpty(object)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个对象不为空
     *
     * @param object 要检查的对象
     * @throws IllegalArgumentException 如果对象为空
     */
    public static void notEmpty(Object object) {
        if (Objects.isEmpty(object)) {
            throw new IllegalArgumentException();
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
        if (Objects.isEmpty(object)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言数组不为空
     *
     * @param array 要检查的数组
     * @throws IllegalArgumentException 如果数组为空
     */
    public static void notEmpty(Object[] array) {
        if (Objects.isEmpty(array)) {
            throw new IllegalArgumentException();
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
        if (Objects.isEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言数组不包含 {@code null} 元素
     *
     * @param array 要检查的数组
     * @throws IllegalArgumentException 如果数组包含 {@code null} 元素
     */
    public static void noNullElements(Object[] array) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new IllegalArgumentException();
                }
            }
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
     * 断言集合不为空
     *
     * @param collection 要检查的集合
     * @throws IllegalArgumentException 如果集合为空
     */
    public static void notEmpty(Collection<?> collection) {
        if (Objects.isEmpty(collection)) {
            throw new IllegalArgumentException();
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
        if (Objects.isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言集合不包含 {@code null} 元素
     *
     * @param collection 要检查的集合
     * @throws IllegalArgumentException 如果集合包含 {@code null} 元素
     * @since 5.2
     */
    public static void noNullElements(Collection<?> collection) {
        if (collection != null) {
            for (Object element : collection) {
                if (element == null) {
                    throw new IllegalArgumentException();
                }
            }
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
     * 断言一个Map不为空
     *
     * @param map 要检查的Map
     * @throws IllegalArgumentException 如果Map为空
     */
    public static void notEmpty(Map<?, ?> map) {
        if (Objects.isEmpty(map)) {
            throw new IllegalArgumentException();
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
        if (Objects.isEmpty(map)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言所提供的对象是所提供类的实例
     *
     * @param type 要检查的类型
     * @param obj  要检查的对象
     *             如果它是空的，或者以":"或";"或"，"或"."结尾，则会附加一条完整的异常消息。
     *             如果它以空格结束，则会附加有问题的子类型的名称。在任何其他情况下，一个带
     *             空格的“:”和有问题的子类型的名称将被追加。
     * @throws IllegalArgumentException 如果对象不是类型的实例
     */
    public static void isInstanceOf(Class<?> type, Object obj) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, "");
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
     * 断言 {@code superType.isAssignableFrom(subType)} 为 {@code true}.
     *
     * @param superType 要检查的超类类型
     * @param subType   要检查的子类类型
     *                  如果它是空的，或者以":"或";"或"，"或"."结尾，则会附加一条完整的异常消息。
     *                  如果它以空格结束，则会附加有问题的子类型的名称。在任何其他情况下，一个带
     *                  空格的“:”和有问题的子类型的名称将被追加。
     * @throws IllegalArgumentException 如果类是不可分配的
     */
    public static void isAssignable(Class<?> superType, Class<?> subType) {
        notNull(superType, "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, "");
        }
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

    private static void instanceCheckFailed(Class<?> type, Object obj, String msg) {
        String className = (obj != null ? obj.getClass().getName() : "null");
        String result = "";
        boolean defaultMessage = true;
        if (Strings.hasLength(msg)) {
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
        if (Strings.hasLength(msg)) {
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

