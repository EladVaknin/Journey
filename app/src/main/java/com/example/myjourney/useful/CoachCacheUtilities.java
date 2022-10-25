package com.example.myjourney.useful;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;

public class CoachCacheUtilities {


    public static final String COACH_USER_NAME_KEY = "userName";
    public static final String AGE_KEY = "age";
    public static final String IMAGE_PROFILE_KEY = "imageProfile";
    public static final String USER_FILE = "user";
    public static final String EXPERIENCE = "experience";
    public static final String EDUCATION = "education";
    public static final String GENDER_KEY = "gender";

    /// caches
    public static void cacheGender (Activity activity, String gender) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE).edit();
        editor.putString(GENDER_KEY, gender);
        editor.apply();
    }

    public static void CoachCacheUserName(Activity activity, String userName) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE).edit();
        editor.putString(COACH_USER_NAME_KEY, userName);
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

    public static void cacheExperience(Activity activity, String experience) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE).edit();
        editor.putString(EXPERIENCE, experience);
        editor.apply();
    }

    public static void cacheEduction(Activity activity, String eduction) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE).edit();
        editor.putString(EDUCATION, eduction);
        editor.apply();
    }




    // gets

    public static String getCoachUserName(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE);
        return prefs.getString(COACH_USER_NAME_KEY, "");

    }

    public static String getAge(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE);
        return prefs.getString(AGE_KEY, "");

    }

    public static String getImageProfile(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE);
        return prefs.getString(IMAGE_PROFILE_KEY, "");
    }


    public static String getExperience(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE);
        return prefs.getString(EXPERIENCE, "");
    }

    public static String getEducation(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences(USER_FILE, MODE_PRIVATE);
        return prefs.getString(EDUCATION, "");
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
