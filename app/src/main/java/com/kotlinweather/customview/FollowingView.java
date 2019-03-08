package com.kotlinweather.customview;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Project Name：KotlinWeather
 * Description ：
 *
 * @author ：ZouWeiLin on 2019.03.06
 */
public class FollowingView extends View {

    private static final String TAG = FollowingView.class.getSimpleName();

    private int color = Color.RED;
    private int radius = 16;
    private float x;
    private float y;
    private Paint paint;
    private BlurMaskFilter maskfilter;

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public FollowingView(Context context) {
        this(context, null);
    }

    public FollowingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FollowingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // 使用软件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        // float radius：用来定义模糊半径，同样是高斯模糊算法。Blur style：发光样式，有内发光、外发光、和内外发光，分别对应：
        // Blur.INNER(内发光)、Blur.SOLID(外发光)、Blur.NORMAL(内外发光)、Blur.OUTER(仅发光部分可见)
        maskfilter = new BlurMaskFilter(radius, BlurMaskFilter.Blur.SOLID);
        paint.setMaskFilter(maskfilter);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(color);
        canvas.drawCircle(x, y, radius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        invalidate();

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
//                performClick();
                break;
            default:
        }
        return true;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }
}
