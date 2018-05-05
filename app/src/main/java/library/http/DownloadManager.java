package library.http;

import com.yanzhenjie.nohttp.RequestMethod;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by fuzheyuan on 2018/4/9.
 */

public class DownloadManager {
    private static DownloadManager instance;
    private BaseDownloader downloader;

    private ExecutorService fixedThreadPool;

    public static DownloadManager getInstance() {
        if (instance == null) {
            synchronized (DownloadManager.class) {
                if (instance == null)
                    instance = new DownloadManager();
            }
        }
        return instance;
    }

    private DownloadManager() {

        downloader = new BaseDownloader();
        fixedThreadPool = Executors.newFixedThreadPool(3);
    }


    /**
     * 下载或 对比文件大小重新下载文件
     *
     * @param url      文件地址
     * @param dirPath  下载目录
     * @param filename 文件名 包含后缀
     * @param listener 两种监听器可选 {@link BaseDownloader.OnDownLoadListener},{@link BaseDownloader.OnDownLoadProgressListener}
     */
    public void downloadOrUpdate(String url, String dirPath, String filename, BaseDownloader.BaseDownloadListener listener) {
        File file = new File(dirPath + filename);
        if (file.exists()) {
            fixedThreadPool.execute(() -> {
                long fileSize = downloader.getFileSize(url);
                if (fileSize == 0 || fileSize == file.length()) {
                    listener.onCompleted(0, filename);
                } else {
                    downloader.download(url, dirPath, filename, true, listener);
                }
            });
        } else {
            downloader.download(url, dirPath, filename, false, listener);
        }
    }

    /**
     * 下载文件 已存在则直接返回结果
     *
     * @param url      文件地址
     * @param dirPath  下载目录
     * @param filename 文件名 包含后缀
     * @param listener 两种监听器可选 {@link BaseDownloader.OnDownLoadListener},{@link BaseDownloader.OnDownLoadProgressListener}
     */
    public void download(String url, String dirPath, String filename, BaseDownloader.BaseDownloadListener listener) {
        downloader.download(url, dirPath, filename, false, listener);
    }

    /**
     * 下载文件 已存在则直接返回结果
     *
     * @param url      文件地址
     * @param dirPath  下载目录
     * @param method   请求方式
     * @param listener 两种监听器可选 {@link BaseDownloader.OnDownLoadListener},{@link BaseDownloader.OnDownLoadProgressListener}
     */
    public void download(String url, RequestMethod method, String dirPath, String filename, boolean deleteOldFile, BaseDownloader.BaseDownloadListener listener) {
        if (filename != null)
            downloader.download(url, method, dirPath, filename, false, listener);
        else
            downloader.download(url, method, dirPath, false, listener);

    }


    public void cancelDownload() {
        downloader.cancelDownload();
    }

}
