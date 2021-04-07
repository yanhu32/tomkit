package tomkit.core.codec.digest;

import tomkit.core.codec.binary.Hex;
import tomkit.core.lang.Strings;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 摘要算法加密工具类
 *
 * @author yh
 * @since 2021/4/7
 */
public final class Digests {

    private static final int STREAM_BUFFER_LENGTH = 1024;

    /**
     * Reads through a byte array and returns the digest for the data. Provided for symmetry with other methods.
     *
     * @param messageDigest The MessageDigest to use (e.g. MD5)
     * @param data          Data to digest
     * @return the digest
     */
    public static byte[] digest(final MessageDigest messageDigest, final byte[] data) {
        return messageDigest.digest(data);
    }

    /**
     * Reads through a ByteBuffer and returns the digest for the data
     *
     * @param messageDigest The MessageDigest to use (e.g. MD5)
     * @param data          Data to digest
     * @return the digest
     */
    public static byte[] digest(final MessageDigest messageDigest, final ByteBuffer data) {
        messageDigest.update(data);
        return messageDigest.digest();
    }

    /**
     * Reads through a File and returns the digest for the data
     *
     * @param messageDigest The MessageDigest to use (e.g. MD5)
     * @param data          Data to digest
     * @return the digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] digest(final MessageDigest messageDigest, final File data) throws IOException {
        return updateDigest(messageDigest, data).digest();
    }

    /**
     * Reads through an InputStream and returns the digest for the data
     *
     * @param messageDigest The MessageDigest to use (e.g. MD5)
     * @param data          Data to digest
     * @return the digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] digest(final MessageDigest messageDigest, final InputStream data) throws IOException {
        return updateDigest(messageDigest, data).digest();
    }

    /**
     * Reads through a File and returns the digest for the data
     *
     * @param messageDigest The MessageDigest to use (e.g. MD5)
     * @param data          Data to digest
     * @param options       options How to open the file
     * @return the digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] digest(final MessageDigest messageDigest, final Path data, final OpenOption... options)
            throws IOException {
        return updateDigest(messageDigest, data, options).digest();
    }

    /**
     * Reads through a RandomAccessFile using non-blocking-io (NIO) and returns the digest for the data
     *
     * @param messageDigest The MessageDigest to use (e.g. MD5)
     * @param data          Data to digest
     * @return the digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] digest(final MessageDigest messageDigest, final RandomAccessFile data) throws IOException {
        return updateDigest(messageDigest, data).digest();
    }

    /**
     * Returns a {@code MessageDigest} for the given {@code algorithm}.
     *
     * @param algorithm the name of the algorithm requested. See <a
     *                  href="http://docs.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#AppA"
     *                  >Appendix A in the Java Cryptography Architecture Reference Guide</a> for information about standard
     *                  algorithm names.
     * @return A digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught.
     * @see MessageDigest#getInstance(String)
     */
    public static MessageDigest getDigest(final String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Returns a {@code MessageDigest} for the given {@code algorithm} or a default if there is a problem
     * getting the algorithm.
     *
     * @param algorithm            the name of the algorithm requested. See
     *                             <a href="http://docs.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#AppA" >
     *                             Appendix A in the Java Cryptography Architecture Reference Guide</a> for information about standard
     *                             algorithm names.
     * @param defaultMessageDigest The default MessageDigest.
     * @return A digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught.
     * @see MessageDigest#getInstance(String)
     */
    public static MessageDigest getDigest(final String algorithm, final MessageDigest defaultMessageDigest) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (final Exception e) {
            return defaultMessageDigest;
        }
    }

    /**
     * Returns an MD2 MessageDigest.
     *
     * @return An MD2 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught, which should never happen because MD2 is a
     *                                  built-in algorithm
     * @see MessageDigestAlgorithms#MD2
     */
    public static MessageDigest getMd2Digest() {
        return getDigest(MessageDigestAlgorithms.MD2);
    }

