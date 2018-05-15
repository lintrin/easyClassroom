package com.example.administrator.myapplication.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MySlidingPaneLayout extends SlidingPaneLayout {
    private ViewPager viewPager;
    public MySlidingPaneLayout(Context context) {
        super(context);  
    }  
  
    public MySlidingPaneLayout(Context context, AttributeSet attrs) {
        super(context, attrs);  
    }  

    public void setViewPager(ViewPager viewPager){
        this.viewPager = viewPager;
    }
    /** 
     * 确定是否拦截触摸事件 
     * @param ev 触摸事件 
     * @return true代表拦截，false代表不拦截 
     */  
    @Override  
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //找到viewPager  

        //判断viewPager是否滑到第一页,如果不是第一页就不拦截事件，不滑出菜单  
        if(viewPager==null||viewPager.getCurrentItem() > 0){
            return false;  
        }  
        //否则就拦截，让滑动菜单处理  
        return super.onInterceptTouchEvent(ev);  
    }  
}