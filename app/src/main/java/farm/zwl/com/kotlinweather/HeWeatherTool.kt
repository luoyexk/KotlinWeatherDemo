package farm.zwl.com.kotlinweather

import farm.zwl.com.kotlinweather.util.Worker

/**
 * Project Name：KotlinWeather
 * Description ：
 * @author     ：ZouWeiLin on 2019.03.04
 */
class HeWeatherTool {

    companion object {
        val myKey = "key=" + ApiService.key
    }

    private fun formatMutilp(pairs: Array<Pair<String, String>>): String {
        var parameters = ""
        for (pair in pairs) {
            parameters += formatSingle(pair)
        }
        /*  if (parameters.endsWith("&")) {
              parameters.substring(0, parameters.length - 1)
          }*/
        return parameters + myKey
    }

    private fun formatSingle(pair: Pair<String, String>): String {
        return pair.first + "=" + pair.second + "&"
    }

    fun requestWeather(cityName: String, callback: Worker.Callback) {
        Worker.instance.getJson(
            ApiService.forecastApi + "?" + formatMutilp(arrayOf(Pair("location", cityName))), callback
        )
    }
}