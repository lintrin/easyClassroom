package com.example.administrator.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.CheckHomeWordAdapter;

public class CheckHomeWordActivity extends AppCompatActivity implements View.OnClickListener {


     //全部下载
    private Button mBtnHomeworkModify;
     // 上传批改结果
    private Button mBtnHomeworkUpdateScore;
    private RecyclerView mRvHomework;
    private CheckHomeWordAdapter adapter;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_home_word);
        context = this;
        initView();
        initData();
    }

    private void initData() {
        adapter = new CheckHomeWordAdapter(context,null);
        mRvHomework.setLayoutManager(new LinearLayoutManager(context));
        View headView = LayoutInflater.from(context).inflate(R.layout.item_homework,null);
        headView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        initHeadView(headView);
        adapter.setHeaderView(headView);
        mRvHomework.setAdapter(adapter);
    }

    private void initHeadView(View view) {
        TextView mTvItemHomeworkId  = (TextView) view.findViewById(R.id.tv_item_homework_id);
        TextView mTvItemHomeworkName  = (TextView) view.findViewById(R.id.tv_item_homework_name);
        TextView mTvItemHomeworkDownload  = (TextView) view.findViewById(R.id.tv_item_homework_download);
        TextView mTvItemHomeworkConfirm  = (TextView) view.findViewById(R.id.tv_item_homework_confirm);
        EditText mEtItemHomeworkScore  = (EditText) view.findViewById(R.id.et_item_homework_score);

        mTvItemHomeworkId.setText("学号");
        mTvItemHomeworkId.setTextColor(ContextCompat.getColor(context,R.color.text_color_black_default));

        mTvItemHomeworkName.setText("姓名");
        mTvItemHomeworkName.setTextColor(ContextCompat.getColor(context,R.color.text_color_black_default));

        mTvItemHomeworkDownload.setText("作业下载");
        mTvItemHomeworkDownload.setTextColor(ContextCompat.getColor(context,R.color.text_color_black_default));

        mEtItemHomeworkScore.setVisibility(View.GONE);

        mTvItemHomeworkConfirm.setText("分数");
        mTvItemHomeworkConfirm.setTextColor(ContextCompat.getColor(context,R.color.text_color_black_default));
        mTvItemHomeworkConfirm.setBackgroundColor(ContextCompat.getColor(context,R.color.transparent));
    }

    private void initView() {
        mBtnHomeworkModify = (Button) findViewById(R.id.btn_homework_modify);
        mBtnHomeworkModify.setOnClickListener(this);
        mBtnHomeworkUpdateScore = (Button) findViewById(R.id.btn_homework_update_score);
        mBtnHomeworkUpdateScore.setOnClickListener(this);
        mRvHomework = (RecyclerView) findViewById(R.id.rv_homework);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_homework_modify:
                break;
            case R.id.btn_homework_update_score:
                break;
        }
    }
}
