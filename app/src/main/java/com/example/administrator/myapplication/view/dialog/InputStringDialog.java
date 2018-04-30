package com.example.administrator.myapplication.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2018/4/30 0030.
 */

public class InputStringDialog extends DialogFragment {

    private View view;
    private AutoCompleteTextView mEtInput;
    private Button btnSubmit;
    private Button btnCancel;
    private OnSubmitClickListener onSubmitClickListener;
    private String hintText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_input_simple, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        mEtInput = view.findViewById(R.id.et_input);
        btnSubmit = view.findViewById(R.id.btn_submit);
        btnCancel =view.findViewById(R.id.btn_cancel);
        btnSubmit.setOnClickListener(v -> {

            if (onSubmitClickListener!=null)
                onSubmitClickListener.onClick(getDialog(),mEtInput.getText().toString());
        });
        mEtInput.setHint(hintText);
        btnCancel.setOnClickListener(v -> dismiss());
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

    public InputStringDialog showDialog(FragmentManager manager, String hintText){
        this.hintText = hintText;
        show(manager,"");
        return this;
    }

    public InputStringDialog setOnSubmitClickListener(OnSubmitClickListener onSubmitClickListener) {
        this.onSubmitClickListener = onSubmitClickListener;
        return this;
    }

    public interface OnSubmitClickListener{
         void onClick(Dialog dialog, String str);
    }
}
