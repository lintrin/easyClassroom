package com.example.administrator.myapplication.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.CourseMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * @author by JingQ on 2018/4/23.
 */

public class ViewDialogCourseMessageFragment extends DialogFragment {

    private AutoCompleteTextView mEtCourseMessage;

    private Integer courseId;

    public void show(FragmentManager fragmentManager, Integer courseId) {
        this.courseId = courseId;
        show(fragmentManager, "ViewDialogCourseMessageFragment");
    }

    public void initView(View view) {

        mEtCourseMessage = view.findViewById(R.id.et_course_message);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_add_course_message, null);
        initView(view);
        builder.setView(view)
                .setPositiveButton("确定", (dialog, which) -> {
                    CourseMessage courseMessage = new CourseMessage();
                    courseMessage.setContent(mEtCourseMessage.getText().toString());
                    courseMessage.setCourseId(courseId);
                    EventBus.getDefault().post(courseMessage);
                    dismiss();
                })
        ;
        return builder.create();
    }
}
