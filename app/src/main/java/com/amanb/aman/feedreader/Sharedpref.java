package com.amanb.aman.feedreader;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by aman on 25/12/15.
 */
public class Sharedpref {
    String store_lang,stored_lang;

    public void storeInSharedPref(String lang,Activity activity) {
        store_lang=lang;

        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();

        editor.putString("language", store_lang);
        editor.apply();


    }

    public String readFromSharedPref(String lang,Activity activity)
    {
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        String defaultValue = "en";
        return sharedPreferences.getString("langauge",defaultValue);

    }
}
