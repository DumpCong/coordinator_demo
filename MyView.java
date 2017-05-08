package com.xu.workwork;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义view
 */
public class MyView extends View {
    private int lineUnSelectColor;
    private int status;
    private int lineSelectColor;
    private Bitmap image;
    private Paint linePaint;//画笔线
    private Paint textPaint;//画笔字
    private int margin = 5;

    private String[] content = {"第一个", "第二个", "第三个", "第四个"};


    /**
     * 使用java代码new的时候会调用此构造方法
     */
    public MyView(Context context) {
        this(context, null);
    }

    /**
     * 在布局文件中创建的 调用此构造方法
     */
    public MyView(Context context, AttributeSet attrs) {
        //都调用三个参数的构造方法
        this(context, attrs, 0);

    }

    /**
     * 在布局文件中创建的 调用此构造方法（只是多了一个style）
     */
    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
//        lineSelectColor = typedArray.getColor(R.styleable.MyView_line_select_color, Color.GRAY);//获取自定义属性值
//        lineUnSelectColor = typedArray.getColor(R.styleable.MyView_line_unselect_color, Color.BLACK);//
//        status = typedArray.getInteger(R.styleable.MyView_status, 0);
//        typedArray.recycle();//释放资源
        init();


    }

    /**
     * 初始化 数据 画笔
     */
    private void init() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(12);
        textPaint.setColor(Color.GREEN);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStrokeWidth(2);

        int imageY = getMeasuredHeight() - 5 - image.getHeight() - (int) textPaint.measureText("asdad");
        //测试做了一修改
    }

    /**
     * 重写onMeasure
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //调用setMeasuredDimension设置view大小
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    /**
     * 测量view大小的方法
     *
     * @param size
     * @param measureSpec
     * @return
     */
    public static int getDefaultSize(int size, int measureSpec) {
        int resultSize = 0;
        //获取宽高mode
        int mode = MeasureSpec.getMode(measureSpec);
        //获取测量的大小
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            //如果是AT_MOST，不能超过父view的宽度
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED://这种情况比较少见，不太会用到。
                resultSize = size;
                break;
            //固定大小 就使用测量好的即可
            case MeasureSpec.EXACTLY:
                resultSize = specSize;
                break;
        }
        return resultSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 4; i++) {
            if (i == 0) {


            }

        }


    }
}
