package com.kotlinweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.kotlinweather.zwl.R
import com.kotlinweather.entity.WeatherForecast
import com.kotlinweather.fragment.SelectedCityFragment
import com.kotlinweather.util.KLog
import com.kotlinweather.util.PreferencesUtil
import com.kotlinweather.util.Worker
import com.kotlinweather.util.csv.CSVCopy
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    companion object {
        val KEY_CITY = "KEY_CITY"
        val TAG = "MainActivity"
    }

    var curCity: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        curCity = PreferencesUtil.getString(KEY_CITY, null)
        KLog.showLog(true)
        initViews()
        copyCSV2Local()
        if (curCity != null) {
            tv.performClick()
        }
    }

    private fun initViews() {
        val fragment = SelectedCityFragment()
        select_city.setOnClickListener {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment, SelectedCityFragment.TAG)
                .commit()
        }
        tv.setOnClickListener {
            if (curCity != null) {
                HeWeatherTool().requestWeather(curCity!!, object : Worker.Callback {
                    override fun result(result: String) {
//                        tv.text = result
                        KLog.d(TAG, result)
                        val j = JSONObject(result)
                        val optJSONArray = j.optJSONArray("HeWeather6")
                        if (optJSONArray.length() > 0) {
                            val forecast = Gson().fromJson<WeatherForecast>(
                                optJSONArray.get(0).toString(),
                                WeatherForecast::class.java
                            )
                            App.makeToast(
                                forecast?.basic?.parent_city + forecast?.daily_forecast?.get(
                                    0
                                )?.cond_txt_d
                            )
                        }
                    }
                })
            }
        }
    }


    fun closeSelectedFragment() {
        val f: SelectedCityFragment? =
            supportFragmentManager.findFragmentByTag(SelectedCityFragment.TAG) as SelectedCityFragment
        supportFragmentManager.beginTransaction().remove(f!!).commit()
    }

    fun copyCSV2Local() {
        CSVCopy.copyCSV(this)
    }
}
