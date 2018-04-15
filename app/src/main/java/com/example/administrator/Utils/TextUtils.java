package com.example.administrator.Utils;

/**
 * Created by fuzheyuan on 2018/3/29.
 */

public class TextUtils {
    public static String wrapperTextColorByHtml(String str, String colorString) {
        return "<font color='" + colorString + "'>" + str + "</font>";
    }

    public static boolean isEmpty(String type) {
        return type == null || type.equals("") || type.length() == 0;
    }
}
