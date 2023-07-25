package com.example.blog.common.util;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConvertUtil {

    public static JSONObject convertObjectToJsonObject(Object obj) throws ParseException {
        JSONParser parser = new JSONParser();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(obj);
        obj = parser.parse( jsonStr );
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;
    }
}
