package com.example.aman.feedreader.fragments;

import android.widget.Toast;

import com.example.aman.feedreader.MainActivity;
import com.example.aman.feedreader.myadapter.postData;

/**
 * Created by aman on 27/12/15.
 */
public class ShowError {
    public void show() {
        Toast.makeText(MainActivity.con, "Error occured. Try refreshing.", Toast.LENGTH_SHORT).show();

    }
}
