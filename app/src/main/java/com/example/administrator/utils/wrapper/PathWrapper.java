package com.example.administrator.utils.wrapper;

import android.content.Context;

import com.example.administrator.utils.StoreUtils;

import java.io.File;

/**
 * Created by Administrator on 2018/5/1 0001.
 */

public class PathWrapper {
    public static String getDocFilePath(Context context, String filename){
        return StoreUtils.getCacheDirectory(context,"homework").getAbsolutePath()
                + File.separator
                +FilenameWrapper.getDocFilename(filename);
    }
}
