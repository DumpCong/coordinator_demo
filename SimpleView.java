package com.xu.workwork;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class SimpleView extends View {

    public final static int DEFAULT_WIDTH = 0;//最小宽度为0x0
    private int height;
    private int width;

    private Bitmap bitmap;
    private int status;// 状态
    private static final int GREEN = Color.argb(255, 8, 180, 132);
    private static final int GARY = Color.GRAY;
    private static final int ORDER_ARRIVED_RES[] = {R.mipmap.order_flow_arrive_one, R.mipmap.order_flow_arrive_two, R.mipmap.order_flow_arrive_three_evaluation, R.mipmap.order_flow_arrive_four};
    private static final int ORDER_UNARRIVED_RES[] = {R.mipmap.order_flow_unarrive_one, R.mipmap.order_flow_unarrive_two, R.mipmap.order_flow_unarrive_three, R.mipmap.order_flow_unarrive_four};
    private static final String[] ORDER_TEXT_RES = {"进行中", "安装汇报", "评价", "完成"};
    private Paint imagePaint;
    private Paint textPaint;
    private Paint linePaint;
    private int startX;//第一张图片绘制的起始x
    private int startY = 30;//第一张图片绘制的起始y
    private static final int lineWidth = 150;


    public SimpleView(Context context) {
        this(context, null);
    }

    public SimpleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //都调用三个参数的构造方法
    public SimpleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        获取属性中status
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleView);
        status = typedArray.getInteger(R.styleable.SimpleView_status, 0);
        typedArray.recycle();

    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量宽度
        width = 0;
        switch (MeasureSpec.getMode(widthMeasureSpec)) {
            case MeasureSpec.EXACTLY://固定大小或者matchparent
                // 就以实际测量大小为准
                width = MeasureSpec.getSize(widthMeasureSpec);
                break;
            case MeasureSpec.AT_MOST://父容器指定了最大值 常用于warpcontent
                // 取最小长度作为组件大小
                width = Math.min(DEFAULT_WIDTH, MeasureSpec.getSize(widthMeasureSpec));
                break;
            case MeasureSpec.UNSPECIFIED:

                break;
        }
        height = 0;
        int i = MeasureSpec.getMode(heightMeasureSpec);
        if (i == MeasureSpec.EXACTLY) {
            //就以实际测量大小为准
            height = MeasureSpec.getSize(heightMeasureSpec);
        } else if (i == MeasureSpec.UNSPECIFIED || i == MeasureSpec.AT_MOST) {
            //取最小长度作为组件大小
            height = Math.min(DEFAULT_WIDTH, MeasureSpec.getSize(heightMeasureSpec));
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec);
        }
        setMeasuredDimension(width, height);//设置view的大小
        //测量完成后初始化画笔 准备绘制view
        initPaint();
    }

    //初始化画笔
    public void initPaint() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        imagePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(20);
        textPaint.setColor(GARY);
        linePaint.setStrokeWidth(5);//线宽
        bitmap = BitmapFactory.decodeResource(getResources(), ORDER_ARRIVED_RES[1]);
//        startX = width / 2 - bitmap.getWidth() * 2 - getPaddingLeft() - lineWidth - lineWidth / 2;
        startX = 40;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 4; i++) {
            if (i <= status) {
                bitmap = BitmapFactory.decodeResource(getResources(), ORDER_ARRIVED_RES[i]);
                textPaint.setColor(GREEN);//文字画笔为绿色
                if (i < status && i < 3) {
                    //绘制已完成部分的绿线
                    linePaint.setColor(GREEN);
                    canvas.drawLine(startX + (i + 1) * bitmap.getWidth() + lineWidth * i, 50, startX + (i + 1) * bitmap.getWidth() + lineWidth + lineWidth, 50, linePaint);
                } else if (i == status && i < 3) {
                    //绘制连接处  一半绿一半灰色
                    linePaint.setColor(GREEN);
                    canvas.drawLine(startX + (i + 1) * bitmap.getWidth() + lineWidth * i, 50, startX + (i + 1) * bitmap.getWidth() + lineWidth + lineWidth / 2, 50, linePaint);
                    linePaint.setColor(GARY);
                    canvas.drawLine(startX + (i + 1) * bitmap.getWidth() + lineWidth * i + lineWidth / 2, 50, startX + (i + 1) * bitmap.getWidth() + lineWidth + lineWidth, 50, linePaint);
                } else if (status == i && status == 3) {
                    linePaint.setColor(GREEN);
                    canvas.drawLine(startX + (i) * bitmap.getWidth() + lineWidth * (i - 1), 50, startX + (i) * bitmap.getWidth() + lineWidth + lineWidth + lineWidth, 50, linePaint);
                }
            } else {
                if (i < 3) {
                    //绘制灰色的线
                    linePaint.setColor(GARY);
                    canvas.drawLine(startX + (i + 1) * bitmap.getWidth() + lineWidth * i, 50, startX + (i + 1) * bitmap.getWidth() + lineWidth * i + lineWidth, 50, linePaint);
                } else if (i == 3) {
                    linePaint.setColor(GARY);
                    canvas.drawLine(startX + (i) * bitmap.getWidth() + lineWidth * (i - 1), 50, startX + (i) * bitmap.getWidth() + lineWidth * (i - 1) + lineWidth, 50, linePaint);

                }
                //设置文字为灰色
                textPaint.setColor(GARY);
                //设置灰色图片资源
                bitmap = BitmapFactory.decodeResource(getResources(), ORDER_UNARRIVED_RES[i]);
            }
            //绘制图片
            canvas.drawBitmap(bitmap, startX + i * (bitmap.getWidth() + lineWidth), startY, imagePaint);
            //绘制图片下文字
            canvas.drawText(ORDER_TEXT_RES[i], startX + i * (bitmap.getWidth() + lineWidth) + 20 - (textPaint.measureText(ORDER_TEXT_RES[i]) / 2), startY + bitmap.getWidth() + 20, textPaint);
        }
    }
}
