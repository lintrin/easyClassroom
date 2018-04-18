package com.example.administrator.myapplication.server;

import com.alibaba.fastjson.JSON;
import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.myapplication.api.CourseApi;
import com.example.administrator.myapplication.libary.http.BaseRequest;
import com.example.administrator.myapplication.model.Course;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CourseServer extends BaseRequest {



    public static  void addCourse(Course course,OnRequestListener listener){
        String jsonString = JsonUtils.toJsonString(course);
        post(CourseApi.addCourse,jsonString,listener);
    }

    public static void getCourse(int id,OnRequestListener listener){
        JSONObject map = new JSONObject();
        try {
            map.put("id",id);
            get(CourseApi.getCourse,map,listener);
        } catch (JSONException e) {
            listener.onError("json错误");
            e.printStackTrace();
        }
    }

}
