package com.xu.workwork.recycler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * 自定义RecyclerView分割线
 * Created by xu on 2017/4/1.
 */
public class MyDecoration extends RecyclerView.ItemDecoration {


    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL = LinearLayoutManager.VERTICAL;
    //通过获取系统属性中的listDivider来添加，在系统中的AppTheme中设置
    public static final int[] ATRRS = new int[]{
            android.R.attr.listDivider
    };

    private int lineMargin = 15;//分割线两边的缩进长度
    private int mDividerHeight = 1;//分割线高度
    private int color = Color.DKGRAY;//分割线颜色
    private int mOrientation;//屏幕方向
    private Paint paint;//画笔

    public MyDecoration() {
        super();
    }


    /**
     * 构造方法传参
     *
     * @param context
     * @param orientation 线的方向
     */
    public MyDecoration(Context context, int orientation) {
        mOrientation = orientation;
        init();

    }

    //初始化画笔
    public void init() {

        paint = new Paint();
        paint.setColor(color);
    }

    //画横线
    public void drawVerticalLine(Canvas c, RecyclerView parent) {
        //获取条目数
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();
            //分割线的上下左右坐标
            int top = params.bottomMargin + childView.getBottom();
            int right = childView.getMeasuredWidth() - lineMargin;
            int left = params.leftMargin + lineMargin;
            int bottom = params.bottomMargin + childView.getBottom() + mDividerHeight;
            Log.e("okey", "top = " + top + "  right = " + right + "  left = " + left + "  bottom = " + bottom);
            //绘制分割线
            c.drawRect(left, top, right, bottom, paint);
        }
    }

    //画竖线
    public void drawHorizontalLine(Canvas c, RecyclerView parent) {

    }


    //绘制方法 在Item绘制之前先开始画，被Item的内容覆盖。
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == VERTICAL) {
            drawHorizontalLine(c, parent);
        } else if (mOrientation == HORIZONTAL) {
            drawVerticalLine(c, parent);
        }
    }

    //绘制方法 在Item绘制之后开始画，覆盖Item的内容。
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    //设置每个条目的偏移量(因为分割线也是有高度的 花完分割线以后 每隔条目需要偏移一段位置)
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, mDividerHeight);
    }
}
