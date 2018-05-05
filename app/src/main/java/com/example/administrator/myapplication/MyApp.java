package com.example.administrator.myapplication;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import cn.jpush.im.android.api.event.MessageEvent;
import library.http.HttpManager;

import cn.jpush.im.android.api.JMessageClient;


public class MyApp extends Application{

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        HttpManager.initNoHttpWhitDBCache(this, false, true);
        JMessageClient.init(context);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
