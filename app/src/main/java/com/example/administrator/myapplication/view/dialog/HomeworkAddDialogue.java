package com.example.administrator.myapplication.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.Course;
import com.example.administrator.myapplication.model.HomeworkOuter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2018/4/30 0030.
 */

public class HomeworkAddDialogue extends DialogFragment {

    private View view;
    private AutoCompleteTextView mEtHomeworkName;
    private AutoCompleteTextView mEtHomeworkContent;
    private Button btnHomeworkAdd;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_homework_add, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        mEtHomeworkName = view.findViewById(R.id.et_homework_name);
        mEtHomeworkContent = view.findViewById(R.id.et_homework_content);
        btnHomeworkAdd = view.findViewById(R.id.btn_homework_add);

        btnHomeworkAdd.setOnClickListener(v -> {
            HomeworkOuter homeworkOuter = new HomeworkOuter();
            homeworkOuter.setHomeworkName(mEtHomeworkName.getText().toString());
            homeworkOuter.setHomeworkContent(mEtHomeworkContent.getText().toString());
            EventBus.getDefault().post(homeworkOuter);
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
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.7), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

}
