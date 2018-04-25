package com.example.administrator.myapplication.server;

import com.example.administrator.myapplication.api.CheckInApi;
import com.google.common.collect.Maps;

import java.util.Map;

import library.http.BaseRequest;

/**
 * @author by JingQ on 2018/4/25.
 */

public class CheckInServer extends BaseRequest{

    public static void getCheckInListByCourseRecordId(Integer courseRecordId, OnRequestListener listener) {
        get(CheckInApi.CHECKIN_LIST_BY_COURSE_RECORD_ID + courseRecordId, listener);
    }


    public static void changeStatus(String idNumber, Integer status, Integer courseRecordId, OnRequestListener listener) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("idNumber", idNumber);
        params.put("status", status);
        params.put("courseRecordId", courseRecordId);
        postForm(CheckInApi.CHANGE_STATUS, params, listener);
    }

    public static void addCheckInRecord(String checkCode, Integer courseId, OnRequestListener listener) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("code", checkCode);
        params.put("courseId", courseId);
        postForm(CheckInApi.CHECKIN_ADD, params, listener);
    }
}
