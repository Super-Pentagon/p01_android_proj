package com.pentagon.p01_android_proj.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class UserPreferenceUtil {

    public final static String PREFERENCE_NAME = "user_preference";

    public final static String PREFERENCE_USER_ID = "user_id";

    /**
     * 判断用户是否已经登陆
     * @param activity
     * @return
     */
    public static boolean isUserLogin(Activity activity) {
        return getUserId(activity) != null && !getUserId(activity).equals("");
    }

    /**
     * 判断用户是否已经登陆
     * @param activity
     * @return
     */
    public static void logout(Activity activity) {
        setUserId(activity, "");
    }

    /**
     * 读取磁盘用户 UserId
     * @param activity
     * @return
     */
    public static String getUserId(Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        String UserId = sharedPref.getString(PREFERENCE_USER_ID, "");
        return UserId;
    }

    /**
     * 更新磁盘用户 UserId
     * @param activity
     * @param newUserId
     * @return
     */
    public static void setUserId(Activity activity, String newUserId) {
        SharedPreferences sharedPref = activity.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PREFERENCE_USER_ID, newUserId);
        editor.apply();
    }

}
