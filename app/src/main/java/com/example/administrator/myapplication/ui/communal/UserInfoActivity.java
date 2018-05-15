package com.example.administrator.myapplication.ui.communal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.User;
import com.example.administrator.myapplication.model.impl.UserModel;
import com.example.administrator.utils.ImageLoadUtils;
import com.example.administrator.utils.TopBarUtils;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    User user;
    private ImageView mIncludeTopbarLeft;
    private TextView mIncludeTopbarTitle;
    private ImageView mIncludeTopbarRight;
    private LinearLayout mIncludeTopbarMain;
    private ImageView mIvUserInfoAvatar;
    private EditText mEtUsername;
    private EditText mEtProfession;
    private Button mBtnUserInfoModify;
    private EditText tv_user_info_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        user = UserModel.getInstance().getUser();
        initView();
        setUser();

    }

    private void initView() {
        TopBarUtils topBarUtils = new TopBarUtils(this);
        topBarUtils.setTitle("个人信息");
        mIvUserInfoAvatar = (ImageView) findViewById(R.id.iv_user_info_avatar);
        mEtUsername = (EditText) findViewById(R.id.et_username);
        mEtProfession = (EditText) findViewById(R.id.et_profession);
        tv_user_info_id = findViewById(R.id.tv_user_info_id);
        mBtnUserInfoModify = (Button) findViewById(R.id.btn_user_info_modify);
        mBtnUserInfoModify.setOnClickListener(this);
    }

    private void setUser(){
        ImageLoadUtils.setAvatarImage(this,mIvUserInfoAvatar,R.mipmap.avatar1,R.mipmap.avatar1);
        mEtUsername.setText(user.getName());
        tv_user_info_id.setText(user.getIdNumber());
        mEtProfession.setText(user.getProfession());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_user_info_modify:
                break;
        }
    }
}
