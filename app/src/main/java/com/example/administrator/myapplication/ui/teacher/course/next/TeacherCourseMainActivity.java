package com.example.administrator.myapplication.ui.teacher.course.next;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.FragmentViewPagerAdapter;
import com.example.administrator.myapplication.ui.communal.CourseChattingFragment;
import com.example.administrator.myapplication.ui.communal.CourseRescourceFragment;
import com.example.administrator.myapplication.ui.student.course.next.homework.StudentHomeworkFragment;
import com.example.administrator.myapplication.ui.student.course.next.news.StudentCourseMessageFragment;
import com.example.administrator.myapplication.ui.student.course.next.sign.StudentCheckInFragment;
import com.example.administrator.myapplication.ui.teacher.course.next.sign.TeacherCheckInFragment;
import com.example.administrator.myapplication.ui.teacher.course.next.news.TeacherCourseMessageFragment;
import com.example.administrator.myapplication.ui.teacher.course.next.homework.TeacherHomeworkOuterFragment;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;


public class TeacherCourseMainActivity extends AppCompatActivity {
    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentViewPagerAdapter adapter;
    private Course course;
    private List<Fragment> fragmentList;
    String title[] = {"公共资源","作业","成员","签到","公共"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course);
        course = (Course) getIntent().getSerializableExtra("course");
        CourseModel.getInstance().setCourse(course);
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
        fragmentList.add(CourseRescourceFragment.newInstance());
        fragmentList.add(TeacherHomeworkOuterFragment.newInstance());
        fragmentList.add(CourseChattingFragment.newInstance());
        fragmentList.add(TeacherCheckInFragment.newInstance());
        fragmentList.add(TeacherCourseMessageFragment.newInstance());


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
}
