package io.github.tomkitjson.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

/**
 * @author yh
 * @since 2021/1/28 上午 12:25
 */
public class Jsons {

    public static boolean isJson(String source) {
        ObjectMapper mapper = new ObjectMapper();
        return false;
    }

    public static void main(String[] args) throws IOException {

        Gson gson = new GsonBuilder().create();


        String jsonStr = "{\"a\":1}";

        JsonFactory factory = new JsonFactoryBuilder().build();
        try (JsonParser parser = factory.createParser(jsonStr)) {
//            System.out.println(parser.readValueAsTree());
            System.out.println(parser.getValueAsString());
            System.out.println(parser);
        }
    }

}
