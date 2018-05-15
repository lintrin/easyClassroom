package com.example.administrator.myapplication.server;

import com.example.administrator.myapplication.api.NewsApi;
import com.example.administrator.myapplication.model.News;

import java.util.HashMap;
import java.util.Map;

import library.http.BaseRequest;

public class NewsServer extends BaseRequest {


    public static void getNews(OnRequestListener listener) {
        get(NewsApi.GET_NEWS_LIST, listener);
    }

    public static void sendNotice(News news, OnRequestListener listener) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", news.getTitle());
        map.put("content", news.getContent());
        post(NewsApi.SEND_NEWS, map, listener);
    }
}
