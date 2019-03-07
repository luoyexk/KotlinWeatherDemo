package com.kotlinweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlinweather.customview.ColorSelectorView
import com.kotlinweather.zwl.R
import kotlinx.android.synthetic.main.activity_custom_view.*

/**
 * Project Name：KotlinWeather
 * Description ：
 * @author     ：ZouWeiLin on 2019.03.06
 */
class CustomViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
        selected_view.callback = object : ColorSelectorView.Callback {
            override fun touchColor(color: Int) {
                following_view.setColor(color)
            }

        }
    }

}