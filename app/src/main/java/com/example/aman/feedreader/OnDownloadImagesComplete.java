package com.example.aman.feedreader;

import android.graphics.Bitmap;

/**
 * Created by aman on 4/1/16.
 */
public interface OnDownloadImagesComplete {

    void OnDownloadImagesCompleted(Bitmap[] bmp);

    void OnDownloadImagesInComplete();


}
