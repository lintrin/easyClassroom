package com.example.administrator.myapplication.api;

import static com.example.administrator.myapplication.api.BaseUrl.baseUrl;

public class CourseApi {
    private static final String baseCourseUrl = baseUrl+"course/";
    public static final String getCourse = baseCourseUrl;
    public static final String addCourse = baseCourseUrl +"add";
    public static final String getCourseList = baseCourseUrl +"list";

    /**
     * 根据课程识别码进行课程查询
     */
    public static final String SEARCH_COURSE_BY_CODE = baseCourseUrl + "byCode?code=";

    /**
     * 绑定用户和课程的关系
     */
    public static final String JOIN_COURSE = baseCourseUrl + "join";

    /**
     * 某门课程相关的用户列表
     */
    public static final String COURSE_USER_LIST = baseUrl + "courseUser/list?courseId=";

    private static final String BASE_COURSE_RESOURCE_URL = baseUrl + "file/";

    /**
     * 上传课程资源
     */
    public static final String UPLOAD_COURSE_RESOURCE = BASE_COURSE_RESOURCE_URL + "upload";

    /**
     * 获取课程资源列表
     */
    public static final String GET_COURSE_RESOURCE_LIST = BASE_COURSE_RESOURCE_URL + "list?courseId=";

    /**
     * 根据ID删除某个课程资源
     */
    public static final String DELETE_COURSE_RESOURCE = BASE_COURSE_RESOURCE_URL + "delete?id=";
}
