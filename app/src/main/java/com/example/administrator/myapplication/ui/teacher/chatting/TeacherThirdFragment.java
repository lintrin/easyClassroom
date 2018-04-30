package com.example.administrator.myapplication.ui.teacher.chatting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.ChatRoomAdapter;

public class TeacherThirdFragment extends Fragment {

    View mainView;
    private RecyclerView mRvChatroom;
    private ChatRoomAdapter adapter;


    public static TeacherThirdFragment newInstance() {
        TeacherThirdFragment fragment = new TeacherThirdFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_teacher_third, container, false);
            initView();
        }
        initData();
        return mainView;
    }

    private void initData() {
        adapter= new ChatRoomAdapter(getContext(),null);
        mRvChatroom.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvChatroom.setAdapter(adapter);
    }

    private void initView() {
        mRvChatroom = (RecyclerView) mainView.findViewById(R.id.rv_chatroom);

    }
}
