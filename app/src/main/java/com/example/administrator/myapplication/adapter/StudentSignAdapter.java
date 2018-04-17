package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.Student;
import com.example.administrator.myapplication.model.StudentSign;

import java.util.ArrayList;
import java.util.List;

public class StudentSignAdapter extends BaseRecycleViewAdapter<StudentSign> {

    Context context;

    public StudentSignAdapter(Context context) {
        this.context = context;
        List<StudentSign> list = new ArrayList<>();
        list.add(new StudentSign("2018-03-03 09:00", "汇编语言", "已签到"));
        list.add(new StudentSign("2018-03-03 18:01", "java程序设计", "已签到"));
        list.add(new StudentSign("2018-03-01 08:55", "汇编语言", "已签到"));
        setData(list);

    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student_sign, null);
        return new VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, StudentSign data) {

        ((VHolder)viewHolder).setData(realPosition,data);
    }

    class VHolder extends RecyclerView.ViewHolder{
        TextView mTvStudentCourseTime;
        TextView mTvStudentCourseName;
        TextView mTvStudentSignState;

        VHolder(View view) {
            super(view);
            this.mTvStudentCourseTime = (TextView) view.findViewById(R.id.tv_student_course_time);
            this.mTvStudentCourseName = (TextView) view.findViewById(R.id.tv_student_course_name);
            this.mTvStudentSignState = (TextView) view.findViewById(R.id.tv_student_sign_state);
        }
        void setData(int position, StudentSign data){
            mTvStudentCourseTime.setText(data.getSignTime());
            mTvStudentCourseName.setText(data.getCourseName());
            mTvStudentSignState.setText(data.getSignState());
            mTvStudentSignState.setTextColor(ContextCompat.getColor(context,R.color.main_blue));
        }
    }
}
