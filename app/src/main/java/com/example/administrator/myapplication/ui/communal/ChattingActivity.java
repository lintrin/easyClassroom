package com.example.administrator.myapplication.ui.communal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.CourseUser;
import com.example.administrator.myapplication.model.User;
import com.example.administrator.myapplication.model.impl.UserModel;
import com.example.administrator.utils.JMessageUtil;

import java.util.List;

import cn.jiguang.imui.messages.MessageList;
import cn.jiguang.imui.messages.MsgListAdapter;
import cn.jiguang.imui.messages.ptr.PtrDefaultHeader;
import cn.jiguang.imui.messages.ptr.PtrHandler;
import cn.jiguang.imui.messages.ptr.PullToRefreshLayout;
import cn.jiguang.imui.utils.DisplayUtil;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.CustomContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

/**
 * 成员聊天
 *
 * 以用户的IdNumber作为唯一识别码进行注册
 *
 * @author by JingQ on 2018/5/1.
 */

public class ChattingActivity extends AppCompatActivity {

    private Conversation conversation;

    /**
     * 登陆者
     */
    private static User user = UserModel.getInstance().getUser();

    /**
     * 聊天者
     */
    private CourseUser otherUser;

    private PullToRefreshLayout ptrLayout;

    private PtrDefaultHeader header;

    private MessageList messageList;

    private MsgListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating);
        JMessageClient.registerEventReceiver(this);
        initView();
        initData();
    }

    private void initView() {

        ptrLayout = (PullToRefreshLayout) findViewById(R.id.pull_to_refresh_layout);
        header = new PtrDefaultHeader(this);

        int[] colors = getResources().getIntArray(R.array.google_color);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        header.setPadding(0, DisplayUtil.dp2px(this,15), 0,DisplayUtil.dp2px(this,10));
        header.setPtrFrameLayout(ptrLayout);

        ptrLayout.setLoadingMinTime(1000);
        ptrLayout.setDurationToCloseHeader(1500);
        ptrLayout.setHeaderView(header);
        ptrLayout.addPtrUIHandler(header);

        messageList = findViewById(R.id.msg_list);
        messageList.setShowSenderDisplayName(true);
        messageList.setShowReceiverDisplayName(true);

        adapter = new MsgListAdapter<>(user.getIdNumber(), null);
        messageList.setAdapter(adapter);

        // 如果设置为 true，下拉刷新时，内容固定，只有 Header 变化
        ptrLayout.setPinContent(true);
        ptrLayout.setPtrHandler(layout -> {
            Log.i("MessageListActivity", "Loading next page");
//                loadNextPage();
            // 加载完历史消息后调用
            ptrLayout.refreshComplete();
        });

    }

    private void initData() {
        otherUser = (CourseUser) getIntent().getSerializableExtra("otherUser");
        conversation = Conversation.createSingleConversation(otherUser.getIdNumber(), JMessageUtil.APP_KEY);

        getMessageList();

    }

    /**
     * 获取消息列表
     */
    private void getMessageList() {
        List<Message> messages = conversation.getAllMessage();
    }

    /**
     * 简单发送消息
     *
     * @param content 内容
     */
    private void sendMessage(String content) {
        Message message = JMessageClient.createSingleTextMessage(otherUser.getIdNumber(), JMessageUtil.APP_KEY, content);

        JMessageClient.sendMessage(message);
    }


    /**
     * 已经进行注册
     * 接收消息处理
     * https://docs.jiguang.cn/jmessage/client/im_sdk_android/#_58
     *
     * @param event
     */
    public void onEvent(MessageEvent event){
        Message msg = event.getMessage();

        switch (msg.getContentType()) {
            case text:
                //处理文字消息
                // TODO
                TextContent textContent = (TextContent) msg.getContent();
                textContent.getText();
                break;
            case image:
                //处理图片消息
                ImageContent imageContent = (ImageContent) msg.getContent();
                imageContent.getLocalPath();//图片本地地址
                imageContent.getLocalThumbnailPath();//图片对应缩略图的本地地址
                break;
            case voice:
                //处理语音消息
                VoiceContent voiceContent = (VoiceContent) msg.getContent();
                voiceContent.getLocalPath();//语音文件本地地址
                voiceContent.getDuration();//语音文件时长
                break;
            case custom:
                //处理自定义消息
                CustomContent customContent = (CustomContent) msg.getContent();
                customContent.getNumberValue("custom_num"); //获取自定义的值
                customContent.getBooleanValue("custom_boolean");
                customContent.getStringValue("custom_string");
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
    }

}
