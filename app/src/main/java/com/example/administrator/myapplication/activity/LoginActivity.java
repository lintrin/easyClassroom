package com.example.administrator.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.libary.http.BaseRequest;
import com.example.administrator.myapplication.server.UserServer;
import com.yanzhenjie.nohttp.rest.Response;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView et_username;
    private AutoCompleteTextView et_password;
    private CheckBox login_checkBox;
    private TextView tv_register;
    private Button btn_login;
    private Context context;
    public boolean isChecked;


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
            UserServer.getInstance().login(username, password, isChecked,new BaseRequest.OnRequestListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onCompleted(Response response) {
                    Log.i("sss", "onCompleted: "+response.toString());
                }

                @Override
                public void onError() {

                }
            });
        });
        login_checkBox.setOnCheckedChangeListener((buttonView, _isChecked) -> isChecked = _isChecked);
    }

    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        tv_register = findViewById(R.id.tv_register);
        btn_login = findViewById(R.id.btn_login);
        login_checkBox = findViewById(R.id.login_checkBox);
    }


}
