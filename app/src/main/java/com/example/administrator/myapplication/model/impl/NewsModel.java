package com.example.administrator.myapplication.model.impl;

import library.http.BaseRequest;
import com.example.administrator.myapplication.model.News;
import com.example.administrator.myapplication.server.NewsServer;

import java.util.ArrayList;
import java.util.List;

public class NewsModel {
    private static NewsModel instance;
    private List<News> newsList;

    public static NewsModel getInstance() {
        if (instance == null) {
            synchronized (NewsModel.class) {
                if (instance == null)
                    instance = new NewsModel();
            }
        }
        return instance;
    }

    public void getNewsFromNetwork(BaseRequest.OnRequestListener listener) {
        NewsServer.getInstance().getNews(listener);
    }


    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> _newsList) {
        if (newsList!=null){
            newsList.clear();
        }else{
            newsList = new ArrayList<>(_newsList.size());
        }
        newsList.addAll(_newsList);
    }
}
