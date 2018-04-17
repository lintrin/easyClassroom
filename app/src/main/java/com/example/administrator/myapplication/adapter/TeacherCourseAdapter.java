package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.TeacherClassActivity;
import com.example.administrator.myapplication.model.TeacherClass;
import com.example.administrator.myapplication.model.TeacherCourse;

import java.util.ArrayList;
import java.util.List;

public class TeacherCourseAdapter extends BaseRecycleViewAdapter<TeacherCourse> {

    private Context context;

    public TeacherCourseAdapter(Context context) {
        this.context = context;
        List<TeacherCourse> list = new ArrayList<>();
        list.add(new TeacherCourse("汇编语言", "2017-1028 第一学期"));
        list.add(new TeacherCourse("java程序设计", "2017-1028 第一学期"));
        list.add(new TeacherCourse("汇编语言", "2017-1028 第一学期"));
        setData(list);
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_teacher_course, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, TeacherCourse data) {
        ((VHolder) viewHolder).setData(realPosition, data);
    }

    class VHolder extends RecyclerView.ViewHolder {
        TextView mTvCourseName;
        TextView mTvCourseSeason;

        VHolder(View view) {
            super(view);
            this.mTvCourseName = (TextView) view.findViewById(R.id.tv_course_name);
            this.mTvCourseSeason = (TextView) view.findViewById(R.id.tv_course_season);
        }

        void setData(int position, TeacherCourse data) {
            mTvCourseName.setText(data.getClassName());
            mTvCourseSeason.setText(data.getSeason());
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, TeacherClassActivity.class);
                intent.putExtra("teacherCourse", data);
                context.startActivity(intent);
            });
        }
    }
}
