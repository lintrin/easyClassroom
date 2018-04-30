package com.example.administrator.myapplication.model;


import com.example.administrator.utils.StoreUtils;
import com.example.administrator.myapplication.MyApp;
import com.yanzhenjie.nohttp.RequestMethod;

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
        homeworkPath = StoreUtils.getCacheDirectory(MyApp.context, "homework").getAbsolutePath();

        if (!new File(homeworkPath).exists()) {
            new File(homeworkPath).mkdirs();
        }

    }

    public void downloadByGet(String url, String filename, BaseDownloader.OnDownLoadListener listener) {

        DownloadManager.getInstance().download(url, RequestMethod.GET,homeworkPath, filename,  false, listener);
    }

    public void downloadByPost(String url, String filename, BaseDownloader.OnDownLoadListener listener) {

        DownloadManager.getInstance().download(url, RequestMethod.POST,  homeworkPath,filename, false, listener);
    }

}
