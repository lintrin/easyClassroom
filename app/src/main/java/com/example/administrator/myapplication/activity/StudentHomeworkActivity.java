package com.example.administrator.myapplication.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.Utils.TopBarUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.StudentHomeworkMarkAdapter;
import com.example.administrator.myapplication.adapter.TeacherHomeworkAdapter;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.Homework;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.example.administrator.myapplication.model.impl.HomeworkModel;
import com.example.administrator.view.DividerLine;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import library.http.BaseRequest;

public class StudentHomeworkActivity extends AppCompatActivity {


    private Context context;
    private Course course;
    private int homeworkOuterId;
    private TextView tvTitle;
    private  RecyclerView rvStudentHomeworkMark;
    private  StudentHomeworkMarkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homework);
        context = this;
        initView();
        initData();
    }

    private void initView() {

        tvTitle = findViewById(R.id.tv_title);

        rvStudentHomeworkMark = findViewById(R.id.rv_student_homework_mark);
        adapter = new StudentHomeworkMarkAdapter(context,null);
        rvStudentHomeworkMark.setAdapter(adapter);
        rvStudentHomeworkMark.setLayoutManager(new LinearLayoutManager(context));



    }

    private void initData() {
        course = CourseModel.getInstance().getCourse();
        tvTitle.setText(course.getName());
        homeworkOuterId = getIntent().getIntExtra("homeworkOuterId",0);
        HomeworkModel.getInstance().requestHomework(homeworkOuterId, new BaseRequest.OnRequestListener() {
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
}
