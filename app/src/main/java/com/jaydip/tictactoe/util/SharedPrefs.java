package com.jaydip.tictactoe.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    static final String PREF_MUSIC = "isMusic";
    static final String PREF_VIBRATE = "isVibrate";
    private static SharedPreferences mPreferences;

    private static SharedPreferences getInstance(Context context) {
        if (mPreferences == null) {
            mPreferences = context.getApplicationContext().getSharedPreferences("Tic_data", 0);
        }
        return mPreferences;
    }

    public static void clearPrefs(Context context) {
        getInstance(context).edit().clear().apply();
    }

    public static boolean getIsVibrate(Context context) {
        return getInstance(context).getBoolean(PREF_VIBRATE, true);
    }

    public static void setIsVibrate(Context context, boolean z) {
        getInstance(context).edit().putBoolean(PREF_VIBRATE, z).apply();
    }

    public static boolean getIsMusic(Context context) {
        return getInstance(context).getBoolean(PREF_MUSIC, true);
    }

    public static void setIsMusic(Context context, boolean z) {
        getInstance(context).edit().putBoolean(PREF_MUSIC, z).apply();
    }
}
