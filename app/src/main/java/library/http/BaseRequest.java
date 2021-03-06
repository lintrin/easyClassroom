package library.http;


import android.widget.Toast;

import com.example.administrator.myapplication.MyApp;
import com.example.administrator.myapplication.R;
import com.yanzhenjie.nohttp.FileBinary;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.error.NetworkError;
import com.yanzhenjie.nohttp.error.NotFoundCacheError;
import com.yanzhenjie.nohttp.error.TimeoutError;
import com.yanzhenjie.nohttp.error.URLError;
import com.yanzhenjie.nohttp.error.UnKnownHostError;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

import java.io.File;
import java.net.ProtocolException;
import java.util.Map;

public class BaseRequest {


    protected static void get(String url, Map<String,Object> data, OnRequestListener listener){
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);
        request.add(data);
        addQeueu(request,listener);
    }
    protected static void postForm(String url,  Map<String,Object> data, OnRequestListener listener){
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add(data);
        request.setHeader("Content-Type","application/x-www-form-urlencoded");
        addQeueu(request,listener);
    }

    protected static void postFile(String url,  Map<String,Object> data, OnRequestListener listener){
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add(data);
        request.setHeader("Content-Type","");
        addQeueu(request,listener);
    }

    protected static void post(String url, Map<String,Object> data, OnRequestListener listener){
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add(data);
        addQeueu(request,listener);
    }
    protected static void post(String url, JSONObject data, OnRequestListener listener){
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.addHeader("Content-Type","application/json");
        request.setDefineRequestBodyForJson(data);
        addQeueu(request,listener);
    }
    protected static void post(String url, String jsonString, OnRequestListener listener){
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.addHeader("Content-Type","application/json");
        request.setDefineRequestBodyForJson(jsonString);
        addQeueu(request,listener);
    }
    protected static void get(String url, JSONObject data, OnRequestListener listener){
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);
        request.addHeader("Content-Type","application/json");
        request.setDefineRequestBodyForJson(data);
        addQeueu(request,listener);
    }
    protected static void get(String url,OnRequestListener listener){
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);
        addQeueu(request,listener);
    }

    private static void addQeueu(Request<String> request, OnRequestListener listener) {
        CallServer.getInstance().add(0, request, new OnResponseListener() {
            @Override
            public void onStart(int what) {
                listener.onStart();
            }

            @Override
            public void onSucceed(int what, Response response) {
                listener.onCompleted(response);
            }

            @Override
            public void onFailed(int what, Response response) {
                Exception e = response.getException();
                if (e instanceof NetworkError) {// 网络不好
                    Toast.makeText(MyApp.context,MyApp.context.getText(R.string.error_please_check_network),Toast.LENGTH_SHORT).show();
                } else if (e instanceof TimeoutError) {// 请求超时
                    Toast.makeText(MyApp.context, MyApp.context.getText(R.string.error_timeout),Toast.LENGTH_SHORT).show();
                } else if (e instanceof UnKnownHostError) {// 找不到服务器
                    Toast.makeText(MyApp.context, MyApp.context.getText(R.string.error_not_found_server),Toast.LENGTH_SHORT).show();
                } else if (e instanceof URLError) {// URL是错的
                    Toast.makeText(MyApp.context, MyApp.context.getText(R.string.error_url_error),Toast.LENGTH_SHORT).show();
                } else if (e instanceof NotFoundCacheError) {
                    // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
                    Toast.makeText(MyApp.context, MyApp.context.getText(R.string.error_not_found_cache),Toast.LENGTH_SHORT).show();
                } else if (e instanceof ProtocolException) {
                    Toast.makeText(MyApp.context, MyApp.context.getText(R.string.error_system_unsupport_method),Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyApp.context, MyApp.context.getText(R.string.error_unknow),Toast.LENGTH_SHORT).show();
                }
                listener.onError("json解析出错");
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }


    public interface OnRequestListener{
        void onStart();
        void onCompleted(Response response);
        void onError(String msg);
    }

}
