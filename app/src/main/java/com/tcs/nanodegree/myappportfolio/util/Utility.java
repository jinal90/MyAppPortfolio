package com.tcs.nanodegree.myappportfolio.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Jinal Tandel on 11/19/2015.
 */
public class Utility {

    public static String getResponseString(URL url) throws IOException {
        URLConnection urlConnection;
        String response = "";
        urlConnection = url.openConnection();
        urlConnection.setConnectTimeout(20000);
        urlConnection.setReadTimeout(15000);
        urlConnection.setUseCaches(false);
        InputStream in = urlConnection.getInputStream();
        StringBuilder sb = new StringBuilder();
        byte[] buff = new byte[1024];
        int count;
        while ((count = in.read(buff)) > 0) {
            sb.append(new String(buff, 0, count));

        }
        response = sb.toString();
        return response;

    }

    public static void saveStringDataInPref(Context context, String key,
                                            String data) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        prefs.edit().putString(key, data).commit();
    }

    public static void saveIntDataInPref(Context context, String key, int data) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        prefs.edit().putInt(key, data).commit();
    }

    public static void saveBooleanDataInPref(Context context, String key,
                                             boolean data) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        prefs.edit().putBoolean(key, data).commit();
    }

    public static String getSavedStringDataFromPref(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        return prefs.getString(key, null);
    }

    public static boolean getSavedBooleanDataFromPref(Context context,
                                                      String key) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }

    public static int getSavedIntDataFromPref(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        return prefs.getInt(key, 0);
    }

    public static long getSavedLongDataFromPref(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        return prefs.getLong(key, 0);
    }

    public static float getSavedFloatDataFromPref(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        return prefs.getFloat(key, 0.0f);
    }

    public static void saveFloatDataInPref(Context context, String key,
                                           float data) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        prefs.edit().putFloat(key, data).commit();
    }


}
