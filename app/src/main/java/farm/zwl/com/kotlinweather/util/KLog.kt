package farm.zwl.com.kotlinweather.util

import android.util.Log
import farm.zwl.com.kotlinweather.Config

/**
 * Project Name：KotlinWeather
 * Description ：打印日志工具
 * @author     ：ZouWeiLin on 2019.03.05
 */
class KLog {

    companion object {

        private var canPrint: Boolean

        init {
            canPrint = PreferencesUtil.getBoolean(Config.KEY_SHOW_LOG, false)
        }

        /**
         * Priority constant for the println method; use Log.v.
         */
        private const val VERBOSE = 2

        /**
         * Priority constant for the println method; use Log.d.
         */
        private const val DEBUG = 3

        /**
         * Priority constant for the println method; use Log.i.
         */
        private const val INFO = 4

        /**
         * Priority constant for the println method; use Log.w.
         */
        private const val WARN = 5

        /**
         * Priority constant for the println method; use Log.e.
         */
        private const val ERROR = 6

        /**
         * Priority constant for the println method.
         */
        private const val ASSERT = 7
        private const val TAG = "KLog"
        private const val MAX_LENGTH = 888

        fun d(tag: String?, msg: String) {
            type(DEBUG, tag, msg)
        }

        fun e(tag: String?, msg: String) {
            type(ERROR, tag, msg)
        }

        fun i(tag: String?, msg: String) {
            type(INFO, tag, msg)
        }

        fun v(tag: String?, msg: String) {
            type(VERBOSE, tag, msg)
        }

        fun w(tag: String?, msg: String) {
            type(WARN, tag, msg)
        }

        fun wtf(tag: String?, msg: String) {
            type(ASSERT, tag, msg)
        }

        fun showLog(show: Boolean) {
            canPrint = show
            PreferencesUtil.putBoolean(Config.KEY_SHOW_LOG, show)
        }

        private fun type(level: Int, tag: String?, msg: String) {
            if (msg.length > MAX_LENGTH) {
                printLarge(level, checkTag(tag), msg)
            } else {
                print(level, checkTag(tag), msg)
            }
        }

        /**
         * msg长度超过Android studio每一行打印长度时，把其拆分成数组
         */
        private fun printLarge(level: Int, tag: String, msg: String) {
            var tem = msg
            while (tem.length > MAX_LENGTH) {
                print(level, tag, tem.substring(0, MAX_LENGTH))
                tem = tem.substring(MAX_LENGTH)
            }
            // 打印剩余的
            print(level, tag, tem)
        }

        private fun print(level: Int, tag: String, msg: String) {
            if (!canPrint) {
                return
            }
            when (level) {
                DEBUG -> Log.d(tag, msg)
                INFO -> Log.i(tag, msg)
                VERBOSE -> Log.v(tag, msg)
                WARN -> Log.w(tag, msg)
                ERROR -> Log.e(tag, msg)
                ASSERT -> Log.wtf(tag, msg)
            }
        }

        /**
         * 检测tag长度，如果是0则返回tag
         */
        private fun checkTag(tag: String?): String = if (tag == null || tag.isEmpty()) TAG else tag


    }


}