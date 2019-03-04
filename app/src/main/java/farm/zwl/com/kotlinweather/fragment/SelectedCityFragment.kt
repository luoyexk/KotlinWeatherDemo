package farm.zwl.com.kotlinweather.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import farm.zwl.com.kotlinweather.MainActivity
import farm.zwl.com.kotlinweather.MainActivity.Companion.KEY_CITY
import farm.zwl.com.kotlinweather.entity.CityInfo
import farm.zwl.com.kotlinweather.util.PreferencesUtil
import farm.zwl.com.kotlinweather.util.csv.CsvFileReader
import kotlinx.android.synthetic.main.fragment_selected.*
import kotlinx.coroutines.*
import kotlinx.coroutines.android.Main
import java.io.File


/**
 * Project Name：KotlinWeather
 * Description ：
 * @author     ：ZouWeiLin on 2019.03.04
 */
class SelectedCityFragment : Fragment() {

    companion object {
        val TAG = "SelectedCityFragment"
    }

    var job: Job? = null
    var list: ArrayList<CityInfo>? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        job = GlobalScope.async(start = CoroutineStart.LAZY) {
            val path = getContext()!!.cacheDir.absolutePath + File.separator + "china-city-list.csv"
            list = CsvFileReader.readCsvFile(path)
            async(Dispatchers.Main) {
                Toast.makeText(getContext()!!, "load ok", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.kotlinweather.zwl.R.layout.fragment_selected, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_back.setOnClickListener {
            if (activity is MainActivity) {
                (activity as MainActivity).closeSelectedFragment()
            }
        }

        job?.start()
        edit_query.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                PreferencesUtil.putString(KEY_CITY, s.toString())
            }
        })
    }

    override fun onDetach() {
        if (job!!.isActive) {
            job!!.cancel()
            Log.d(TAG, "cancel")
        }
        list?.clear()
        list = null
        super.onDetach()
    }

}