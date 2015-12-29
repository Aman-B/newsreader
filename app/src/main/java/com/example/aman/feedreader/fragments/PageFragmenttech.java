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
import com.example.aman.feedreader.OnAsyncTaskCompleted;
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
public class PageFragmenttech extends Fragment implements IShowedFragment, OnAsyncTaskCompleted {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    public postData[] newsDetailses = new postData[10];
    private String prev_lang;


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



            //finished





        return view;
    }

    //TODO: put hindi clause in all other and this fragment too!
    @Override
    public void onShowedFragment() {
        if(MainActivity.RSS_done[3]==0)
        {
            executeRSS();

            //waitAndSetData();



        }

        else{
            showAlreadySavedData();

        }
    }

    @Override
    public void showAlreadySavedData() {
        if(MainActivity.lang.equals(prev_lang)) {
            //TODO : CHECK En-hindi-en condition

            setUpAdapterWithData();
        }
        else
        {
            executeRSS();
           // waitAndSetData();
        }
    }

    public void waitAndSetData() {
      /*  final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {*/

                if((MainActivity.t_listData!=null))
                {
                    setUpAdapterWithData();

                }
                else{
                    retryDataSetting();
                }

     /*       }

        },MainActivity.wait_time);*/


    }

    public void retryDataSetting() {
        /*Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {*/
                if ((MainActivity.t_listData != null)) {
                    setUpAdapterWithData();
                }
                else{
                    Log.i("Here are you tech2? ", "yes");
                    Toast.makeText(MainActivity.con,"No connection, try again.",Toast.LENGTH_SHORT).show();

                }

        /*    }
        },(MainActivity.rsstime_out-MainActivity.wait_time));*/
    }

    public void setUpAdapterWithData() {

       Log.i("Here are you tech? ","yes");

        newsDetailses =MainActivity.t_listData;
        mAdapter = new CardAdapter(newsDetailses,"tech");
        mRecyclerView.setAdapter(mAdapter);

        MainActivity.RSS_done[3]=1;
        MainActivity.viewPager.setVisibility(View.VISIBLE);
    }

    public void executeRSS() {

        RssDataController2 rc = new RssDataController2(this);
      //  rc.execute("http://news.google.co.in/news?cf=all&hl="+MainActivity.lang+"&pz=1&ned=in&topic=tc&output=rss","tech");
        rc.execute("http://news.google.co.in/news?cf=all&hl=en&pz=1&ned=in&topic=tc&output=rss","tech");
        prev_lang=MainActivity.lang;

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
