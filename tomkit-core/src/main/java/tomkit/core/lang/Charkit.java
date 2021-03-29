package tomkit.core.lang;

/**
 * 字符工具类
 *
 * @author yh
 * @since 2021/2/3
 */
public final class Charkit {

    private static final char[] ASCII_CHAR_ARRAY = new char[128];

    private static final String[] ASCII_CHAR_STR_ARRAY = new String[128];
    /**
     * ascii码最小字符十进制值
     */
    private static final int MIN_ASCII = 0x0000;
    /**
     * ascii码最大字符十进制值
     */
    private static final int MAX_ASCII = 0x007F;
    /**
     * 最小字符
     */
    private static final int MIN_CHAR = 0x0000;
    /**
     * 最大字符
     */
    private static final int MAX_CHAR = 0xFFFF;

    static {
        for (int i = MIN_ASCII; i <= MAX_ASCII; i++) {
            ASCII_CHAR_ARRAY[i] = (char) i;
            ASCII_CHAR_STR_ARRAY[i] = String.valueOf((char) i);
        }
    }

    private Charkit() {
    }

    public static char[] getAsciiCharArray() {
        return ASCII_CHAR_ARRAY;
    }

    public static char getChar(int i) {
        return ASCII_CHAR_ARRAY[i];
    }

