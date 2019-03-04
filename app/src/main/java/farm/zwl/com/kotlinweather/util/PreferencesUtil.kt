package farm.zwl.com.kotlinweather.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Project Name：KotlinWeather
 * Description ：
 * @author     ：ZouWeiLin on 2019.03.04
 */
class PreferencesUtil {

    companion object {
        private const val NAME = "PreferencesUtil"

        private var sharedPreferences: SharedPreferences? = null

        fun init(context: Context) {
            sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        }

        private fun getEditor(): SharedPreferences.Editor {
            return sharedPreferences!!.edit()
        }


        operator fun contains(key: String): Boolean {
            return sharedPreferences!!.contains(key)
        }


        operator fun get(param: String): String? {
            var result: String? = null
            try {
                result = sharedPreferences!!.getString(param, "")
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return result
        }

        /**
         * 删除配置文件
         *
         * @param param
         */
        fun removePreference(param: String) {
            val editor = getEditor()
            editor.remove(param)
            editor.commit()
        }

        fun putInt(key: String, value: Int) {
            val editor = getEditor()
            editor.putInt(key, value)
            editor.commit()
        }

        fun putLong(key: String, value: Long) {
            val editor = getEditor()
            editor.putLong(key, value)
            editor.commit()
        }

        fun putBoolean(key: String, value: Boolean) {
            val editor = getEditor()
            editor.putBoolean(key, value)
            // 修改为commit，反馈说50%几率发生点击了switch后显示为未选中，退出界面再进入看到还是选中状态
            editor.commit()
        }

        fun putString(key: String, value: String) {
            val editor = getEditor()
            editor.putString(key, value)
            editor.commit()
        }

        fun getInt(key: String, defValue: Int): Int {
            try {
                return sharedPreferences!!.getInt(key, defValue)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return defValue
        }

        fun getLong(key: String, defValue: Long): Long {

            try {
                return sharedPreferences!!.getLong(key, defValue)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return defValue
        }


        fun getBoolean(key: String, defValue: Boolean): Boolean {
            try {
                return sharedPreferences!!.getBoolean(key, defValue)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return defValue
        }

        fun getString(key: String, defValue: String?): String? {

            try {
                return sharedPreferences!!.getString(key, defValue)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return defValue
        }
    }

}