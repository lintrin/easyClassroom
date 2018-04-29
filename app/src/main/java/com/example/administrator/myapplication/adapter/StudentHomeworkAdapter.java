package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.Utils.ImageLoadUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.Homework;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

public class StudentHomeworkAdapter extends BaseRecycleViewAdapter<Homework> {

    Context context;

    public StudentHomeworkAdapter(Context context, List<Homework> list) {
        this.context = context;
        setData(list);
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_teacher_homework, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, Homework data) {

        ((VHolder)viewHolder).setData(realPosition,data);
    }

    static class VHolder extends RecyclerView.ViewHolder {

        ImageView mIvItemTeacherHomework;
        TextView mTvItemTeacherHomeworkName;
        TextView mTvItemTeacherHomeworkUserId;
        TextView mTvItemTeacherHomeworkTime;
        TextView mTvItemTeacherHomeworkTeacherScore;
        TextView mTvItemTeacherHomeworkSubmit;

        VHolder(View view) {
            super(view);
            this.mIvItemTeacherHomework = (ImageView) view.findViewById(R.id.iv_item_teacher_homework);
            this.mTvItemTeacherHomeworkName = (TextView) view.findViewById(R.id.tv_item_teacher_homework_name);
            this.mTvItemTeacherHomeworkUserId = (TextView) view.findViewById(R.id.tv_item_teacher_homework_userId);
            this.mTvItemTeacherHomeworkTime = (TextView) view.findViewById(R.id.tv_item_teacher_homework_time);
            this.mTvItemTeacherHomeworkTeacherScore = (TextView) view.findViewById(R.id.tv_item_teacher_homework_teacher_score);
            this.mTvItemTeacherHomeworkSubmit = view.findViewById(R.id.tv_item_teacher_homework_submit);
        }

        void setData(int position, Homework data) {
            ImageLoadUtils.setImageByUrl(mIvItemTeacherHomework,data.getMarkHomeworkUrl());
            mTvItemTeacherHomeworkName.setText(data.getUploadName());
            mTvItemTeacherHomeworkUserId.setText(data.getUserId() + "");
            mTvItemTeacherHomeworkTime.setText(data.getUploadDate());
            mTvItemTeacherHomeworkTeacherScore.setText(data.getScore());
        }
    }


}
