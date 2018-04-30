package com.example.administrator.myapplication.ui.communal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.Utils.JMessageUtil;
import com.example.administrator.Utils.TextUtils;
import com.example.administrator.myapplication.R;

import java.util.List;

import cn.jpush.im.android.api.ChatRoomManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.RequestCallback;
import cn.jpush.im.android.api.event.ChatRoomMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.helpers.IMReceiver;
import cn.jpush.im.api.BasicCallback;

public class ChattingRoomActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 测试聊天室：
     * roomId：10033704
     * 聊天室名称：Java交流
     * 描述：Java学习
     */

    /**
     * 聊天室房间ID，文档中是long形式
     */
    private long roomId;

    private Conversation conv;

    private AutoCompleteTextView mEtMessage;

    private Button mBtnMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating_room);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mEtMessage = findViewById(R.id.et_chat_room_message);
        mBtnMessage = findViewById(R.id.btn_chat_room_send);
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
//        暂时没有，用mock数据
        assert bundle != null;
        roomId = (long) bundle.get(JMessageUtil.CHATTING_ROOM_KEY);
        if (roomId == 0) {
            roomId = 10033704;
        }
        //进入聊天室
        ChatRoomManager.enterChatRoom(roomId, new RequestCallback<Conversation>() {
            @Override
            public void gotResult(int responseCode, String responseMessage, Conversation conversation) {
                String result = null != conversation ? conversation.toString() : null;
            }
        });
        conv = JMessageClient.getChatRoomConversation(roomId);
    }

    private void initListener() {
        mBtnMessage.setOnClickListener(this);
        // TODO 进行类注册
        JMessageClient.registerEventReceiver(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_chat_room_send:
                String text = mEtMessage.getText().toString();
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(this, "发送内容不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                sendMessage(roomId, text);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁时退出聊天室
        ChatRoomManager.leaveChatRoom(roomId, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
//                postTextToDisplay("leaveChatRoom", responseCode, responseMessage, null);
            }
        });
    }

    private void sendMessage(long roomID, String text) {
        // 发送聊天室消息
        if (null == conv) {
            conv = Conversation.createChatRoomConversation(roomID);
        }
        final Message msg = conv.createSendTextMessage(text);//实际聊天室可以支持所有类型的消息发送，demo为了简便，仅仅实现了文本类型的消息发送
        msg.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
                if (0 == responseCode) {
                    //成功处理
//                    postMessageToDisplay("MessageSent", responseCode, responseMessage, msg);
                } else {
                    //失败处理
//                    postTextToDisplay("MessageSent", responseCode, responseMessage, "消息发送失败");
                }
            }
        });
        JMessageClient.sendMessage(msg);
    }


    /**
     * 接收聊天室消息，即使注册了，但是并没有进入这个方法？？
     *
     * @param event
     */
    public void onEventMainThread(ChatRoomMessageEvent event) {
        // TODO 消息监听
        Log.d("tag", "ChatRoomMessageEvent received .");
        List<Message> msgs = event.getMessages();
        for (Message msg : msgs) {
            Log.i("sss", msg.getContent().toString());
        }
    }

}