    public static String getCharStr(int i) {
        return ASCII_CHAR_STR_ARRAY[i];
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Converts the Character to a char throwing an exception for <code>null</code>.</p>
     *
     * <pre>
     *   Chars.toChar(null) = IllegalArgumentException
     *   Chars.toChar(' ')  = ' '
     *   Chars.toChar('A')  = 'A'
     * </pre>
     *
     * @param ch the character to convert
     * @return the char value of the Character
     * @throws IllegalArgumentException if the Character is null
     */
    public static char toChar(Character ch) {
        if (ch == null) {
            throw new IllegalArgumentException("The Character must not be null");
        }
        return ch;
    }

    /**
     * <p>Converts the Character to a char handling <code>null</code>.</p>
     *
     * <pre>
     *   Chars.toChar(null, 'X') = 'X'
     *   Chars.toChar(' ', 'X')  = ' '
     *   Chars.toChar('A', 'X')  = 'A'
     * </pre>
     *
     * @param ch           the character to convert
     * @param defaultValue the value to use if the  Character is null
     * @return the char value of the Character or the default if null
     */
    public static char toChar(Character ch, char defaultValue) {
        if (ch == null) {
            return defaultValue;
        }
        return ch;
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Converts the character to the Integer it represents, throwing an
     * exception if the character is not numeric.</p>
     *
     * <p>This method coverts the char '1' to the int 1 and so on.</p>
     *
     * <pre>
     *   Chars.toIntValue('3')  = 3
     *   Chars.toIntValue('A')  = IllegalArgumentException
     * </pre>
     *
     * @param ch the character to convert
     * @return the int value of the character
     * @throws IllegalArgumentException if the character is not ASCII numeric
     */
    public static int toIntValue(char ch) {
        if (!isAsciiNumeric(ch)) {
            throw new IllegalArgumentException("The character " + ch + " is not in the range '0' - '9'");
        }
        return ch - 48;
    }

    /**
     * <p>Converts the character to the Integer it represents, throwing an
     * exception if the character is not numeric.</p>
     *
     * <p>This method coverts the char '1' to the int 1 and so on.</p>
     *
     * <pre>
     *   Chars.toIntValue('3', -1)  = 3
     *   Chars.toIntValue('A', -1)  = -1
     * </pre>
     *
     * @param ch           the character to convert
     * @param defaultValue the default value to use if the character is not numeric
     * @return the int value of the character
     */
    public static int toIntValue(char ch, int defaultValue) {
        if (!isAsciiNumeric(ch)) {
            return defaultValue;
        }
        return ch - 48;
    }

    /**
     * <p>Converts the character to the Integer it represents, throwing an
     * exception if the character is not numeric.</p>
     *
     * <p>This method coverts the char '1' to the int 1 and so on.</p>
     *
     * <pre>
     *   Chars.toIntValue(null) = IllegalArgumentException
     *   Chars.toIntValue('3')  = 3
     *   Chars.toIntValue('A')  = IllegalArgumentException
     * </pre>
     *
     * @param ch the character to convert, not null
     * @return the int value of the character
     * @throws IllegalArgumentException if the Character is not ASCII numeric or is null
     */
    public static int toIntValue(Character ch) {
        if (ch == null) {
            throw new IllegalArgumentException("The character must not be null");
        }
        return toIntValue(ch.charValue());
    }

    /**
     * <p>Converts the character to the Integer it represents, throwing an
     * exception if the character is not numeric.</p>
     *
     * <p>This method coverts the char '1' to the int 1 and so on.</p>
     *
     * <pre>
     *   Chars.toIntValue(null, -1) = -1
     *   Chars.toIntValue('3', -1)  = 3
     *   Chars.toIntValue('A', -1)  = -1
     * </pre>
     *
     * @param ch           the character to convert
     * @param defaultValue the default value to use if the character is not numeric
     * @return the int value of the character
     */
    public static int toIntValue(Character ch, int defaultValue) {
        if (ch == null) {
            return defaultValue;
        }
        return toIntValue(ch.charValue(), defaultValue);
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Converts the character to a String that contains the one character.</p>
     *
     * <p>For ASCII 7 bit characters, this uses a cache that will return the
     * same String object each time.</p>
     *
     * <pre>
     *   Chars.toString(' ')  = " "
     *   Chars.toString('A')  = "A"
     * </pre>
     *
     * @param ch the character to convert
     * @return a String containing the one specified character
     */
    public static String toString(char ch) {
        if (ch < 128) {
            return ASCII_CHAR_STR_ARRAY[ch];
        }
        return new String(new char[]{ch});
    }

    /**
     * <p>Converts the character to a String that contains the one character.</p>
     *
     * <p>For ASCII 7 bit characters, this uses a cache that will return the
     * same String object each time.</p>
     *
     * <p>If <code>null</code> is passed in, <code>null</code> will be returned.</p>
     *
     * <pre>
     *   Chars.toString(null) = null
     *   Chars.toString(' ')  = " "
     *   Chars.toString('A')  = "A"
     * </pre>
     *
     * @param ch the character to convert
     * @return a String containing the one specified character
     */
    public static String toString(Character ch) {
        if (ch == null) {
            return null;
        }
        return toString(ch.charValue());
    }

    //--------------------------------------------------------------------------

    /**
     * <p>Converts the string to the unicode format '\u0020'.</p>
     *
     * <p>This format is the Java source code format.</p>
     *
     * <pre>
     *   Chars.unicodeEscaped(' ') = "\u0020"
     *   Chars.unicodeEscaped('A') = "\u0041"
     * </pre>
     *
     * @param ch the character to convert
     * @return the escaped unicode string
     */
    public static String unicodeEscaped(char ch) {
        String hex = Integer.toHexString(ch);
        switch (hex.length()) {
            case 1:
                return "\\u000" + hex;
            case 2:
                return "\\u00" + hex;
            case 3:
                return "\\u0" + hex;
            default:
                return "\\u" + hex;
        }
    }

    /**
     * <p>Converts the string to the unicode format '\u0020'.</p>
     *
     * <p>This format is the Java source code format.</p>
     *
     * <p>If <code>null</code> is passed in, <code>null</code> will be returned.</p>
     *
     * <pre>
     *   Chars.unicodeEscaped(null) = null
     *   Chars.unicodeEscaped(' ')  = "\u0020"
     *   Chars.unicodeEscaped('A')  = "\u0041"
     * </pre>
     *
     * @param ch the character to convert, may be null
     * @return the escaped unicode string, null if null input
     */
    public static String unicodeEscaped(Character ch) {
        if (ch == null) {
            return null;
        }
        return unicodeEscaped(ch.charValue());
    }

    //--------------------------------------------------------------------------

    /**
     * <p>Checks whether the character is ASCII 7 bit.</p>
     *
     * <pre>
     *   Chars.isAscii('a')  = true
     *   Chars.isAscii('A')  = true
     *   Chars.isAscii('3')  = true
     *   Chars.isAscii('-')  = true
     *   Chars.isAscii('\n') = true
     *   Chars.isAscii('&copy;') = false
     * </pre>
     *
     * @param ch the character to check
     * @return true if less than 128
     */
    public static boolean isAscii(char ch) {
        return ch < 128;
    }

    /**
     * <p>Checks whether the character is ASCII 7 bit printable.</p>
     *
     * <pre>
     *   Chars.isAsciiPrintable('a')  = true
     *   Chars.isAsciiPrintable('A')  = true
     *   Chars.isAsciiPrintable('3')  = true
     *   Chars.isAsciiPrintable('-')  = true
     *   Chars.isAsciiPrintable('\n') = false
     *   Chars.isAsciiPrintable('&copy;') = false
     * </pre>
     *
     * @param ch the character to check
     * @return true if between 32 and 126 inclusive
     */
    public static boolean isAsciiPrintable(char ch) {
        return ch >= 32 && ch < 127;
    }

    /**
     * <p>Checks whether the character is ASCII 7 bit control.</p>
     *
     * <pre>
     *   Chars.isAsciiControl('a')  = false
     *   Chars.isAsciiControl('A')  = false
     *   Chars.isAsciiControl('3')  = false
     *   Chars.isAsciiControl('-')  = false
     *   Chars.isAsciiControl('\n') = true
     *   Chars.isAsciiControl('&copy;') = false
     * </pre>
     *
     * @param ch the character to check
     * @return true if less than 32 or equals 127
     */
    public static boolean isAsciiControl(char ch) {
        return ch < 32 || ch == 127;
    }

    /**
     * <p>Checks whether the character is ASCII 7 bit alphabetic.</p>
     *
     * <pre>
     *   Chars.isAsciiAlpha('a')  = true
     *   Chars.isAsciiAlpha('A')  = true
     *   Chars.isAsciiAlpha('3')  = false
     *   Chars.isAsciiAlpha('-')  = false
     *   Chars.isAsciiAlpha('\n') = false
     *   Chars.isAsciiAlpha('&copy;') = false
     * </pre>
     *
     * @param ch the character to check
     * @return true if between 65 and 90 or 97 and 122 inclusive
     */
    public static boolean isAsciiAlpha(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    /**
     * <p>Checks whether the character is ASCII 7 bit alphabetic upper case.</p>
     *
     * <pre>
     *   Chars.isAsciiAlphaUpper('a')  = false
     *   Chars.isAsciiAlphaUpper('A')  = true
     *   Chars.isAsciiAlphaUpper('3')  = false
     *   Chars.isAsciiAlphaUpper('-')  = false
     *   Chars.isAsciiAlphaUpper('\n') = false
     *   Chars.isAsciiAlphaUpper('&copy;') = false
     * </pre>
     *
     * @param ch the character to check
     * @return true if between 65 and 90 inclusive
     */
    public static boolean isAsciiAlphaUpper(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    /**
     * <p>Checks whether the character is ASCII 7 bit alphabetic lower case.</p>
     *
     * <pre>
     *   Chars.isAsciiAlphaLower('a')  = true
     *   Chars.isAsciiAlphaLower('A')  = false
     *   Chars.isAsciiAlphaLower('3')  = false
     *   Chars.isAsciiAlphaLower('-')  = false
     *   Chars.isAsciiAlphaLower('\n') = false
     *   Chars.isAsciiAlphaLower('&copy;') = false
     * </pre>
     *
     * @param ch the character to check
     * @return true if between 97 and 122 inclusive
     */
    public static boolean isAsciiAlphaLower(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    /**
     * <p>Checks whether the character is ASCII 7 bit numeric.</p>
     *
     * <pre>
     *   Chars.isAsciiNumeric('a')  = false
     *   Chars.isAsciiNumeric('A')  = false
     *   Chars.isAsciiNumeric('3')  = true
     *   Chars.isAsciiNumeric('-')  = false
     *   Chars.isAsciiNumeric('\n') = false
     *   Chars.isAsciiNumeric('&copy;') = false
     * </pre>
     *
     * @param ch the character to check
     * @return true if between 48 and 57 inclusive
     */
    public static boolean isAsciiNumeric(char ch) {
        return ch >= '0' && ch <= '9';
    }

    /**
     * <p>Checks whether the character is ASCII 7 bit numeric.</p>
     *
     * <pre>
     *   Chars.isAsciiAlphanumeric('a')  = true
     *   Chars.isAsciiAlphanumeric('A')  = true
     *   Chars.isAsciiAlphanumeric('3')  = true
     *   Chars.isAsciiAlphanumeric('-')  = false
     *   Chars.isAsciiAlphanumeric('\n') = false
     *   Chars.isAsciiAlphanumeric('&copy;') = false
     * </pre>
     *
     * @param ch the character to check
     * @return true if between 48 and 57 or 65 and 90 or 97 and 122 inclusive
     */
    public static boolean isAsciiAlphanumeric(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9');
    }

}
