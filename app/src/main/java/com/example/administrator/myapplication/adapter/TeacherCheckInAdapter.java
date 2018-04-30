package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.ui.teacher.course.next.sign.TeacherCheckInDetailsActivity;
import com.example.administrator.myapplication.model.CourseRecord;

/**
 * @author by JingQ on 2018/4/27.
 */

public class TeacherCheckInAdapter extends BaseRecycleViewAdapter<CourseRecord> {
    
    private Context context;

    public TeacherCheckInAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_teacher_check_in, parent, false);
        return new TeacherCheckInAdapter.VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, CourseRecord data) {
        ((TeacherCheckInAdapter.VHolder) viewHolder).setData(realPosition, data);
    }

    class VHolder extends RecyclerView.ViewHolder {
        TextView mTVCourseRecordTime;
        TextView mTVHasCheckCount;
        TextView mTVAllCount;
        TextView mTVCheckCode;
        Button mBtnEnter;

        VHolder(View view) {
            super(view);
            this.mTVCourseRecordTime = view.findViewById(R.id.tv_course_record);
            this.mTVHasCheckCount = view.findViewById(R.id.tv_has_check_count);
            this.mTVAllCount = view.findViewById(R.id.tv_all_count);
            this.mTVCheckCode = view.findViewById(R.id.tv_check_code);
            this.mBtnEnter = view.findViewById(R.id.btn_enter);
        }

        public void setData(int position, CourseRecord data) {
            mTVCourseRecordTime.setText(data.getClassTime());
            mTVHasCheckCount.setText(data.getCheckCount() + "");
            mTVAllCount.setText(data.getAllStudentCount() + "");
            mTVCheckCode.setText(data.getCheckCode());

            mBtnEnter.setOnClickListener(view -> {
                Intent intent = new Intent(context, TeacherCheckInDetailsActivity.class);
                intent.putExtra("courseRecordId", data.getId());
                context.startActivity(intent);
            });
        }
    }


}
