package com.example.aman.feedreader.fragments;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aman.feedreader.IShowedFragment;
import com.example.aman.feedreader.MainActivity;
import com.example.aman.feedreader.R;
import com.example.aman.feedreader.RssDataController;
import com.example.aman.feedreader.myadapter.CardAdapter;
import com.example.aman.feedreader.myadapter.DownloadImages;
import com.example.aman.feedreader.myadapter.postData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by aman on 11/12/15.
 */
// In this case, the fragment displays simple text based on the page
public class PageFragmentworld extends Fragment implements IShowedFragment{
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    static RecyclerView.Adapter mAdapter;
    public postData[] w_newsDetailses=new postData[10];


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
    public void onShowedFragment() {
        if(MainActivity.RSS_done[0]==0) {


            RssDataController rc = new RssDataController();
            rc.execute("http://news.google.co.in/news?cf=all&hl=en&pz=1&ned=in&topic=w&output=rss", "world");
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if ((MainActivity.w_listData != null)) {
                        w_newsDetailses = MainActivity.w_listData;
                        mAdapter = new CardAdapter(w_newsDetailses, "world");
                        mRecyclerView.setAdapter(mAdapter);
                        MainActivity.RSS_done[0] = 1;
                       MainActivity.viewPager.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(MainActivity.con, "No adapter for you.", Toast.LENGTH_SHORT).show();

                    }
                }
            }, MainActivity.wait_time);

        }

        else{
            Toast.makeText(MainActivity.con, "Inside showed fragment.", Toast.LENGTH_SHORT).show();

            w_newsDetailses = MainActivity.w_listData;
            mAdapter = new CardAdapter(w_newsDetailses, "world");
            mRecyclerView.setAdapter(mAdapter);
            MainActivity.viewPager.setVisibility(View.VISIBLE);
        }
    }
}
