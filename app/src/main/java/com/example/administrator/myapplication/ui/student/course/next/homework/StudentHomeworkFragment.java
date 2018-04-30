package com.example.administrator.myapplication.ui.student.course.next.homework;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.Utils.TopBarUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.HomeworkOuterAdapter;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.HomeworkOuter;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.example.administrator.myapplication.model.impl.HomeworkModel;
import com.example.administrator.myapplication.view.dialog.HomeworkAddDialogue;
import com.example.administrator.view.DividerLine;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import library.http.BaseRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentHomeworkFragment extends Fragment {

    private View view;
    private TextView mTvTitle;
    private RecyclerView mRvHomework;
    private HomeworkOuterAdapter adapter;
    private Course course;
    private HomeworkAddDialogue dialogue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_student_homework, container, false);
            initData();
            initView();
        }
        return view;
    }


    private void initData() {
        course = CourseModel.getInstance().getCourse();
        getHomeWorkOuterList();
    }

    private void getHomeWorkOuterList() {
        HomeworkModel.getInstance().requestHomeworkOuterList(course.getId(), new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                List<HomeworkOuter> homeworkOuterList = JsonUtils.parseArray(response.get().toString(), "body", HomeworkOuter.class);
                adapter.refreshData(homeworkOuterList);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }


    private void initView() {
        TopBarUtils topBar = new TopBarUtils(view);
        topBar.setTitle(course.getName());
        mRvHomework = view.findViewById(R.id.rv_student_homework);
        mRvHomework.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeworkOuterAdapter(getContext(), null);
        adapter.setOnItemClickListener((position, _data) -> {
            HomeworkOuter data = (HomeworkOuter) _data;
            Intent intent = new Intent(getContext(), StudentHomeworkRecordActivity.class);
            intent.putExtra("homeworkOuterId", data.getCreateId());
            intent.putExtra("title", data.getHomeworkName());
            startActivity(intent);
        });
        mRvHomework.setAdapter(adapter);
        DividerLine line = new DividerLine(DividerLine.VERTICAL);
        line.setSize(1);
        line.setColor(ContextCompat.getColor(getContext(), R.color.stroke_main));
        mRvHomework.addItemDecoration(line);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
