package com.example.administrator.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.administrator.Utils.JMessageUtil;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.MainViewPagerAdapter;
import com.example.administrator.myapplication.fragment.StudentFirstFragment;
import com.example.administrator.myapplication.fragment.StudentSecondFragment;
import com.example.administrator.myapplication.fragment.SutdentThirdFragment;
import com.example.administrator.myapplication.view.SteerableViewPager;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

public class StudentMainActivity extends AppCompatActivity {

    private SteerableViewPager vpMain;
    private BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        List<Fragment> fragmentList = new ArrayList<>(3);
        fragmentList.add(StudentFirstFragment.newInstance());
        fragmentList.add(StudentSecondFragment.newInstance());
        fragmentList.add(SutdentThirdFragment.newInstance());
        vpMain = findViewById(R.id.vp_main);
        vpMain.setLeft_allow(false);
        vpMain.setRight_allow(false);
        vpMain.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        navigation = findViewById(R.id.navigation);
        initListener();
        getConversation();
    }

    private void initListener() {
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    vpMain.setCurrentItem(0, false);
                    return true;
                case R.id.navigation_dashboard:
                    vpMain.setCurrentItem(1, false);

                    return true;
                case R.id.navigation_notifications:
                    vpMain.setCurrentItem(2, false);

                    return true;
            }
            return false;
        }
    };

    /**
     * 单聊会话
     */
    private void getConversation() {
        String idNumber = "101026";
        Conversation conversation = Conversation.createSingleConversation(idNumber, JMessageUtil.APP_KEY);
        JMessageClient.getConversationList();
//        sendMessage();
        conversation.getAllMessage();
    }

    /**
     * 简单发送消息
     */
    private void sendMessage() {
        JMessageClient.createSingleTextMessage("101026", JMessageUtil.APP_KEY, "第一条测试消息");
    }
}
