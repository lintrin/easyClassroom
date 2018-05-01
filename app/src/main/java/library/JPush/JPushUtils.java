package library.JPush;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.myapplication.R;
import com.example.administrator.utils.ImageLoadUtils;

import cn.jiguang.imui.commons.ImageLoader;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class JPushUtils {
    public static ImageLoader getImageLoader(Activity activity) {
        return  new ImageLoader() {
            @Override
            public void loadAvatarImage(ImageView avatarImageView, String string) {
                // You can use other image load libraries.
                if (string.contains("R.drawable")) {
                    Integer resId = activity.getResources().getIdentifier(string.replace("R.drawable.", ""),
                            "drawable", activity.getPackageName());
                    ImageLoadUtils.setAvatarImage(activity,avatarImageView,resId,R.drawable.aurora_picture_not_found);

                } else if (string.contains("R.mipmap")){
                    Integer resId = activity.getResources().getIdentifier(string.replace("R.mipmap.", ""),
                            "mipmap", activity.getPackageName());
                    ImageLoadUtils.setAvatarImage(activity,avatarImageView,resId,R.drawable.aurora_picture_not_found);

                } else{
                    ImageLoadUtils.setImage(activity,avatarImageView,string,0,R.drawable.aurora_picture_not_found);
                }
            }

            /**
             * Load image message
             * @param imageView Image message's ImageView.
             * @param string A file path, or a uri or url.
             */
            @Override
            public void loadImage(final ImageView imageView, String string) {
                ImageLoadUtils.setImage(activity,imageView,string,0,R.drawable.aurora_picture_not_found);

            }

            /**
             * Load video message
             * @param imageCover Video message's image cover
             * @param uri Local path or url.
             */
            @Override
            public void loadVideo(ImageView imageCover, String uri) {
                ImageLoadUtils.setImage(activity,imageCover,uri,0,R.drawable.aurora_picture_not_found);

            }
        };
    }
}
