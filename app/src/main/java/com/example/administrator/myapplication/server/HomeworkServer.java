package com.example.administrator.myapplication.server;

import com.example.administrator.myapplication.api.HomeworkApi;

import java.util.HashMap;
import java.util.Map;

import library.http.BaseRequest;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class HomeworkServer extends BaseRequest {
    public static void getHomeworkList(int courseId,OnRequestListener listener){
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseId);
        get(HomeworkApi.getHomeworkList,map,listener);
    }
}
