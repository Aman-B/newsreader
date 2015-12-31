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
import com.example.aman.feedreader.myadapter.CardAdapter;
import com.example.aman.feedreader.myadapter.postData;

/**
 * Created by aman on 11/12/15.
 */
// In this case, the fragment displays simple text based on the page
public class PageFragmentsports extends Fragment implements IShowedFragment, OnAsyncTaskCompleted {
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
    public void onShowedFragment(String activity) {

        calling_activity=activity;

        if(MainActivity.RSS_done[4]==0) {
            executeRSS();


           // waitAndSetData();


        }
        else{
           showAlreadySavedData();
        }

    }

    @Override
    public void showAlreadySavedData() {
        if(MainActivity.lang.equals(prev_lang)) {

            setUpAdapterWithData();
        }
        else
        {
            executeRSS();
           // waitAndSetData();
        }
    }

    @Override
    public void executeRSS() {
        RssDataController rc = new RssDataController(this);
        rc.execute("http://news.google.co.in/news?cf=all&hl="+MainActivity.lang+"&pz=1&ned=in&topic=s&output=rss","sports");
        prev_lang=MainActivity.lang;
    }

    @Override
    public void setUpAdapterWithData() {
        Log.i("Here are you sports? ", "yes");

        newsDetailses = MainActivity.sp_listData;
        mAdapter = new CardAdapter(newsDetailses, "sports");
        mRecyclerView.setAdapter(mAdapter);

        MainActivity.RSS_done[4] = 1;
        //finished
        ShowViewPager showViewPager = new ShowViewPager();
        showViewPager.show(calling_activity);

    }

    @Override
    public void retryDataSetting() {

                if ((MainActivity.sp_listData != null)) {
                    setUpAdapterWithData();
                }
                else
                {
                    Toast.makeText(MainActivity.con, "No connection, try again", Toast.LENGTH_SHORT).show();
                }

    }



    @Override
    public void waitAndSetData() {

                if ((MainActivity.sp_listData != null)) {

                   setUpAdapterWithData();
                } else {
                    retryDataSetting();

                }


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
