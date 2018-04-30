package com.example.administrator.myapplication.model;


import com.example.administrator.Utils.StoreUtils;
import com.example.administrator.myapplication.MyApp;

import java.io.File;

import library.http.BaseDownloader;
import library.http.DownloadManager;

/**
 * Created by fuzheyuan on 2018/4/10.
 */

public class DownloadModel {
    private String homeworkPath;

    private static DownloadModel instance;

    public static DownloadModel getInstance() {
        if (instance == null) {
            synchronized (DownloadModel.class) {
                if (instance == null) {
                    instance = new DownloadModel();
                }
            }
        }
        return instance;
    }

    private DownloadModel() {
        homeworkPath = StoreUtils.getCacheDirectory(MyApp.context,"homework").getAbsolutePath();

        if (!new File(homeworkPath).exists()) {
            new File(homeworkPath).mkdirs();
        }

    }

    public void download(String url,String num, BaseDownloader.OnDownLoadListener listener) {
        DownloadManager.getInstance().download(url, homeworkPath, false, listener);
    }


}
