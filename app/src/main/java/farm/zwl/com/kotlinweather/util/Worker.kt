package farm.zwl.com.kotlinweather.util

import android.os.Handler
import android.os.Looper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

/**
 * Project Name：KotlinWeather
 * Description ：
 * @author     ：ZouWeiLin on 2019.03.04
 */
class Worker {
    private val id = AtomicInteger(1)
    private var worker =
        ThreadPoolExecutor(4, 8, 5, TimeUnit.SECONDS,
            LinkedBlockingQueue(8),
            ThreadFactory { r ->
                Thread(r, "he-weather-" + id.getAndIncrement())
            })

    /**
     * 单例：懒汉模式
     */
    companion object {
        val instance: Worker by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Worker()
        }
    }

    /**
     * get请求
     */
    fun getJson(path: String, callback: Callback) {
        worker.execute {
            val u = URL(path)
            val openConnection = u.openConnection() as HttpURLConnection
            openConnection.requestMethod = "GET"
            openConnection.connectTimeout = 5000
            openConnection.readTimeout = 5000
            val reader = BufferedReader(InputStreamReader(openConnection.inputStream) as Reader?)
            val buffer = StringBuilder()
            val content = CharArray(1024)
            while (reader.read(content) != -1) {
                buffer.append(content)
            }
            val result = buffer.toString()
            Handler(Looper.getMainLooper()).post { callback.result(result) }
        }
    }

    interface Callback {
        fun result(result: String)
    }
}