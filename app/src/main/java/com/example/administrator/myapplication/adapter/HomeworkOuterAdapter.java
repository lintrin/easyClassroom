package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.TeacherHomeworkActivity;
import com.example.administrator.myapplication.model.HomeworkOuter;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class HomeworkOuterAdapter extends BaseRecycleViewAdapter<HomeworkOuter> {

    private Context context;

    public HomeworkOuterAdapter(Context context, List<HomeworkOuter> homeworkOuters) {
        this.context = context;
        setData(homeworkOuters);
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_teacher_homework_outer, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, HomeworkOuter data) {
        ((VHolder) viewHolder).setData(realPosition, data);

    }


    class VHolder extends RecyclerView.ViewHolder {
        TextView mTvItemHomeworkName;
        VHolder(View view) {
            super(view);

            this.mTvItemHomeworkName = (TextView) view.findViewById(R.id.tv_item_homework_name_outer);
        }

        void setData(int position, HomeworkOuter data) {
            mTvItemHomeworkName.setText(data.getHomeworkName());
        }
    }
}
