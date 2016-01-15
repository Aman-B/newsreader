package com.amanb.aman.feedreader;

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

/**
 * Created by aman on 11/12/15.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 9;
    private String tabTitles[] = new String[] { MainActivity.con.getResources().getString(R.string.highlights),MainActivity.con.getResources().getString(R.string.world),
            MainActivity.con.getResources().getString(R.string.nation),  MainActivity.con.getResources().getString(R.string.busy),
            MainActivity.con.getResources().getString(R.string.tech), MainActivity.con.getResources().getString(R.string.sports),
            MainActivity.con.getResources().getString(R.string.enter)  , MainActivity.con.getResources().getString(R.string.science),
            MainActivity.con.getResources().getString(R.string.health)};
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


                case 7:

                        Log.i("Goin' in? ", "yes");
                        return new PageFragmentscience();

                case 8:

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