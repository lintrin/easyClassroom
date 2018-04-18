package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.NewsActivity;
import com.example.administrator.myapplication.model.News;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsAdapter extends BaseRecycleViewAdapter<News> {
    private Context context;


    public NewsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, News data) {
        ((VHolder) viewHolder).setData(realPosition, data);
    }

    class VHolder extends RecyclerView.ViewHolder {
        TextView mTvNewsTime;
        TextView mTvNewsMsg;

        VHolder(View view) {
            super(view);
            this.mTvNewsTime = (TextView) view.findViewById(R.id.tv_news_time);
            this.mTvNewsMsg = (TextView) view.findViewById(R.id.tv_news_msg);
        }

        public void setData(int position, News data) {

            mTvNewsTime.setText(data.getDate());
            mTvNewsMsg.setText(data.getTitle());
            mTvNewsMsg.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context,NewsActivity.class);
                intent.putExtra("news",data);
                context.startActivity(intent);
            });
        }
    }
}
