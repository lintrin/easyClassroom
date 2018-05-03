package com.example.administrator.myapplication.ui.student.course.next;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.ui.communal.CourseChattingFragment;
import com.example.administrator.myapplication.ui.student.course.next.news.StudentCourseMessageFragment;
import com.example.administrator.myapplication.ui.student.course.next.sign.StudentCheckInFragment;
import com.example.administrator.myapplication.ui.communal.CourseResourceFragment;
import com.example.administrator.myapplication.ui.student.course.next.homework.StudentHomeworkFragment;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.impl.CourseModel;

import java.util.List;


public class StudentCourseMainActivity extends AppCompatActivity {
    private RadioGroup mTabRg;
    private FragmentTabHost mTabHost;

    private Course course;

    //todo 五个页面实现
    private final Class[] fragments = {CourseResourceFragment.class,StudentHomeworkFragment.class,
            CourseChattingFragment.class, StudentCheckInFragment.class, StudentCourseMessageFragment.class
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
        course = (Course) getIntent().getSerializableExtra("course");
        CourseModel.getInstance().setCourse(course);
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