    /**
     * Returns an MD5 MessageDigest.
     *
     * @return An MD5 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught, which should never happen because MD5 is a
     *                                  built-in algorithm
     * @see MessageDigestAlgorithms#MD5
     */
    public static MessageDigest getMd5Digest() {
        return getDigest(MessageDigestAlgorithms.MD5);
    }

    /**
     * Returns an SHA-1 digest.
     *
     * @return An SHA-1 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught, which should never happen because SHA-1 is a
     *                                  built-in algorithm
     * @see MessageDigestAlgorithms#SHA_1
     */
    public static MessageDigest getSha1Digest() {
        return getDigest(MessageDigestAlgorithms.SHA_1);
    }

    /**
     * Returns an SHA-256 digest.
     *
     * @return An SHA-256 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught, which should never happen because SHA-256 is a
     *                                  built-in algorithm
     * @see MessageDigestAlgorithms#SHA_256
     */
    public static MessageDigest getSha256Digest() {
        return getDigest(MessageDigestAlgorithms.SHA_256);
    }

    /**
     * Returns an SHA3-224 digest.
     *
     * @return An SHA3-224 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught, which should not happen on
     *                                  Oracle Java 9 andgreater.
     * @see MessageDigestAlgorithms#SHA3_224
     */
    public static MessageDigest getSha3_224Digest() {
        return getDigest(MessageDigestAlgorithms.SHA3_224);
    }

    /**
     * Returns an SHA3-256 digest.
     *
     * @return An SHA3-256 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught, which should not happen on
     *                                  Oracle Java 9 and greater.
     * @see MessageDigestAlgorithms#SHA3_256
     */
    public static MessageDigest getSha3_256Digest() {
        return getDigest(MessageDigestAlgorithms.SHA3_256);
    }

    /**
     * Returns an SHA3-384 digest.
     *
     * @return An SHA3-384 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught, which should not happen on
     *                                  Oracle Java 9 and greater.
     * @see MessageDigestAlgorithms#SHA3_384
     */
    public static MessageDigest getSha3_384Digest() {
        return getDigest(MessageDigestAlgorithms.SHA3_384);
    }

    /**
     * Returns an SHA3-512 digest.
     *
     * @return An SHA3-512 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught, which should not happen
     *                                  on Oracle Java 9 and greater.
     * @see MessageDigestAlgorithms#SHA3_512
     */
    public static MessageDigest getSha3_512Digest() {
        return getDigest(MessageDigestAlgorithms.SHA3_512);
    }

    /**
     * Returns an SHA-384 digest.
     *
     * @return An SHA-384 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught, which should never happen
     *                                  because SHA-384 is a built-in algorithm
     * @see MessageDigestAlgorithms#SHA_384
     */
    public static MessageDigest getSha384Digest() {
        return getDigest(MessageDigestAlgorithms.SHA_384);
    }

    /**
     * Returns an SHA-512/224 digest.
     *
     * @return An SHA-512/224 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught.
     * @see MessageDigestAlgorithms#SHA_512_224
     */
    public static MessageDigest getSha512_224Digest() {
        return getDigest(MessageDigestAlgorithms.SHA_512_224);
    }

    /**
     * Returns an SHA-512/256 digest.
     *
     * @return An SHA-512/256 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught.
     * @see MessageDigestAlgorithms#SHA_512_224
     */
    public static MessageDigest getSha512_256Digest() {
        return getDigest(MessageDigestAlgorithms.SHA_512_256);
    }

    /**
     * Returns an SHA-512 digest.
     *
     * @return An SHA-512 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught, which should never happen
     *                                  because SHA-512 is a built-in algorithm
     * @see MessageDigestAlgorithms#SHA_512
     */
    public static MessageDigest getSha512Digest() {
        return getDigest(MessageDigestAlgorithms.SHA_512);
    }

