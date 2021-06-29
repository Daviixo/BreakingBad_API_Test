package com.daviixo.breakingbad_api_test

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SharedPrefManager {

    private val TAG = "SharedPrefManager"

    fun getSharedPref(mContext: Context): SharedPreferences? {
        return mContext.getSharedPreferences(
            mContext.getString(R.string.key_validar_primera_vez),
            Context.MODE_PRIVATE
        )
    }

    fun setStringPrefVal(mContext: Context, key: String?, value: String?) {
        if (key != null) {
            val edit: SharedPreferences.Editor = getSharedPref(mContext)!!.edit()
            edit.putString(key, value)
            edit.commit()
        }
    }

    fun setIntPrefVal(mContext: Context, key: String?, value: Int) {
        if (key != null) {
            val edit: SharedPreferences.Editor = getSharedPref(mContext)!!.edit()
            edit.putInt(key, value)
            edit.apply()
        }
    }

    fun setLongPrefVal(mContext: Context, key: String?, value: Long?) {
        if (key != null) {
            val edit: SharedPreferences.Editor = getSharedPref(mContext)!!.edit()
            edit.putLong(key, value!!)
            edit.commit()
        }
    }

    fun setBooleanPrefVal(mContext: Context, key: String?, value: Boolean) {
        if (key != null) {
            val edit: SharedPreferences.Editor = getSharedPref(mContext)!!.edit()
            edit.putBoolean(key, value)
            edit.commit()
        }
    }


    fun getStringVal(mContext: Context, key: String?): String? {
        val pref: SharedPreferences = getSharedPref(mContext)!!
        var temp: String? = ""
        try {
            temp = if (pref.contains(key)) pref.getString(key, "") else ""
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return temp
    }

    fun getIntPrefVal(mContext: Context, key: String?): Int {
        val pref: SharedPreferences = getSharedPref(mContext)!!
        var temp = 0
        try {
            if (pref.contains(key)) temp = pref.getInt(key, 0)
            Log.e(TAG, "getIntPrefVal: Valor Actual trace: $temp")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return temp
    }

    fun getLongPrefVal(mContext: Context, key: String?): Long? {
        val pref: SharedPreferences = getSharedPref(mContext)!!
        var temp: Long? = null
        try {
            if (pref.contains(key)) temp = pref.getLong(key, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return temp
    }

    fun getBooleanPrefVal(mContext: Context, key: String?): Boolean {
        val pref: SharedPreferences = getSharedPref(mContext)!!
        var temp = false
        try {
            if (pref.contains(key)) temp = pref.getBoolean(key, false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return temp
    }
}