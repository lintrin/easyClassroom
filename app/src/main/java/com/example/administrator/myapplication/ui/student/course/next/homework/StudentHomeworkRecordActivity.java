package com.example.administrator.myapplication.ui.student.course.next.homework;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.config.Consant;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.StudentHomeworkMarkAdapter;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.Homework;
import com.example.administrator.myapplication.model.Student;
import com.example.administrator.myapplication.model.User;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.example.administrator.myapplication.model.impl.HomeworkModel;
import com.example.administrator.myapplication.model.impl.UserModel;
import com.example.administrator.utils.ImageLoadUtils;
import com.example.administrator.utils.JsonUtils;
import com.example.administrator.utils.PeriodUtil;
import com.example.administrator.utils.StoreUtils;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;
import com.yanzhenjie.nohttp.rest.Response;

import java.io.File;
import java.util.List;

import library.http.BaseRequest;
import library.http.HttpStateUtils;

public class StudentHomeworkRecordActivity extends AppCompatActivity {


    private Activity context;
    private Course course;
    private int homeworkId;
    private TextView tvTitle;
    private Button btnStudentHomeworkSubmit;
    private String filename;
    private ImageView iv_student_avatar;
    private TextView tv_student_name;
    private TextView tv_student_number;
    private TextView tv_student_date;
    private TextView tv_student_homework_state;
    private TextView tv_student_homework_score;
    private TextView tv_student_number2;
    private ImageView iv_student_homework_mark_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homework);
        context = this;
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        btnStudentHomeworkSubmit.setOnClickListener(v -> {
            if (filename == null) {
                new LFilePicker()
                        .withActivity(context)
                        .withRequestCode(Consant.REQUEST_CODE_FROM_ACTIVITY)
                        .withStartPath(StoreUtils.getSystemStore(context))
                        .withIconStyle(Constant.ICON_STYLE_GREEN)
                        .withMutilyMode(false) //false单选 true多选
                        .start();
            } else {
                HomeworkModel.getInstance().addStudentHomework(course.getId(), homeworkId, new File(filename), new BaseRequest.OnRequestListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(Response response) {
                        Log.i("sss", "onCompleted: " + response.toString());
                        HttpStateUtils.showRequestMsg(context, response);
                    }

                    @Override
                    public void onError(String msg) {

                    }
                });
            }
        });
    }

    private void initView() {

        tvTitle = findViewById(R.id.tv_title);
        iv_student_avatar = findViewById(R.id.iv_student_avatar);
        btnStudentHomeworkSubmit = findViewById(R.id.btn_student_homework_submit);
        tv_student_name = findViewById(R.id.tv_student_name);
        tv_student_number = findViewById(R.id.tv_student_number);
        tv_student_date = findViewById(R.id.tv_student_date);
        tv_student_homework_state = findViewById(R.id.tv_student_homework_state);
        tv_student_number2 = findViewById(R.id.tv_student_number2);
        tv_student_homework_score = findViewById(R.id.tv_student_homework_score);
        iv_student_homework_mark_icon = findViewById(R.id.iv_student_homework_mark_icon);

        ImageLoadUtils.setAvatarImage(this,iv_student_avatar,R.mipmap.avatar1,R.mipmap.avatar1);
    }

    private void initData() {
        course = CourseModel.getInstance().getCourse();
        tvTitle.setText(course.getName());
        homeworkId = getIntent().getIntExtra("id", 0);
        HomeworkModel.getInstance().requestHomeworkList(homeworkId, new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                Log.i("sss", "onCompleted: " + response.toString());
                List<Homework> homeworkList = JsonUtils.parseArray(response.get().toString(), "body", Homework.class);
                if (homeworkList.size() > 0) {
                    Homework homework = homeworkList.get(0);
                    tv_student_name.setText(homework.getUploadName());
                    tv_student_number.setText(homework.getIdNumber());
                    tv_student_date.setText(homework.getCreateDate());
                    tv_student_number2.setText(homework.getIdNumber());
                    tv_student_homework_state.setText("提交");
                    tv_student_homework_score.setText(homework.getScore()+"分");
                    iv_student_homework_mark_icon.setVisibility(View.VISIBLE);
                }else{
                    User user = UserModel.getInstance().getUser();
                    tv_student_name.setText(user.getName());
                    tv_student_number.setText(user.getIdNumber());
                    tv_student_homework_state.setText("未提交");
                    iv_student_homework_mark_icon.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Consant.REQUEST_CODE_FROM_ACTIVITY) {
                List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);
                filename = list.get(0);
                Log.i("sss", "initListener: " + filename);
                btnStudentHomeworkSubmit.setText("确认提交");
                Toast.makeText(getApplicationContext(), "选中了" + list.size() + "个文件", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
