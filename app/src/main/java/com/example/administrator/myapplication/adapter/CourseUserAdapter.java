package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.CourseUser;
import com.example.administrator.myapplication.model.User;
import com.example.administrator.myapplication.model.impl.UserModel;
import com.example.administrator.myapplication.ui.communal.ChattingActivity;

/**
 * @author by JingQ on 2018/5/1.
 */

public class CourseUserAdapter extends BaseRecycleViewAdapter<CourseUser> {

    private Context context;

    private static User user = UserModel.getInstance().getUser();

    public CourseUserAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chatting_user,  parent, false);
        return new CourseUserAdapter.VHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, CourseUser data) {
        ((CourseUserAdapter.VHolder) viewHolder).setData(realPosition, data);
    }

    class VHolder extends RecyclerView.ViewHolder {
        TextView mTvUserName;
        TextView mTvUserIdNumber;
        ImageView mImgAvatar;

        VHolder(View view) {
            super(view);
            this.mTvUserName = view.findViewById(R.id.tv_user_name);
            this.mTvUserIdNumber = view.findViewById(R.id.tv_user_id_num);
            this.mImgAvatar = view.findViewById(R.id.img_avatar);
        }

        public void setData(int position, CourseUser data) {
            mTvUserName.setText(data.getUserName());
            mTvUserIdNumber.setText(data.getIdNumber());
            Glide.with(context).load(R.mipmap.avatar1).asBitmap().centerCrop().into(new BitmapImageViewTarget(mImgAvatar) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    mImgAvatar.setImageDrawable(circularBitmapDrawable);
                }
            });
            //互发聊天信息
            itemView.setOnClickListener(view -> {

                if (user != null && user.getId() == data.getUserId()) {
                    Toast.makeText(context, "你点击了自己~~", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(context, ChattingActivity.class);
                    intent.putExtra("otherUser", data);
                    context.startActivity(intent);
                }
            });
        }


    }
}