    /**
     * Returns an SHA-1 digest.
     *
     * @return An SHA-1 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught
     * @deprecated (1.11) Use {@link #getSha1Digest()}
     */
    @Deprecated
    public static MessageDigest getShaDigest() {
        return getSha1Digest();
    }

    /**
     * Test whether the algorithm is supported.
     *
     * @param messageDigestAlgorithm the algorithm name
     * @return {@code true} if the algorithm can be found
     */
    public static boolean isAvailable(final String messageDigestAlgorithm) {
        return getDigest(messageDigestAlgorithm, null) != null;
    }

    /**
     * Calculates the MD2 digest and returns the value as a 16 element {@code byte[]}.
     *
     * @param data Data to digest
     * @return MD2 digest
     */
    public static byte[] md2(final byte[] data) {
        return getMd2Digest().digest(data);
    }

    /**
     * Calculates the MD2 digest and returns the value as a 16 element {@code byte[]}.
     *
     * @param data Data to digest
     * @return MD2 digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] md2(final InputStream data) throws IOException {
        return digest(getMd2Digest(), data);
    }

    /**
     * Calculates the MD2 digest and returns the value as a 16 element {@code byte[]}.
     *
     * @param data Data to digest; converted to bytes using {@link tomkit.core.lang.Strings#getBytes(String)} }
     * @return MD2 digest
     */
    public static byte[] md2(final String data) {
        return md2(Strings.getBytes(data));
    }

    /**
     * Calculates the MD2 digest and returns the value as a 32 character hex string.
     *
     * @param data Data to digest
     * @return MD2 digest as a hex string
     */
    public static String md2Hex(final byte[] data) {
        return Hex.encodeHexString(md2(data));
    }

