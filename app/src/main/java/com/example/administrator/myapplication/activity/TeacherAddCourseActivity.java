package com.example.administrator.myapplication.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.yanzhenjie.nohttp.rest.Response;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.HashMap;

import library.http.BaseRequest;

public class TeacherAddCourseActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private EditText mEtCourseName;
    private EditText mEtCourseIntroduction;
    private AppCompatSpinner mEtCourseBegin;
    private AppCompatSpinner mEtCourseEnd;
    private AppCompatSpinner mEtCourseLesson;
    private AppCompatSpinner mEtCourseBuilding;
    private EditText mEtCourseClassroom;
    private TextView mTvCourseTime;
    private Button mSubmit;
    private int mYear;
    private int mMonth;
    private int mDay;
    private boolean isBeginPeriod = true;
    private String beginPeriod;
    private String endPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_teacher_add_course);
        initView();
    }


    private void initView() {
        mEtCourseName = (EditText) findViewById(R.id.et_course_name);
        mEtCourseIntroduction = (EditText) findViewById(R.id.et_course_introduction);
        mEtCourseBegin = (AppCompatSpinner) findViewById(R.id.et_course_begin);
        mEtCourseEnd = (AppCompatSpinner) findViewById(R.id.et_course_end);
        mEtCourseLesson = (AppCompatSpinner) findViewById(R.id.et_course_lesson);
        mEtCourseBuilding = (AppCompatSpinner) findViewById(R.id.et_course_building);
        mEtCourseClassroom = (EditText) findViewById(R.id.et_course_classroom);
        mTvCourseTime = (TextView) findViewById(R.id.tv_course_time);
        mSubmit = (Button) findViewById(R.id.submit);


        mTvCourseTime.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
    }

    private void selectData() {
        Toast.makeText(context, "请选择开始时间", Toast.LENGTH_SHORT).show();

        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, onDateSetListener, mYear, mMonth, mDay).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.submit:
                createCourse();
                break;
            case R.id.tv_course_time:
                selectData();
                break;
        }
    }

    private void createCourse() {
        Course course = new Course();
        course.setName(mEtCourseName.getText().toString());
        course.setBriefIntroduction(mEtCourseIntroduction.getText().toString());
        course.setClassBegin(mEtCourseBegin.getSelectedItem().toString());
        course.setClassEnd(mEtCourseEnd.getSelectedItem().toString());
        course.setLesson(mEtCourseLesson.getSelectedItem().toString());
        course.setBuildingNumber(mEtCourseBuilding.getSelectedItem().toString());
        course.setClassroom(mEtCourseClassroom.getText().toString());
        course.setTerm("1");
        course.setBeginPeriod(beginPeriod);
        course.setEndPeriod(endPeriod);
        Log.i("sss", "createCourse: "+JsonUtils.toJsonString(course));
        CourseModel.getInstance().addCourse(course, new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                Log.i("sss", "onCompleted: " + response.toString());
                HashMap map = JsonUtils.parseObject(response.get().toString(), "head", HashMap.class);
                if (!map.get("status").equals("Y")) {
                    if (map.get("msg") != null)
                        Toast.makeText(context, map.get("msg").toString(), Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(context, "提交失败", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context,"创建成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-")
                            .append("0").append(mMonth + 1).append("-")
                            .append("0").append(mDay)
                            .toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-")
                            .append("0").append(mMonth + 1).append("-")
                            .append(mDay)
                            .toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-")
                            .append(mMonth + 1).append("-")
                            .append("0").append(mDay)
                            .toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-")
                            .append(mDay)
                            .toString();
                }

            }

            if (isBeginPeriod) {
                beginPeriod = days;
                isBeginPeriod = false;
                //再选一次
                Toast.makeText(context, "请选择结束时间", Toast.LENGTH_SHORT).show();
                new DatePickerDialog(context, onDateSetListener, mYear, mMonth, mDay).show();
            } else {
                endPeriod = days;
                mTvCourseTime.setText(beginPeriod+" ~ "+endPeriod);
            }
        }

    };
}
