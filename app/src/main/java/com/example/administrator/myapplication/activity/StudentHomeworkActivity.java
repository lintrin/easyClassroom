package com.example.administrator.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.Utils.TopBarUtils;
import com.example.administrator.config.Consant;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.StudentHomeworkMarkAdapter;
import com.example.administrator.myapplication.adapter.TeacherHomeworkAdapter;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.Homework;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.example.administrator.myapplication.model.impl.HomeworkModel;
import com.example.administrator.view.DividerLine;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;
import com.yanzhenjie.nohttp.rest.Response;

import java.io.File;
import java.util.List;

import library.http.BaseRequest;

public class StudentHomeworkActivity extends AppCompatActivity {


    private Activity context;
    private Course course;
    private int homeworkOuterId;
    private TextView tvTitle;
    private RecyclerView rvStudentHomeworkMark;
    private Button btnStudentHomeworkSubmit;
    private StudentHomeworkMarkAdapter adapter;
    private String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homework);
        context = this;
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        btnStudentHomeworkSubmit.setOnClickListener(v -> {
            if (filename == null) {
                new LFilePicker()
                        .withActivity(context)
                        .withRequestCode(Consant.REQUEST_CODE_FROM_ACTIVITY)
                        .withIconStyle(Constant.ICON_STYLE_GREEN)
                        .withMutilyMode(false) //false单选 true多选
                        .start();
            } else {
                HomeworkModel.getInstance().addStudentHomework(course.getId(), homeworkOuterId, new File(filename), new BaseRequest.OnRequestListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(Response response) {
                        Log.i("sss", "onCompleted: "+response.toString());
                    }

                    @Override
                    public void onError(String msg) {

                    }
                });
            }
        });
    }

    private void initView() {

        tvTitle = findViewById(R.id.tv_title);
        btnStudentHomeworkSubmit = findViewById(R.id.btn_student_homework_submit);
        rvStudentHomeworkMark = findViewById(R.id.rv_student_homework_mark);
        adapter = new StudentHomeworkMarkAdapter(context, null);
        rvStudentHomeworkMark.setAdapter(adapter);
        rvStudentHomeworkMark.setLayoutManager(new LinearLayoutManager(context));


    }

    private void initData() {
        course = CourseModel.getInstance().getCourse();
        tvTitle.setText(course.getName());
        homeworkOuterId = getIntent().getIntExtra("homeworkOuterId", 0);
        HomeworkModel.getInstance().requestHomework(homeworkOuterId, new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                Log.i("sss", "onCompleted: " + response.toString());
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Consant.REQUEST_CODE_FROM_ACTIVITY) {
                List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);
                filename = list.get(0);
                Log.i("sss", "initListener: "+filename);
                btnStudentHomeworkSubmit.setText("确认提交");
                Toast.makeText(getApplicationContext(), "选中了" + list.size() + "个文件", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
