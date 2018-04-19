package com.example.administrator.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.Utils.TopBarUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.News;

public class NewsActivity extends AppCompatActivity {

    News news;
    Context context;
    private TextView mTvNewsTitle;
    private TextView mTvNewsCreateUserName;
    private TextView mTvNewsTime;
    private TextView mTvNewsContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        context = this;
        initView();
        initData();

    }

    private void initData() {
        news = (News) getIntent().getSerializableExtra("news");
        mTvNewsTitle.setText(news.getTitle());
        mTvNewsContent.setText(news.getContent());
        mTvNewsTime.setText(news.getDate());
        mTvNewsCreateUserName.setText("发布人："+news.getCreateUserName());
    }

    private void initView() {
        mTvNewsTitle = (TextView) findViewById(R.id.tv_news_title);
        mTvNewsCreateUserName = (TextView) findViewById(R.id.tv_news_createUserName);
        mTvNewsTime = (TextView) findViewById(R.id.tv_news_time);
        mTvNewsContent = (TextView) findViewById(R.id.tv_news_content);
        TopBarUtils topBarUtils  =new TopBarUtils(this);
        topBarUtils.setTitle("公告");
    }
}
