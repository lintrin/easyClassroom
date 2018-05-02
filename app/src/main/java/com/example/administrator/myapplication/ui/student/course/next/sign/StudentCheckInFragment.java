package com.example.administrator.myapplication.ui.student.course.next.sign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.utils.JsonUtils;
import com.example.administrator.utils.TextUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.StudentCheckInAdapter;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.CourseRecord;
import com.example.administrator.myapplication.model.impl.CheckInModel;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import library.http.BaseRequest;
import library.http.HttpHead;

/**
 * @author by JingQ on 2018/4/27.
 */

public class StudentCheckInFragment extends Fragment {

    private View mainView;

    private RecyclerView rvCourseRecord;

    private StudentCheckInAdapter studentCheckInAdapter;

    private Button btnCheck;

    private Course course;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Fragment> fragments = this.getFragmentManager().getFragments();
        course = CourseModel.getInstance().getCourse();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_student_check_in, container, false);

            initView();
            initData();
            initListener();
        }
        return mainView;
    }

    private void initListener() {
        btnCheck.setOnClickListener(view -> {
            showDialog();
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_search_course, null);
        AutoCompleteTextView mEtCheckCode = view.findViewById(R.id.et_search_course);
        builder.setView(view)
                .setPositiveButton("确定", (dialog, which) -> {
                    String code = mEtCheckCode.getText().toString();
                    if (TextUtils.isEmpty(code)) {
                        Toast.makeText(getContext(), "识别码不能为空！", Toast.LENGTH_SHORT).show();
                    } else {
                        addCheckIn(code);
                    }
                })
        ;
        builder.create();
        builder.show();
    }

    private void initView() {
        rvCourseRecord = mainView.findViewById(R.id.rv_student_check_in);
        rvCourseRecord.setLayoutManager(new LinearLayoutManager(getContext()));
        studentCheckInAdapter = new StudentCheckInAdapter(getContext());
        rvCourseRecord.setAdapter(studentCheckInAdapter);
        btnCheck = mainView.findViewById(R.id.btn_add_check_in);

    }

    private void initData() {
        getCourseRecordList();
    }

    private void getCourseRecordList() {
        CourseModel.getInstance().getCourseRecordList(course.getId(), new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                List<CourseRecord> courseRecords = JsonUtils.parseArray(response.get().toString(), "body", CourseRecord.class);
                if (courseRecords == null || courseRecords.isEmpty()) {
                    Toast.makeText(getContext(), "暂时没有课时噢", Toast.LENGTH_SHORT).show();
                } else {
                    studentCheckInAdapter.refreshData(courseRecords);
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    private void addCheckIn(String code) {
        CheckInModel.getInstance().addCheckInRecord(code, course.getId(), new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                HttpHead head = JsonUtils.parseObject(response.get().toString(), "head", HttpHead.class);
                Toast.makeText(getContext(), head.getMsg(), Toast.LENGTH_SHORT).show();
                getCourseRecordList();
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
