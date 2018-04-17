package com.example.administrator.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.TeacherClassAdapter;
import com.example.administrator.myapplication.adapter.TeacherCourseAdapter;

public class TeacherSecondFragment extends Fragment {

    View mainView;
    RecyclerView rv_course_teacher;

    public static TeacherSecondFragment newInstance() {
        TeacherSecondFragment fragment = new TeacherSecondFragment();
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
        if (mainView==null) {
            mainView = inflater.inflate(R.layout.fragment_teacher_second, null);
            initView();
        }
        return mainView;
    }

    private void initView() {
        rv_course_teacher = mainView.findViewById(R.id.rv_course_teacher);
        rv_course_teacher.setAdapter(new TeacherCourseAdapter(getContext()));
        rv_course_teacher.setLayoutManager(new GridLayoutManager(getContext(),3));
    }

}
