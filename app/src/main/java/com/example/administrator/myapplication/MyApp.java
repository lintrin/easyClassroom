package com.example.administrator.myapplication;

import android.app.Application;
import android.content.Context;

import com.example.administrator.myapplication.libary.http.HttpManager;

import cn.jpush.im.android.api.JMessageClient;


public class MyApp extends Application{

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        HttpManager.initNoHttpWhitDBCache(this, false, false);
        JMessageClient.init(context);
    }
}
