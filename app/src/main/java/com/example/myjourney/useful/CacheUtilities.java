package com.example.myjourney.useful;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;

public class CacheUtilities {

    public static final String USER_NAME_KEY = "userName";
    public static final String GENDER_KEY = "gender";
    public static final String AGE_KEY = "age";
    public static final String HEIGHT_KEY = "height";
    public static final String WEIGHT_KEY = "weight";
    public static final String IMAGE_PROFILE_KEY = "imageProfile";
    public static final String USER_FILE = "user";

/// caches
    public static void cacheUserName(Activity activity, String userName) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE).edit();
        editor.putString(USER_NAME_KEY, userName);
        editor.apply();
    }

    public static void cacheGender (Activity activity, String gender) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE).edit();
        editor.putString(GENDER_KEY, gender);
        editor.apply();
    }

    public static void cacheHeight(Activity activity, String height) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE).edit();
        editor.putString(HEIGHT_KEY, height);
        editor.apply();
    }

    public static void cacheWeight(Activity activity, String weight) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE).edit();
        editor.putString(WEIGHT_KEY, weight);
        editor.apply();
    }

    public static void cacheImageProfile(Activity activity, String imageProfile) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE).edit();
        editor.putString(IMAGE_PROFILE_KEY, imageProfile);
        editor.apply();
    }

    public static void cacheAge(Activity activity, String age) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE).edit();
        editor.putString(AGE_KEY, age);
        editor.apply();
    }




    // gets

    public static String getUserName(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE);
        return prefs.getString(USER_NAME_KEY, "");

    }

    public static String getAge(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE);
        return prefs.getString(AGE_KEY, "");

    }

    public static String getImageProfile(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE);
        return prefs.getString(IMAGE_PROFILE_KEY, "");
    }

    public static String getHeight(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE);
        return prefs.getString(HEIGHT_KEY, "");
    }

    public static String getWeight(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE);
        return prefs.getString(WEIGHT_KEY, "");
    }

    public static String getGender(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE);
        return prefs.getString(GENDER_KEY, "");

    }

    /// clear

    public static void clearAll(Activity activity) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE).edit();
        editor.clear().apply();
    }


}
