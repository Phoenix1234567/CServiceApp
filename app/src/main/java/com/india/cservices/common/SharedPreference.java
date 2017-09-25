package com.india.cservices.common;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by rajat on 20/04/17.
 */

public class SharedPreference {
    private static String SHARE_PREF_NAME = AppConstants.PREF_NAME;
    private static SharedPreference tweetSharedPreference;
    private SharedPreferences sharedPreference;
    private SharedPreferences.Editor editor;
    private Context context;
    private String deviceId;


    private SharedPreference() {

    }

    private SharedPreference(Context context) {
        this.context = context;
        sharedPreference = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
    }

    public static SharedPreference getInstance(Context context) {
        if (tweetSharedPreference == null) {
            tweetSharedPreference = new SharedPreference(context);
        }
        return tweetSharedPreference;
    }


    public void setInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void setString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void setBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void setFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public void putSet(String key, Set<String> set) {
        editor.putStringSet(key, set);
        editor.commit();
    }

    public int getInt(String key) {
        return sharedPreference.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreference.getInt(key, defaultValue);
    }

    public String getString(String key) {
        return sharedPreference.getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        return sharedPreference.getString(key, defaultValue);
    }

    public boolean getBoolean(String key) {
        return sharedPreference.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreference.getBoolean(key, defaultValue);
    }

    public float getFloat(String key) {
        return sharedPreference.getFloat(key, 0);
    }

    public float getFloat(String key, float defValue) {
        return sharedPreference.getFloat(key, defValue);
    }

    public Set<String> getSet(String key) {
        return sharedPreference.getStringSet(key, null);
    }

    public void removeKey(String key) {
        editor.remove(key);
        editor.apply();
    }

    public void clearSharedPreferences() {
        editor.clear();
        editor.commit();
    }

}
