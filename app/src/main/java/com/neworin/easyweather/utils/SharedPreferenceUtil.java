package com.neworin.easyweather.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {

    private static SharedPreferences getInstance(Context context) {
        return context.getSharedPreferences(Constant.INSTANCE.getSHARED_PREF_NAME(), Context.MODE_PRIVATE);
    }

    /**
     * 存放String类型
     *
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, String value) {
        getInstance(context).edit().putString(key, value).apply();
    }

    /**
     * 获取String类型
     *
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        return getInstance(context).getString(key, null);
    }
}