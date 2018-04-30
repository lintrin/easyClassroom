package com.example.administrator.myapplication.model.impl;

import com.example.administrator.myapplication.model.HomeworkOuter;
import com.example.administrator.myapplication.server.HomeworkServer;

import java.io.File;
import java.util.List;

import library.http.BaseRequest;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class HomeworkModel {
    private static HomeworkModel instance;

    private List<HomeworkOuter> homeworkOuterList;

    public static HomeworkModel getInstance() {
        if (instance == null) {
            synchronized (HomeworkModel.class) {
                if (instance == null)
                    instance = new HomeworkModel();
            }
        }
        return instance;
    }

    public void requestHomeworkOuterList(int courseId, BaseRequest.OnRequestListener listener) {
        HomeworkServer.getHomeworkOuterList(courseId, listener);
    }

    public void requestHomeworkList(int homeworkOuterId, BaseRequest.OnRequestListener listener) {
        HomeworkServer.getHomeworkList(homeworkOuterId, listener);
    }

    public List<HomeworkOuter> getHomeworkOuterList() {
        return homeworkOuterList;
    }

    public void setHomeworkOuterList(List<HomeworkOuter> homeworkOuterOuterList) {
        this.homeworkOuterList = homeworkOuterOuterList;
    }

    public void addHomeWork(HomeworkOuter homeworkOuter, BaseRequest.OnRequestListener listener) {
        HomeworkServer.addHomeWork(homeworkOuter, listener);
    }

    public void requestHomework(int homeworkOuterId, BaseRequest.OnRequestListener listener) {
        HomeworkServer.getHomeWork(homeworkOuterId, listener);
    }

    public void addStudentHomework(int courseId, int homeworkOuterId, File file, BaseRequest.OnRequestListener listener) {
        HomeworkServer.addStudentHomework(courseId, homeworkOuterId, file, listener);
    }

    public void markHomeworkScore(int id, String score, String comment,BaseRequest.OnRequestListener listener){
        HomeworkServer.markHomeworkScore(id,score,comment,listener);
    }

    public void returnHomework(int id, File file,BaseRequest.OnRequestListener listener){
        HomeworkServer.returnHomework(id,file,listener);
    }
}
