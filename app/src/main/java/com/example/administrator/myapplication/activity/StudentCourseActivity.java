package com.example.administrator.myapplication.activity;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.fragment.StudentCourseMessageFragment;
import com.example.administrator.myapplication.fragment.CourseRescouresFragment;
import com.example.administrator.myapplication.fragment.HomeworkFragment;


public class StudentCourseActivity extends AppCompatActivity {
    private RadioGroup mTabRg;
    private FragmentTabHost mTabHost;


    //todo 五个页面实现
    private final Class[] fragments = {CourseRescouresFragment.class,HomeworkFragment.class,
            HomeworkFragment.class, HomeworkFragment.class, StudentCourseMessageFragment.class
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course);
        initView();
    }

    private void initView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabRg = (RadioGroup) findViewById(R.id.tab_rg_menu);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        initPager();
        initListener();
    }

    private void initPager() {
        int count =  fragments.length;
        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(i + "").setIndicator(i + "");
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragments[i], null);
        }
    }

    private void initListener() {

        mTabRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_resource:

                        mTabHost.setCurrentTab(0);

                        break;
                    case R.id.tab_homework:

                        mTabHost.setCurrentTab(1);

                        break;
                    case R.id.tab_member:
                        mTabHost.setCurrentTab(2);


                        break;
                    case R.id.tab_sign:
                        mTabHost.setCurrentTab(3);

                        break;
                    case R.id.tab_notic:
                        mTabHost.setCurrentTab(4);

                        break;


                    default:
                        break;
                }
            }
        });

        mTabHost.setCurrentTab(0);
    }
}
