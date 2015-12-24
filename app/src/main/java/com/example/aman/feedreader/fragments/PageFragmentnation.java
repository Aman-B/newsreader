package com.example.aman.feedreader.fragments;

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
import com.example.aman.feedreader.RssDataController2;
import com.example.aman.feedreader.myadapter.CardAdapter;
import com.example.aman.feedreader.myadapter.postData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 11/12/15.
 */
// In this case, the fragment displays simple text based on the page
public class PageFragmentnation extends Fragment implements IShowedFragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    public postData[] newsDetailses=new postData[10];







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



/*
MainActivity.viewPager.setVisibility(View.GONE);
        MainActivity.pb.setVisibility(View.VISIBLE);*/
      /*   final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {*/
                RssDataController2 rc = new RssDataController2();
                rc.execute("http://news.google.co.in/news?cf=all&hl=en&pz=1&ned=in&topic=n&output=rss", "nation");
        /*    }

        }, 7000);*/
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        Log.i("Got in nation? ", "yes");


     /*   Log.i("container:"," "+container);
        Log.i("View:"," "+view);*/

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(MainActivity.con);
        mRecyclerView.setLayoutManager(mLayoutManager);



        //assiging listData
      /*  final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
      *//*  MainActivity.n_listData=new postData[10];
        for (int i = 0; i < 10; i++) {
            MainActivity.n_listData[i]=(postData)MainActivity.nat.get(i);

        }*//*

                    if ((MainActivity.n_listData != null)) {

                        Log.i("Here are you nation? ", "yes");

                        newsDetailses = MainActivity.n_listData;
                        mAdapter = new CardAdapter(newsDetailses,"nation");
                        mRecyclerView.setAdapter(mAdapter);

                        MainActivity.RSS_done[1]=1;
                        //finished
                        if(MainActivity.RSS_lock==2) {
                            MainActivity.viewPager.setVisibility(View.VISIBLE);
                            MainActivity.pb.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.con, "Cancelled by nation.", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Log.i("Here are you nation2? ", "yes");
                        Toast.makeText(MainActivity.con, "No adapter for you.", Toast.LENGTH_SHORT).show();
                        if(MainActivity.RSS_lock==2) {
                            MainActivity.viewPager.setVisibility(View.VISIBLE);
                            MainActivity.pb.setVisibility(View.GONE);
                        }
                    }

                }
            }, 5000);//11000*/

        return view;
    }

    @Override
    public void onShowedFragment() {
        if(MainActivity.RSS_done[1]==0)
        {
            RssDataController2 rc = new RssDataController2();
            rc.execute("http://news.google.co.in/news?cf=all&hl=en&pz=1&ned=in&topic=n&output=rss", "nation");

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if ((MainActivity.n_listData != null)) {
                        newsDetailses = MainActivity.n_listData;
                        mAdapter = new CardAdapter(newsDetailses, "nation");
                        mRecyclerView.setAdapter(mAdapter);
                        MainActivity.viewPager.setVisibility(View.VISIBLE);
                        MainActivity.RSS_done[1] = 1;
                    }

                    else {
                        Log.i("Here are you nation2? ", "yes");
                        Toast.makeText(MainActivity.con, "No adapter for you.", Toast.LENGTH_SHORT).show();


                    }


            }
        }, MainActivity.wait_time);
        }

        else{
            Toast.makeText(MainActivity.con, "Inside showed fragment.", Toast.LENGTH_SHORT).show();

            newsDetailses = MainActivity.n_listData;
            mAdapter = new CardAdapter(newsDetailses, "nation");
            mRecyclerView.setAdapter(mAdapter);
            MainActivity.viewPager.setVisibility(View.VISIBLE);

        }


    }
}
