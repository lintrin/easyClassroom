package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.CheckInRecord;
import com.example.administrator.myapplication.model.impl.CheckInModel;

/**
 * @author by JingQ on 2018/4/25.
 */

public class CheckInDetailsAdapter extends BaseRecycleViewAdapter<CheckInRecord> {

    private Context context;

    private boolean isRefreshing = true;

    public CheckInDetailsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_teacher_check_in_details, parent, false);
        return new CheckInDetailsAdapter.VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, CheckInRecord data) {
        ((CheckInDetailsAdapter.VHolder) viewHolder).setData(realPosition, data);
    }

    class VHolder extends RecyclerView.ViewHolder {
        TextView mTvUserIdNumber;
        AppCompatSpinner mEtCheckStatus;

        VHolder(View view) {
            super(view);
            this.mTvUserIdNumber = view.findViewById(R.id.tv_user_id_number);
            this.mEtCheckStatus = view.findViewById(R.id.et_check_in_status);
        }

        public void setData(int position, CheckInRecord data) {
            mTvUserIdNumber.setText(data.getIdNumber());
            // 选择签到状态
            if (data.getCheck()) {
                mEtCheckStatus.setSelection(1);
            } else {
                mEtCheckStatus.setSelection(0);
            }
            // 修改状态
            if (!isRefreshing) {
                mEtCheckStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.i("wodianji", "" + position);
                        Log.i("checkIn", data.getIdNumber());
                        // 修改状态接口
//                        changeStatus(data.getIdNumber(), position, data.getCourseRecordId());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }
    }



    private void changeStatus(String idNumber, Integer status, Integer courseRecordId) {
        CheckInModel.getInstance().changeStatus(idNumber, status, courseRecordId, null);
    }

    public void setRefreshing(boolean refreshing) {
        this.isRefreshing = refreshing;
    }
}
