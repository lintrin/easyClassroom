package com.example.administrator.myapplication.ui.communal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.CourseUser;
import com.example.administrator.myapplication.model.DefaultUser;
import com.example.administrator.myapplication.model.MyMessage;
import com.example.administrator.myapplication.model.User;
import com.example.administrator.myapplication.model.impl.UserModel;
import com.example.administrator.utils.JMessageUtil;

import java.util.ArrayList;
import java.util.List;

import cn.jiguang.imui.chatinput.ChatInputView;
import cn.jiguang.imui.chatinput.listener.OnMenuClickListener;
import cn.jiguang.imui.chatinput.model.FileItem;
import cn.jiguang.imui.commons.models.IMessage;
import cn.jiguang.imui.messages.MessageList;
import cn.jiguang.imui.messages.MsgListAdapter;
import cn.jiguang.imui.messages.ptr.PtrDefaultHeader;
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
import library.JPush.JPushUtils;

/**
 * 成员聊天
 * <p>
 * 以用户的IdNumber作为唯一识别码进行注册
 *
 * @author by JingQ on 2018/5/1.
 */

public class ChattingActivity extends AppCompatActivity {

    private Conversation conversation;

    private User user = UserModel.getInstance().getUser();
    private static DefaultUser myUser;
    private static DefaultUser otherUser;

    private ChatInputView chatInputView;
    private PullToRefreshLayout ptrLayout;
    private PtrDefaultHeader header;
    private MessageList messageList;
    private int page = 0;
    private int limit = 5; //每次读取N条
    private MsgListAdapter<MyMessage> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating);
        JMessageClient.registerEventReceiver(this);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        chatInputView.setMenuClickListener(new OnMenuClickListener() {
            @Override
            public boolean onSendTextMessage(CharSequence input) {
                sendMessage(input.toString());
                return true;
            }

            @Override
            public void onSendFiles(List<FileItem> list) {

            }

            @Override
            public boolean switchToMicrophoneMode() {
                return false;
            }

            @Override
            public boolean switchToGalleryMode() {
                return false;
            }

            @Override
            public boolean switchToCameraMode() {
                return false;
            }

            @Override
            public boolean switchToEmojiMode() {
                return false;
            }
        });

    }

    private void initView() {

        ptrLayout = (PullToRefreshLayout) findViewById(R.id.pull_to_refresh_layout);
        header = new PtrDefaultHeader(this);
        chatInputView = findViewById(R.id.chat_input);
        int[] colors = getResources().getIntArray(R.array.google_color);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        header.setPadding(0, DisplayUtil.dp2px(this, 15), 0, DisplayUtil.dp2px(this, 10));
        header.setPtrFrameLayout(ptrLayout);

        ptrLayout.setLoadingMinTime(1000);
        ptrLayout.setDurationToCloseHeader(1500);
        ptrLayout.setHeaderView(header);
        ptrLayout.addPtrUIHandler(header);

        messageList = findViewById(R.id.msg_list);
        messageList.setShowSenderDisplayName(true);
        messageList.setShowReceiverDisplayName(true);

        adapter = new MsgListAdapter<>(user.getIdNumber(), JPushUtils.getImageLoader(this));
        messageList.setAdapter(adapter);

        // 如果设置为 true，下拉刷新时，内容固定，只有 Header 变化
        ptrLayout.setPinContent(true);
        ptrLayout.setPtrHandler(layout -> {
            Log.i("MessageListActivity", "Loading next page");
                loadFormerPage();
            // 加载完历史消息后调用
            ptrLayout.refreshComplete();
        });

    }

    private void loadFormerPage() {
        List<Message> messages = getMessages();
        List<MyMessage> myMessageList = new ArrayList<>(messages.size());
        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            MyMessage  myMessage = createMessage(message);
            myMessage.setUserInfo(myUser);
            myMessageList.add(myMessage);
        }

        adapter.addToEndChronologically(myMessageList);

    }

    private void initData() {
        String avatarPath = "R.mipmap.avatar1";
        CourseUser courseUser = (CourseUser) getIntent().getSerializableExtra("otherUser");
        myUser = new DefaultUser(user.getIdNumber(), user.getName(), avatarPath);
        otherUser = new DefaultUser(courseUser.getIdNumber(), courseUser.getUserName(), avatarPath);

        //需要先注册
//        JMessageClient.register(myUser.getId(), "123456", new BasicCallback() {
//            @Override
//            public void gotResult(int i, String s) {
//
//            }
//        });
        Log.i("sss", "initData: " + myUser.getId());
        JMessageClient.login(myUser.getId(), "123456", new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                conversation = Conversation.createSingleConversation(otherUser.getId(), JMessageUtil.APP_KEY);
                initMessageList();

            }
        });


    }


    /**
     * 获取消息列表
     */
    private void initMessageList() {
        List<Message> messages = getMessages();
        List<MyMessage> myMessageList = new ArrayList<>(messages.size());
        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            MyMessage  myMessage = createMessage(message);
            myMessageList.add(myMessage);
            adapter.addToStart(myMessage, true);

        }


    }

    private List<Message> getMessages() {
        //参数 offset limit
        List<Message> messages = conversation.getMessagesFromNewest(page, limit);
        Log.i("sss", "initMessageList: " + messages);
        page+=limit;
        return messages;
    }

    /**
     * 简单发送消息
     *
     * @param content 内容
     */
    private void sendMessage(String content) {
        Message message = JMessageClient.createSingleTextMessage(otherUser.getId(), JMessageUtil.APP_KEY, content);
        JMessageClient.sendMessage(message);
        MyMessage myMessage = createMessage(message);
        adapter.addToStart(myMessage, true);
    }


    /**
     * 已经进行注册
     * 接收消息处理
     * https://docs.jiguang.cn/jmessage/client/im_sdk_android/#_58
     *
     * @param event
     */
    public void onEvent(MessageEvent event) {
        Message msg = event.getMessage();
        MyMessage myMessage = createMessage(msg);
        runOnUiThread(() -> adapter.addToStart(myMessage, true));
    }

    //目前只处理文字信息
    public MyMessage createMessage(Message msg) {
        MyMessage myMessage = null;
        int type = 0;
        DefaultUser user;
        Log.i("sss", "createMessage: "+msg.getFromUser().getUserName());
        if (msg.getFromUser().getUserName().equals(myUser.getId())) {
            type = IMessage.MessageType.SEND_TEXT.ordinal();
            user = myUser;
        } else {
            type = IMessage.MessageType.RECEIVE_TEXT.ordinal();
            user = otherUser;
        }
        switch (msg.getContentType()) {
            case text:
                //处理文字消息
                //  TODO
                TextContent textContent = (TextContent) msg.getContent();

                myMessage = new MyMessage(textContent.getText(), type);
                myMessage.setUserInfo(user);
                myMessage.setMsgStatus(IMessage.MessageStatus.SEND_SUCCEED);
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
            default:
                break;

        }
        if (myMessage != null) {
            Log.i("sss", "createMessage: "+user.getId());
            myMessage.setUserInfo(user);
        }

        return myMessage;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
    }

}
