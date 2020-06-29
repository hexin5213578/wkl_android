package com.example.wkl_android.main.home.ui;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.wkl_android.widget.asvp.AutoScrollViewPager;

public class LoopRecyclerViewPager extends AutoScrollViewPager {
    public LoopRecyclerViewPager(Context paramContext) {
        super(paramContext);
    }

    public LoopRecyclerViewPager(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    PointF downP = new PointF();
    PointF curP = new PointF();

    private float xDown;// 记录手指按下时的横坐标。
    private float xMove;// 记录手指移动时的横坐标。
    private float yDown;// 记录手指按下时的纵坐标。

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // 每次进行onTouch事件都记录当前的按下的坐标
        curP.x = ev.getX();
        curP.y = ev.getY();

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 这两个参数记录的暂时不知道做什么用，也没有地方进行引用。
            downP.x = ev.getX();
            downP.y = ev.getY();

            xDown = ev.getX();
            yDown = ev.getY();

            // 此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        // 移动的时候进行判断
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            xMove = ev.getX();
            float yMove = ev.getY();
            // 这里判断是横向还是纵向移动，
            if (Math.abs(yMove - yDown) < Math.abs(xMove - xDown) && Math.abs(xMove - xDown) > 2) {
                // 这里还要再进行一次判断，不然横向滑动的时候直接没效果了
                if (Math.abs(xMove - xDown) > 2) {
                    // 通知父控件不要进行拦截了，事件自己消费
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    // 这里返回False 事件自己消费了，不用往下传递。
                    return false;
                }
            } else {
                // 通知父控件进行事件拦截
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.onTouchEvent(ev);
    }

}
