package com.example.administrator.myapplication.model.impl;

import com.example.administrator.myapplication.model.CourseMessage;
import com.example.administrator.myapplication.model.Homework;
import com.example.administrator.myapplication.server.HomeworkServer;

import java.util.List;

import library.http.BaseRequest;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class HomeworkModel {
    private static HomeworkModel instance;

    private List<Homework> homeworkList;

    public static HomeworkModel getInstance() {
        if (instance == null) {
            synchronized (HomeworkModel.class) {
                if (instance == null)
                    instance = new HomeworkModel();
            }
        }
        return instance;
    }

    public void requestHomeworkList(int courseId, BaseRequest.OnRequestListener listener) {
        HomeworkServer.getHomeworkList(courseId, listener);
    }

    public List<Homework> getHomeworkList() {
        return homeworkList;
    }

    public void setHomeworkList(List<Homework> homeworkList) {
        this.homeworkList = homeworkList;
    }
}
