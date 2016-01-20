package com.amanb.aman.feedreader;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.amanb.aman.feedreader.fragments.PageFragmentbusy;
import com.amanb.aman.feedreader.fragments.PageFragmententer;
import com.amanb.aman.feedreader.fragments.PageFragmenthealth;
import com.amanb.aman.feedreader.fragments.PageFragmenthighlights;
import com.amanb.aman.feedreader.fragments.PageFragmentnation;
import com.amanb.aman.feedreader.fragments.PageFragmentscience;
import com.amanb.aman.feedreader.fragments.PageFragmentsports;
import com.amanb.aman.feedreader.fragments.PageFragmenttech;
import com.amanb.aman.feedreader.fragments.PageFragmentworld;

import java.util.Collection;

/**
 * Created by aman on 20/1/16.
 */
public class GermanFragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 8;
    private String tabTitles[];
    private Context context;

    public GermanFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        tabTitles= new String[] { context.getResources().getString(R.string.highlights),context.getResources().getString(R.string.world),
                context.getResources().getString(R.string.nation),  context.getResources().getString(R.string.busy),
                context.getResources().getString(R.string.tech), context.getResources().getString(R.string.sports),
                context.getResources().getString(R.string.enter)   /*context.getResources().getString(R.string.science)*/,
                context.getResources().getString(R.string.health)};
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
                return new PageFragmenthighlights();

            case 1:


                Log.i("Goin' in? ", "yes");
                return new PageFragmentworld();



            case 2:


                Log.i("Goin' in? ", "yes");
                return new PageFragmentnation();



            case 3:


                Log.i("Goin' in? ", "yes");
                return new PageFragmentbusy();


            case 4:


                Log.i("Goin' in? ", "yes");
                return new PageFragmenttech();


            case 5:


                Log.i("Goin' in? ", "yes");
                return new PageFragmentsports();

            case 6:

                Log.i("Goin' in? ", "yes");
                return new PageFragmententer();


          /*  case 7:

                Log.i("Goin' in? ", "yes");
                return new PageFragmentscience();*/

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
