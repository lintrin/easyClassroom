package com.example.administrator.myapplication.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.utils.JsonUtils;
import com.example.administrator.myapplication.R;

import cn.jpush.im.android.api.options.RegisterOptionalUserInfo;
import library.http.BaseRequest;
import com.example.administrator.myapplication.model.User;
import com.example.administrator.myapplication.model.impl.UserModel;
import com.example.administrator.myapplication.ui.student.StudentMainActivity;
import com.example.administrator.myapplication.ui.teacher.TeacherMainActivity;
import com.yanzhenjie.nohttp.rest.Response;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView et_username;
    private AutoCompleteTextView et_password;
    private CheckBox login_checkBox;
    private TextView tv_register;
    private Button btn_login;
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_login);

        initView();
        initListener();

    }

    private void initListener() {
        tv_register.setOnClickListener(view -> {
            Intent intent = new Intent(context, RegistryActivity.class);
            startActivity(intent);

        });
        btn_login.setOnClickListener(view -> {
            String username = et_username.getText().toString();
            String password = et_password.getText().toString();
            UserModel.getInstance().login(username, password, login_checkBox.isChecked(),new BaseRequest.OnRequestListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onCompleted(Response response) {
                    User user = JsonUtils.parseObject(response.get().toString(),"body", User.class);
                    if (user!=null) {
                        UserModel.getInstance().setUser(user);
                        //进行极光SDK登录或者注册
                        getUserInfo(user.getIdNumber());
                        //type 2学生 1老师
                        if (user.getType() == 2) {
                            Intent intent = new Intent(context, StudentMainActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(context, TeacherMainActivity.class);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(context, "用户为空", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onError(String msg) {

                }
            });
        });
    }

    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        tv_register = findViewById(R.id.tv_register);
        btn_login = findViewById(R.id.btn_login);
        login_checkBox = findViewById(R.id.login_checkBox);
    }

    private void getUserInfo(String idNumber) {
        JMessageClient.getUserInfo(idNumber, new GetUserInfoCallback() {
            @Override
            public void gotResult(int i, String s, UserInfo userInfo) {
                //查看是否有用户信息，有进行登录，没有的话进行注册
                if (userInfo == null) {
                    registerIM(idNumber);
                } else {
                    loginIM(idNumber);
                }
            }
        });
    }

    private void registerIM(String idNumber) {
        String defaultPassword = "123456";
        User user = UserModel.getInstance().getUser();
        // 极光用户注册信息
        RegisterOptionalUserInfo userInfo = new RegisterOptionalUserInfo();
        userInfo.setNickname(user.getName());
        JMessageClient.register(idNumber, defaultPassword, userInfo, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                loginIM(idNumber);
            }
        });
    }

    private void loginIM(String idNumber) {
        String defaultPassword = "123456";
        JMessageClient.login(idNumber, defaultPassword, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {

            }
        });
    }


    private void loginOutIM(String idNumber) {
        JMessageClient.logout();
    }
}
