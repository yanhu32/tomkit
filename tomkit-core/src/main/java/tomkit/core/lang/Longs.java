package tomkit.core.lang;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yh
 * @since 2021/4/7
 */
public final class Longs {
    private Longs() {
    }

    /**
     * The number of bytes required to represent a primitive {@code long} value.
     *
     * <p><b>Java 8 users:</b> use {@link Long#BYTES} instead.
     */
    public static final int BYTES = Long.SIZE / Byte.SIZE;

    /**
     * The largest power of two that can be represented as a {@code long}.
     */
    public static final long MAX_POWER_OF_TWO = 1L << (Long.SIZE - 2);

    /**
     * Returns a hash code for {@code value}; equal to the result of invoking {@code ((Long)
     * value).hashCode()}.
     *
     * <p>This method always return the value specified by {@link Long#hashCode()} in java, which
     * might be different from {@code ((Long) value).hashCode()} in GWT because {@link
     * Long#hashCode()} in GWT does not obey the JRE contract.
     *
     * <p><b>Java 8 users:</b> use {@link Long#hashCode(long)} instead.
     *
     * @param value a primitive {@code long} value
     * @return a hash code for the value
     */
    public static int hashCode(long value) {
        return (int) (value ^ (value >>> 32));
    }

    /**
     * Compares the two specified {@code long} values. The sign of the value returned is the same as
     * that of {@code ((Long) a).compareTo(b)}.
     *
     * <p><b>Note for Java 7 and later:</b> this method should be treated as deprecated; use the
     * equivalent {@link Long#compare} method instead.
     *
     * @param a the first {@code long} to compare
     * @param b the second {@code long} to compare
     * @return a negative value if {@code a} is less than {@code b}; a positive value if {@code a} is
     * greater than {@code b}; or zero if they are equal
     */
    public static int compare(long a, long b) {
        return (a < b) ? -1 : ((a > b) ? 1 : 0);
    }

