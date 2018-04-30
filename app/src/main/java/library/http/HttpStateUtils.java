package library.http;

import android.content.Context;
import android.widget.Toast;

import com.example.administrator.utils.JsonUtils;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/1 0001.
 */

public class HttpStateUtils {
    public static void showRequestMsg(Context context,Response response){
        HashMap map = JsonUtils.parseObject(response.get().toString(), "head", HashMap.class);
        if (map.get("msg") != null)
            Toast.makeText(context, map.get("msg").toString(), Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(context, map.get("提交失败").toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
