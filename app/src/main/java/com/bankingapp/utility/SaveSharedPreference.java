package com.bankingapp.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {

    public static final String IS_FIRST_TIME = "first_time";
    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     * @param context
     * @param loggedIn
     */
    public  void setIsFirstTime(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(IS_FIRST_TIME, loggedIn);
        editor.apply();
    }

    public  void setUserId(Context context, int id) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt("user_id",id);
        editor.apply();
    }

    public  String getUserName(Context context) {
        return getPreferences(context).getString("user_name", "");
    }

    public  void setUserName(Context context, String name) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString("user_name",name);
        editor.apply();
    }

    public  int getUserid(Context context) {
        return getPreferences(context).getInt("user_id", 0);
    }
    /**
     * Get the Login Status
     * @param context
     * @return boolean: login status
     */
    public  boolean isFirstTime(Context context) {
        return getPreferences(context).getBoolean(IS_FIRST_TIME, false);
    }
}
