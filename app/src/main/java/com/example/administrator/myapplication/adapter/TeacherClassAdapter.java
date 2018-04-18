package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.CheckHomeWordActivity;
import com.example.administrator.myapplication.model.TeacherClass;

import java.util.ArrayList;
import java.util.List;

public class TeacherClassAdapter extends BaseRecycleViewAdapter<TeacherClass> {


    private Context context;

    public TeacherClassAdapter(Context context) {
        this.context = context;
        List<TeacherClass> list = new ArrayList<>();
        list.add(new TeacherClass(1, 70, 80, "SX990S"));
        list.add(new TeacherClass(2, 72, 80, "SX990S"));
        list.add(new TeacherClass(3, 79, 80, "SX990S"));
        setData(list);

    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_teacher_class, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, TeacherClass data) {

        ((VHolder) viewHolder).setData(realPosition, data);
    }

    class VHolder extends RecyclerView.ViewHolder {
        TextView mTvCourseId;
        TextView mTvCourseSignCount;
        TextView mTvCourseStudentCount;
        TextView mTvCourseSignCode;
        TextView mTvCourseWork;

        public VHolder(View view) {
            super(view);
            this.mTvCourseId = view.findViewById(R.id.tv_course_id);
            this.mTvCourseSignCount = view.findViewById(R.id.tv_course_sign_count);
            this.mTvCourseStudentCount = view.findViewById(R.id.tv_course_student_count);
            this.mTvCourseSignCode = view.findViewById(R.id.tv_course_sign_code);
            this.mTvCourseWork = view.findViewById(R.id.tv_course_work);
        }

        void setData(int position, TeacherClass data) {
            mTvCourseId.setText(data.getId() + "");
            mTvCourseSignCount.setText(data.getSignCount() + "");
            mTvCourseStudentCount.setText(data.getStudentCount() + "");
            mTvCourseSignCode.setText(data.getSignCode() + "");
            mTvCourseWork.setText("批改作业");
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, CheckHomeWordActivity.class);
                context.startActivity(intent);
            });
        }
    }


}
