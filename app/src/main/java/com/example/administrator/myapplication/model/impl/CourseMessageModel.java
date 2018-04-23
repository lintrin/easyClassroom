package com.example.administrator.myapplication.model.impl;

import com.example.administrator.myapplication.model.CourseMessage;
import com.example.administrator.myapplication.server.CourseMessageServer;

import java.util.List;

import library.http.BaseRequest;

/**
 * Created by yejingqi on 2018/4/23.
 */

public class CourseMessageModel {

    private static CourseMessageModel instance;

    private List<CourseMessage> messageList;

    public static CourseMessageModel getInstance() {
        if (instance == null) {
            synchronized (CourseMessageModel.class) {
                if (instance == null)
                    instance = new CourseMessageModel();
            }
        }
        return instance;
    }

    public void getCourseMessageList(int id, BaseRequest.OnRequestListener listener) {
        CourseMessageServer.getCourseMessageList(id, listener);
    }


    public void addCourseMessage(CourseMessage courseMessage, BaseRequest.OnRequestListener listener) {
        CourseMessageServer.addCourseMessage(courseMessage, listener);
    }

}
