package com.kotlinweather.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

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
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
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

       /* int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            default:
        }*/
        return true;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }
}
