package com.example.administrator.myapplication.server;

import com.example.administrator.myapplication.api.NewsApi;
import library.http.BaseRequest;

public class NewsServer extends BaseRequest {


    public static void getNews(OnRequestListener listener) {
        get(NewsApi.GET_NEWS_LIST, listener);
    }

}
