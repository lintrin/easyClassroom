package com.example.administrator.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.Utils.ResourceUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.WebActivity;
import com.example.administrator.myapplication.adapter.NewsAdapter;
import com.example.administrator.myapplication.libary.http.BaseRequest;
import com.example.administrator.myapplication.model.News;
import com.example.administrator.myapplication.model.impl.NewsModel;
import com.example.administrator.view.LocalImageHolderView;
import com.yanzhenjie.nohttp.rest.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StudentFirstFragment extends Fragment implements OnItemClickListener {


    View mainView;
    private RecyclerView mRvFirst;
    private NewsAdapter newsAdapter;
    private ConvenientBanner convenientBanner;
    private List<Integer> localImages = new ArrayList<>();

    public static StudentFirstFragment newInstance() {
        StudentFirstFragment fragment = new StudentFirstFragment();
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
            mainView = inflater.inflate(R.layout.fragment_student_first, container, false);
            initView();
            initData();

        }

        return mainView;
    }

    private void initData() {

        setBanner();
        getNews();
    }

    private void getNews() {
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
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
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

    private void setBanner() {
        //获取本地图片资源ID
        for (int i = 1; i <= 3; i++) {
            localImages.add(ResourceUtils.getResId("pic" + i, R.drawable.class));
        }
        convenientBanner.setPages((CBViewHolderCreator<LocalImageHolderView>) () -> new LocalImageHolderView(), localImages)
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setOnItemClickListener(this);
        convenientBanner.setManualPageable(true);//设置手动影响


    }

    private void initView() {
        mRvFirst = (RecyclerView) mainView.findViewById(R.id.rv_first);
        mRvFirst.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext());
        mRvFirst.setAdapter(newsAdapter);

        convenientBanner = mainView.findViewById(R.id.convenientBanner);


    }


    @Override
    public void onResume() {
        super.onResume();
        //开始翻页
        convenientBanner.startTurning(3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }

    //banner点击事件
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), WebActivity.class);
        intent.putExtra("url", "http://www.zstu.edu.cn/");
        startActivity(intent);
    }
}
