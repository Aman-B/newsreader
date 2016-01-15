package com.amanb.aman.feedreader.fragments;

import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amanb.aman.feedreader.IShowedFragment;
import com.amanb.aman.feedreader.MainActivity;
import com.amanb.aman.feedreader.R;
import com.amanb.aman.feedreader.myadapter.postData;

/**
 * Created by aman on 11/12/15.
 */
// In this case, the fragment displays simple text based on the page
public class PageFragment extends Fragment implements IShowedFragment{
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    public postData[] newsDetailses=new postData[10];



    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDetach() {
        super.onDetach();


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

    }

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




            Toast.makeText(MainActivity.con,"No adapter for you.",Toast.LENGTH_SHORT).show();




        return view;
    }

    @Override
    public void onShowedFragment(String activity) {

    }

    @Override
    public void showAlreadySavedData() {

    }

    @Override
    public void executeRSS() {

    }

    @Override
    public void setUpAdapterWithData() {

    }

    @Override
    public void retryDataSetting() {

    }

    @Override
    public void waitAndSetData() {

    }
}
