package com.example.administrator.myapplication.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.TeacherHomeworkAdapter;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.Homework;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.example.administrator.myapplication.model.impl.HomeworkModel;
import com.example.administrator.view.DividerLine;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import library.http.BaseRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherHomeworkFragment extends Fragment {


    private View view;
    private TextView mTvTitle;
    private RecyclerView mRvHomework;
    private TeacherHomeworkAdapter adapter;
    private Course course;


    public TeacherHomeworkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_teacher_homework, container, false);
            initData();
            initView();
        }
        return view;
    }

    private void initData() {
        course = CourseModel.getInstance().getCourse();
        HomeworkModel.getInstance().requestHomeworkList(course.getId(), new BaseRequest.OnRequestListener() {
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


    private void initView() {
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvTitle.setText(course.getName());
        mRvHomework = view.findViewById(R.id.rv_homework);
        mRvHomework.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TeacherHomeworkAdapter(getContext(),null);
        mRvHomework.setAdapter(adapter);
        DividerLine line = new DividerLine(DividerLine.VERTICAL);
        line.setSize(1);
        line.setColor(ContextCompat.getColor(getContext(),R.color.stroke_main));
        mRvHomework.addItemDecoration(line);
    }


}
