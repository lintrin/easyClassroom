package com.example.administrator.myapplication.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class SteerableViewPager extends ViewPager {
    private float x;
    private float mLastMotionX;
    private boolean left_allow = true;
    private boolean right_allow = true;

    public SteerableViewPager(Context context) {
        super(context);
    }

    public SteerableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {


        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                x = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (right_allow&&left_allow){

                }else {
                    mLastMotionX = ev.getX() - x;
                    if (mLastMotionX > 0) {
                        if (!left_allow)
                            return false;
                    } else if (mLastMotionX < 0) {
                        if (!right_allow)
                            return false;
                    }
                }

                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean isLeft_allow() {
        return left_allow;
    }

    public void setLeft_allow(boolean left_allow) {
        this.left_allow = left_allow;
    }

    public boolean isRight_allow() {
        return right_allow;
    }

    public void setRight_allow(boolean right_allow) {
        this.right_allow = right_allow;
    }
}