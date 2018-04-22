package com.example.administrator.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.Utils.TopBarUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.TeacherClassAdapter;
import library.http.BaseRequest;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.yanzhenjie.nohttp.rest.Response;

public class TeacherCourseActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvCourseTime;
    private TextView mTvCourseWeek;
    private TextView mTvCourseTeacher;
    private TextView mTvCourseClassroom;
    private TextView mTvCourseSignTime;
    /**
     * 全部课程
     */
    private Button mBtnCourseAll;
    private RecyclerView mRvClass;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_course);
        initView();
        initData();
        setData();
    }
    private void initData() {
        course = (Course) getIntent().getSerializableExtra("course");
        CourseModel.getInstance().getCourse(course.getId(), new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                Log.i("sss", "onCompleted: "+response.get().toString());
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
    private void setData() {
        TopBarUtils topBarUtils = new TopBarUtils(this);
        topBarUtils.setTitle(course.getName());

        mTvCourseTime.setText(course.getBeginPeriod()+"-"+course.getEndPeriod()+" 第" +course.getTerm()+"学期");
        mTvCourseWeek.setText(course.getDay()+"");
        mTvCourseTeacher.setText(course.getTeachers());
        mTvCourseClassroom.setText("教室："+course.getBuildingNumber()+"#"+course.getClassroom());


        TeacherClassAdapter adapter= new TeacherClassAdapter(this);

        View headView = LayoutInflater.from(this).inflate(R.layout.item_teacher_class,null);
        headView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setHeadView(headView);
        adapter.setHeaderView(headView);
        mRvClass.setAdapter(adapter);
        mRvClass.setLayoutManager(new LinearLayoutManager(this));
    }



    private void initView() {
        mTvCourseTime = (TextView) findViewById(R.id.tv_course_time);
        mTvCourseWeek = (TextView) findViewById(R.id.tv_course_week);
        mTvCourseTeacher = (TextView) findViewById(R.id.tv_course_teacher);
        mTvCourseClassroom = (TextView) findViewById(R.id.tv_course_classroom);
        mTvCourseSignTime = (TextView) findViewById(R.id.tv_course_sign_time);
        mBtnCourseAll = (Button) findViewById(R.id.btn_course_all);
        mBtnCourseAll.setOnClickListener(this);
        mRvClass = (RecyclerView) findViewById(R.id.rv_class);




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
