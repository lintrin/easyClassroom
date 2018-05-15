package com.example.administrator.myapplication.ui.student.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.utils.JsonUtils;
import com.example.administrator.utils.TextUtils;
import com.example.administrator.utils.TopBarUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.StudentCourseAdapter;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.example.administrator.view.DividerLine;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import library.http.BaseRequest;

public class StudentSecondFragment extends Fragment implements View.OnClickListener {

    View mainView;
    private RecyclerView mRvCourseStudent;
    private Button btnSearchCourse;
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
            mainView = inflater.inflate(R.layout.fragment_student_course, container, false);
            initView();
            initData();
            initListener();
        }
        return mainView;
    }

    private void initListener() {
        btnSearchCourse.setOnClickListener(this);
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
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.stroke));
        mRvCourseStudent.addItemDecoration(decoration);
        mRvCourseStudent.setLayoutManager(new LinearLayoutManager(getContext()));
        btnSearchCourse = mainView.findViewById(R.id.btn_search_course);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search_course:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View view = inflater.inflate(R.layout.dialog_search_course, null);
                AutoCompleteTextView mEtSearchCourse = view.findViewById(R.id.et_search_course);
                builder.setView(view)
                        .setPositiveButton("确定", (dialog, which) -> {
                            String code = mEtSearchCourse.getText().toString();
                            if (TextUtils.isEmpty(code)) {
                                Toast.makeText(getContext(), "识别码不能为空！", Toast.LENGTH_SHORT).show();
                            } else {
                                searchCourseByNetwork(code);
                            }
                        })
                ;
                builder.create();
                builder.show();
                break;
            default:
                break;
        }
    }

    private void searchCourseByNetwork(String code) {
        CourseModel.getInstance().searchCourseByCode(code, new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                Course course = JsonUtils.parseObject(response.get().toString(), "body", Course.class);
                if (course == null) {
                    Toast.makeText(getContext(), "没有找到相应的课程", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getContext(), StudentJoinCourseActivity.class);
                    intent.putExtra("course", course);
                    startActivity(intent);
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
