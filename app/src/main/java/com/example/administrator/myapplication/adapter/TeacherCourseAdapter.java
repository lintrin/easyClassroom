package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.TeacherCourseActivity;
import com.example.administrator.myapplication.model.Course;

import java.util.ArrayList;
import java.util.List;

public class TeacherCourseAdapter extends BaseRecycleViewAdapter<Course> {

    private Context context;

    public TeacherCourseAdapter(Context context) {
        this.context = context;
        List<Course> list = new ArrayList<>();
        list.add(new Course("汇编语言", "2017", "2018", 1));
        list.add(new Course("java程序设计", "2017", "2018", 1));
        list.add(new Course("汇编语言", "2017", "2018", 1));
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
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, Course data) {
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

        void setData(int position, Course data) {
            mTvCourseName.setText(data.getName());
            mTvCourseSeason.setText(data.getBeginPeriod()+"-"+data.getEndPeriod()+" 第"+data.getTerm()+"学期");
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, TeacherCourseActivity.class);
                intent.putExtra("course", data);
                context.startActivity(intent);
            });
        }
    }
}
