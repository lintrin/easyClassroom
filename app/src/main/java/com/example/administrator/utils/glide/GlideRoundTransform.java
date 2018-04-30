package com.example.administrator.utils.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by Administrator on 2018/1/20 0020.
 */

public class GlideRoundTransform extends BitmapTransformation {

    public static final int ALL = 0;//全圆角 默认
    public static final int TOP = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    public static final int TOP_LEFT = 13;//左上
    public static final int TOP_RIGHT = 14;//右上
    public static final int BOTTOM_LEFT = 23;//左下
    public static final int BOTTOM_RIGHT = 24;//右下

    private int type;

    private static float radius = 0f;

    public GlideRoundTransform(Context context) {
        this(context, 4, 0);
    }

    public GlideRoundTransform(Context context, int dp) {
        this(context, dp, 0);
    }

    /**
     * @param dp   圆角半径 dp
     * @param type 圆角类型
     */
    public GlideRoundTransform(Context context, int dp, int type) {
        super(context);
        radius = Resources.getSystem().getDisplayMetrics().density * dp;
        this.type = type;


    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform,type);
    }

    private static Bitmap roundCrop(BitmapPool pool, Bitmap source,int type) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_4444);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_4444);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        switch (type) {
            case ALL:
                clipAll(canvas, paint, radius, source.getWidth(), source.getHeight());
                break;
            case TOP:
                clipTop(canvas, paint, radius, source.getWidth(), source.getHeight());
                break;
            case BOTTOM:
                clipBottom(canvas, paint, radius, source.getWidth(), source.getHeight());
                break;
            case LEFT:
                clipLeft(canvas, paint, radius, source.getWidth(), source.getHeight());
                break;
            case RIGHT:
                clipRight(canvas, paint, radius, source.getWidth(), source.getHeight());
                break;
            case TOP_LEFT:
                clipTopLeft(canvas, paint, radius, source.getWidth(), source.getHeight());
                break;
            case TOP_RIGHT:
                clipTopRight(canvas, paint, radius, source.getWidth(), source.getHeight());
                break;
            case BOTTOM_LEFT:
                clipBottomLeft(canvas, paint, radius, source.getWidth(), source.getHeight());
                break;
            case BOTTOM_RIGHT:
                clipBottomRight(canvas, paint, radius, source.getWidth(), source.getHeight());
                break;
            default:
                break;
        }
        return result;
    }


    private static void clipAll(Canvas canvas, Paint paint, float radius, int width, int height) {
        RectF rectF = new RectF(0, 0, width, height);
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }

    private static void clipLeft(Canvas canvas, Paint paint, float radius, int width, int height) {
        RectF block = new RectF(radius, 0, width, height);
        canvas.drawRect(block, paint);
        RectF rectF = new RectF(0, 0, radius * 2, height);
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }

    private static void clipRight(Canvas canvas, Paint paint, float radius, int width, int height) {
        RectF block = new RectF(0, 0, width - radius, height);
        canvas.drawRect(block, paint);
        RectF rectF = new RectF(width - radius * 2, 0, width, height);
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }

    private static void clipTop(Canvas canvas, Paint paint, float radius, int width, int height) {
        RectF block = new RectF(0, radius, width, height);
        canvas.drawRect(block, paint);
        RectF rectF = new RectF(0, 0, width, radius * 2);
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }

    /**
     * 指定左上角为圆角
     *
     * @param canvas
     * @param paint
     * @param radius
     * @param width
     * @param height
     */
    private static void clipTopLeft(Canvas canvas, Paint paint, float radius, int width, int height) {
        RectF block = new RectF(0, radius, width, height);
        canvas.drawRect(block, paint);
        RectF rectF = new RectF(0, 0, width / 2, radius * 2);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        RectF block1 = new RectF(width / 2 - radius * 2, 0, width, height);
        canvas.drawRect(block1, paint);
    }

    /**
     * 指定右上角为圆角
     *
     * @param canvas
     * @param paint
     * @param radius
     * @param width
     * @param height
     */
    private static void clipTopRight(Canvas canvas, Paint paint, float radius, int width, int height) {
        RectF block = new RectF(0, radius, width, height);
        canvas.drawRect(block, paint);
        RectF rectF = new RectF(width / 2 - radius * 2, 0, width, radius * 2);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        RectF block1 = new RectF(0, 0, width / 2, height);
        canvas.drawRect(block1, paint);
    }

    private static void clipBottom(Canvas canvas, Paint paint, float radius, int width, int height) {
        RectF block = new RectF(0, 0, width, height - radius);
        canvas.drawRect(block, paint);
        RectF rectF = new RectF(0, height - radius * 2, width, height);
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }

    /**
     * 指定左下角为圆角
     *
     * @param canvas
     * @param paint
     * @param radius
     * @param width
     * @param height
     */
    private static void clipBottomLeft(Canvas canvas, Paint paint,
                                       float radius, int width, int height) {
        RectF block = new RectF(0, 0, width, height - radius);
        canvas.drawRect(block, paint);
        RectF rectF = new RectF(0, height - radius * 2, width / 2, height);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        RectF block1 = new RectF(width / 2 - radius * 2, height - radius * 2, width, height);
        canvas.drawRect(block1, paint);
    }

    /**
     * 指定右下角为圆角
     *
     * @param canvas
     * @param paint
     * @param radius
     * @param width
     * @param height
     */
    private static void clipBottomRight(Canvas canvas, Paint paint,
                                        float radius, int width, int height) {
        RectF block = new RectF(0, 0, width, height - radius);
        canvas.drawRect(block, paint);
        RectF rectF = new RectF(width / 2, height - radius * 2, width, height);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        RectF block1 = new RectF(0, height - radius * 2, width / 2 + radius * 2, height);
        canvas.drawRect(block1, paint);
    }


    @Override
    public String getId() {
        return getClass().getName() + Math.round(radius);
    }
}
