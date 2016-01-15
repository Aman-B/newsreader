package com.amanb.aman.feedreader;

import com.amanb.aman.feedreader.myadapter.postData;

/**
 * Created by aman on 26/12/15.
 */
public interface OnAsyncTaskCompleted {
    void onAsyncTaskCompleted(postData[] listData);

    void onAsyncTaskInComplete(postData[] listData);
}
