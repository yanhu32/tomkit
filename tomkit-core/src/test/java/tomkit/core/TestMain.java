package tomkit.core;

import tomkit.core.codec.binary.Hex;

import java.io.IOException;

/**
 * @author yh
 * @since 2021/2/18
 */
public class TestMain {

    public static void main(String[] args) throws IOException {
        Hex hex = new Hex();
        hex.decode();
    }

    static enum A {
        A, B, C
    }

}

