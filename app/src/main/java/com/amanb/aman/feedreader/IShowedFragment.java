package com.amanb.aman.feedreader;

import android.content.Context;

/**
 * Created by aman on 23/12/15.
 */
public interface IShowedFragment {

    public void onShowedFragment(String activity, String lang, Context con);
    public void showAlreadySavedData();
    public void executeRSS();
    public void setUpAdapterWithData();
    public void retryDataSetting();
    public void waitAndSetData();


}
