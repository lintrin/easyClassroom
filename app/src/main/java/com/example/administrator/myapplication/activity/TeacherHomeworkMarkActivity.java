package com.example.administrator.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.DownloadModel;

import library.http.DownloadManager;

public class TeacherHomeworkMarkActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIvMarkFile;
    private EditText mEdMarkScore;
    private Button mBtnMarkSubmit;
    private Button mBtnMarkResultSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_homework_mark);
        initView();
    }

    private void initView() {
        mIvMarkFile = (ImageView) findViewById(R.id.iv_mark_file);
        mIvMarkFile.setOnClickListener(this);
        mEdMarkScore = (EditText) findViewById(R.id.ed_mark_score);
        mBtnMarkSubmit = (Button) findViewById(R.id.btn_mark_submit);
        mBtnMarkSubmit.setOnClickListener(this);
        mBtnMarkResultSubmit = (Button) findViewById(R.id.btn_mark_result_submit);
        mBtnMarkResultSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_mark_file:
//                DownloadModel.getInstance().download();
                break;
            case R.id.btn_mark_submit:
                break;
            case R.id.btn_mark_result_submit:
                break;
        }
    }
}
