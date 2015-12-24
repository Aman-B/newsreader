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
public class PageFragmentscience extends Fragment implements IShowedFragment{
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





        return view;
    }

    @Override
    public void onShowedFragment() {


            RssDataController rc = new RssDataController();
            rc.execute("http://news.google.co.in/news?cf=all&hl=en&pz=1&ned=in&topic=snc&output=rss", "science");

        if(MainActivity.RSS_done[6]==0)
        {

            //assiging listData
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if((MainActivity.sc_listData!=null)) {

                        Log.i("Here are you science? ","yes");

                        newsDetailses = MainActivity.sc_listData;
                        mAdapter = new CardAdapter(newsDetailses,"science");
                        mRecyclerView.setAdapter(mAdapter);
                        MainActivity.RSS_done[6]=1;

                        //finished
                        MainActivity.viewPager.setVisibility(View.VISIBLE);


                    }
                    else{
                        Log.i("Here are you science2? ", "yes");
                        Toast.makeText(MainActivity.con,"No adapter for you.",Toast.LENGTH_SHORT).show();
                        //finished
                        if(MainActivity.RSS_lock==7) {
                            MainActivity.viewPager.setVisibility(View.VISIBLE);
                            MainActivity.pb.setVisibility(View.GONE);
                        }
                    }
                }

            },MainActivity.wait_time);

        }

        else{
            newsDetailses = MainActivity.sc_listData;
            mAdapter = new CardAdapter(newsDetailses,"science");
            mRecyclerView.setAdapter(mAdapter);
            MainActivity.RSS_done[6]=1;

            //finished
            MainActivity.viewPager.setVisibility(View.VISIBLE);
        }


    }
}
