package com.example.administrator.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.administrator.Utils.glide.GlideRoundTransform;
import com.example.administrator.myapplication.R;

/**
 * Created by fuzheyuan on 2018/1/12.
 */

public class ImageLoadUtils {


    public static void setImageByUrl(ImageView imageView, String url) {
        setImage(imageView.getContext(), imageView, url, 0, R.drawable.ic_assignment_blue);
    }

    public static void setImageByUrl(ImageView imageView, String url, int type) {
        setImage(imageView.getContext(), imageView, url, type, R.drawable.ic_assignment_blue);
    }

    public static void setImage(Context context, ImageView imageView, String url, int type, int defaultDrawable) {
        if (url.startsWith("http"))
            Glide.with(context).load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(defaultDrawable)
                    .transform(new CenterCrop(context), new GlideRoundTransform(context, 2, type))
                    .error(defaultDrawable)
                    .into(imageView);
        else imageView.setImageResource(defaultDrawable);
    }

    public static void setImage(Context context, ImageView imageView, int rId, int defaultDrawable) {
        Glide.with(context).load(rId)
                .placeholder(defaultDrawable)
                .transform(new CenterCrop(context), new GlideRoundTransform(context, 2))
                .error(defaultDrawable)
                .into(imageView);
    }
}
