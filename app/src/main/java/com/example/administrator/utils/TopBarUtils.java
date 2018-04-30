package com.example.administrator.utils;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

public class TopBarUtils {
    private ImageView btnLeft;
    private TextView tvTitle;
    private ImageView btnRight;


    public TopBarUtils(Activity activity) {
        btnLeft = activity.findViewById(R.id.include_topbar_left);
        tvTitle = activity.findViewById(R.id.include_topbar_title);
        btnRight = activity.findViewById(R.id.include_topbar_right);
    }

    public TopBarUtils(View view){
        btnLeft = view.findViewById(R.id.include_topbar_left);
        tvTitle = view.findViewById(R.id.include_topbar_title);
        btnRight = view.findViewById(R.id.include_topbar_right);
    }

    public void setTitle(String text) {
        tvTitle.setText(text);
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public ImageView getBtnLeft() {
        return btnLeft;
    }

    public ImageView getBtnRight() {
        return btnRight;
    }
}
