package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.CourseResource;
import com.example.administrator.utils.DateUtils;
import com.example.administrator.utils.PeriodUtil;

/**
 * @author by JingQ on 2018/4/23.
 */

public class CourseResourceAdapter extends BaseRecycleViewAdapter<CourseResource> {

    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private Context context;

    public CourseResourceAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_course_resource, parent, false);
        return new CourseResourceAdapter.VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, CourseResource data) {
        ((CourseResourceAdapter.VHolder) viewHolder).setData(realPosition, data);
    }

    class VHolder extends RecyclerView.ViewHolder {
        TextView mtvFileName;
        TextView mtvUploadTime;

        VHolder(View view) {
            super(view);
            this.mtvFileName = view.findViewById(R.id.tv_file_name);
            this.mtvUploadTime = view.findViewById(R.id.tv_upload_time);
        }

        public void setData(int position, CourseResource data) {
            mtvFileName.setText(data.getName());
            mtvUploadTime.setText(PeriodUtil.format(data.getUploadDate(), TIME_PATTERN));


        }
    }
}
