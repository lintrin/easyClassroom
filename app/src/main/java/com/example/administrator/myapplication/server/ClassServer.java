package com.example.administrator.myapplication.server;

import com.example.administrator.myapplication.api.ClassApi;
import library.http.BaseRequest;

public class ClassServer extends BaseRequest{

    public static void getClassList(int id,OnRequestListener listener){
        get(ClassApi.getClassList+"?id="+id,listener);
    }
}
