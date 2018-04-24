package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.Utils.TextUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.StudentCourseMainActivity;
import com.example.administrator.myapplication.model.Course;

import java.util.List;

/**
 * Created by Administrator on 2018/4/22 0022.
 */

public class StudentCourseAdapter extends BaseRecycleViewAdapter<Course> {
    Context context;

    public StudentCourseAdapter(Context context, List<Course> list) {
        this.context = context;
        setData(list);
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_student_course, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, Course data) {
        ((VHolder) viewHolder).setData(realPosition, data);
    }

    class VHolder extends RecyclerView.ViewHolder {
        ImageView mIvItemStudentCourse;
        TextView mTvItemStudentCourseClass;
        TextView mTvItemStudentCourseTitle;
        TextView mTvItemStudentCourseTeacher;
        TextView mTvItemStudentCourseCode;

        VHolder(View view) {
            super(view);
            this.mIvItemStudentCourse = (ImageView) view.findViewById(R.id.iv_item_student_course);
            this.mTvItemStudentCourseClass = (TextView) view.findViewById(R.id.tv_item_student_course_class);
            this.mTvItemStudentCourseTitle = (TextView) view.findViewById(R.id.tv_item_student_course_title);
            this.mTvItemStudentCourseTeacher = (TextView) view.findViewById(R.id.tv_item_student_course_teacher);
            this.mTvItemStudentCourseCode = (TextView) view.findViewById(R.id.tv_item_student_course_teacher_code);

        }

        void setData(int position, Course data) {
            mTvItemStudentCourseClass.setText(data.getBuildingNumber() + "# " + data.getClassroom());
            mTvItemStudentCourseTitle.setText(data.getName());
            if (TextUtils.isEmpty(data.getTeachers()))
                mTvItemStudentCourseTeacher.setText("X老师");
            else
                mTvItemStudentCourseTeacher.setText(data.getTeachers());
            mTvItemStudentCourseCode.setText(data.getCode());

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, StudentCourseMainActivity.class);
                context.startActivity(intent);
            });
        }
    }
}
