package com.example.administrator.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.NewsAdapter;
import com.example.administrator.myapplication.libary.http.BaseRequest;
import com.example.administrator.myapplication.model.News;
import com.example.administrator.myapplication.model.impl.NewsModel;
import com.yanzhenjie.nohttp.rest.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherFirstFragment extends Fragment {

    View mainView;
    private RecyclerView mRvFirst;
    private NewsAdapter newsAdapter;

    public static TeacherFirstFragment newInstance() {
        TeacherFirstFragment fragment = new TeacherFirstFragment();
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
            mainView = inflater.inflate(R.layout.fragment_teacher_first, container, false);
            initView();
        }

        return mainView;
    }


    private void initData() {
        NewsModel.getInstance().getNewsFromNetwork(new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                News news = JsonUtils.parseObject(response.get().toString(), "body", News.class);
                List<News> newsList = new ArrayList<>();
                newsList.add(news);
                Log.i("sss", "onCompleted: " + news.toString());
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                for (int i = 0; i < newsList.size(); i++) {
                    Date dt = new Date(newsList.get(i).getCreateTime());
                    String sDateTime = sdf.format(dt);
                    newsList.get(i).setDate(sDateTime);
                }
                newsAdapter.refreshData(newsList);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    private void initView() {
        mRvFirst = (RecyclerView) mainView.findViewById(R.id.rv_first);
        mRvFirst.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext());
        mRvFirst.setAdapter(newsAdapter);
    }
}
