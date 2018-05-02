package com.example.administrator.myapplication.ui.teacher.course.next.news;


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
import android.widget.Toast;

import com.example.administrator.myapplication.model.impl.CourseModel;
import com.example.administrator.utils.JsonUtils;
import com.example.administrator.utils.TextUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.CourseMessageAdapter;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.CourseMessage;
import com.example.administrator.myapplication.model.impl.CourseMessageModel;
import com.example.administrator.myapplication.view.dialog.ViewDialogCourseMessageFragment;
import com.yanzhenjie.nohttp.rest.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import library.http.BaseRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherCourseMessageFragment extends Fragment {

    private View mainView;

    private RecyclerView rvCourseMessage;

    private Button btnSend;

    private CourseMessageAdapter courseMessageAdapter;

    private Course course;

    public TeacherCourseMessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        course = CourseModel.getInstance().getCourse();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_course_message, container, false);
            initView();
            initData();
            initListener();
        }
        return mainView;
    }

    private void initView() {
        rvCourseMessage = mainView.findViewById(R.id.rv_course_message);
        rvCourseMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        courseMessageAdapter = new CourseMessageAdapter(getContext());
        rvCourseMessage.setAdapter(courseMessageAdapter);

        // 老师发课程公告
        btnSend = mainView.findViewById(R.id.btn_teacher_add_courseMessage);
    }

    private void initData() {
        getCourseMessageList();
    }

    private void initListener() {
        btnSend.setOnClickListener(view -> {
            showDialog();
        });
    }

    private void showDialog() {
        ViewDialogCourseMessageFragment dialogCourseMessageFragment = new ViewDialogCourseMessageFragment();
        dialogCourseMessageFragment.show(getFragmentManager(), course.getId());
    }

    private void getCourseMessageList() {
        CourseMessageModel.getInstance().getCourseMessageList(course.getId(), new BaseRequest.OnRequestListener() {
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
    public void addMessage (CourseMessage courseMessage) {
        if (courseMessage == null || TextUtils.isEmpty(courseMessage.getContent())) {
            Toast.makeText(getContext(), "公告内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        CourseMessageModel.getInstance().addCourseMessage(courseMessage, new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                if (response.getHeaders().getResponseCode() == 200) {
                    getCourseMessageList();
                } else {
                    String msg = JsonUtils.parseObject(response.get().toString(), "msg", String.class);
                    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
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
        EventBus.getDefault().unregister(this);
    }
}
