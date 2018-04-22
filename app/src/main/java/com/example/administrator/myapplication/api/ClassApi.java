package com.example.administrator.myapplication.api;

import static com.example.administrator.myapplication.api.BaseUrl.baseUrl;

public class ClassApi {
    private static final String baseClassUrl = baseUrl+"class/";
    public static final String getClassList = baseClassUrl+"list";
    public static final String getClass = baseClassUrl+"get";
}
