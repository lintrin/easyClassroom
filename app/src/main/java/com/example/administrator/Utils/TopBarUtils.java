package com.example.administrator.Utils;

import android.app.Activity;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

public class TopBarUtils {
    private ImageButton btnLeft;
    private TextView tvTitle;


    public TopBarUtils(Activity activity) {
        btnLeft = activity.findViewById(R.id.include_topbar_close);
        tvTitle = activity.findViewById(R.id.include_topbar_title);
        if (btnLeft != null) {
            btnLeft.setOnClickListener(view -> {
                activity.finish();
            });
        }
    }

    public void setTitle(String text) {
        tvTitle.setText(text);
    }

    public ImageButton getBtnLeft() {
        return btnLeft;
    }
}
