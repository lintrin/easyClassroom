package com.example.administrator.myapplication.server;


import android.util.Log;

import com.example.administrator.myapplication.api.UserApi;
import com.example.administrator.myapplication.libary.http.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class UserServer extends BaseRequest {
    private static UserServer instance;

    public static UserServer getInstance() {
        if (instance == null) {
            synchronized (UserServer.class) {
                if (instance == null) {
                    instance = new UserServer();
                }
            }
        }
        return instance;
    }

    private UserServer() {

    }

    public void login(String idNumber, String password, boolean isChecked, OnRequestListener listener) {
        JSONObject map = new JSONObject();
        try {
            map.put("idNumber", idNumber);
            map.put("password", password);
            if (isChecked)
                map.put("type", 1);
            else
                map.put("type", 2);
        } catch (JSONException e) {
            Log.i("sss", "login: error");
            e.printStackTrace();
        }
        post(UserApi.login, map, listener);
    }

    public void registry(String idNumber, String password, int userType, OnRequestListener listener) {
        JSONObject map = new JSONObject();
        try {
            map.put("idNumber", idNumber);
            map.put("password", password);
            map.put("type", userType);
            post(UserApi.registry, map, listener);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
