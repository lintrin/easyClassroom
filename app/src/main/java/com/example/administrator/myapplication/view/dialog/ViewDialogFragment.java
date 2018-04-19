package com.example.administrator.myapplication.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.Course;

import org.greenrobot.eventbus.EventBus;

public class ViewDialogFragment extends DialogFragment {


    private AutoCompleteTextView mEtCourseName;

    private AutoCompleteTextView mEtCourseSummary;

    private AutoCompleteTextView mEtCourseBegin;

    private AutoCompleteTextView mEtCourseEnd;

    private AutoCompleteTextView mEtCourseLesson;

    private AutoCompleteTextView mEtCourseTerm;

    private AutoCompleteTextView mEtCourseBuilding;

    private AutoCompleteTextView mEtCourseClassroom;

    private AutoCompleteTextView mEtCourseDay;


    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, "ViewDialogFragment");
    }

    public void initView(View view) {

        mEtCourseName = view.findViewById(R.id.et_course_name);
        mEtCourseSummary = view.findViewById(R.id.et_course_summary);
        mEtCourseBegin = view.findViewById(R.id.et_course_begin);
        mEtCourseEnd = view.findViewById(R.id.et_course_end);
        mEtCourseLesson = view.findViewById(R.id.et_course_lesson);
        mEtCourseTerm = view.findViewById(R.id.et_course_term);
        mEtCourseBuilding = view.findViewById(R.id.et_course_building);
        mEtCourseClassroom = view.findViewById(R.id.et_course_classroom);
        mEtCourseDay = view.findViewById(R.id.et_course_day);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_add_course, null);
        initView(view);
        builder.setView(view)
                .setPositiveButton("确定", (dialog, which) -> {

                    Course course = new Course();
                    course.setName(mEtCourseName.getText().toString());
                    course.setBriefIntroduction(mEtCourseSummary.getText().toString());
                    course.setBeginPeriod(mEtCourseBegin.getText().toString());
                    course.setEndPeriod(mEtCourseEnd.getText().toString());
                    course.setLesson(Integer.parseInt(mEtCourseLesson.getText().toString()));
                    course.setTerm(Integer.parseInt(mEtCourseTerm.getText().toString()));
                    course.setBuildingNumber(Integer.parseInt(mEtCourseBuilding.getText().toString()));
                    course.setClassroom(mEtCourseClassroom.getText().toString());
                    course.setDay(Integer.parseInt(mEtCourseDay.getText().toString()));
                    EventBus.getDefault().post(course);
                    dismiss();
                })
        ;
        return builder.create();
    }


}