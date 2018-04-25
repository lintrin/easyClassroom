package com.example.administrator.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.Utils.JsonUtils;
import com.example.administrator.Utils.MyHandler;
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

public class CheckInDetailsFragment extends Fragment {

    private View mainView;

    private RecyclerView rvCheckInDetails;

    private CheckInDetailsAdapter mAdapter;

    public CheckInDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_teacher_check_in_details, container, false);

            initView();
            initData();
        }
        return mainView;
    }

    private void initView() {
        rvCheckInDetails = mainView.findViewById(R.id.rv_check_in_details);
        rvCheckInDetails.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CheckInDetailsAdapter(getContext());
        rvCheckInDetails.setAdapter(mAdapter);
    }

    private void initData() {
        //TODO MOCK数据，课时数据
        getCheckInList(20);
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
