package com.example.administrator.myapplication.server;

import com.example.administrator.myapplication.api.HomeworkApi;

import java.util.HashMap;
import java.util.Map;

import library.http.BaseRequest;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class HomeworkServer extends BaseRequest {
    public static void getHomeworkOuterList(int courseId, OnRequestListener listener){
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseId);
        get(HomeworkApi.getHomeworkOuterList,map,listener);
    }

    public static void getHomeworkList(int homeworkOuterId, OnRequestListener listener){
        Map<String,Object> map = new HashMap<>();
        map.put("homeworkOuterId",homeworkOuterId);
        get(HomeworkApi.getHomeworkList,map,listener);
    }
}