    /**
     * Returns {@code true} if {@code target} is present as an element anywhere in {@code array}.
     *
     * @param array  an array of {@code long} values, possibly empty
     * @param target a primitive {@code long} value
     * @return {@code true} if {@code array[i] == target} for some value of {@code i}
     */
    public static boolean contains(long[] array, long target) {
        for (long value : array) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the index of the first appearance of the value {@code target} in {@code array}.
     *
     * @param array  an array of {@code long} values, possibly empty
     * @param target a primitive {@code long} value
     * @return the least index {@code i} for which {@code array[i] == target}, or {@code -1} if no
     * such index exists.
     */
    public static int indexOf(long[] array, long target) {
        return indexOf(array, target, 0, array.length);
    }

    // TODO(kevinb): consider making this public
    private static int indexOf(long[] array, long target, int start, int end) {
        for (int i = start; i < end; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the start position of the first occurrence of the specified {@code target} within
     * {@code array}, or {@code -1} if there is no such occurrence.
     *
     * <p>More formally, returns the lowest index {@code i} such that {@code Arrays.copyOfRange(array,
     * i, i + target.length)} contains exactly the same elements as {@code target}.
     *
     * @param array  the array to search for the sequence {@code target}
     * @param target the array to search for as a sub-sequence of {@code array}
     */
    public static int indexOf(long[] array, long[] target) {
        java.util.Objects.requireNonNull(array, "array");
        java.util.Objects.requireNonNull(target, "target");
        if (target.length == 0) {
            return 0;
        }

        outer:
        for (int i = 0; i < array.length - target.length + 1; i++) {
            for (int j = 0; j < target.length; j++) {
                if (array[i + j] != target[j]) {
                    continue outer;
                }
            }
            return i;
        }
        return -1;
    }

    /**
     * Returns the index of the last appearance of the value {@code target} in {@code array}.
     *
     * @param array  an array of {@code long} values, possibly empty
     * @param target a primitive {@code long} value
     * @return the greatest index {@code i} for which {@code array[i] == target}, or {@code -1} if no
     * such index exists.
     */
    public static int lastIndexOf(long[] array, long target) {
        return lastIndexOf(array, target, 0, array.length);
    }

    // TODO(kevinb): consider making this public
    private static int lastIndexOf(long[] array, long target, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the least value present in {@code array}.
     *
     * @param array a <i>nonempty</i> array of {@code long} values
     * @return the value present in {@code array} that is less than or equal to every other value in
     * the array
     * @throws IllegalArgumentException if {@code array} is empty
     */
    public static long min(long... array) {
        Assert.checkArgument(array.length > 0);
        long min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * Returns the greatest value present in {@code array}.
     *
     * @param array a <i>nonempty</i> array of {@code long} values
     * @return the value present in {@code array} that is greater than or equal to every other value
     * in the array
     * @throws IllegalArgumentException if {@code array} is empty
     */
    public static long max(long... array) {
        Assert.checkArgument(array.length > 0);
        long max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Returns the value nearest to {@code value} which is within the closed range {@code [min..max]}.
     *
     * <p>If {@code value} is within the range {@code [min..max]}, {@code value} is returned
     * unchanged. If {@code value} is less than {@code min}, {@code min} is returned, and if {@code
     * value} is greater than {@code max}, {@code max} is returned.
     *
     * @param value the {@code long} value to constrain
     * @param min   the lower bound (inclusive) of the range to constrain {@code value} to
     * @param max   the upper bound (inclusive) of the range to constrain {@code value} to
     * @throws IllegalArgumentException if {@code min > max}
     */
    public static long constrainToRange(long value, long min, long max) {
        Assert.checkArgument(min <= max, "min must be less than or equal to max");
        return Math.min(Math.max(value, min), max);
    }

    /**
     * Returns the values from each provided array combined into a single array. For example, {@code
     * concat(new long[] {a, b}, new long[] {}, new long[] {c}} returns the array {@code {a, b, c}}.
     *
     * @param arrays zero or more {@code long} arrays
     * @return a single array containing all the values from the source arrays, in order
     */
    public static long[] concat(long[]... arrays) {
        int length = 0;
        for (long[] array : arrays) {
            length += array.length;
        }
        long[] result = new long[length];
        int pos = 0;
        for (long[] array : arrays) {
            System.arraycopy(array, 0, result, pos, array.length);
            pos += array.length;
        }
        return result;
    }

    /**
     * Returns a big-endian representation of {@code value} in an 8-element byte array; equivalent to
     * {@code ByteBuffer.allocate(8).putLong(value).array()}. For example, the input value {@code
     * 0x1213141516171819L} would yield the byte array {@code {0x12, 0x13, 0x14, 0x15, 0x16, 0x17,
     * 0x18, 0x19}}.
     */
    public static byte[] toByteArray(long value) {
        // Note that this code needs to stay compatible with GWT, which has known
        // bugs when narrowing byte casts of long values occur.
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte) (value & 0xffL);
            value >>= 8;
        }
        return result;
    }

    /**
     * Returns the {@code long} value whose big-endian representation is stored in the first 8 bytes
     * of {@code bytes}; equivalent to {@code ByteBuffer.wrap(bytes).getLong()}. For example, the
     * input byte array {@code {0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19}} would yield the
     * {@code long} value {@code 0x1213141516171819L}.
     *
     * <p>Arguably, it's preferable to use {@link java.nio.ByteBuffer}; that library exposes much more
     * flexibility at little cost in readability.
     *
     * @throws IllegalArgumentException if {@code bytes} has fewer than 8 elements
     */
    public static long fromByteArray(byte[] bytes) {
        Assert.checkArgument(bytes.length >= BYTES, "array too small");
        return fromBytes(
                bytes[0], bytes[1], bytes[2], bytes[3], bytes[4], bytes[5], bytes[6], bytes[7]);
    }

    /**
     * Returns the {@code long} value whose byte representation is the given 8 bytes, in big-endian
     * order; equivalent to {@code Longs.fromByteArray(new byte[] {b1, b2, b3, b4, b5, b6, b7, b8})}.
     */
    public static long fromBytes(
            byte b1, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        return (b1 & 0xFFL) << 56
                | (b2 & 0xFFL) << 48
                | (b3 & 0xFFL) << 40
                | (b4 & 0xFFL) << 32
                | (b5 & 0xFFL) << 24
                | (b6 & 0xFFL) << 16
                | (b7 & 0xFFL) << 8
                | (b8 & 0xFFL);
    }

    /*
     * Moving asciiDigits into this static holder class lets ProGuard eliminate and inline the Longs
     * class.
     */
    static final class AsciiDigits {
        private AsciiDigits() {
        }

        private static final byte[] asciiDigits;

        static {
            byte[] result = new byte[128];
            java.util.Arrays.fill(result, (byte) -1);
            for (int i = 0; i < 10; i++) {
                result['0' + i] = (byte) i;
            }
            for (int i = 0; i < 26; i++) {
                result['A' + i] = (byte) (10 + i);
                result['a' + i] = (byte) (10 + i);
            }
            asciiDigits = result;
        }

        static int digit(char c) {
            return (c < 128) ? asciiDigits[c] : -1;
        }
    }

    /**
     * Parses the specified string as a signed decimal long value. The ASCII character {@code '-'} (
     * <code>'&#92;u002D'</code>) is recognized as the minus sign.
     *
     * <p>Unlike {@link Long#parseLong(String)}, this method returns {@code null} instead of throwing
     * an exception if parsing fails. Additionally, this method only accepts ASCII digits, and returns
     * {@code null} if non-ASCII digits are present in the string.
     *
     * <p>Note that strings prefixed with ASCII {@code '+'} are rejected, even under JDK 7, despite
     * the change to {@link Long#parseLong(String)} for that version.
     *
     * @param string the string representation of a long value
     * @return the long value represented by {@code string}, or {@code null} if {@code string} has a
     * length of zero or cannot be parsed as a long value
     * @throws NullPointerException if {@code string} is {@code null}
     */
    public static Long tryParse(String string) {
        return tryParse(string, 10);
    }

    /**
     * Parses the specified string as a signed long value using the specified radix. The ASCII
     * character {@code '-'} (<code>'&#92;u002D'</code>) is recognized as the minus sign.
     *
     * <p>Unlike {@link Long#parseLong(String, int)}, this method returns {@code null} instead of
     * throwing an exception if parsing fails. Additionally, this method only accepts ASCII digits,
     * and returns {@code null} if non-ASCII digits are present in the string.
     *
     * <p>Note that strings prefixed with ASCII {@code '+'} are rejected, even under JDK 7, despite
     * the change to {@link Long#parseLong(String, int)} for that version.
     *
     * @param string the string representation of an long value
     * @param radix  the radix to use when parsing
     * @return the long value represented by {@code string} using {@code radix}, or {@code null} if
     * {@code string} has a length of zero or cannot be parsed as a long value
     * @throws IllegalArgumentException if {@code radix < Character.MIN_RADIX} or {@code radix >
     *                                  Character.MAX_RADIX}
     * @throws NullPointerException     if {@code string} is {@code null}
     */
    public static Long tryParse(String string, int radix) {
        if (java.util.Objects.requireNonNull(string).isEmpty()) {
            return null;
        }
        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX) {
            throw new IllegalArgumentException(
                    "radix must be between MIN_RADIX and MAX_RADIX but was " + radix);
        }
        boolean negative = string.charAt(0) == '-';
        int index = negative ? 1 : 0;
        if (index == string.length()) {
            return null;
        }
        int digit = AsciiDigits.digit(string.charAt(index++));
        if (digit < 0 || digit >= radix) {
            return null;
        }
        long accum = -digit;

        long cap = Long.MIN_VALUE / radix;

        while (index < string.length()) {
            digit = AsciiDigits.digit(string.charAt(index++));
            if (digit < 0 || digit >= radix || accum < cap) {
                return null;
            }
            accum *= radix;
            if (accum < Long.MIN_VALUE + digit) {
                return null;
            }
            accum -= digit;
        }

        if (negative) {
            return accum;
        } else if (accum == Long.MIN_VALUE) {
            return null;
        } else {
            return -accum;
        }
    }

    /**
     * Returns an array containing the same values as {@code array}, but guaranteed to be of a
     * specified minimum length. If {@code array} already has a length of at least {@code minLength},
     * it is returned directly. Otherwise, a new array of size {@code minLength + padding} is
     * returned, containing the values of {@code array}, and zeroes in the remaining places.
     *
     * @param array     the source array
     * @param minLength the minimum length the returned array must guarantee
     * @param padding   an extra amount to "grow" the array by if growth is necessary
     * @return an array containing the values of {@code array}, with guaranteed minimum length {@code
     * minLength}
     * @throws IllegalArgumentException if {@code minLength} or {@code padding} is negative
     */
    public static long[] ensureCapacity(long[] array, int minLength, int padding) {
        Assert.checkArgument(minLength >= 0, "Invalid minLength: " + minLength);
        Assert.checkArgument(padding >= 0, "Invalid padding: " + padding);
        return (array.length < minLength) ? java.util.Arrays.copyOf(array, minLength + padding) : array;
    }

    /**
     * Returns a string containing the supplied {@code long} values separated by {@code separator}.
     * For example, {@code join("-", 1L, 2L, 3L)} returns the string {@code "1-2-3"}.
     *
     * @param separator the text that should appear between consecutive values in the resulting string
     *                  (but not at the start or end)
     * @param array     an array of {@code long} values, possibly empty
     */
    public static String join(String separator, long... array) {
        java.util.Objects.requireNonNull(separator);
        if (array.length == 0) {
            return "";
        }

        // For pre-sizing a builder, just get the right order of magnitude
        StringBuilder builder = new StringBuilder(array.length * 10);
        builder.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            builder.append(separator).append(array[i]);
        }
        return builder.toString();
    }

    /**
     * Returns a comparator that compares two {@code long} arrays <a
     * href="http://en.wikipedia.org/wiki/Lexicographical_order">lexicographically</a>. That is, it
     * compares, using {@link #compare(long, long)}), the first pair of values that follow any common
     * prefix, or when one array is a prefix of the other, treats the shorter array as the lesser. For
     * example, {@code [] < [1L] < [1L, 2L] < [2L]}.
     *
     * <p>The returned comparator is inconsistent with {@link Object#equals(Object)} (since arrays
     * support only identity equality), but it is consistent with {@link java.util.Arrays#equals(long[],
     * long[])}.
     */
    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    private enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        @Override
        public int compare(long[] left, long[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; i++) {
                int result = Longs.compare(left[i], right[i]);
                if (result != 0) {
                    return result;
                }
            }
            return left.length - right.length;
        }

        @Override
        public String toString() {
            return "Longs.lexicographicalComparator()";
        }
    }

    /**
     * Sorts the elements of {@code array} in descending order.
     */
    public static void sortDescending(long[] array) {
        java.util.Objects.requireNonNull(array);
        sortDescending(array, 0, array.length);
    }

    /**
     * Sorts the elements of {@code array} between {@code fromIndex} inclusive and {@code toIndex}
     * exclusive in descending order.
     */
    public static void sortDescending(long[] array, int fromIndex, int toIndex) {
        java.util.Objects.requireNonNull(array);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }

    /**
     * Reverses the elements of {@code array}. This is equivalent to {@code
     * Collections.reverse(Longs.asList(array))}, but is likely to be more efficient.
     */
    public static void reverse(long[] array) {
        java.util.Objects.requireNonNull(array);
        reverse(array, 0, array.length);
    }

    /**
     * Reverses the elements of {@code array} between {@code fromIndex} inclusive and {@code toIndex}
     * exclusive. This is equivalent to {@code
     * Collections.reverse(Longs.asList(array).subList(fromIndex, toIndex))}, but is likely to be more
     * efficient.
     *
     * @throws IndexOutOfBoundsException if {@code fromIndex < 0}, {@code toIndex > array.length}, or
     *                                   {@code toIndex > fromIndex}
     */
    public static void reverse(long[] array, int fromIndex, int toIndex) {
        java.util.Objects.requireNonNull(array);
        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            long tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

}
