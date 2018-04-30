package com.example.administrator.myapplication.ui.teacher.course.next.homework;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.DownloadModel;
import com.example.administrator.myapplication.model.impl.HomeworkModel;
import com.example.administrator.myapplication.view.dialog.InputStringDialog;
import com.example.administrator.utils.wrapper.FilenameWrapper;
import com.example.administrator.utils.wrapper.PathWrapper;
import com.yanzhenjie.nohttp.rest.Response;

import java.io.File;
import java.net.URLEncoder;

import library.doc.DocTextParser;
import library.http.BaseDownloader;
import library.http.BaseRequest;
import library.http.HttpStateUtils;

public class TeacherHomeworkMarkActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private ImageView mIvMarkFile;
    private TextView tvMarkDoc;
    private EditText mEdMarkScore;
    private Button mBtnMarkSubmit;
    private Button mBtnMarkResultSubmit;
    private String fileUrl;
    private int id;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_homework_mark);
        context = this;
        initData();
        initView();
    }

    private void initData() {
        fileUrl = getIntent().getStringExtra("fileUrl");
        fileUrl = fileUrl.replace("第一次作业", URLEncoder.encode("第一次作业")).replace("周杰伦吹胡萝卜", URLEncoder.encode("周杰伦吹胡萝卜"));
        id = getIntent().getIntExtra("id", 0);
        userId = getIntent().getStringExtra("userId");
    }

    private void initView() {
        mIvMarkFile = (ImageView) findViewById(R.id.iv_mark_file);
        mIvMarkFile.setOnClickListener(this);
        mEdMarkScore = (EditText) findViewById(R.id.ed_mark_score);
        tvMarkDoc = findViewById(R.id.tv_mark_doc);
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
                DownloadModel.getInstance().downloadByGet(fileUrl, FilenameWrapper.getDocFilename(userId+"-"+id), new BaseDownloader.OnDownLoadListener() {
                    @Override
                    public void onCompleted(int position, String filepath) {
                        Log.i("sss", "onCompleted: " + filepath);
                        String text = DocTextParser.readWord(filepath);
                        runOnUiThread(() -> tvMarkDoc.setText(text));
                        Intent intent = getWordFileIntent(filepath);
                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(context,
                                    "没有找到可以打开doc的软件",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String msg) {

                    }
                });
                break;
            case R.id.btn_mark_submit:

                new InputStringDialog().showDialog(getSupportFragmentManager(), "输入评语").
                        setOnSubmitClickListener((dialog, str) -> HomeworkModel.getInstance().markHomeworkScore(id, mEdMarkScore.getText().toString(), str, new BaseRequest.OnRequestListener() {
                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onCompleted(Response response) {
                                Log.i("sss", "onCompleted: " + response);
                                HttpStateUtils.showRequestMsg(context, response);
                                dialog.dismiss();
                            }

                            @Override
                            public void onError(String msg) {

                            }
                        }));

                break;
            case R.id.btn_mark_result_submit:
                File file = new File(PathWrapper.getDocFilePath(context,userId+"-"+id));

                if (!file.exists())
                    Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
                else
                    HomeworkModel.getInstance().returnHomework(id, file, new BaseRequest.OnRequestListener() {
                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onCompleted(Response response) {
                            HttpStateUtils.showRequestMsg(context, response);
                        }

                        @Override
                        public void onError(String msg) {

                        }
                    });
                break;
        }
    }

    public static Intent getWordFileIntent(String Path) {
        File file = new File(Path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }


}
