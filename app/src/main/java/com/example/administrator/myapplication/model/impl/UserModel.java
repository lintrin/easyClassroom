package com.example.administrator.myapplication.model.impl;

import android.util.Log;

import com.example.administrator.myapplication.api.UserApi;
import com.example.administrator.myapplication.libary.http.BaseRequest;
import com.example.administrator.myapplication.model.Student;
import com.example.administrator.myapplication.model.User;
import com.example.administrator.myapplication.server.UserServer;

import org.json.JSONException;
import org.json.JSONObject;

public class UserModel {
    private static UserModel instance;
    private User user;

    public static UserModel getInstance() {
        if (instance==null){
            synchronized (UserModel.class){
                if (instance==null)
                    instance = new UserModel();
            }
        }
        return instance;
    }

    public void login(String idNumber, String password, boolean isTeacher, BaseRequest.OnRequestListener listener) {
        UserServer.getInstance().login(idNumber,password,isTeacher,listener);

    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
