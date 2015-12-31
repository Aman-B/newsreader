package com.example.aman.feedreader.fragments;

import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aman.feedreader.IShowedFragment;
import com.example.aman.feedreader.MainActivity;
import com.example.aman.feedreader.NextActivity;
import com.example.aman.feedreader.OnAsyncTaskCompleted;
import com.example.aman.feedreader.R;
import com.example.aman.feedreader.RssDataController;
import com.example.aman.feedreader.Sharedpref;
import com.example.aman.feedreader.myadapter.CardAdapter;
import com.example.aman.feedreader.myadapter.postData;

/**
 * Created by aman on 11/12/15.
 */
// In this case, the fragment displays simple text based on the page
public class PageFragmentworld extends Fragment implements IShowedFragment, OnAsyncTaskCompleted {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    static RecyclerView.Adapter mAdapter;
    public postData[] w_newsDetailses=new postData[10];

    public String prev_lang=null;
    public String calling_activity;

   /* @Override
    public void onDestroyView() {
        super.onDestroyView();
        MainActivity.RSS_done[0]=0;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_page, container, false);
        /*MainActivity.viewPager.setVisibility(View.GONE);
        MainActivity.pb.setVisibility(View.VISIBLE);



        MainActivity.pb.setVisibility(View.VISIBLE);*/




        Log.i("got world","yes");



     /*   Log.i("container:"," "+container);
        Log.i("View:"," "+view);*/

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(MainActivity.con);
        mRecyclerView.setLayoutManager(mLayoutManager);



        //assiging listData
     /*   final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {*/
                // Do something after 5s = 5000ms




                   /* if ((MainActivity.w_listData != null)) {
                        Log.i("Got in world? ", "yes");
                        Log.i("Here are you world? ", "yes");

                        w_newsDetailses = MainActivity.w_listData;
                        mAdapter = new CardAdapter(w_newsDetailses, "world");
                        mRecyclerView.setAdapter(mAdapter);
                        MainActivity.RSS_done[0] = 1;

                        //finished
                        if (MainActivity.RSS_lock == 1) {
                            MainActivity.viewPager.setVisibility(View.VISIBLE);
                            MainActivity.pb.setVisibility(View.GONE);
                        }
                    } else if (w_newsDetailses != null) {

                        w_newsDetailses = MainActivity.w_listData;
                       *//* mAdapter = new CardAdapter(w_newsDetailses, "world");
                        mRecyclerView.setAdapter(mAdapter);*//*


                        //finished
                        if (MainActivity.RSS_lock == 1) {
                            MainActivity.viewPager.setVisibility(View.VISIBLE);
                            MainActivity.pb.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.con, "Cancelled by world.", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Log.i("Here are you world2? ", "yes");
                  *//*  MainActivity.viewPager.setVisibility(View.VISIBLE);
                    MainActivity.pb.setVisibility(View.GONE);*//*
                        Toast.makeText(MainActivity.con, "No adapter for you.", Toast.LENGTH_SHORT).show();

                    }*/

        /*    }
        }, 5000);*/





        return view;
    }

    @Override
    public void onShowedFragment(String activity) {
        calling_activity=activity;
        if(MainActivity.RSS_done[0]==0)
        {
            Log.i("got showed","yes");
            executeRSS();

           // waitAndSetData();


        }

        else{
            Log.i("got already","yes");
            showAlreadySavedData();

        }
    }

    public void waitAndSetData() {

        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {*/
                if ((MainActivity.w_listData != null))
                {Log.i("got wait and setup","yes");
                    setUpAdapterWithData();
                }
                else
                {Log.i("got wait and retry","yes");
                    retryDataSetting();
                }
      /*      }
       },MainActivity.wait_time);*/

    }




    public void executeRSS() {
        RssDataController rc = new RssDataController(this);
        rc.execute("http://news.google.co.in/news?cf=all&hl="+MainActivity.lang+"&pz=1&ned=in&topic=w&output=rss", "world");

        Sharedpref sharedpref = new Sharedpref();
/*
        sharedpref.storeInSharedPref(MainActivity.lang,);
*/
       /* prev_lang=sharedpref.readFromSharedPref(MainActivity.lang,((Activity)MainActivity.con));*/
        Log.i("langly1 ", " " + prev_lang);
        Log.i("langly2 ", "" + MainActivity.lang);
    }

    public void showAlreadySavedData() {
      //  Toast.makeText(MainActivity.con, "Inside showed fragment.", Toast.LENGTH_SHORT).show();
        if(MainActivity.lang.equals(prev_lang)) {
            //TODO : CHECK En-hindi-en condition
            Log.i("lang1 "," "+prev_lang);
            Log.i("lang2 ","" + MainActivity.lang);
            setUpAdapterWithData();
        }
        else
        {

            executeRSS();
            //waitAndSetData();
        }
    }


    public void setUpAdapterWithData() {
/*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {*/
//                Log.i("Datam :", " " + MainActivity.w_listData[0].postTitle);
                Log.i("Data :", " " + w_newsDetailses);
                w_newsDetailses = MainActivity.w_listData;
                Log.i("Data :", " " + w_newsDetailses);

                mAdapter = new CardAdapter(w_newsDetailses, "world");
                mRecyclerView.setAdapter(mAdapter);
                MainActivity.RSS_done[0] = 1;
                ShowViewPager showViewPager= new ShowViewPager();
                showViewPager.show(calling_activity);


        /*    }
        }, 0000);
*/


    }

    public void retryDataSetting() {
       /* Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {*/
                if ((MainActivity.w_listData != null)) {
                    setUpAdapterWithData();
                } else {
                    Toast.makeText(MainActivity.con, "No connection, try again.", Toast.LENGTH_SHORT).show();

                        MainActivity.viewPager.setVisibility(View.VISIBLE);

                }
/*
            }
        }, (MainActivity.rsstime_out - MainActivity.wait_time));
*/
    }



    public void onAsyncTaskCompleted(){
waitAndSetData();

    }

    @Override
    public void onAsyncTaskInComplete() {
        retryDataSetting();
    }


}
