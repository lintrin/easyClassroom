package com.example.administrator.myapplication.ui.student;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.model.CourseUser;
import com.example.administrator.myapplication.model.User;
import com.example.administrator.myapplication.model.impl.UserModel;
import com.example.administrator.myapplication.ui.communal.ChattingActivity;
import com.example.administrator.myapplication.ui.communal.UserInfoActivity;
import com.example.administrator.myapplication.view.MySlidingPaneLayout;
import com.example.administrator.utils.ImageLoadUtils;
import com.example.administrator.utils.JMessageUtil;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.MainViewPagerAdapter;
import com.example.administrator.myapplication.ui.student.homepage.StudentFirstFragment;
import com.example.administrator.myapplication.ui.student.course.StudentSecondFragment;
import com.example.administrator.myapplication.ui.student.chatting.StudentThirdFragment;
import com.example.administrator.myapplication.view.SteerableViewPager;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;

public class StudentMainActivity extends AppCompatActivity {

    private SteerableViewPager vpMain;
    private BottomNavigationView navigation;
    private Context context;


    /*
    侧边栏
     */
    public MySlidingPaneLayout slidingPaneLayout;
    public LinearLayout leftLayout;
    private TextView tv_user_info_modify;
    private ImageView iv_user_info_avatar;
    private TextView tv_user_info_name;
    private TextView tv_id_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
        JMessageClient.registerEventReceiver(this);
    }

    private void initView() {
        List<Fragment> fragmentList = new ArrayList<>(3);
        fragmentList.add(StudentFirstFragment.newInstance());
        fragmentList.add(StudentSecondFragment.newInstance());
        fragmentList.add(StudentThirdFragment.newInstance());
        vpMain = findViewById(R.id.vp_main);
        vpMain.setLeft_allow(false);
        vpMain.setRight_allow(false);
        vpMain.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        navigation = findViewById(R.id.navigation);
        //初始化侧边栏
        initLeftLayout();
        initListener();
//        getConversation();
    }

    private void initLeftLayout() {

        slidingPaneLayout = findViewById(R.id.main_sliding_layout);
        slidingPaneLayout.setViewPager(vpMain);
        leftLayout = findViewById(R.id.layout_left);
        tv_id_number = findViewById(R.id.tv_id_number);
        tv_user_info_modify = findViewById(R.id.tv_user_info_modify);
        iv_user_info_avatar = findViewById(R.id.iv_user_info_avatar);
        tv_user_info_name = findViewById(R.id.tv_user_info_name);

        User user = UserModel.getInstance().getUser();
        ImageLoadUtils.setAvatarImage(context,iv_user_info_avatar,R.mipmap.avatar1,R.mipmap.avatar1);
        tv_id_number.setText(user.getIdNumber());
        tv_user_info_name.setText(user.getName());

    }

    private void initListener() {
        tv_user_info_modify.setOnClickListener(v -> {
            Intent intent = new Intent(context, UserInfoActivity.class);
            startActivity(intent);
        });
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

    @Override
    protected void onDestroy() {
        JMessageClient.unRegisterEventReceiver(this);
        super.onDestroy();
    }

    public void onEvent(NotificationClickEvent event) {

        JMessageClient.getUserInfo(event.getMessage().getFromUser().getUserName(), new GetUserInfoCallback() {
            @Override
            public void gotResult(int i, String s, UserInfo userInfo) {
                if (userInfo != null) {
                    CourseUser courseUser = new CourseUser();
                    courseUser.setIdNumber(event.getMessage().getFromUser().getUserName());
                    courseUser.setUserName(event.getMessage().getFromUser().getNickname());
                    Intent notificationIntent = new Intent(context, ChattingActivity.class);
                    notificationIntent.putExtra("otherUser", courseUser);
                    startActivity(notificationIntent);//自定义跳转到指定页面
                }
            }
        });


    }
}
