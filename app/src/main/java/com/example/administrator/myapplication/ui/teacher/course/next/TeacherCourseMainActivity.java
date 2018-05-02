package com.example.administrator.myapplication.ui.teacher.course.next;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.ui.communal.CourseChattingFragment;
import com.example.administrator.myapplication.ui.communal.CourseResourceFragment;
import com.example.administrator.myapplication.ui.teacher.course.next.sign.TeacherCheckInFragment;
import com.example.administrator.myapplication.ui.teacher.course.next.news.TeacherCourseMessageFragment;
import com.example.administrator.myapplication.ui.teacher.course.next.homework.TeacherHomeworkOuterFragment;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.impl.CourseModel;


public class TeacherCourseMainActivity extends AppCompatActivity {
    private RadioGroup mTabRg;
    private FragmentTabHost mTabHost;

    private Course course;

    //todo 五个页面实现
    private final Class[] fragments = {CourseResourceFragment.class,TeacherHomeworkOuterFragment.class,
            CourseChattingFragment.class, TeacherCheckInFragment.class, TeacherCourseMessageFragment.class
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course);
        course = (Course) getIntent().getSerializableExtra("course");
        CourseModel.getInstance().setCourse(course);
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
        //MOCK一个课程数据
        Bundle bundle = new Bundle();
        bundle.putSerializable("course", course);
        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(i + "").setIndicator(i + "");
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragments[i], bundle);
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
