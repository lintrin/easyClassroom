package com.example.administrator.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.Utils.TopBarUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.StudentCourseAdapter;
import com.example.administrator.myapplication.adapter.StudentSignAdapter;
import com.example.administrator.myapplication.adapter.TeacherCourseAdapter;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import library.http.BaseRequest;

public class StudentSecondFragment extends Fragment implements View.OnClickListener {

    View mainView;
    private RecyclerView mRvCourseStudent;
    private StudentCourseAdapter adapter;


    public static StudentSecondFragment newInstance() {
        StudentSecondFragment fragment = new StudentSecondFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_second, container, false);
            initView();
            initData();
        }
        return mainView;
    }

    private void initData() {

        CourseModel.getInstance().getCourseList(new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                Log.i("sss", "getCourseList: "+response.get().toString());
                List<Course> courseList = JsonUtils.parseArray(response.get().toString(),"body",Course.class);

                adapter.refreshData(courseList);

            }

            @Override
            public void onError(String msg) {

            }
        });

    }

    private void initView() {
        mRvCourseStudent = mainView.findViewById(R.id.rv_course_student);
        adapter = new StudentCourseAdapter(getContext(),null);
        mRvCourseStudent.setAdapter(adapter);
        mRvCourseStudent.setLayoutManager(new LinearLayoutManager(getContext()));
        TopBarUtils topBarUtils = new TopBarUtils(mainView);
        topBarUtils.setTitle("课程");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}
