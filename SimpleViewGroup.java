package com.xu.workwork;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class SimpleViewGroup extends ViewGroup {

    private List<List<View>> mAllViews;
    private List<Integer> mAllLineHeight;
    private List<View> lineViews;


    public SimpleViewGroup(Context context) {
        this(context, null);
    }

    public SimpleViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mAllLineHeight = new ArrayList<>();
        mAllViews = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        getsize(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    //获取view的大小
    public void getsize(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        mAllLineHeight.clear();
        mAllViews.clear();
        // Viewgroup的宽
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = 0;

        //因为width一般设置为matchParent 所以这里至于要获取高度即可。
        //高度取决于子view的高度和个数。
        int count = getChildCount();//子view的个数
        int lineWidth = 0;//当前行的宽度
        int lineHeight = 0;//当前行的高度
        int lastViewHeight = 0;//上一个view的高度
        lineViews = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            //测量子view
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
            //获取子view的宽 包括左右边距
            int childWidth = layoutParams.leftMargin + layoutParams.rightMargin + childView.getMeasuredWidth();
            //子view的高度  包括上下边距
            int childHeight = layoutParams.bottomMargin + layoutParams.topMargin + childView.getMeasuredHeight();


            //如果当前行加上子view的宽度小于等于父view的宽度  不需换行
            if (lineWidth + childWidth <= widthSize) {
                //记录当前行的宽度 累加之前的view
                lineWidth += childWidth;
                //取当前行的最高的子view的高度作为当前行的高度
                lineHeight = Math.max(childHeight, lastViewHeight);
                lineViews.add(childView);
                if (i == count - 1) {
                    heightSize += lineHeight;
                    mAllLineHeight.add(heightSize);
                    mAllViews.add(lineViews);
                }
            }
            //当前超出当前行的宽度  需要换行
            else {
                //换行 设置父view的高度累加上一行的高度
                heightSize += lineHeight;
                mAllLineHeight.add(heightSize);
                mAllViews.add(lineViews);
                lineViews = new ArrayList<>();
                //新一行的宽度等于当前view的宽度
                lineWidth = childWidth;
                lineHeight = childHeight;
                lineViews.add(childView);

                //如果最后一个view换行显示  则累加最后一个view的高度
                if (i == count - 1) {
                    heightSize += childHeight;
                    mAllLineHeight.add(heightSize);
                    mAllViews.add(lineViews);
                }
            }
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        for (int i = 0; i < mAllViews.size(); i++) {
//            List<View> vlist = mAllViews.get(i);
//            Log.e("lineHeight", "Line " + i + "    height=" + mAllLineHeight.get(i));
//            for (int j = 0; j < vlist.size(); j++) {
//                Log.e("View", "width = " + vlist.get(j).getMeasuredWidth() + "   height =" + vlist.get(j).getMeasuredHeight());
//            }
//        }
        for (int i = 0; i < mAllViews.size(); i++) {
            List<View> lineViews = mAllViews.get(i);
            int leftTotalMargin = 0;
            for (int j = 0; j < lineViews.size(); j++) {
                View childView = lineViews.get(j);
                MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
                //获取子view的宽 包括左右边距
                int leftPosition = layoutParams.leftMargin + leftTotalMargin;
                int topPosition = i > 0 ? layoutParams.topMargin + mAllLineHeight.get(i - 1) : layoutParams.topMargin;
                int rightPosition = childView.getMeasuredWidth() + leftPosition;
                int bottomPosition = childView.getMeasuredHeight() + topPosition;
                childView.layout(leftPosition, topPosition, rightPosition, bottomPosition);
                leftTotalMargin = rightPosition + layoutParams.rightMargin;
            }
        }
    }
}
