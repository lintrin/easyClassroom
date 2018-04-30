package com.example.administrator.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.Utils.TopBarUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.BaseRecycleViewAdapter;
import com.example.administrator.myapplication.adapter.TeacherHomeworkAdapter;
import com.example.administrator.myapplication.model.Homework;
import com.example.administrator.myapplication.model.impl.HomeworkModel;
import com.example.administrator.view.DividerLine;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import library.http.BaseRequest;

public class TeacherHomeworkActivity extends AppCompatActivity {

    private Context context;
    int homeworkOuterId;
    TeacherHomeworkAdapter adapter;
    private RecyclerView rvHomework;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_homework);
        context = this;
        initView();
        initData();
        initListener();
    }

    private void initListener() {

    }

    private void initView() {
        String title = getIntent().getStringExtra("title");

        TopBarUtils topbar = new TopBarUtils(this);
        topbar.setTitle(title);
        rvHomework = findViewById(R.id.rv_teacher_homework);
        rvHomework.setLayoutManager(new LinearLayoutManager(context));
        adapter = new TeacherHomeworkAdapter(context,null);
        DividerLine line = new DividerLine();
        rvHomework.addItemDecoration(line);
        rvHomework.setAdapter(adapter);


    }

    private void initData() {
        homeworkOuterId = getIntent().getIntExtra("homeworkOuterId",0);
        HomeworkModel.getInstance().requestHomeworkList(homeworkOuterId, new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                Log.i("sss", "onCompleted: "+response.toString());
                List<Homework> homeworkList = JsonUtils.parseArray(response.get().toString(),"body",Homework.class);
                adapter.refreshData(homeworkList);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
