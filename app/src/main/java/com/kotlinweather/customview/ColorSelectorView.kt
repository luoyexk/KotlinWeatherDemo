package com.kotlinweather.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import com.kotlinweather.App.Companion.context
import com.kotlinweather.util.KLog
import com.kotlinweather.util.PreferencesUtil.Companion.init


/**
 * Project Name：KotlinWeather
 * Description ：https://github.com/DuanJiaNing/ColorPicker/blob/master/library/src/main/java/com/duan/colorpicker/ColorPickerView.java
 *
 * @author ：ZouWeiLin on 2019.03.06
 */
class ColorSelectorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    View(context, attrs, defStyle) {

    companion object {
        val TAG = "ColorSelectorView"
    }

    private val paint: Paint
    private var colorStart = Color.WHITE
    private var colorEnd = Color.BLACK
    private var width = 50f
    private var height = 400f
    private var layoutleft = 0f
    private var layouttop = 0f
    private var layoutright = 0f
    private var layoutbottom = 0f
    private var touchX = 0f
    private var touchY = 0f
    private var bitmapForColor: Bitmap? = null


    public var callback: Callback? = null
        set(value) {
            field = value
        }

    /* fun setCallback(callback: Callback?) {
         this.callback = callback
     }*/

    init {
        paint = Paint()
        paint.isAntiAlias = true
        val attr =
            context.obtainStyledAttributes(attrs, com.kotlinweather.zwl.R.styleable.ColorSelectorView, defStyle, 0)
        colorStart = attr.getColor(com.kotlinweather.zwl.R.styleable.ColorSelectorView_start_color, colorStart)
        colorEnd = attr.getColor(com.kotlinweather.zwl.R.styleable.ColorSelectorView_end_color, colorEnd)
        width = attr.getDimension(com.kotlinweather.zwl.R.styleable.ColorSelectorView_default_width, width)
        height = attr.getDimension(com.kotlinweather.zwl.R.styleable.ColorSelectorView_default_height, height)
        attr.recycle()
        setLayerType(LAYER_TYPE_SOFTWARE, null)

        bitmapForColor = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        // 浅色在上，深色在下
        /*if (colorStart < colorEnd) {
            val tmp = colorEnd
            colorEnd = colorStart
            colorStart = tmp
        }*/

    }

    private fun updateColors(start: Int, end: Int) {
        val linearGradient = LinearGradient(
            0f,
            0f,
            width,
            height,
            start,
            end,
            Shader.TileMode.REPEAT
        )
        paint.shader = linearGradient
        paint.strokeWidth = width
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(width.toInt(), height.toInt())
        KLog.d(TAG, "onMeasure:set" + width.toInt() + " " + height.toInt())
        KLog.d(TAG, "onMeasure:def" + widthMeasureSpec + " " + heightMeasureSpec)
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        KLog.d(
            TAG,
            "onLayout() called with: changed = [$changed], left = [$left], top = [$top], right = [$right], bottom = [$bottom]"
        )

        if (!changed) {
            layoutleft = left.toFloat()
            layouttop = top.toFloat()
            layoutright = right.toFloat()
            layoutbottom = bottom.toFloat()
            updateColors(colorStart, colorEnd)
//            cacheB = Bitmap.createBitmap(layoutright.toInt(), layoutbottom.toInt(), Bitmap.Config.ARGB_8888)

            // 绘制位置计算
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBitmapCache()
        canvas?.drawLine(layoutleft, layouttop, layoutleft, height, paint)
    }

    private fun drawBitmapCache() {
        val canvas = Canvas(bitmapForColor)
//        canvas.
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        touchX = event?.x ?: 0f
        touchY = event?.y ?: 0f
//        val percent = touchY / height
//        val color = ((colorStart + colorEnd) * percent).toInt()
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
                if (bitmapForColor != null && bitmapForColor!!.byteCount > 0) {
                    val pixel = bitmapForColor!!.getPixel(x.toInt(), y.toInt())
                    val red = Color.red(pixel)
                    val green = Color.green(pixel)
                    val blue = Color.blue(pixel)
                    val rgb = Color.rgb(red, green, blue)
                    callback?.touchColor(rgb)

                }
            }
            else -> {
            }
        }
        return true
    }

    interface Callback {
        fun touchColor(color: Int)
    }
}
