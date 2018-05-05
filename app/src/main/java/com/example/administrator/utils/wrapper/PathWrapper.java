package com.example.administrator.utils.wrapper;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;

import com.example.administrator.utils.StoreUtils;

import java.io.File;

/**
 * Created by Administrator on 2018/5/1 0001.
 */

public class PathWrapper {
    public static String getDocFilePath(Context context, String filename){
        return StoreUtils.getSystemStore(context)
                + File.separator
                +FilenameWrapper.getDocFilename(filename);
    }

    public static Uri getUriFromDrawableRes(Context context, int id) {
        Resources resources = context.getResources();
        String path = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + resources.getResourcePackageName(id) + "/"
                + resources.getResourceTypeName(id) + "/"
                + resources.getResourceEntryName(id);
        return Uri.parse(path);
    }
}
