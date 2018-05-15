package com.example.administrator.myapplication.ui.teacher.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.administrator.myapplication.view.dialog.NoticeAddDialogue;
import com.example.administrator.utils.JsonUtils;
import com.example.administrator.utils.ResourceUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.ui.communal.WebActivity;
import com.example.administrator.myapplication.adapter.NewsAdapter;

import library.http.BaseRequest;

import com.example.administrator.myapplication.model.News;
import com.example.administrator.myapplication.model.impl.NewsModel;
import com.example.administrator.view.LocalImageHolderView;
import com.yanzhenjie.nohttp.rest.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherFirstFragment extends Fragment implements OnItemClickListener {


    View mainView;
    private RecyclerView mRvFirst;
    private NewsAdapter newsAdapter;
    private Button btn_send_notice;
    private ConvenientBanner convenientBanner;
    private NoticeAddDialogue dialogue;
    private List<Integer> localImages = new ArrayList<>();

    public static TeacherFirstFragment newInstance() {
        TeacherFirstFragment fragment = new TeacherFirstFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_teacher_first, container, false);
            initView();
            initData();
            initListener();
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
                List<News> newsList = JsonUtils.parseArray(response.get().toString(), "body", News.class);
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
        btn_send_notice = mainView.findViewById(R.id.btn_send_notice);
        mRvFirst.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getContext());
        mRvFirst.setAdapter(newsAdapter);

        convenientBanner = mainView.findViewById(R.id.convenientBanner);
    }
    private void initListener(){
        btn_send_notice.setOnClickListener(v -> {
            dialogue = new  NoticeAddDialogue();
            dialogue.show(getFragmentManager(),"");
        });
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void sendNotice(News news){
        NewsModel.getInstance().sendNotice(news, new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onCompleted(Response response) {
                if (dialogue!=null)
                    dialogue.dismiss();
              getNews();
            }

            @Override
            public void onError(String msg) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "发布失败", Toast.LENGTH_SHORT).show();
                        if (dialogue!=null)
                            dialogue.dismiss();
                    }
                });
              
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}


