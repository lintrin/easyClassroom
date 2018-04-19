package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.ChatingRoomActivity;
import com.example.administrator.myapplication.model.Course;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomAdapter extends BaseRecycleViewAdapter<Course> {
    Context context;

    public ChatRoomAdapter(Context context, List<Course> courseList) {
        this.context = context;
        if (courseList == null) {
            courseList = new ArrayList<>();
            Course course = new Course();
            course.setClassroomId("S001");
            course.setName("大学英语三");
            course.setTeacherName("陈老师");
            courseList.add(course);
        }

        setData(courseList);

    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chatroom, parent, false);

        return new VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, Course data) {

        ((VHolder)viewHolder).setData(realPosition,data);
    }

    class VHolder extends RecyclerView.ViewHolder {
        View view;
        TextView mTvItemChatroomId;
        TextView mTvItemChatroomName;
        TextView mTvItemChatroomTeacherName;
        TextView mTvItemChatroomEnter;

        VHolder(View view) {
            super(view);
            this.view = view;
            this.mTvItemChatroomId = (TextView) view.findViewById(R.id.tv_item_chatroom_id);
            this.mTvItemChatroomName = (TextView) view.findViewById(R.id.tv_item_chatroom_name);
            this.mTvItemChatroomTeacherName = (TextView) view.findViewById(R.id.tv_item_chatroom_teacher_name);
            this.mTvItemChatroomEnter = (TextView) view.findViewById(R.id.tv_item_chatroom_enter);
        }
        void setData(int position,Course data){
            mTvItemChatroomId.setText("课程号："+data.getClassroomId());
            mTvItemChatroomName.setText("课程："+data.getName());
            mTvItemChatroomTeacherName.setText("任课老师："+data.getTeacherName());
            itemView.setOnClickListener(view1 -> {
               //todo 群聊
                Intent intent = new Intent(context, ChatingRoomActivity.class);
                context.startActivity(intent);
            });
        }
    }
}
