package library.http;


import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by fuzheyuan on 2018/4/9.
 */

public class BaseDownloader {

    public static final String TAG = "BaseDownloader";
    private static final String CANCEL_SIGN = "base_download";


    private OnDownLoadListener downloadListener;
    private OnDownLoadProgressListener downLoadProgressListener;

    public BaseDownloader() {

    }




    public void download(String url, String filePath, String fileName, boolean deleteOldFile, BaseDownloadListener listener) {
        DownloadRequest downloadRequest = NoHttp.createDownloadRequest(url, filePath, fileName, false, deleteOldFile);
        downloadRequest.addHeader("referer", "http://joyapper.com");
        downloadRequest.setCancelSign(CANCEL_SIGN);
        if (listener instanceof OnDownLoadListener)
            addDownloadQueue(downloadRequest, (OnDownLoadListener) listener);
        else addDownloadQueue(downloadRequest, (OnDownLoadProgressListener) listener);
    }
    public void download(String url, String filePath,boolean deleteOldFile, BaseDownloadListener listener) {
        DownloadRequest downloadRequest = NoHttp.createDownloadRequest(url,RequestMethod.POST,filePath,false,deleteOldFile);
        downloadRequest.addHeader("referer", "http://joyapper.com");
        downloadRequest.setCancelSign(CANCEL_SIGN);
        if (listener instanceof OnDownLoadListener)
            addDownloadQueue(downloadRequest, (OnDownLoadListener) listener);
        else addDownloadQueue(downloadRequest, (OnDownLoadProgressListener) listener);
    }

    private void addDownloadQueue(DownloadRequest downloadRequest, final OnDownLoadListener listener) {
        DownloadServer.getInstance().add(0, downloadRequest, new DownloadListener() {
            @Override
            public void onDownloadError(int what, Exception exception) {
                listener.onError(exception.toString());
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {

            }

            @Override
            public void onProgress(int what, int progress, long fileCount, long speed) {

            }

            @Override
            public void onFinish(int what, String filePath) {
                listener.onCompleted(what, filePath);
            }

            @Override
            public void onCancel(int what) {

            }
        });
    }

    private void addDownloadQueue(DownloadRequest downloadRequest, final OnDownLoadProgressListener listener) {
        DownloadServer.getInstance().add(0, downloadRequest, new DownloadListener() {
            @Override
            public void onDownloadError(int what, Exception exception) {
                listener.onError(exception.toString());
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {

            }

            @Override
            public void onProgress(int what, int progress, long fileCount, long speed) {
                listener.onProgress(what, progress, fileCount,speed);
            }

            @Override
            public void onFinish(int what, String filePath) {
                listener.onCompleted(what, filePath);
            }

            @Override
            public void onCancel(int what) {

            }
        });
    }


    /**
     *
     * @param url 文件地址
     */
    public int getFileSize(String url){
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.HEAD);
        request.setConnectTimeout(2000);
        request.setReadTimeout(2000);
        Response<String> response = NoHttp.startRequestSync(request);
        if (response.isSucceed()) {
            return response.getHeaders().getContentLength();
        } else {

            return 0;
        }
    }

    public void cancelDownload() {
        DownloadServer.getInstance().cancelAll();
    }

    public interface BaseDownloadListener {
        void onCompleted(int position, String filepath);

        void onError(String msg);
    }

    public interface OnDownLoadListener extends BaseDownloadListener {
    }

    public interface OnDownLoadProgressListener extends BaseDownloadListener {
        void onProgress(int position, int progress, long fileCount, long speed);
    }

}
