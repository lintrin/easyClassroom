package com.example.administrator.myapplication.ui.teacher.course.next.homework;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.utils.JsonUtils;
import com.example.administrator.utils.TopBarUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.TeacherHomeworkAdapter;
import com.example.administrator.myapplication.model.Homework;
import com.example.administrator.myapplication.model.impl.HomeworkModel;
import com.example.administrator.view.DividerLine;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import library.http.BaseRequest;

public class TeacherHomeworkListActivity extends AppCompatActivity {

    private Context context;
    int homeworkId;
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
        homeworkId = getIntent().getIntExtra("id",0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        HomeworkModel.getInstance().requestHomeworkList(homeworkId, new BaseRequest.OnRequestListener() {
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
