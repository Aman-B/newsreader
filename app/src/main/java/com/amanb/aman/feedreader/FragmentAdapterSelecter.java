package com.amanb.aman.feedreader;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by aman on 21/1/16.
 */
public class FragmentAdapterSelecter {


    public FragmentPagerAdapter setFragmentAdapter(FragmentManager supportFragmentManager, Activity activity, String lang) {

        switch (lang)
        {
            case "en":
                SampleFragmentPagerAdapter sampleFragmentPagerAdapter = new
                        SampleFragmentPagerAdapter(supportFragmentManager,activity);

                return sampleFragmentPagerAdapter;

            case "hi":
                SampleFragmenth sampleFragmenth= new
                        SampleFragmenth(supportFragmentManager,activity);
                return sampleFragmenth;

            case "de":
                GermanFragmentAdapter germanFragmentAdapter = new
                        GermanFragmentAdapter(supportFragmentManager,activity);
                Log.i("German is ","set");
                return germanFragmentAdapter;


        }


        return null;
    }
}
