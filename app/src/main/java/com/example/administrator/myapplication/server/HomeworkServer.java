package com.example.administrator.myapplication.server;

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.myapplication.api.HomeworkApi;
import com.example.administrator.myapplication.model.HomeworkOuter;

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

    public static void addHomeWork(HomeworkOuter homeworkOuter, OnRequestListener listener) {

        String jsonString = JsonUtils.toJsonString(homeworkOuter);
        post(HomeworkApi.addHomeworkOuterList,jsonString,listener);
    }

    public static void getHomeWork(int homeworkOuterId, OnRequestListener listener) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",homeworkOuterId);
        get(HomeworkApi.getHomework,map,listener);
    }
}
