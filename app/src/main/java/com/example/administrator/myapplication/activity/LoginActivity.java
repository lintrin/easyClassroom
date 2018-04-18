package com.example.administrator.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.libary.http.BaseRequest;
import com.example.administrator.myapplication.model.User;
import com.example.administrator.myapplication.model.impl.UserModel;
import com.yanzhenjie.nohttp.rest.Response;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView et_username;
    private AutoCompleteTextView et_password;
    private CheckBox login_checkBox;
    private TextView tv_register;
    private Button btn_login;
    private Context context;
    public boolean isTeacher;


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
            UserModel.getInstance().login(username, password, isTeacher,new BaseRequest.OnRequestListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onCompleted(Response response) {
                    User user = JsonUtils.parseObject(response.get().toString(),"body", User.class);
                    //type 2学生 1老师
                    if (user.getType()==2){
                        Intent intent = new Intent(context,StudentMainActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(context,TeacherMainActivity.class);
                        startActivity(intent);
                    }

                }

                @Override
                public void onError(String json解析出错) {

                }
            });
        });
        login_checkBox.setOnCheckedChangeListener((buttonView, _isChecked) -> isTeacher = _isChecked);
    }

    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        tv_register = findViewById(R.id.tv_register);
        btn_login = findViewById(R.id.btn_login);
        login_checkBox = findViewById(R.id.login_checkBox);
    }


}
