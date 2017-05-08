package com.xu.workwork.view;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * 自定义下拉展示头部图片的View
 * <p>
 * 实现了NestedScrollingParent接口用于处理滑动
 */
public class PullShowView extends FrameLayout implements NestedScrollingParent {
    private Scroller mScroller;
    private int maxScrollY = 120;

    public PullShowView(Context context) {
        super(context);
    }

    public PullShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullShowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PullShowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //返回true 能接受子view的滑动事件
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        // dy > 0表示子View向上滑动;

        //头部view是否隐藏 子View向上滑动且父View的偏移量<ImageView高度
        boolean hiddenTop = dy > 0 && getScrollY() < maxScrollY;

        // 子View向下滑动(说明此时父View已经往上偏移了)且父View还在屏幕外面, 另外内部View不能在垂直方向往下移动了
        /**
         * ViewCompat.canScrollVertically(view, int)
         * 负数: 顶部是否可以滚动(官方描述: 能否往上滚动, 不太准确吧~)
         * 正数: 底部是否可以滚动
         */
        boolean showTop = dy < 0 && getScrollY() > 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (velocityY > 0 && getScrollY() < maxScrollY) // 向上滑动, 且当前View还没滑到顶
        {
            fling((int) velocityY, maxScrollY);
            return true;
        } else if (velocityY < 0 && getScrollY() > 0) // 向下滑动, 且当前View部分在屏幕外
        {
            fling((int) velocityY, 0);
            return true;
        }
        return false;
    }

    public void fling(int velocityY, int maxY) {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, maxY);
        invalidate();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) // 不允许向下滑动
        {
            y = 0;
        }
        if (y > maxScrollY) // 防止向上滑动距离大于最大滑动距离
        {
            y = maxScrollY;
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }
}
