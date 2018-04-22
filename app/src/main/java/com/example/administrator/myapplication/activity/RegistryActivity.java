package com.example.administrator.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.Utils.TextUtils;
import com.example.administrator.myapplication.R;
import library.http.BaseRequest;
import com.example.administrator.myapplication.model.User;
import com.example.administrator.myapplication.model.impl.UserModel;
import com.example.administrator.myapplication.server.UserServer;
import com.yanzhenjie.nohttp.rest.Response;

public class RegistryActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private EditText et_password_again;
    private Button btn_registry;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_registry);
        initView();
        initListener();


    }

    private void initListener() {
        btn_registry.setOnClickListener(view -> {
            String username = et_username.getText().toString();
            if (TextUtils.isEmpty(username)) {
                Toast.makeText(context, "用户名不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            String password = et_password.getText().toString();
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            } else if (password.length() < 6) {
                Toast.makeText(context, "密码长度不足6位", Toast.LENGTH_SHORT).show();
                return;
            }
            String passwordAgain = et_password_again.getText().toString();
            if (TextUtils.isEmpty(passwordAgain)) {
                Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            } else if (passwordAgain.length() < 6) {
                Toast.makeText(context, "密码长度不足6位", Toast.LENGTH_SHORT).show();
                return;
            }

            UserServer.getInstance().registry(username, password, 0, new BaseRequest.OnRequestListener() {
                @Override
                public void onStart() {
                    btn_registry.setEnabled(false);
                    btn_registry.setBackgroundResource(R.color.gray_color);
                }

                @Override
                public void onCompleted(Response response) {
                    User user = JsonUtils.parseObject(response.get().toString(),"body", User.class);
                    UserModel.getInstance().setUser(user);
                    Log.i("sss", "onCompleted: "+user.toString());
                    btn_registry.setEnabled(true);
                    btn_registry.setBackgroundResource(R.color.main_blue);
                    Intent intent = new Intent(context,StudentMainActivity.class);
//                    startActivity(intent);
                }

                @Override
                public void onError(String json解析出错) {
                    btn_registry.setEnabled(true);
                    btn_registry.setBackgroundResource(R.color.main_blue);
                }
            });
        });

    }

    private void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_password_again = findViewById(R.id.et_password_again);
        btn_registry = findViewById(R.id.btn_registry);
    }
}
