package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.Homework;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

public class StudentHomeworkMarkAdapter extends BaseRecycleViewAdapter<Homework> {

    Context context;

    public StudentHomeworkMarkAdapter(Context context, List<Homework> list) {
        this.context = context;
        setData(list);
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student_homework_mark, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, Homework data) {
        ((VHolder)viewHolder).setData(realPosition,data);
    }


    static class VHolder extends RecyclerView.ViewHolder {
        TextView mTvStudentNumber;
        TextView mTvStudentHomeworkScore;

        VHolder(View view) {
            super(view);
            this.mTvStudentNumber = view.findViewById(R.id.tv_student_number);
            this.mTvStudentHomeworkScore = view.findViewById(R.id.tv_student_homework_score);
        }
        public void setData(int position,Homework data){
            mTvStudentNumber.setText(data.getUserId()+"");
            mTvStudentHomeworkScore.setText(data.getScore()+"分");

        }
    }
}
