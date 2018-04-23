package com.example.administrator.myapplication.api;

import static com.example.administrator.myapplication.api.BaseUrl.baseUrl;

/**
 * Created by yejingqi on 2018/4/23.
 */

public class CourseMessageApi {

    /**
     * 基础URL
     */
    private static final String baseCourseMessageUrl = baseUrl+"courseMessage/";

    /**
     * 添加
     */
    public static final String addCourseMessage = baseCourseMessageUrl +"add/";

    /**
     * 获取消息
     */
    public static final String getCourseMessageList = baseCourseMessageUrl +"listAll";

}
