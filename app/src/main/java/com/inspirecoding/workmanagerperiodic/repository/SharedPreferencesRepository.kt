package com.inspirecoding.workmanagerperiodic.repository

import android.content.Context
import android.content.SharedPreferences

// 1
class SharedPreferencesRepository(context: Context)
{
    // 2
    private val SHARED_PREFERENCES = "shared_preferences"
    private val KEY_INFO = "info"

    // 3
    private var mySharedPreferences: SharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCES, Context.MODE_PRIVATE
    )
    private lateinit var editor: SharedPreferences.Editor

    // 4
    fun setInfo (info : String)
    {
        editor = mySharedPreferences.edit()
        editor.putString(KEY_INFO, info)
        editor.apply()
    }

    // 5
    fun getInfo() : String?
    {
        return mySharedPreferences.getString(KEY_INFO, "")
    }

    // 6
    fun clearInfo()
    {
        editor = mySharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
