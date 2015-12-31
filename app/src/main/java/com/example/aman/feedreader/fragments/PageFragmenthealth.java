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
import com.example.aman.feedreader.RssDataController2;
import com.example.aman.feedreader.myadapter.CardAdapter;
import com.example.aman.feedreader.myadapter.postData;

/**
 * Created by aman on 11/12/15.
 */
// In this case, the fragment displays simple text based on the page
public class PageFragmenthealth extends Fragment implements IShowedFragment, OnAsyncTaskCompleted {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    public postData[] newsDetailses=new postData[10];
    private String prev_lang;
    private String calling_activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_page, container, false);





     /*   Log.i("container:"," "+container);
        Log.i("View:"," "+view);*/

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(MainActivity.con);
        mRecyclerView.setLayoutManager(mLayoutManager);





        return view;
    }

    @Override
    public void onShowedFragment(String activity) {

        calling_activity=activity;
        if(MainActivity.RSS_done[7]==0)
        {

            executeRSS();
            //waitAndSetData();
            //assiging listData

        }

        else{

           showAlreadySavedData();
        }


    }

    @Override
    public void showAlreadySavedData() {
        if (MainActivity.lang.equals(prev_lang)) {

            setUpAdapterWithData();
        } else {
            executeRSS();
            //waitAndSetData();
        }
    }

    @Override
    public void executeRSS() {
        RssDataController2 rc = new RssDataController2(this);
        rc.execute("http://news.google.co.in/news?cf=all&hl="+MainActivity.lang+"&pz=1&ned=in&topic=m&output=rss", "health");
        prev_lang=MainActivity.lang;

    }

    @Override
    public void setUpAdapterWithData() {

        newsDetailses = MainActivity.h_listData;
        mAdapter = new CardAdapter(newsDetailses,"health");
        mRecyclerView.setAdapter(mAdapter);
        MainActivity.RSS_done[7]=1;

        //finished
        ShowViewPager showViewPager = new ShowViewPager();
        showViewPager.show(calling_activity);
        //Toast.makeText(MainActivity.con,"We've arrived",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void retryDataSetting() {
      /*  Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {*/
                if ((MainActivity.h_listData != null)) {
                    setUpAdapterWithData();
                }
                else
                {
                    Toast.makeText(MainActivity.con, "No connection, try again.", Toast.LENGTH_SHORT).show();
                }
    /*        }
        }, (MainActivity.rsstime_out - MainActivity.wait_time));*/

    }

    @Override
    public void waitAndSetData() {
       /* final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {*/
                if((MainActivity.h_listData!=null)) {

                    Log.i("Here are you health? ", "yes");

                    setUpAdapterWithData();

                }
                else{
                   retryDataSetting();
                }
        /*    }

        },MainActivity.wait_time);*/
    }

    @Override
    public void onAsyncTaskCompleted() {
    waitAndSetData();
    }

    @Override
    public void onAsyncTaskInComplete() {
        retryDataSetting();
    }
}