    /**
     * Calculates the MD2 digest and returns the value as a 32 character hex string.
     *
     * @param data Data to digest
     * @return MD2 digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public static String md2Hex(final InputStream data) throws IOException {
        return Hex.encodeHexString(md2(data));
    }

    /**
     * Calculates the MD2 digest and returns the value as a 32 character hex string.
     *
     * @param data Data to digest
     * @return MD2 digest as a hex string
     */
    public static String md2Hex(final String data) {
        return Hex.encodeHexString(md2(data));
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element {@code byte[]}.
     *
     * @param data Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(final byte[] data) {
        return getMd5Digest().digest(data);
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element {@code byte[]}.
     *
     * @param data Data to digest
     * @return MD5 digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] md5(final InputStream data) throws IOException {
        return digest(getMd5Digest(), data);
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element {@code byte[]}.
     *
     * @param data Data to digest; converted to bytes using {@link Strings#getBytes(String)}}
     * @return MD5 digest
     */
    public static byte[] md5(final String data) {
        return md5(Strings.getBytes(data));
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex string.
     *
     * @param data Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(final byte[] data) {
        return Hex.encodeHexString(md5(data));
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex string.
     *
     * @param data Data to digest
     * @return MD5 digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public static String md5Hex(final InputStream data) throws IOException {
        return Hex.encodeHexString(md5(data));
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex string.
     *
     * @param data Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(final String data) {
        return Hex.encodeHexString(md5(data));
    }

    /**
     * Calculates the SHA-1 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA-1 digest
     */
    public static byte[] sha1(final byte[] data) {
        return getSha1Digest().digest(data);
    }

    /**
     * Calculates the SHA-1 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA-1 digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] sha1(final InputStream data) throws IOException {
        return digest(getSha1Digest(), data);
    }

    /**
     * Calculates the SHA-1 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest; converted to bytes using {@link Strings#getBytes(String)}
     * @return SHA-1 digest
     */
    public static byte[] sha1(final String data) {
        return sha1(Strings.getBytes(data));
    }

    /**
     * Calculates the SHA-1 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-1 digest as a hex string
     */
    public static String sha1Hex(final byte[] data) {
        return Hex.encodeHexString(sha1(data));
    }

    /**
     * Calculates the SHA-1 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-1 digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public static String sha1Hex(final InputStream data) throws IOException {
        return Hex.encodeHexString(sha1(data));
    }

    /**
     * Calculates the SHA-1 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-1 digest as a hex string
     */
    public static String sha1Hex(final String data) {
        return Hex.encodeHexString(sha1(data));
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA-256 digest
     */
    public static byte[] sha256(final byte[] data) {
        return getSha256Digest().digest(data);
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA-256 digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] sha256(final InputStream data) throws IOException {
        return digest(getSha256Digest(), data);
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest; converted to bytes using {@link Strings#getBytes(String)}
     * @return SHA-256 digest
     */
    public static byte[] sha256(final String data) {
        return sha256(Strings.getBytes(data));
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-256 digest as a hex string
     */
    public static String sha256Hex(final byte[] data) {
        return Hex.encodeHexString(sha256(data));
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-256 digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public static String sha256Hex(final InputStream data) throws IOException {
        return Hex.encodeHexString(sha256(data));
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-256 digest as a hex string
     */
    public static String sha256Hex(final String data) {
        return Hex.encodeHexString(sha256(data));
    }

    /**
     * Calculates the SHA3-224 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA3-224 digest
     */
    public static byte[] sha3_224(final byte[] data) {
        return getSha3_224Digest().digest(data);
    }

    /**
     * Calculates the SHA3-224 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA3-224 digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] sha3_224(final InputStream data) throws IOException {
        return digest(getSha3_224Digest(), data);
    }

    /**
     * Calculates the SHA3-224 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest; converted to bytes using {@link Strings#getBytes(String)}
     * @return SHA3-224 digest
     */
    public static byte[] sha3_224(final String data) {
        return sha3_224(Strings.getBytes(data));
    }

    /**
     * Calculates the SHA3-224 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA3-224 digest as a hex string
     */
    public static String sha3_224Hex(final byte[] data) {
        return Hex.encodeHexString(sha3_224(data));
    }

    /**
     * Calculates the SHA3-224 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA3-224 digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public static String sha3_224Hex(final InputStream data) throws IOException {
        return Hex.encodeHexString(sha3_224(data));
    }

    /**
     * Calculates the SHA3-224 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA3-224 digest as a hex string
     */
    public static String sha3_224Hex(final String data) {
        return Hex.encodeHexString(sha3_224(data));
    }

    /**
     * Calculates the SHA3-256 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA3-256 digest
     */
    public static byte[] sha3_256(final byte[] data) {
        return getSha3_256Digest().digest(data);
    }

    /**
     * Calculates the SHA3-256 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA3-256 digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] sha3_256(final InputStream data) throws IOException {
        return digest(getSha3_256Digest(), data);
    }

    /**
     * Calculates the SHA3-256 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest; converted to bytes using {@link Strings#getBytes(String)}
     * @return SHA3-256 digest
     */
    public static byte[] sha3_256(final String data) {
        return sha3_256(Strings.getBytes(data));
    }

    /**
     * Calculates the SHA3-256 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA3-256 digest as a hex string
     */
    public static String sha3_256Hex(final byte[] data) {
        return Hex.encodeHexString(sha3_256(data));
    }

    /**
     * Calculates the SHA3-256 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA3-256 digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public static String sha3_256Hex(final InputStream data) throws IOException {
        return Hex.encodeHexString(sha3_256(data));
    }

    /**
     * Calculates the SHA3-256 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA3-256 digest as a hex string
     */
    public static String sha3_256Hex(final String data) {
        return Hex.encodeHexString(sha3_256(data));
    }

    /**
     * Calculates the SHA3-384 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA3-384 digest
     */
    public static byte[] sha3_384(final byte[] data) {
        return getSha3_384Digest().digest(data);
    }

    /**
     * Calculates the SHA3-384 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA3-384 digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] sha3_384(final InputStream data) throws IOException {
        return digest(getSha3_384Digest(), data);
    }

    /**
     * Calculates the SHA3-384 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest; converted to bytes using {@link Strings#getBytes(String)}
     * @return SHA3-384 digest
     */
    public static byte[] sha3_384(final String data) {
        return sha3_384(Strings.getBytes(data));
    }

    /**
     * Calculates the SHA3-384 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA3-384 digest as a hex string
     */
    public static String sha3_384Hex(final byte[] data) {
        return Hex.encodeHexString(sha3_384(data));
    }

    /**
     * Calculates the SHA3-384 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA3-384 digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public static String sha3_384Hex(final InputStream data) throws IOException {
        return Hex.encodeHexString(sha3_384(data));
    }

    /**
     * Calculates the SHA3-384 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA3-384 digest as a hex string
     */
    public static String sha3_384Hex(final String data) {
        return Hex.encodeHexString(sha3_384(data));
    }

    /**
     * Calculates the SHA3-512 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA3-512 digest
     */
    public static byte[] sha3_512(final byte[] data) {
        return getSha3_512Digest().digest(data);
    }

    /**
     * Calculates the SHA3-512 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA3-512 digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] sha3_512(final InputStream data) throws IOException {
        return digest(getSha3_512Digest(), data);
    }

    /**
     * Calculates the SHA3-512 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest; converted to bytes using {@link Strings#getBytes(String)}
     * @return SHA3-512 digest
     */
    public static byte[] sha3_512(final String data) {
        return sha3_512(Strings.getBytes(data));
    }

    /**
     * Calculates the SHA3-512 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA3-512 digest as a hex string
     */
    public static String sha3_512Hex(final byte[] data) {
        return Hex.encodeHexString(sha3_512(data));
    }

    /**
     * Calculates the SHA3-512 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA3-512 digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public static String sha3_512Hex(final InputStream data) throws IOException {
        return Hex.encodeHexString(sha3_512(data));
    }

    /**
     * Calculates the SHA3-512 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA3-512 digest as a hex string
     */
    public static String sha3_512Hex(final String data) {
        return Hex.encodeHexString(sha3_512(data));
    }

    /**
     * Calculates the SHA-384 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA-384 digest
     */
    public static byte[] sha384(final byte[] data) {
        return getSha384Digest().digest(data);
    }

    /**
     * Calculates the SHA-384 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA-384 digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] sha384(final InputStream data) throws IOException {
        return digest(getSha384Digest(), data);
    }

    /**
     * Calculates the SHA-384 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest; converted to bytes using {@link Strings#getBytes(String)}
     * @return SHA-384 digest
     */
    public static byte[] sha384(final String data) {
        return sha384(Strings.getBytes(data));
    }

    /**
     * Calculates the SHA-384 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-384 digest as a hex string
     */
    public static String sha384Hex(final byte[] data) {
        return Hex.encodeHexString(sha384(data));
    }

    /**
     * Calculates the SHA-384 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-384 digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public static String sha384Hex(final InputStream data) throws IOException {
        return Hex.encodeHexString(sha384(data));
    }

    /**
     * Calculates the SHA-384 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-384 digest as a hex string
     */
    public static String sha384Hex(final String data) {
        return Hex.encodeHexString(sha384(data));
    }

    /**
     * Calculates the SHA-512 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA-512 digest
     */
    public static byte[] sha512(final byte[] data) {
        return getSha512Digest().digest(data);
    }

    /**
     * Calculates the SHA-512 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA-512 digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] sha512(final InputStream data) throws IOException {
        return digest(getSha512Digest(), data);
    }

    /**
     * Calculates the SHA-512 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest; converted to bytes using {@link Strings#getBytes(String)}
     * @return SHA-512 digest
     */
    public static byte[] sha512(final String data) {
        return sha512(Strings.getBytes(data));
    }

    /**
     * Calculates the SHA-512/224 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA-512/224 digest
     */
    public static byte[] sha512_224(final byte[] data) {
        return getSha512_224Digest().digest(data);
    }

    /**
     * Calculates the SHA-512/224 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA-512/224 digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] sha512_224(final InputStream data) throws IOException {
        return digest(getSha512_224Digest(), data);
    }

    /**
     * Calculates the SHA-512/224 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest; converted to bytes using {@link Strings#getBytes(String)}
     * @return SHA-512/224 digest
     */
    public static byte[] sha512_224(final String data) {
        return sha512_224(Strings.getBytes(data));
    }

    /**
     * Calculates the SHA-512/224 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-512/224 digest as a hex string
     */
    public static String sha512_224Hex(final byte[] data) {
        return Hex.encodeHexString(sha512_224(data));
    }

    /**
     * Calculates the SHA-512/224 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-512/224 digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public static String sha512_224Hex(final InputStream data) throws IOException {
        return Hex.encodeHexString(sha512_224(data));
    }

    /**
     * Calculates the SHA-512/224 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-512/224 digest as a hex string
     */
    public static String sha512_224Hex(final String data) {
        return Hex.encodeHexString(sha512_224(data));
    }

    /**
     * Calculates the SHA-512/256 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA-512/256 digest
     */
    public static byte[] sha512_256(final byte[] data) {
        return getSha512_256Digest().digest(data);
    }

    /**
     * Calculates the SHA-512/256 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest
     * @return SHA-512/256 digest
     * @throws IOException On error reading from the stream
     */
    public static byte[] sha512_256(final InputStream data) throws IOException {
        return digest(getSha512_256Digest(), data);
    }

    /**
     * Calculates the SHA-512/256 digest and returns the value as a {@code byte[]}.
     *
     * @param data Data to digest; converted to bytes using {@link Strings#getBytes(String)}
     * @return SHA-512/224 digest
     */
    public static byte[] sha512_256(final String data) {
        return sha512_256(Strings.getBytes(data));
    }

    /**
     * Calculates the SHA-512/256 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-512/256 digest as a hex string
     */
    public static String sha512_256Hex(final byte[] data) {
        return Hex.encodeHexString(sha512_256(data));
    }

    /**
     * Calculates the SHA-512/256 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-512/256 digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public static String sha512_256Hex(final InputStream data) throws IOException {
        return Hex.encodeHexString(sha512_256(data));
    }

    /**
     * Calculates the SHA-512/256 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-512/256 digest as a hex string
     */
    public static String sha512_256Hex(final String data) {
        return Hex.encodeHexString(sha512_256(data));
    }

    /**
     * Calculates the SHA-512 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-512 digest as a hex string
     */
    public static String sha512Hex(final byte[] data) {
        return Hex.encodeHexString(sha512(data));
    }

    /**
     * Calculates the SHA-512 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-512 digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public static String sha512Hex(final InputStream data) throws IOException {
        return Hex.encodeHexString(sha512(data));
    }

    /**
     * Calculates the SHA-512 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @return SHA-512 digest as a hex string
     */
    public static String sha512Hex(final String data) {
        return Hex.encodeHexString(sha512(data));
    }

    /**
     * Updates the given {@link MessageDigest}.
     *
     * @param messageDigest the {@link MessageDigest} to update
     * @param valueToDigest the value to update the {@link MessageDigest} with
     * @return the updated {@link MessageDigest}
     */
    public static MessageDigest updateDigest(final MessageDigest messageDigest, final byte[] valueToDigest) {
        messageDigest.update(valueToDigest);
        return messageDigest;
    }

    /**
     * Updates the given {@link MessageDigest}.
     *
     * @param messageDigest the {@link MessageDigest} to update
     * @param valueToDigest the value to update the {@link MessageDigest} with
     * @return the updated {@link MessageDigest}
     */
    public static MessageDigest updateDigest(final MessageDigest messageDigest, final ByteBuffer valueToDigest) {
        messageDigest.update(valueToDigest);
        return messageDigest;
    }

    /**
     * Reads through a File and updates the digest for the data
     *
     * @param digest The MessageDigest to use (e.g. MD5)
     * @param data   Data to digest
     * @return the digest
     * @throws IOException On error reading from the stream
     */
    public static MessageDigest updateDigest(final MessageDigest digest, final File data) throws IOException {
        try (final BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(data))) {
            return updateDigest(digest, inputStream);
        }
    }

    /**
     * Reads through a RandomAccessFile and updates the digest for the data using non-blocking-io (NIO).
     * <p>
     * TODO Decide if this should be public.
     *
     * @param digest The MessageDigest to use (e.g. MD5)
     * @param data   Data to digest
     * @return the digest
     * @throws IOException On error reading from the stream
     */
    private static MessageDigest updateDigest(final MessageDigest digest, final FileChannel data) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(STREAM_BUFFER_LENGTH);
        while (data.read(buffer) > 0) {
            buffer.flip();
            digest.update(buffer);
            buffer.clear();
        }
        return digest;
    }

    /**
     * Reads through an InputStream and updates the digest for the data
     *
     * @param digest      The MessageDigest to use (e.g. MD5)
     * @param inputStream Data to digest
     * @return the digest
     * @throws IOException On error reading from the stream
     */
    public static MessageDigest updateDigest(final MessageDigest digest, final InputStream inputStream)
            throws IOException {
        final byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
        int read = inputStream.read(buffer, 0, STREAM_BUFFER_LENGTH);

        while (read > -1) {
            digest.update(buffer, 0, read);
            read = inputStream.read(buffer, 0, STREAM_BUFFER_LENGTH);
        }

        return digest;
    }

    /**
     * Reads through a Path and updates the digest for the data
     *
     * @param digest  The MessageDigest to use (e.g. MD5)
     * @param path    Data to digest
     * @param options options How to open the file
     * @return the digest
     * @throws IOException On error reading from the stream
     */
    public static MessageDigest updateDigest(final MessageDigest digest, final Path path, final OpenOption... options)
            throws IOException {
        try (final BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(path, options))) {
            return updateDigest(digest, inputStream);
        }
    }

    /**
     * Reads through a RandomAccessFile and updates the digest for the data using non-blocking-io (NIO)
     *
     * @param digest The MessageDigest to use (e.g. MD5)
     * @param data   Data to digest
     * @return the digest
     * @throws IOException On error reading from the stream
     */
    public static MessageDigest updateDigest(final MessageDigest digest, final RandomAccessFile data)
            throws IOException {
        return updateDigest(digest, data.getChannel());
    }

    /**
     * Updates the given {@link MessageDigest} from a String (converted to bytes using UTF-8).
     * <p>
     * To update the digest using a different charset for the conversion,
     * convert the String to a byte array using
     * {@link String#getBytes(java.nio.charset.Charset)} and pass that
     * to the {@link Digests#updateDigest(MessageDigest, byte[])} method
     *
     * @param messageDigest the {@link MessageDigest} to update
     * @param valueToDigest the value to update the {@link MessageDigest} with;
     *                      converted to bytes using {@link Strings#getBytes(String)}
     * @return the updated {@link MessageDigest}
     */
    public static MessageDigest updateDigest(final MessageDigest messageDigest, final String valueToDigest) {
        messageDigest.update(Strings.getBytes(valueToDigest));
        return messageDigest;
    }

    private final MessageDigest messageDigest;

    private Digests() {
        this.messageDigest = null;
    }

    /**
     * Creates an instance using the provided {@link MessageDigest} parameter.
     * <p>
     * This can then be used to create digests using methods such as
     * {@link #digest(byte[])} and {@link #digestAsHex(File)}.
     *
     * @param digest the {@link MessageDigest} to use
     */
    private Digests(final MessageDigest digest) {
        this.messageDigest = digest;
    }

    /**
     * Creates an instance using the provided {@link MessageDigest} parameter.
     * <p>
     * This can then be used to create digests using methods such as
     * {@link #digest(byte[])} and {@link #digestAsHex(File)}.
     *
     * @param name the name of the {@link MessageDigest} to use
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught.
     * @see #getDigest(String)
     */
    private Digests(final String name) {
        this(getDigest(name));
    }

    /**
     * Reads through a byte array and returns the digest for the data.
     *
     * @param data Data to digest
     * @return the digest
     */
    public byte[] digest(final byte[] data) {
        return updateDigest(messageDigest, data).digest();
    }

    /**
     * Reads through a ByteBuffer and returns the digest for the data
     *
     * @param data Data to digest
     * @return the digest
     */
    public byte[] digest(final ByteBuffer data) {
        return updateDigest(messageDigest, data).digest();
    }

    /**
     * Reads through a File and returns the digest for the data
     *
     * @param data Data to digest
     * @return the digest
     * @throws IOException On error reading from the stream
     */
    public byte[] digest(final File data) throws IOException {
        return updateDigest(messageDigest, data).digest();
    }

    /**
     * Reads through an InputStream and returns the digest for the data
     *
     * @param data Data to digest
     * @return the digest
     * @throws IOException On error reading from the stream
     */
    public byte[] digest(final InputStream data) throws IOException {
        return updateDigest(messageDigest, data).digest();
    }

    /**
     * Reads through a File and returns the digest for the data
     *
     * @param data    Data to digest
     * @param options options How to open the file
     * @return the digest
     * @throws IOException On error reading from the stream
     */
    public byte[] digest(final Path data, final OpenOption... options) throws IOException {
        return updateDigest(messageDigest, data, options).digest();
    }

    /**
     * Reads through a byte array and returns the digest for the data.
     *
     * @param data Data to digest treated as UTF-8 string
     * @return the digest
     */
    public byte[] digest(final String data) {
        return updateDigest(messageDigest, data).digest();
    }

    /**
     * Reads through a byte array and returns the digest for the data.
     *
     * @param data Data to digest
     * @return the digest as a hex string
     */
    public String digestAsHex(final byte[] data) {
        return Hex.encodeHexString(digest(data));
    }

    /**
     * Reads through a ByteBuffer and returns the digest for the data
     *
     * @param data Data to digest
     * @return the digest as a hex string
     */
    public String digestAsHex(final ByteBuffer data) {
        return Hex.encodeHexString(digest(data));
    }

    /**
     * Reads through a File and returns the digest for the data
     *
     * @param data Data to digest
     * @return the digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public String digestAsHex(final File data) throws IOException {
        return Hex.encodeHexString(digest(data));
    }

    /**
     * Reads through an InputStream and returns the digest for the data
     *
     * @param data Data to digest
     * @return the digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public String digestAsHex(final InputStream data) throws IOException {
        return Hex.encodeHexString(digest(data));
    }

    /**
     * Reads through a File and returns the digest for the data
     *
     * @param data    Data to digest
     * @param options options How to open the file
     * @return the digest as a hex string
     * @throws IOException On error reading from the stream
     */
    public String digestAsHex(final Path data, final OpenOption... options) throws IOException {
        return Hex.encodeHexString(digest(data, options));
    }

    /**
     * Reads through a byte array and returns the digest for the data.
     *
     * @param data Data to digest treated as UTF-8 string
     * @return the digest as a hex string
     */
    public String digestAsHex(final String data) {
        return Hex.encodeHexString(digest(data));
    }

    /**
     * Returns the message digest instance.
     *
     * @return the message digest instance
     */
    public MessageDigest getMessageDigest() {
        return messageDigest;
    }

}
