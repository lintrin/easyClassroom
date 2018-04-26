package com.example.administrator.myapplication.server;

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.myapplication.api.CourseApi;
import library.http.BaseRequest;
import com.example.administrator.myapplication.model.Course;
import com.google.common.collect.Maps;

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
}
