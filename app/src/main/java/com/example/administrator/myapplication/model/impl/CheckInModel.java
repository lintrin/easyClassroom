package com.example.administrator.myapplication.model.impl;

import com.example.administrator.myapplication.server.CheckInServer;

import library.http.BaseRequest;

/**
 * @author by JingQ on 2018/4/25.
 */

public class CheckInModel {

    private static CheckInModel instance;

    public static CheckInModel getInstance() {
        if (instance == null) {
            synchronized (CheckInModel.class) {
                if (instance == null)
                    instance = new CheckInModel();
            }
        }
        return instance;
    }


    public void addCheckInRecord(String code, Integer courseId, BaseRequest.OnRequestListener listener) {
        CheckInServer.addCheckInRecord(code, courseId, listener);
    }

    public void changeStatus(String idNumber, Integer status, Integer courseRecordId, BaseRequest.OnRequestListener listener) {
        CheckInServer.changeStatus(idNumber, status, courseRecordId, listener);
    }

    public void getCheckListByCRId(Integer courseRecordId, BaseRequest.OnRequestListener listener) {
        CheckInServer.getCheckInListByCourseRecordId(courseRecordId, listener);
    }
}
