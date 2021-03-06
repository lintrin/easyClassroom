package com.example.administrator.myapplication.server;

import com.example.administrator.utils.JsonUtils;
import com.example.administrator.myapplication.api.HomeworkApi;
import com.example.administrator.myapplication.model.HomeworkOuter;
import com.yanzhenjie.nohttp.FileBinary;

import java.io.File;
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

    public static void addStudentHomework(int courseId,int homeworkOuterId,File file,OnRequestListener listener){
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseId);
        map.put("homeworkOuterId",homeworkOuterId);
        map.put("file",new FileBinary(file));
        postFile(HomeworkApi.getHomeworkStudentAdd,map,listener);

    }

    public static void markHomeworkScore(int id, String score, String comment, OnRequestListener listener) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("score",score);
        map.put("comment",comment);
        postForm(HomeworkApi.markHomeworkScore,map,listener);
    }

    public static void returnHomework(int id, File file, OnRequestListener listener) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("file",new FileBinary(file));
        postFile(HomeworkApi.returnHomework,map,listener);
    }
}
