package com.amanb.aman.feedreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

/**
 * Created by aman on 17/1/16.
 */
public class SplashActivity extends AppCompatActivity{

    String language;
    Context context;
    Activity activity;
    Intent launchActivity;
    Locale myLocale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = getApplicationContext();
        activity = SplashActivity.this;

        final Sharedpref sharedpref = new Sharedpref();

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        final String countryCode = tm.getSimCountryIso();


        Handler handler1 = new Handler();

        //get language
        language=getLanguage(sharedpref,context);

        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                setLocal(language,countryCode,activity);
            }
        },3000);



    }
        public void setLocal(String lang,String countryCode, Context applicationContext) {
        Log.i("Locale is set", "!");
        context=applicationContext;
        myLocale = new Locale(lang);

        Resources res =context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Log.i("Locale is set", " " + lang);

        choseActivity(lang,context);

        launchActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        launchActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        launchActivity.putExtra("country",countryCode);
        launchActivity.putExtra("language", lang);
        ((Activity)context).finish();
        context.startActivity(launchActivity);
    //    Log.i("Locale is set", " " + MainActivity.lang);

    }


    private void choseActivity(String lang,Context context) {
        switch (lang)
        {
            case "en":
                launchActivity = new Intent(context, MainActivity.class);
                break;

            case"hi":
                launchActivity= new Intent(context,MainActivity.class);
                break;

            case"de":
                launchActivity= new Intent(context,MainActivity.class);
                break;

            default:
                launchActivity = new Intent(context, MainActivity.class);

                break;
        }

    }


    public String getLanguage(Sharedpref sharedpref,Context context) {

        String got_language=sharedpref.readFromSharedPref("language", "en", context);
        switch (got_language)
        {
            case "en":
               language="en";
                break;

            case "hi":
                language="hi";
                break;

            case "de":
                language="de";
                break;

            default:
                language="en";
                break;

        }


        return language;
    }
}
