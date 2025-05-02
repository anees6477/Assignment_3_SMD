package com.scheduler.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private static final String PREF_NAME = "SchedulerPrefs";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_DARK_MODE = "dark_mode";

    private final SharedPreferences sharedPreferences;

    public PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public String getUserName() {
        return sharedPreferences.getString(KEY_USER_NAME, "");
    }

    public void setUserName(String value) {
        sharedPreferences.edit().putString(KEY_USER_NAME, value).apply();
    }

    public String getUserEmail() {
        return sharedPreferences.getString(KEY_USER_EMAIL, "");
    }

    public void setUserEmail(String value) {
        sharedPreferences.edit().putString(KEY_USER_EMAIL, value).apply();
    }

    public boolean isDarkMode() {
        return sharedPreferences.getBoolean(KEY_DARK_MODE, false);
    }

    public void setDarkMode(boolean value) {
        sharedPreferences.edit().putBoolean(KEY_DARK_MODE, value).apply();
    }
} 