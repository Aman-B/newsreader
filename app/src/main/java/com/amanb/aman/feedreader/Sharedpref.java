package com.amanb.aman.feedreader;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by aman on 25/12/15.
 */
public class Sharedpref {
    String store_lang,stored_lang;

    public void storeInSharedPref(String key,String value,Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor =sharedPreferences.edit();

        editor.putString(key,value);
        editor.apply();


    }

    public String readFromSharedPref(String key,String defaultvalue,Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
String result= sharedPreferences.getString(key,defaultvalue);
        return result;

    }
}
