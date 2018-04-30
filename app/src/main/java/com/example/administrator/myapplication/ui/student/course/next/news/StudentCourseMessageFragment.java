package com.example.administrator.myapplication.ui.student.course.next.news;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.utils.JsonUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.CourseMessageAdapter;
import com.example.administrator.myapplication.model.CourseMessage;
import com.example.administrator.myapplication.model.impl.CourseMessageModel;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import library.http.BaseRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentCourseMessageFragment extends Fragment {

    private View mainView;

    private RecyclerView rvCourseMessage;

    private Button btnSend;

    private CourseMessageAdapter courseMessageAdapter;

    public StudentCourseMessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_course_message, container, false);
            initView();
            initData();
        }
        return mainView;
    }

    private void initView() {
        rvCourseMessage = mainView.findViewById(R.id.rv_course_message);
        rvCourseMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        courseMessageAdapter = new CourseMessageAdapter(getContext());
        rvCourseMessage.setAdapter(courseMessageAdapter);

        //学生不发课程公告
        btnSend = mainView.findViewById(R.id.btn_teacher_add_courseMessage);
        btnSend.setVisibility(View.INVISIBLE);
    }

    private void initData() {
        getCourseMessageList();
    }

    private void getCourseMessageList() {
        CourseMessageModel.getInstance().getCourseMessageList(19, new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                Log.i("sss", response.get().toString());
                List<CourseMessage> courseMessages = JsonUtils.parseArray(response.get().toString(), "body", CourseMessage.class);
                if (courseMessages != null) {
                    courseMessageAdapter.refreshData(courseMessages);
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
