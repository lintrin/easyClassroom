package com.example.administrator.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.StudentSignAdapter;
import com.example.administrator.myapplication.adapter.TeacherCourseAdapter;

public class StudentSecondFragment extends Fragment implements View.OnClickListener {

    View mainView;
    private Button mBtnStudyAddCourseainView;
    private RecyclerView mRvCourseStudentainView;
    private RecyclerView mRvCourseSignainView;


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
        }
        return mainView;
    }

    private void initView() {
        mainView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_second, null);
        mBtnStudyAddCourseainView = mainView.findViewById(R.id.btn_study_add_course);
        mBtnStudyAddCourseainView.setOnClickListener(this);
        mRvCourseStudentainView = mainView.findViewById(R.id.rv_course_student);
        mRvCourseSignainView = mainView.findViewById(R.id.rv_course_sign);

        mRvCourseStudentainView.setAdapter(new TeacherCourseAdapter(getContext()));
        mRvCourseStudentainView.setLayoutManager(new GridLayoutManager(getContext(),3));

        mRvCourseSignainView.setAdapter(new StudentSignAdapter(getContext()));
        mRvCourseSignainView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_study_add_course:
                break;
        }
    }
}
