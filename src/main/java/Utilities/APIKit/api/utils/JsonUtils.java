//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.santander.api.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtils {
    public JsonUtils() {
    }

    public static JSONObject ReadJsonFile(String fileAbsPath) throws FileNotFoundException, ParseException, IOException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(fileAbsPath));
        JSONObject jsonObject = (JSONObject)object;
        return jsonObject;
    }

    public static JsonNode loadResource(String fileAbsPath) throws IOException {
        return JsonLoader.fromResource(fileAbsPath);
    }

    public static JsonNode loadResourcefromString(String json) throws IOException {
        return JsonLoader.fromString(json);
    }
}
