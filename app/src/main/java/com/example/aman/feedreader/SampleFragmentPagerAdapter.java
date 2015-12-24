package com.example.aman.feedreader;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.aman.feedreader.fragments.PageFragment;
import com.example.aman.feedreader.fragments.PageFragmentbusy;
import com.example.aman.feedreader.fragments.PageFragmententer;
import com.example.aman.feedreader.fragments.PageFragmenthealth;
import com.example.aman.feedreader.fragments.PageFragmentnation;
import com.example.aman.feedreader.fragments.PageFragmentscience;
import com.example.aman.feedreader.fragments.PageFragmentsports;
import com.example.aman.feedreader.fragments.PageFragmenttech;
import com.example.aman.feedreader.fragments.PageFragmentworld;
import com.example.aman.feedreader.myadapter.DownloadImages;

import java.util.concurrent.ExecutionException;

/**
 * Created by aman on 11/12/15.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 8;
    private String tabTitles[] = new String[] { "Worldwide", "Nation", "Business","Technology","Sports","Entertainment","Science","Health"};
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

 /*   @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }*/


    @Override
    public Fragment getItem(int position) {

            switch(position)
            {
                case 0:

                        Log.i("Goin' in? ", "yes");
                        return new PageFragmentworld();

                case 1:


                        Log.i("Goin' in? ", "yes");
                        return new PageFragmentnation();



               case 2:


                       Log.i("Goin' in? ", "yes");
                       return new PageFragmentbusy();



                 case 3:


                         Log.i("Goin' in? ", "yes");
                         return new PageFragmenttech();


                case 4:


                        Log.i("Goin' in? ", "yes");
                        return new PageFragmentsports();


                case 5:


                        Log.i("Goin' in? ", "yes");
                        return new PageFragmententer();

                case 6:

                        Log.i("Goin' in? ", "yes");
                        return new PageFragmentscience();


                case 7:

                        Log.i("Goin' in? ", "yes");
                        return new PageFragmenthealth();
                default:
                  return  new PageFragmentworld();
            }

        }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}