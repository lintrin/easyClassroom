package com.example.administrator.myapplication.server;

import com.example.administrator.myapplication.api.NewsApi;
import com.example.administrator.myapplication.libary.http.BaseRequest;

public class NewsServer extends BaseRequest {

    private static NewsServer instance;

    public static NewsServer getInstance() {
        if (instance == null) {
            synchronized (NewsServer.class) {
                if (instance == null) {
                    instance = new NewsServer();
                }
            }
        }
        return instance;
    }

    public void getNews(OnRequestListener listener) {
        get(NewsApi.getNews, listener);
    }

}
