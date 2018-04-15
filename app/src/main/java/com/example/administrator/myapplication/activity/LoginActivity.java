package com.example.administrator.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView et_username;
    private AutoCompleteTextView et_password;
    private TextView tv_register;
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
    }

    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        tv_register = findViewById(R.id.tv_register);
    }


}
