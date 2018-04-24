package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.Homework;

import java.util.ArrayList;
import java.util.List;

public class CheckHomeWordAdapter extends BaseRecycleViewAdapter<Homework> {

    Context context;

    public CheckHomeWordAdapter(Context context, List<Homework> list) {
        this.context = context;
        setData(list);
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_homework, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, Homework data) {
        ((VHolder)viewHolder).setData(realPosition,data);

    }

    class VHolder extends RecyclerView.ViewHolder {
        View view;
        TextView mTvItemHomeworkId;
        TextView mTvItemHomeworkName;
        TextView mTvItemHomeworkDownload;
        TextView mTvItemHomeworkConfirm;
        EditText mEtItemHomeworkScore;

        VHolder(View view) {
            super(view);

            this.mTvItemHomeworkId = (TextView) view.findViewById(R.id.tv_item_homework_id);
            this.mTvItemHomeworkName = (TextView) view.findViewById(R.id.tv_item_homework_name);
            this.mTvItemHomeworkDownload = (TextView) view.findViewById(R.id.tv_item_homework_download);
            this.mTvItemHomeworkConfirm = (TextView) view.findViewById(R.id.tv_item_homework_confirm);
            this.mEtItemHomeworkScore = (EditText) view.findViewById(R.id.et_item_homework_score);
        }

        void setData(int position,Homework data){
            mTvItemHomeworkId.setText("");
            mTvItemHomeworkName.setText("");
            mEtItemHomeworkScore.setText("");

        }
    }
}
