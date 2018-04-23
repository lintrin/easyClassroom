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
import com.example.administrator.myapplication.model.CourseMessage;
import com.example.administrator.myapplication.model.News;

/**
 * @author by JingQ on 2018/4/23.
 */

public class CourseMessageAdapter extends BaseRecycleViewAdapter<CourseMessage> {

    private Context context;

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new CourseMessageAdapter.VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, CourseMessage data) {
        ((CourseMessageAdapter.VHolder) viewHolder).setData(realPosition, data);
    }

    class VHolder extends RecyclerView.ViewHolder {
        TextView mTvNewsTime;
        TextView mTvNewsMsg;

        VHolder(View view) {
            super(view);
            this.mTvNewsTime = (TextView) view.findViewById(R.id.tv_news_time);
            this.mTvNewsMsg = (TextView) view.findViewById(R.id.tv_news_msg);
        }

        public void setData(int position, CourseMessage data) {

            mTvNewsTime.setText(data.getPublishTime());
            mTvNewsMsg.setText(data.getContent());
            mTvNewsMsg.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
//            itemView.setOnClickListener(view -> {
//                Intent intent = new Intent(context,NewsActivity.class);
//                intent.putExtra("news",data);
//                context.startActivity(intent);
//            });
        }
    }
}
