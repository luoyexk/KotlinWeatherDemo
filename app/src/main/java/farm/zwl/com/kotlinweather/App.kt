package farm.zwl.com.kotlinweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import farm.zwl.com.kotlinweather.util.PreferencesUtil

/**
 * Project Name：KotlinWeather
 * Description ：
 * @author     ：ZouWeiLin on 2019.03.04
 */
class App : Application() {

    companion object {
        private val handler: Handler = Handler(Looper.getMainLooper())
        private var toast: Toast? = null
        var context: Context? = null

        fun makeToast(msg: String) {
            handler.post {
                toast?.setText(msg)
                toast?.show()
            }
        }

    }

    @SuppressLint("ShowToast")
    override fun onCreate() {
        super.onCreate()
        context = this
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT)
        PreferencesUtil.init(this)
    }
}