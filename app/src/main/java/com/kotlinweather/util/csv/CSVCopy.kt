package com.kotlinweather.util.csv

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.channels.FileChannel

/**
 * Project Name：KotlinWeather
 * Description ：
 * @author     ：ZouWeiLin on 2019.03.04
 */

class CSVCopy {
    companion object {
        fun copyCSV(context: Context) {
            GlobalScope.async() {
                var ioIs: InputStream? = null
                try {
                    ioIs = context.assets.open("china-city-list.csv")
                    val srcLength = ioIs!!.available()
                    val f = File(context.cacheDir.absolutePath + File.separator + "china-city-list.csv")
                    var copy = false
                    // 不存在则复制
                    if (!f.exists()) {
                        f.createNewFile()
                        copy = true
                    }
                    // 存在但大小不一样复制
                    if (f.length().toInt() != srcLength) {
                        copy = true
                    }
                    // 开始复制
                    if (copy) {
                        ioIs.copyTo(FileOutputStream(f))
                    }
                } catch (e: Exception) {
                } finally {
                    ioIs?.close()
                }
            }
        }
    }

}