package com.india.cservices.common;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by sujeet on 20/04/17.
 *
 */

public class SharedPreference {
    private static String SHARE_PREF_NAME = AppConstants.PREF_NAME;
    private static SharedPreference csPreference;
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
        if (csPreference == null) {
            csPreference = new SharedPreference(context);
        }
        return csPreference;
    }

    /**
     * save userId for getting response from server
     * @param key
     * @param value
     */
    public void saveUserID(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * get user id from sharedprefence
     * @param key
     * @return
     */
    public String getUserID(String key) {
        return sharedPreference.getString(key, "");
    }

    /**
     * save token in sharedprefrence. its use for app authentication on every call
     * key is taken identification key .
     * @param key
     * @param token
     */
    public void saveToken(String key, String token) {
        editor.putString(key, token);
        editor.commit();
    }

    /**
     * get app token for sharedfrefrence for service request
     * @param key
     * @return
     */
    public String getToken(String key) {
        return sharedPreference.getString(key, "");
    }

    /**
     * save user name for showing in app
     * @param key
     * @param user_name
     */
    public void saveUserName(String key, String user_name) {
        editor.putString(key, user_name);
        editor.commit();
    }

    /**
     * get user name from sharedprefence
     * @param key
     * @return
     */
    public String getUserName(String key) {
        return sharedPreference.getString(key, "Guest User");
    }


    /**
     * save user name for showing in app
     * @param key
     * @param email_id
     */
    public void saveUserEmailId(String key, String email_id) {
        editor.putString(key, email_id);
        editor.commit();
    }

    /**
     * get user email from sharedprefence
     * @param key
     * @return
     */
    public String getUserEmailId(String key) {
        return sharedPreference.getString(key, "guest@cservice.com");
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

    /**
     * delete all prefrences from sharedprefrence
     */
    public void clearSharedPreferences() {
        editor.clear();
        editor.commit();
    }

}
