package com.amanb.aman.feedreader;

/**
 * Created by aman on 23/12/15.
 */
public interface IShowedFragment {

    public void onShowedFragment(String activity);
    public void showAlreadySavedData();
    public void executeRSS();
    public void setUpAdapterWithData();
    public void retryDataSetting();
    public void waitAndSetData();


}
