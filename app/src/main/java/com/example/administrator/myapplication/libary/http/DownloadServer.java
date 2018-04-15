package com.example.administrator.myapplication.libary.http;


import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;

/**
 * Created by Administrator on 2017/2/2 0002.
 */

public class DownloadServer {

    private static DownloadServer instance;

    /**
     * 请求队列。
     */
    private DownloadQueue downloadQueue;

    private DownloadServer() {
        downloadQueue = NoHttp.newDownloadQueue(2);
    }

    /**
     * 请求队列。
     */
    public synchronized static DownloadServer getInstance() {
        if (instance == null)
            synchronized (DownloadServer.class) {
                if (instance == null)
                    instance = new DownloadServer();
            }
        return instance;
    }

    /**
     * 添加一个请求到请求队列。
     *
     * @param what      用来标志请求, 当多个请求使用同一个Listener时, 在回调方法中会返回这个what。
     * @param request   请求对象。
     * @param listener  结果回调对象。
     */
    public <T> void add(int what, DownloadRequest request, DownloadListener listener) {
        downloadQueue.add(what, request, listener);
    }

    /**
     * 取消这个sign标记的所有请求。
     * @param sign 请求的取消标志。
     */
    public void cancelBySign(Object sign) {
        downloadQueue.cancelBySign(sign);
    }

    /**
     * 取消队列中所有请求。
     */
    public void cancelAll() {
        downloadQueue.cancelAll();
    }

}
