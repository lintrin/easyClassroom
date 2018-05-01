package com.example.administrator.myapplication.model.impl;

import library.http.BaseRequest;

import com.example.administrator.myapplication.model.User;
import com.example.administrator.myapplication.server.UserServer;

public class UserModel {
    private static UserModel instance;
    private static User user;

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
        UserServer.login(idNumber,password,isTeacher,listener);

    }


    public User getUser() {
        return UserModel.user;
    }

    public void setUser(User user) {
        UserModel.user = user;
    }
}
