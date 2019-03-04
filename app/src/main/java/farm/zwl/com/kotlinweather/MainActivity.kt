package farm.zwl.com.kotlinweather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.kotlinweather.zwl.R
import farm.zwl.com.kotlinweather.entity.WeatherForecast
import farm.zwl.com.kotlinweather.fragment.SelectedCityFragment
import farm.zwl.com.kotlinweather.util.PreferencesUtil
import farm.zwl.com.kotlinweather.util.Worker
import farm.zwl.com.kotlinweather.util.csv.CSVCopy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity() {

    companion object {
        val KEY_CITY = "KEY_CITY"
        val TAG ="MainActivity"
    }

    val CITY = "深圳市"
    var curCity: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        curCity = PreferencesUtil.getString(KEY_CITY, null)
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
                        tv.text = result
                        Log.d(TAG,result)
                        val forecast = Gson().fromJson<WeatherForecast>(result, WeatherForecast::class.java)

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
