package com.example.administrator.myapplication.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.HomeworkOuter;
import com.example.administrator.myapplication.model.News;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2018/4/30 0030.
 */

public class NoticeAddDialogue extends DialogFragment {

    private View view;
    private AutoCompleteTextView mEtNoticeTitle;
    private AutoCompleteTextView mEtNoticeContent;
    private Button btnNoticekAdd;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_notice_add, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        mEtNoticeTitle = view.findViewById(R.id.et_notice_title);
        mEtNoticeContent = view.findViewById(R.id.et_notice_content);
        btnNoticekAdd = view.findViewById(R.id.btn_notice_add);

        btnNoticekAdd.setOnClickListener(v -> {
            News news = new News();
            news.setTitle(mEtNoticeTitle.getText().toString());
            news.setContent(mEtNoticeContent.getText().toString());
            EventBus.getDefault().post(news);
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();

            //设置弹框的占屏宽
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 8), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

}
