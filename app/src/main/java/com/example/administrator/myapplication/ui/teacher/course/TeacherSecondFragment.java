package com.example.administrator.myapplication.ui.teacher.course;

import android.content.Intent;
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

import com.example.administrator.utils.JsonUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.TeacherCourseAdapter;
import library.http.BaseRequest;
import library.http.HttpStateUtils;

import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.example.administrator.myapplication.server.CourseServer;
import com.example.administrator.view.DividerLine;
import com.yanzhenjie.nohttp.rest.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class TeacherSecondFragment extends Fragment {

    View mainView;
    RecyclerView rv_course_teacher;
    Button btn_teacher_add_course;
    private TeacherCourseAdapter adapter;

    public static TeacherSecondFragment newInstance() {
        TeacherSecondFragment fragment = new TeacherSecondFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_teacher_second, null);
            initView();
            initListener();
        }
        return mainView;
    }

    private void initListener() {
        btn_teacher_add_course.setOnClickListener(view -> {
            showAddCourseDialog();
        });
    }
    private void getCourseList() {
        CourseServer.getCourseList(new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                List<Course> courseList = JsonUtils.parseArray(response.get().toString(), "body", Course.class);
                if (courseList != null)
                    adapter.refreshData(courseList);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getCourseList();
    }

    private void showAddCourseDialog() {
//        ViewDialogFragment viewDialogFragment = new ViewDialogFragment();
//        viewDialogFragment.show(getFragmentManager());
        //换成activity了
        Intent intent = new Intent(getContext(), TeacherAddCourseActivity.class);
        startActivity(intent);
    }

    private void initView() {
        rv_course_teacher = mainView.findViewById(R.id.rv_course_teacher);

        adapter = new TeacherCourseAdapter(getContext(),null);
        rv_course_teacher.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerLine line = new DividerLine();
        rv_course_teacher.addItemDecoration(line);
        rv_course_teacher.setAdapter(adapter);
        btn_teacher_add_course = mainView.findViewById(R.id.btn_teacher_add_course);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //添加课程点击按钮后
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addCourse(Course course) {
        CourseModel.getInstance().addCourse(course, new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                Log.i("sss", "onCompleted: " + response.toString());
                HttpStateUtils.showRequestMsg(getContext(),response);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
