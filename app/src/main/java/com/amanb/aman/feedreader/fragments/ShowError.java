package com.amanb.aman.feedreader.fragments;

import android.widget.Toast;

import com.amanb.aman.feedreader.MainActivity;

/**
 * Created by aman on 27/12/15.
 */
public class ShowError {
    public void show() {
        Toast.makeText(MainActivity.con, "Error occured. Try refreshing.", Toast.LENGTH_SHORT).show();

    }
}
