package com.tcs.nanodegree.myappportfolio.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tcs.nanodegree.myappportfolio.bean.Movie;
import com.tcs.nanodegree.myappportfolio.bean.Result;
import com.tcs.nanodegree.myappportfolio.bean.Review;
import com.tcs.nanodegree.myappportfolio.bean.Trailer;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

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

    public static void deleteSavedStringDatafromPref(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        prefs.edit().remove(key).commit();
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


    public static String getJsonStringFromObj(Object obj){


        Gson gson = new Gson();
        String jsonString = gson.toJson(obj);

        return jsonString;
    }

    public static Object getObjFromJsonString(String jsonString, Type T){

        Object obj = new Object();
        Gson gson = new Gson();

        if(T == Review.class) {
            obj = gson.fromJson(jsonString, Review.class);
        }else if(T == Trailer.class) {
            obj = gson.fromJson(jsonString, Trailer.class);
        }else if(T == Movie.class) {
            obj = gson.fromJson(jsonString, Movie.class);
        }

        return obj;
    }

    public static boolean notEmpty(Object obj) {
        boolean result = true;
        if (obj != null) {
            if (obj instanceof String) {

                if (obj.toString().trim().length() != 0
                        && !obj.toString().trim().equalsIgnoreCase("null"))
                    result = false;
            } else if (obj instanceof List) {
                if (((List) obj).size() > 0)
                    result = false;
            } else if (obj instanceof Map) {
                if (((Map) obj).size() > 0)
                    result = false;
            }
        }

        return !result;

    }

}
