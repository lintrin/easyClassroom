package com.example.administrator.myapplication.fragment;


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

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.CourseMessageAdapter;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.CourseMessage;
import com.example.administrator.myapplication.model.impl.CourseMessageModel;
import com.yanzhenjie.nohttp.rest.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        EventBus.getDefault().register(this);
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
        rvCourseMessage = (RecyclerView) mainView.findViewById(R.id.rv_course_message);
        rvCourseMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        CourseMessageAdapter adapter = new CourseMessageAdapter();
        rvCourseMessage.setAdapter(adapter);

        //学生不发课程公告
        btnSend = (Button) mainView.findViewById(R.id.btn_teacher_add_courseMessage);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshLayout() {
        getCourseMessageList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
