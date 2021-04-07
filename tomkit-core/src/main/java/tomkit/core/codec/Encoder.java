package tomkit.core.codec;

/**
 * @author yh
 * @since 2021/4/7
 */
public interface Encoder {

    /**
     * Encodes an "Object" and returns the encoded content as an Object. The Objects here may just be
     * {@code byte[]} or {@code String}s depending on the implementation used.
     *
     * @param source An object to encode
     * @return An "encoded" Object
     * @throws EncoderException An encoder exception is thrown if the encoder experiences a failure condition during the encoding
     *                          process.
     */
    Object encode(Object source) throws EncoderException;
}
