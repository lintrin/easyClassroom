package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.CourseRecord;

/**
 * @author by JingQ on 2018/4/27.
 */

public class StudentCheckInAdapter extends BaseRecycleViewAdapter<CourseRecord> {
    
    private Context context;

    public StudentCheckInAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student_check_in, parent, false);
        return new StudentCheckInAdapter.VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, CourseRecord data) {
        ((StudentCheckInAdapter.VHolder) viewHolder).setData(realPosition, data);
    }

    class VHolder extends RecyclerView.ViewHolder {
        TextView mTVCourseRecordTime;
        TextView mTVCheckStatus;

        VHolder(View view) {
            super(view);
            this.mTVCourseRecordTime = view.findViewById(R.id.tv_course_record);
            this.mTVCheckStatus = view.findViewById(R.id.tv_check_in_status);
        }

        public void setData(int position, CourseRecord data) {

            mTVCourseRecordTime.setText(data.getClassTime());
            if (data.getCheckIn()) {
                mTVCheckStatus.setText("已签到");
            } else {
                mTVCheckStatus.setText("未签到");
            }
        }
    }


}
