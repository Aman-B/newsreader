package com.amanb.aman.feedreader.fragments;

import android.view.View;

import com.amanb.aman.feedreader.MainActivity;
import com.amanb.aman.feedreader.NextActivity;

/**
 * Created by aman on 1/1/16.
 */
public class ShowViewPager {
    public void show(String calling_activity) {
        switch (calling_activity)
        {
            case "main":
                MainActivity.viewPager.setVisibility(View.VISIBLE);
                break;

            case "next":
                NextActivity.viewPager.setVisibility(View.VISIBLE);
                break;

            default:
                MainActivity.viewPager.setVisibility(View.VISIBLE);
                break;

        }


    }
}
