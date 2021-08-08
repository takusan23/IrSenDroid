package io.github.takusan23.irsendroid

import android.app.Application
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import org.json.JSONArray
import org.json.JSONException

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    private val prefSetting = PreferenceManager.getDefaultSharedPreferences(context)

    val patternLiveData = MutableLiveData<String>(prefSetting.getString("pattern", ""))

    val isErrorLiveData = MutableLiveData(false)

    fun setPattern(patternString: String) {
        prefSetting.edit { putString("pattern", patternString) }
        patternLiveData.value = patternString
    }

    fun sendIr() {
        try {
            IrSendTool.sendIr(context, parseIrFromPatternString())
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    private fun parseIrFromPatternString(): MutableList<Int> {
        val irPatternIntList = mutableListOf<Int>()
        isErrorLiveData.value = false
        try {
            val jsonArray = JSONArray("[${patternLiveData.value}]")
            for (i in 0 until jsonArray.length()) {
                val time = jsonArray.getInt(i)
                irPatternIntList.add(time)
            }
        } catch (e: JSONException) {
            isErrorLiveData.value = true
            e.printStackTrace()
        }
        return irPatternIntList
    }

}