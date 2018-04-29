package com.example.administrator.myapplication.api;

import static com.example.administrator.myapplication.api.BaseUrl.baseUrl;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class HomeworkApi {
    private static final String baseHomeworkOuterUrl = baseUrl+"homeworkOuter/";
    public static final String getHomeworkOuterList = baseHomeworkOuterUrl +"list";
    public static final String addHomeworkOuterList = baseHomeworkOuterUrl +"add";




    private static final String baseHomeworkUrl = baseUrl+"homework/";
    public static final String getHomework = baseUrl+"homework";
    public static final String getHomeworkList = baseHomeworkUrl+"list";



}
