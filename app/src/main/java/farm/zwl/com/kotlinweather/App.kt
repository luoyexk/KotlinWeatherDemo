package farm.zwl.com.kotlinweather

import android.app.Application
import farm.zwl.com.kotlinweather.util.PreferencesUtil

/**
 * Project Name：KotlinWeather
 * Description ：
 * @author     ：ZouWeiLin on 2019.03.04
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferencesUtil.init(this)
    }
}