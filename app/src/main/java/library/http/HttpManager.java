package library.http;

import android.content.Context;

import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;

/**
 * Created by Administrator on 2017/2/1 0001.
 */

public class HttpManager {
    public static void initNoHttpWhitDBCache(Context context, boolean needDB, boolean needCookie) {

        InitializationConfig config = InitializationConfig.newBuilder(context)
                // 全局连接服务器超时时间，单位毫秒，默认10s。
                .connectionTimeout(10 * 1000)
                // 全局等待服务器响应超时时间，单位毫秒，默认10s。
                .readTimeout(12 * 1000)
                // 配置缓存，默认保存数据库DBCacheStore，保存到SD卡使用DiskCacheStore。
                .cacheStore(
                        // 如果不使用缓存，setEnable(false)禁用。
                        new DBCacheStore(context).setEnable(needDB)
                )
                // 配置Cookie，默认保存数据库DBCookieStore，开发者可以自己实现CookieStore接口。
                .cookieStore(
                        // 如果不维护cookie，setEnable(false)禁用。
                        new DBCookieStore(context).setEnable(needCookie)
                )
                // 配置网络层，默认URLConnectionNetworkExecutor，如果想用OkHttp：OkHttpNetworkExecutor。
                .networkExecutor(new OkHttpNetworkExecutor())
                // 全局通用Header，add是添加，多次调用add不会覆盖上次add。
//                .addHeader("referer", "http://joyapper.com")
                .addHeader("Content-Type","application/json")
                // 全局通用Param，add是添加，多次调用add不会覆盖上次add。
//                .addParam()
                .retry(2) // 全局重试次数，配置后每个请求失败都会重试x次。
                .build();

        NoHttp.initialize(config);
        Logger.setDebug(true);
        Logger.setTag("NoHttpSample");
    }
}
