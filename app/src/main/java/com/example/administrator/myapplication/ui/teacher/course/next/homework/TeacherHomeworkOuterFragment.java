package com.example.administrator.myapplication.ui.teacher.course.next.homework;


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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.Utils.DateUtils;
import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.HomeworkOuterAdapter;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.HomeworkOuter;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.example.administrator.myapplication.model.impl.HomeworkModel;
import com.example.administrator.myapplication.view.dialog.HomeworkAddDialogue;
import com.example.administrator.view.DividerLine;
import com.yanzhenjie.nohttp.rest.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import library.http.BaseRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherHomeworkOuterFragment extends Fragment {


    private View view;
    private TextView mTvTitle;
    private RecyclerView mRvHomework;
    private Button btnHomeworkOuterAdd;
    private HomeworkOuterAdapter adapter;
    private Course course;
    private  HomeworkAddDialogue dialogue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_teacher_homework, container, false);
            initData();
            initView();
            initListener();
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
                List<HomeworkOuter> homeworkOuterList = JsonUtils.parseArray(response.get().toString(),"body",HomeworkOuter.class);
                adapter.refreshData(homeworkOuterList);
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
        btnHomeworkOuterAdd = view.findViewById(R.id.btn_homework_outer_add);
        mRvHomework.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeworkOuterAdapter(getContext(),null);
        mRvHomework.setAdapter(adapter);
        DividerLine line = new DividerLine(DividerLine.VERTICAL);
        line.setSize(1);
        line.setColor(ContextCompat.getColor(getContext(),R.color.stroke_main));
        mRvHomework.addItemDecoration(line);
    }
    private void initListener() {
        btnHomeworkOuterAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogue = new HomeworkAddDialogue();
                dialogue.show(getFragmentManager(),"");
            }
        });

        adapter.setOnItemClickListener((position, _data) -> {
            HomeworkOuter data = (HomeworkOuter) _data;
            Intent intent = new Intent(getContext(), TeacherHomeworkActivity.class);
            intent.putExtra("homeworkOuterId", data.getCreateId());
            intent.putExtra("title", data.getHomeworkName());
            startActivity(intent);
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addHomework(HomeworkOuter homeworkOuter){
        homeworkOuter.setCourseId(course.getId());
        homeworkOuter.setCreateDate(DateUtils.getDateString("yyyymmddHHMMSS"));
        HomeworkModel.getInstance().addHomeWork(homeworkOuter, new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {

                HashMap map = JsonUtils.parseObject(response.get().toString(), "head", HashMap.class);
                if (map.get("msg") != null)
                    Toast.makeText(getContext(), map.get("msg").toString(), Toast.LENGTH_SHORT).show();
                if (dialogue!=null)
                    dialogue.dismiss();
                getHomeWorkOuterList();
            }

            @Override
            public void onError(String msg) {

                Toast.makeText(getContext(), "添加失败", Toast.LENGTH_SHORT).show();
                if (dialogue!=null)
                    dialogue.dismiss();
            }
        });
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
