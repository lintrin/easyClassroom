package com.example.administrator.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.Utils.TopBarUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.MainViewPagerAdapter;
import com.example.administrator.myapplication.fragment.TeacherFirstFragment;
import com.example.administrator.myapplication.fragment.TeacherSecondFragment;
import com.example.administrator.myapplication.fragment.TeacherThirdFragment;
import com.example.administrator.myapplication.view.SteerableViewPager;

import java.util.ArrayList;
import java.util.List;

public class TeacherMainActivity extends AppCompatActivity {

    private SteerableViewPager vpMain;
    private BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        TopBarUtils topBarUtils = new TopBarUtils(this);
        topBarUtils.setTitle("我的课程");
        topBarUtils.getBtnLeft().setVisibility(View.GONE);
        List<Fragment> fragmentList = new ArrayList<>(3);
        fragmentList.add(TeacherFirstFragment.newInstance());
        fragmentList.add(TeacherSecondFragment.newInstance());
        fragmentList.add(TeacherThirdFragment.newInstance());
        vpMain = findViewById(R.id.vp_main);
        vpMain.setLeft_allow(false);
        vpMain.setRight_allow(false);
        vpMain.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        navigation = findViewById(R.id.navigation);
        initListener();
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
}
