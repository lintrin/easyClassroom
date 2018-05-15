package com.example.administrator.myapplication.api;

import static com.example.administrator.myapplication.api.BaseUrl.baseUrl;

public class NewsApi {
    private static final String baseNewsUrl = baseUrl+"notice/";
    public static final String getNews = baseNewsUrl+"detail?id=44";

    public static final String SEND_NEWS = baseNewsUrl+"send";
    public static final String GET_NEWS_LIST = baseNewsUrl + "list?pageNo=0&pageSize=30";
}
