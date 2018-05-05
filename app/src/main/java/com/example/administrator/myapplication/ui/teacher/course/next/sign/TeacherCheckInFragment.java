package com.example.administrator.myapplication.ui.teacher.course.next.sign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.myapplication.ui.communal.CourseChattingFragment;
import com.example.administrator.utils.JsonUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.TeacherCheckInAdapter;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.CourseRecord;
import com.example.administrator.myapplication.model.impl.CourseModel;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import library.http.BaseRequest;

/**
 * @author by JingQ on 2018/4/27.
 */

public class TeacherCheckInFragment extends Fragment {

    private View mainView;

    private RecyclerView rvCheck;

    private TeacherCheckInAdapter teacherCheckInAdapter;

    private Course course;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Fragment> fragments = this.getFragmentManager().getFragments();
        course = CourseModel.getInstance().getCourse();
    }

    public static TeacherCheckInFragment newInstance() {
        Bundle args = new Bundle();
        TeacherCheckInFragment fragment = new TeacherCheckInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_teacher_check_in, container, false);

            initView();
            initData();
            initListener();
        }
        return mainView;
    }

    private void initView() {
        rvCheck = mainView.findViewById(R.id.rv_teacher_check_in);
        rvCheck.setLayoutManager(new LinearLayoutManager(getContext()));
        teacherCheckInAdapter = new TeacherCheckInAdapter(getContext());
        rvCheck.setAdapter(teacherCheckInAdapter);

    }

    private void initData() {
        getCourseRecordList();
    }

    private void initListener() {

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
                    teacherCheckInAdapter.refreshData(courseRecords);
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }


}
