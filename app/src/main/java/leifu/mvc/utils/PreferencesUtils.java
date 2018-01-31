package leifu.mvc.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import leifu.mvc.app.App;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/29 15:11
 * 描述:本地存储
 */

public class PreferencesUtils {
    private final static String sharedpreferences_name = "JSON_CACHE";

    public static SharedPreferences getAppSp() {
        return App.getInstance().getSharedPreferences(sharedpreferences_name, Context.MODE_PRIVATE);
    }

    public static void setString(String key, String value) {
        getAppSp().edit().putString(key, value).apply();
    }

    public static void setInt(String key, int value) {
        getAppSp().edit().putInt(key, value).apply();
    }

    public static int getInt(String key, int value) {
        return getAppSp().getInt(key, value);
    }

    public static String getString(String key,
                                   String defValue) {
        return getAppSp().getString(key, defValue);
    }

    public static void setBoolean(String key,
                                  boolean value) {
        getAppSp().edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key,
                                     boolean defValue) {
        return getAppSp().getBoolean(key, defValue);
    }

    public static void setLong(String key,
                               long value) {
        getAppSp().edit().putLong(key, value).apply();
    }

    public static long getLong(String key,
                               long defValue) {
        return getAppSp().getLong(key, defValue);
    }
    public static void remove(String key) {
        getAppSp().edit().remove(key).apply();
    }
    public static void removeAll() {
        SharedPreferences sp = App.getInstance().getSharedPreferences(
                sharedpreferences_name, Activity.MODE_PRIVATE);
        sp.edit().clear().apply();
    }
}
