package com.example.administrator.utils;

import com.example.administrator.myapplication.model.MyMessage;
import com.google.common.collect.Lists;

import java.util.List;

import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Message;

/**
 * 极光SDK
 *
 * 以用户的学号/工号(idNumber)作为用户名注册到极光
 *
 * Created by yejingqi on 2018/4/18.
 */

public class JMessageUtil {

    public static final String APP_KEY = "2eb5590b3ee5d3b3f72d4762";


    public static final String CHATTING_ROOM_KEY = "chat_room_id";

    public static List<MyMessage> msg2MyMSG(List<Message> msgList) {
        List<MyMessage> result = Lists.newArrayList();
        if (msgList != null && msgList.size() > 0) {
            for (Message msg : msgList) {
                TextContent textContent = (TextContent) msg.getContent();
                int messgeType = 0;
                MyMessage tmp = new MyMessage(textContent.getText(), 1);
                result.add(tmp);
            }
        }
        return result;
    }

}
