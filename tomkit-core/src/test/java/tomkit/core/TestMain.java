package tomkit.core;

import tomkit.core.io.IOStreams;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author yh
 * @since 2021/2/18
 */
public class TestMain {

    public static void main(String[] args) throws IOException {
        String filename = "D:\\test\\yh_rsa_2048";
        FileInputStream fin = new FileInputStream(new File(filename));
        byte[] bytes = IOStreams.copyToByteArray(fin);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));

        System.out.println(IOStreams.copyToString(fin, StandardCharsets.UTF_8));
    }

}

