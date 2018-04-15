package com.example.administrator.myapplication.server;

import com.example.administrator.myapplication.api.UserApi;
import com.example.administrator.myapplication.libary.http.BaseRequest;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.HashMap;
import java.util.Map;

public class UserServer extends BaseRequest {
    private static UserServer instance;

    public static UserServer getInstance() {
        if (instance==null){
            synchronized (UserServer.class){
                if (instance==null){
                    instance = new UserServer();
                }
            }
        }
        return instance;
    }

    private UserServer(){

    }

    public void login(String idNumber,String password,OnRequestListener listener){
        Map<String,Object> map = new HashMap<>();
        map.put("idNumber",idNumber);
        map.put("password",password);
        post(UserApi.login, map, listener);
    }

    public void registry(String idNumber,String password,int userType,OnRequestListener listener){
        Map<String,Object> map = new HashMap<>();
        map.put("idNumber",idNumber);
        map.put("password",password);
        map.put("type",userType);
        post(UserApi.registry, map, listener);
    }

}
