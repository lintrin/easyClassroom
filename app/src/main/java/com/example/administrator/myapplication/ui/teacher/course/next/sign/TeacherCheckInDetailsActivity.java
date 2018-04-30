package com.example.administrator.myapplication.ui.teacher.course.next.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.utils.JsonUtils;
import com.example.administrator.utils.MyHandler;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.CheckInDetailsAdapter;
import com.example.administrator.myapplication.model.CheckInRecord;
import com.example.administrator.myapplication.model.impl.CheckInModel;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import library.http.BaseRequest;

/**
 * @author by JingQ on 2018/4/25.
 */

public class TeacherCheckInDetailsActivity extends AppCompatActivity {

    private RecyclerView rvCheckInDetails;

    private CheckInDetailsAdapter mAdapter;

    private Integer courseRecordId;

    public TeacherCheckInDetailsActivity() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_check_in_details);

        initView();
        initData();
    }

    private void initView() {
        rvCheckInDetails = findViewById(R.id.rv_check_in_details);
        rvCheckInDetails.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CheckInDetailsAdapter(this);
        rvCheckInDetails.setAdapter(mAdapter);
    }

    private void initData() {
        courseRecordId = (Integer) getIntent().getSerializableExtra("courseRecordId");
        getCheckInList(courseRecordId);
    }

    private void getCheckInList(Integer courseRecordId) {
        CheckInModel.getInstance().getCheckListByCRId(courseRecordId, new BaseRequest.OnRequestListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(Response response) {
                List<CheckInRecord> checkInRecords = JsonUtils.parseArray(response.get().toString(), "body", CheckInRecord.class);
                mAdapter.refreshData(checkInRecords);
                MyHandler.getBusiness0Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setRefreshing(false);
                    }
                },1000);

            }

            @Override
            public void onError(String msg) {

            }
        });
    }


}
