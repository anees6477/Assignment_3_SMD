package com.scheduler.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREF_NAME,
        Context.MODE_PRIVATE
    )

    companion object {
        private const val PREF_NAME = "SchedulerPrefs"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_DARK_MODE = "dark_mode"
    }

    var userName: String
        get() = sharedPreferences.getString(KEY_USER_NAME, "") ?: ""
        set(value) = sharedPreferences.edit().putString(KEY_USER_NAME, value).apply()

    var userEmail: String
        get() = sharedPreferences.getString(KEY_USER_EMAIL, "") ?: ""
        set(value) = sharedPreferences.edit().putString(KEY_USER_EMAIL, value).apply()

    var isDarkMode: Boolean
        get() = sharedPreferences.getBoolean(KEY_DARK_MODE, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_DARK_MODE, value).apply()
} 