package tomkit.core.codec.digest;

/**
 * @author yh
 * @since 2021/4/7
 */
public final class MessageDigestAlgorithms {

    /**
     * The MD2 message digest algorithm defined in RFC 1319.
     */
    public static final String MD2 = "MD2";

    /**
     * The MD5 message digest algorithm defined in RFC 1321.
     */
    public static final String MD5 = "MD5";

    /**
     * The SHA-1 hash algorithm defined in the FIPS PUB 180-2.
     */
    public static final String SHA_1 = "SHA-1";

    /**
     * The SHA-224 hash algorithm defined in the FIPS PUB 180-3.
     * <p>
     * Present in Oracle Java 8.
     * </p>
     *
     * @since 1.11
     */
    public static final String SHA_224 = "SHA-224";

    /**
     * The SHA-256 hash algorithm defined in the FIPS PUB 180-2.
     */
    public static final String SHA_256 = "SHA-256";

    /**
     * The SHA-384 hash algorithm defined in the FIPS PUB 180-2.
     */
    public static final String SHA_384 = "SHA-384";

    /**
     * The SHA-512 hash algorithm defined in the FIPS PUB 180-2.
     */
    public static final String SHA_512 = "SHA-512";

    /**
     * The SHA-512 hash algorithm defined in the FIPS PUB 180-4.
     * <p>
     * Included starting in Oracle Java 9.
     * </p>
     *
     * @since 1.14
     */
    public static final String SHA_512_224 = "SHA-512/224";

    /**
     * The SHA-512 hash algorithm defined in the FIPS PUB 180-4.
     * <p>
     * Included starting in Oracle Java 9.
     * </p>
     *
     * @since 1.14
     */
    public static final String SHA_512_256 = "SHA-512/256";

    /**
     * The SHA3-224 hash algorithm defined in the FIPS PUB 202.
     * <p>
     * Included starting in Oracle Java 9.
     * </p>
     *
     * @since 1.11
     */
    public static final String SHA3_224 = "SHA3-224";

    /**
     * The SHA3-256 hash algorithm defined in the FIPS PUB 202.
     * <p>
     * Included starting in Oracle Java 9.
     * </p>
     *
     * @since 1.11
     */
    public static final String SHA3_256 = "SHA3-256";

    /**
     * The SHA3-384 hash algorithm defined in the FIPS PUB 202.
     * <p>
     * Included starting in Oracle Java 9.
     * </p>
     *
     * @since 1.11
     */
    public static final String SHA3_384 = "SHA3-384";

    /**
     * The SHA3-512 hash algorithm defined in the FIPS PUB 202.
     * <p>
     * Included starting in Oracle Java 9.
     * </p>
     *
     * @since 1.11
     */
    public static final String SHA3_512 = "SHA3-512";

    /**
     * Gets all constant values defined in this class.
     *
     * @return all constant values defined in this class.
     * @since 1.11
     */
    public static String[] values() {
        // N.B. do not use a constant array here as that can be changed externally by accident or design
        return new String[]{
                MD2, MD5, SHA_1, SHA_224, SHA_256, SHA_384, SHA_512, SHA_512_224, SHA_512_256, SHA3_224, SHA3_256, SHA3_384, SHA3_512
        };
    }

    private MessageDigestAlgorithms() {
        // cannot be instantiated.
    }

}
