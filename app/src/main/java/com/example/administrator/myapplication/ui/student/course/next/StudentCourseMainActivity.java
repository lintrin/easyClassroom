package com.example.administrator.myapplication.ui.student.course.next;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewParent;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.FragmentViewPagerAdapter;
import com.example.administrator.myapplication.ui.communal.CourseChattingFragment;
import com.example.administrator.myapplication.ui.communal.CourseResourceFragment;
import com.example.administrator.myapplication.ui.student.course.next.news.StudentCourseMessageFragment;
import com.example.administrator.myapplication.ui.student.course.next.sign.StudentCheckInFragment;
import com.example.administrator.myapplication.ui.communal.CourseRescourceFragment;
import com.example.administrator.myapplication.ui.student.course.next.homework.StudentHomeworkFragment;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;


public class StudentCourseMainActivity extends AppCompatActivity {
    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentViewPagerAdapter adapter;
    private Course course;
    private List<Fragment> fragmentList;
    String title[] = {"公共资源","作业","成员","签到","公告"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course);
        initView();
    }

    private void initView() {
        tabLayout = findViewById(R.id.tablayout_student);
        viewPager = findViewById(R.id.vp_student);
        initPager();
        initListener();
    }

    private void initPager() {
        fragmentList =new ArrayList<>(5);
        fragmentList.add(CourseResourceFragment.newInstance());
        fragmentList.add(StudentHomeworkFragment.newInstance());
        fragmentList.add(CourseChattingFragment.newInstance());
        fragmentList.add(StudentCheckInFragment.newInstance());
        fragmentList.add(StudentCourseMessageFragment.newInstance());


        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(),fragmentList);
        course = (Course) getIntent().getSerializableExtra("course");
        CourseModel.getInstance().setCourse(course);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager,title);
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager fragmentManager=getSupportFragmentManager();
        for(int indext=0;indext<fragmentManager.getFragments().size();indext++)
        {
            Fragment fragment=fragmentManager.getFragments().get(indext); //找到第一层Fragment
            if(fragment==null)
                Log.w("sss", "Activity result no fragment exists for index: 0x"
                        + Integer.toHexString(requestCode));
            else
                handleResult(fragment,requestCode,resultCode,data);
        }
    }
    /**
     * 递归调用，对所有的子Fragment生效
     * @param fragment
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void handleResult(Fragment fragment,int requestCode,int resultCode,Intent data)
    {
        fragment.onActivityResult(requestCode, resultCode, data);//调用每个Fragment的onActivityResult
        Log.e("sss", "handleResult");
        List<Fragment> childFragment = fragment.getChildFragmentManager().getFragments(); //找到第二层Fragment
        if(childFragment!=null)
            for(Fragment f:childFragment)
                if(f!=null)
                {
                    handleResult(f, requestCode, resultCode, data);
                }
        if(childFragment==null)
            Log.e("ssss", "null");
    }

}
