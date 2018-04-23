package com.example.administrator.myapplication.server;

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.myapplication.api.CourseMessageApi;
import com.example.administrator.myapplication.model.CourseMessage;
import com.google.common.collect.Maps;

import java.util.Map;

import library.http.BaseRequest;

/**
 * @author by JingQ on 2018/4/23.
 */

public class CourseMessageServer extends BaseRequest {

    /**
     * 获取列表
     * @param id 课程ID
     * @param listener 回调
     */
    public static void getCourseMessageList(Integer id, OnRequestListener listener) {
        get(CourseMessageApi.getCourseMessageList + "?courseId=" + id, listener);
    }


    /**
     * 发送公告
     *
     * @param courseMessage 课程消息
     * @param listener 回调
     */
    public static  void addCourseMessage(CourseMessage courseMessage, OnRequestListener listener){
        Map<String, Object> map = Maps.newHashMap();
        map.put("courseId", courseMessage.getCourseId());
        map.put("content", courseMessage.getContent());
        postForm(CourseMessageApi.addCourseMessage, map, listener);
    }

}
