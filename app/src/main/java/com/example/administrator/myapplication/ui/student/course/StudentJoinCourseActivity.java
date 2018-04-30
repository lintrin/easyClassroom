package com.example.administrator.myapplication.ui.student.course;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.utils.JsonUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.yanzhenjie.nohttp.rest.Response;

import library.http.BaseRequest;
import library.http.HttpHead;

/**
 * @author by JingQ on 2018/4/26.
 */

public class StudentJoinCourseActivity extends AppCompatActivity {

    private TextView mTVCourseName;

    private TextView mTVCourseBrief;

    private TextView mTVClassBegin;

    private TextView mTVClassEnd;

    private TextView mTVLesson;

    private TextView mTVTerm;

    private TextView mTVBuildingNumber;

    private TextView mTVBuildingRoom;

    private TextView mTVTeacher;

    private TextView mTVBeginPeriod;

    private Button btnJoin;

    private Course course;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add_course);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        btnJoin.setOnClickListener(view -> {
            joinCourse(course.getId());
        });
    }

    private void initView() {
        mTVCourseName = findViewById(R.id.tv_course_name);
        mTVCourseBrief = findViewById(R.id.tv_course_introduction);
        mTVClassBegin = findViewById(R.id.tv_course_begin);
        mTVClassEnd = findViewById(R.id.tv_course_end);
        mTVLesson = findViewById(R.id.tv_course_lesson);
        mTVBuildingNumber = findViewById(R.id.tv_course_building);
        mTVBuildingRoom = findViewById(R.id.tv_course_classroom);
        mTVTeacher = findViewById(R.id.tv_course_teacher);
        mTVBeginPeriod = findViewById(R.id.tv_course_time);

        btnJoin = findViewById(R.id.btn_join);
    }

    private void initData() {
        course = (Course) getIntent().getSerializableExtra("course");
        mTVCourseName.setText(course.getName());
        mTVCourseBrief.setText(course.getBriefIntroduction());
        mTVClassBegin.setText(course.getClassBegin());
        mTVClassEnd.setText(course.getClassEnd());
        mTVBuildingRoom.setText(course.getClassroom());
        mTVBuildingNumber.setText(course.getBuildingNumber());
        mTVTeacher.setText(course.getTeacherName());
        mTVBeginPeriod.setText(course.getBeginPeriod());
    }

    private void joinCourse(Integer courseId) {
        CourseModel.getInstance().joinCourse(courseId, new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                HttpHead msg = JsonUtils.parseObject(response.get().toString(), "head", HttpHead.class);
                Toast.makeText(StudentJoinCourseActivity.this, msg.getMsg(), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
