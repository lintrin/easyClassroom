package com.example.administrator.myapplication.server;

import com.example.administrator.utils.JsonUtils;
import com.example.administrator.myapplication.api.CourseApi;
import library.http.BaseRequest;

import com.example.administrator.myapplication.api.CourseRecordApi;
import com.example.administrator.myapplication.model.Course;
import com.google.common.collect.Maps;
import com.yanzhenjie.nohttp.FileBinary;

import java.io.File;
import java.util.Map;

public class CourseServer extends BaseRequest {



    public static  void addCourse(Course course,OnRequestListener listener){
        String jsonString = JsonUtils.toJsonString(course);
        post(CourseApi.addCourse,jsonString,listener);
    }

    public static void getCourse(int id,OnRequestListener listener){
        get(CourseApi.getCourse+"?id="+id,listener);

    }

    public static void getCourseList(OnRequestListener listener){
        get(CourseApi.getCourseList,listener);
    }

    public static void searchCourseByCode(String code, OnRequestListener listener) {
        get(CourseApi.SEARCH_COURSE_BY_CODE + code, listener);
    }

    public static void joinCourse(Integer courseId, OnRequestListener listener) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("courseId", courseId);
        postForm(CourseApi.JOIN_COURSE, params, listener);
    }

    public static void getCourseRecordList(Integer courseId, OnRequestListener listener) {
        get(CourseRecordApi.GET_COURSE_RECORD_LIST + courseId, listener);
    }

    public static void getCourseUserList(Integer courseId, OnRequestListener listener) {
        get(CourseApi.COURSE_USER_LIST + courseId, listener);
    }

    public static void uploadResource(Integer courseId, File file, OnRequestListener listener) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("courseId", courseId);
        map.put("file", new FileBinary(file));
        postFile(CourseApi.UPLOAD_COURSE_RESOURCE, map, listener);
    }

    public static void getCourseResources(Integer courseId, OnRequestListener listener) {
        get(CourseApi.GET_COURSE_RESOURCE_LIST + courseId, listener);
    }

    public static void deleteCourseResource(Integer id, OnRequestListener listener) {
        get(CourseApi.DELETE_COURSE_RESOURCE + id, listener);
    }
}
