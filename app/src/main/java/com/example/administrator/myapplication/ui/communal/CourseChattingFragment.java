package com.example.administrator.myapplication.ui.communal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.CourseUserAdapter;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.CourseUser;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.example.administrator.utils.JsonUtils;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import cn.jiguang.imui.messages.MessageList;
import cn.jiguang.imui.messages.MsgListAdapter;
import cn.jiguang.imui.messages.ptr.PtrDefaultHeader;
import cn.jiguang.imui.messages.ptr.PtrHandler;
import cn.jiguang.imui.messages.ptr.PullToRefreshLayout;
import cn.jiguang.imui.utils.DisplayUtil;
import library.http.BaseRequest;

/**
 * @author by JingQ on 2018/5/1.
 */

public class CourseChattingFragment extends Fragment {

    View mainView;

    private Context context;

    private RecyclerView rvChattingUsers;

    private CourseUserAdapter adapter;

    private Course course;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
        course = CourseModel.getInstance().getCourse();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_course_chatting, container, false);
            initView();
        }
        initData();
        return mainView;
    }

    private void initData() {
        getCourseUsers();
    }

    private void initView() {
        rvChattingUsers = mainView.findViewById(R.id.rv_chatting_users);
        rvChattingUsers.setLayoutManager(new LinearLayoutManager(context));

        adapter = new CourseUserAdapter(context);
        rvChattingUsers.setAdapter(adapter);
    }

    private void getCourseUsers() {
        CourseModel.getInstance().courseUsers(course.getId(), new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                List<CourseUser> users = JsonUtils.parseArray(response.get().toString(), "body", CourseUser.class);
                if (users != null && users.size() > 0) {
                    adapter.refreshData(users);
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
