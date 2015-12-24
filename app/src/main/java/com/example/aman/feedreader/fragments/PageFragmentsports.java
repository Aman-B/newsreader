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
import com.example.aman.feedreader.myadapter.CardAdapter;
import com.example.aman.feedreader.myadapter.postData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 11/12/15.
 */
// In this case, the fragment displays simple text based on the page
public class PageFragmentsports extends Fragment implements IShowedFragment{
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    public postData[] newsDetailses=new postData[10];




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_page, container, false);


     /*   Log.i("container:"," "+container);
        Log.i("View:"," "+view);*/

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(MainActivity.con);
        mRecyclerView.setLayoutManager(mLayoutManager);



        //assiging listData

    return view;
    }

    @Override
    public void onShowedFragment() {


        if(MainActivity.RSS_done[4]==0) {
            RssDataController rc = new RssDataController();
            rc.execute("http://news.google.co.in/news?cf=all&hl=en&pz=1&ned=in&topic=s&output=rss","sports");

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if ((MainActivity.sp_listData != null)) {

                        Log.i("Here are you sports? ", "yes");

                        newsDetailses = MainActivity.sp_listData;
                        mAdapter = new CardAdapter(newsDetailses, "sports");
                        mRecyclerView.setAdapter(mAdapter);

                        MainActivity.RSS_done[4] = 1;
                        //finished
                      MainActivity.viewPager.setVisibility(View.VISIBLE);

                    } else {
                        Log.i("Here are you sports2? ", "yes");
                        Toast.makeText(MainActivity.con, "No adapter for you.", Toast.LENGTH_SHORT).show();

                    }
                }

            }, MainActivity.wait_time);


        }
        else{
            Toast.makeText(MainActivity.con, "Inside showed fragment.", Toast.LENGTH_SHORT).show();

            newsDetailses = MainActivity.sp_listData;
            mAdapter = new CardAdapter(newsDetailses, "sports");
            mRecyclerView.setAdapter(mAdapter);
            MainActivity.viewPager.setVisibility(View.VISIBLE);
        }

    }
}
