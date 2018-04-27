package com.example.administrator.myapplication.api;

/**
 * 课时URL
 *
 * @author by JingQ on 2018/4/27.
 */

public class CourseRecordApi {

    private static final String BASE_COURSE_RECORD_URL = BaseUrl.baseUrl + "courseRecord/";

    public static final String GET_COURSE_RECORD_LIST = BASE_COURSE_RECORD_URL + "list?courseId=";

    public static final String ONE_COURSE_RECORD_DETAIL = BASE_COURSE_RECORD_URL + "id=";
}
