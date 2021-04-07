package tomkit.core.lang;

import java.io.IOException;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author yh
 * @since 2021/4/7
 */
public final class Joiner {

    /**
     * Returns a joiner which automatically places {@code separator} between consecutive elements.
     */
    public static Joiner on(String separator) {
        return new Joiner(separator);
    }

    /**
     * Returns a joiner which automatically places {@code separator} between consecutive elements.
     */
    public static Joiner on(char separator) {
        return new Joiner(String.valueOf(separator));
    }

    private final String separator;

    private Joiner(String separator) {
        this.separator = Objects.checkNotNull(separator);
    }

    private Joiner(Joiner prototype) {
        this.separator = prototype.separator;
    }

    /**
     * Appends the string representation of each of {@code parts}, using the previously configured
     * separator between each, to {@code appendable}.
     */
    public <A extends Appendable> A appendTo(A appendable, Iterable<?> parts) throws IOException {
        return appendTo(appendable, parts.iterator());
    }

    /**
     * Appends the string representation of each of {@code parts}, using the previously configured
     * separator between each, to {@code appendable}.
     */
    public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
        Assert.notNull(appendable);
        if (parts.hasNext()) {
            appendable.append(toString(parts.next()));
            while (parts.hasNext()) {
                appendable.append(separator);
                appendable.append(toString(parts.next()));
            }
        }
        return appendable;
    }

    /**
     * Appends the string representation of each of {@code parts}, using the previously configured
     * separator between each, to {@code appendable}.
     */
    public final <A extends Appendable> A appendTo(A appendable, Object[] parts) throws IOException {
        return appendTo(appendable, Arrays.asList(parts));
    }

    /**
     * Appends to {@code appendable} the string representation of each of the remaining arguments.
     */
    public final <A extends Appendable> A appendTo(
            A appendable, Object first, Object second, Object... rest)
            throws IOException {
        return appendTo(appendable, iterable(first, second, rest));
    }

    /**
     * Appends the string representation of each of {@code parts}, using the previously configured
     * separator between each, to {@code builder}. Identical to {@link #appendTo(Appendable,
     * Iterable)}, except that it does not throw {@link IOException}.
     */
    public final StringBuilder appendTo(StringBuilder builder, Iterable<?> parts) {
        return appendTo(builder, parts.iterator());
    }

    /**
     * Appends the string representation of each of {@code parts}, using the previously configured
     * separator between each, to {@code builder}. Identical to {@link #appendTo(Appendable,
     * Iterable)}, except that it does not throw {@link IOException}.
     */
    public final StringBuilder appendTo(StringBuilder builder, Iterator<?> parts) {
        try {
            appendTo((Appendable) builder, parts);
        } catch (IOException impossible) {
            throw new AssertionError(impossible);
        }
        return builder;
    }

    /**
     * Appends the string representation of each of {@code parts}, using the previously configured
     * separator between each, to {@code builder}. Identical to {@link #appendTo(Appendable,
     * Iterable)}, except that it does not throw {@link IOException}.
     */
    public final StringBuilder appendTo(StringBuilder builder, Object[] parts) {
        return appendTo(builder, Arrays.asList(parts));
    }

    /**
     * Appends to {@code builder} the string representation of each of the remaining arguments.
     * Identical to {@link #appendTo(Appendable, Object, Object, Object...)}, except that it does not
     * throw {@link IOException}.
     */
    public final StringBuilder appendTo(
            StringBuilder builder, Object first, Object second, Object... rest) {
        return appendTo(builder, iterable(first, second, rest));
    }

    /**
     * Returns a string containing the string representation of each of {@code parts}, using the
     * previously configured separator between each.
     */
    public final String join(Iterable<?> parts) {
        return join(parts.iterator());
    }

    /**
     * Returns a string containing the string representation of each of {@code parts}, using the
     * previously configured separator between each.
     */
    public final String join(Iterator<?> parts) {
        return appendTo(new StringBuilder(), parts).toString();
    }

    /**
     * Returns a string containing the string representation of each of {@code parts}, using the
     * previously configured separator between each.
     */
    public final String join(Object[] parts) {
        return join(Arrays.asList(parts));
    }

    /**
     * Returns a string containing the string representation of each argument, using the previously
     * configured separator between each.
     */
    public final String join(Object first, Object second, Object... rest) {
        return join(iterable(first, second, rest));
    }

    /**
     * Returns a {@code MapJoiner} using the given key-value separator, and the same configuration as
     * this {@code Joiner} otherwise.
     */
    public MapJoiner withKeyValueSeparator(char keyValueSeparator) {
        return withKeyValueSeparator(String.valueOf(keyValueSeparator));
    }

    /**
     * Returns a {@code MapJoiner} using the given key-value separator, and the same configuration as
     * this {@code Joiner} otherwise.
     */
    public MapJoiner withKeyValueSeparator(String keyValueSeparator) {
        return new MapJoiner(this, keyValueSeparator);
    }

    /**
     * An object that joins map entries in the same manner as {@code Joiner} joins iterables and
     * arrays. Like {@code Joiner}, it is thread-safe and immutable.
     *
     * <p>In addition to operating on {@code Map} instances, {@code MapJoiner} can operate on {@code
     * Multimap} entries in two distinct modes:
     *
     * <ul>
     *   <li>To output a separate entry for each key-value pair, pass {@code multimap.entries()} to a
     *       {@code MapJoiner} method that accepts entries as input, and receive output of the form
     *       {@code key1=A&key1=B&key2=C}.
     *   <li>To output a single entry for each key, pass {@code multimap.asMap()} to a {@code
     *       MapJoiner} method that accepts a map as input, and receive output of the form {@code
     *       key1=[A, B]&key2=C}.
     * </ul>
     */
    public static final class MapJoiner {
        private final Joiner joiner;
        private final String keyValueSeparator;

        private MapJoiner(Joiner joiner, String keyValueSeparator) {
            this.joiner = joiner; // only "this" is ever passed, so don't checkNotNull
            this.keyValueSeparator = Objects.checkNotNull(keyValueSeparator);
        }

        /**
         * Appends the string representation of each entry of {@code map}, using the previously
         * configured separator and key-value separator, to {@code appendable}.
         */
        public <A extends Appendable> A appendTo(A appendable, Map<?, ?> map) throws IOException {
            return appendTo(appendable, map.entrySet());
        }

        /**
         * Appends the string representation of each entry of {@code map}, using the previously
         * configured separator and key-value separator, to {@code builder}. Identical to {@link
         * #appendTo(Appendable, Map)}, except that it does not throw {@link IOException}.
         */
        public StringBuilder appendTo(StringBuilder builder, Map<?, ?> map) {
            return appendTo(builder, map.entrySet());
        }

        /**
         * Appends the string representation of each entry in {@code entries}, using the previously
         * configured separator and key-value separator, to {@code appendable}.
         */
        public <A extends Appendable> A appendTo(A appendable, Iterable<? extends Entry<?, ?>> entries)
                throws IOException {
            return appendTo(appendable, entries.iterator());
        }

        /**
         * Appends the string representation of each entry in {@code entries}, using the previously
         * configured separator and key-value separator, to {@code appendable}.
         */
        public <A extends Appendable> A appendTo(A appendable, Iterator<? extends Entry<?, ?>> parts)
                throws IOException {
            Assert.notNull(appendable);
            if (parts.hasNext()) {
                Entry<?, ?> entry = parts.next();
                appendable.append(joiner.toString(entry.getKey()));
                appendable.append(keyValueSeparator);
                appendable.append(joiner.toString(entry.getValue()));
                while (parts.hasNext()) {
                    appendable.append(joiner.separator);
                    Entry<?, ?> e = parts.next();
                    appendable.append(joiner.toString(e.getKey()));
                    appendable.append(keyValueSeparator);
                    appendable.append(joiner.toString(e.getValue()));
                }
            }
            return appendable;
        }

        /**
         * Appends the string representation of each entry in {@code entries}, using the previously
         * configured separator and key-value separator, to {@code builder}. Identical to {@link
         * #appendTo(Appendable, Iterable)}, except that it does not throw {@link IOException}.
         */
        public StringBuilder appendTo(StringBuilder builder, Iterable<? extends Entry<?, ?>> entries) {
            return appendTo(builder, entries.iterator());
        }

        /**
         * Appends the string representation of each entry in {@code entries}, using the previously
         * configured separator and key-value separator, to {@code builder}. Identical to {@link
         * #appendTo(Appendable, Iterable)}, except that it does not throw {@link IOException}.
         */
        public StringBuilder appendTo(StringBuilder builder, Iterator<? extends Entry<?, ?>> entries) {
            try {
                appendTo((Appendable) builder, entries);
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
            return builder;
        }

        /**
         * Returns a string containing the string representation of each entry of {@code map}, using the
         * previously configured separator and key-value separator.
         */
        public String join(Map<?, ?> map) {
            return join(map.entrySet());
        }

        /**
         * Returns a string containing the string representation of each entry in {@code entries}, using
         * the previously configured separator and key-value separator.
         */
        public String join(Iterable<? extends Entry<?, ?>> entries) {
            return join(entries.iterator());
        }

        /**
         * Returns a string containing the string representation of each entry in {@code entries}, using
         * the previously configured separator and key-value separator.
         */
        public String join(Iterator<? extends Entry<?, ?>> entries) {
            return appendTo(new StringBuilder(), entries).toString();
        }

    }

    CharSequence toString(Object part) {
        Assert.notNull(part); // checkNotNull for GWT (do not optimize).
        return (part instanceof CharSequence) ? (CharSequence) part : part.toString();
    }

    private static Iterable<Object> iterable(
            final Object first, final Object second, final Object[] rest) {
        Assert.notNull(rest);
        return new AbstractList<Object>() {
            @Override
            public int size() {
                return rest.length + 2;
            }

            @Override
            public Object get(int index) {
                switch (index) {
                    case 0:
                        return first;
                    case 1:
                        return second;
                    default:
                        return rest[index - 2];
                }
            }
        };
    }
}

