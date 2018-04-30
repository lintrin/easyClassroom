package com.example.administrator.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class JsonUtils {

    public static  <T> T parseObject(String jsonString,String key,Class<T> _class){
        String str = JSONObject.parseObject(jsonString).getString(key);
        return JSON.parseObject(str, _class);
    }

    public static  <T> List<T> parseArray(String jsonString, String key, Class<T> _class){
        String str = JSONObject.parseObject(jsonString).getString(key);
        return JSON.parseArray(str, _class);
    }

    public static String toJsonString(Object o) {
        return JSON.toJSONString(o);
    }
}
