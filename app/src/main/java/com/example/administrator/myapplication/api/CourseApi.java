package com.example.administrator.myapplication.api;

import static com.example.administrator.myapplication.api.BaseUrl.baseUrl;

public class CourseApi {
    private static final String baseCourseUrl = baseUrl+"course/";
    public static final String getCourse = baseCourseUrl;
    public static final String addCourse = baseCourseUrl +"add";
    public static final String getCourseList = baseCourseUrl +"list";


}
