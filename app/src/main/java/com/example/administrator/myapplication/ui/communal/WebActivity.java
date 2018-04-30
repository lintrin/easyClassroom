package com.example.administrator.myapplication.ui.communal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

public class WebActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.webView);

        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);


        String url = getIntent().getStringExtra("url");
        if (url == null || !url.startsWith("http"))
            Toast.makeText(this, "url异常", Toast.LENGTH_SHORT).show();
        else
            webView.loadUrl(url);
    }
}
