package com.example.administrator.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.TeacherClassAdapter;
import com.example.administrator.myapplication.model.TeacherClass;

public class TeacherClassActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvCourseSeason;
    private TextView mTvCourseWeek;
    private TextView mTvCourseTeacher;
    private TextView mTvCourseClassroom;
    private TextView mTvCourseSignTime;
    /**
     * 全部课程
     */
    private Button mBtnCourseAll;
    private RecyclerView mRvClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class); initView();
    }

    private void initView() {
        mTvCourseSeason = (TextView) findViewById(R.id.tv_course_season);
        mTvCourseWeek = (TextView) findViewById(R.id.tv_course_week);
        mTvCourseTeacher = (TextView) findViewById(R.id.tv_course_teacher);
        mTvCourseClassroom = (TextView) findViewById(R.id.tv_course_classroom);
        mTvCourseSignTime = (TextView) findViewById(R.id.tv_course_sign_time);
        mBtnCourseAll = (Button) findViewById(R.id.btn_course_all);
        mBtnCourseAll.setOnClickListener(this);
        mRvClass = (RecyclerView) findViewById(R.id.rv_class);
        TeacherClassAdapter adapter= new TeacherClassAdapter(this);

        View headView = LayoutInflater.from(this).inflate(R.layout.item_teacher_class,null);
        headView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setHeadView(headView);
        adapter.setHeaderView(headView);
        mRvClass.setAdapter(adapter);
        mRvClass.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_course_all:
                break;
        }
    }

    public void setHeadView(View view) {
        TextView mTvCourseId = view.findViewById(R.id.tv_course_id);
        TextView mTvCourseSignCount =view.findViewById(R.id.tv_course_sign_count);
        TextView mTvCourseStudentCount = view.findViewById(R.id.tv_course_student_count);
        TextView mTvCourseSignCode= view.findViewById(R.id.tv_course_sign_code);
        TextView mTvCourseWork = view.findViewById(R.id.tv_course_work);
        mTvCourseId.setText("序号");
        mTvCourseSignCount.setText("已签到");
        mTvCourseStudentCount.setText("总人数");
        mTvCourseSignCode.setText("签到识别码");
        mTvCourseWork.setText("");

    }
}
