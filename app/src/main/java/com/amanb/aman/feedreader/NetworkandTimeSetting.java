package com.amanb.aman.feedreader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by aman on 24/12/15.
 */

public class NetworkandTimeSetting {
    public  static boolean isOnline=false;
    public  Context context;

    public static String getNetworkClass(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info==null || !info.isConnected())
            return "-"; //not connected
        if(info.getType() == ConnectivityManager.TYPE_WIFI)
            return "WIFI";
        if(info.getType() == ConnectivityManager.TYPE_MOBILE){
            int networkType = info.getSubtype();
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                    return "2G";
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                    return "3G";
                case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                    return "4G";
                default:
                    return "?";
            }
        }
        return "?";
    }

    public boolean isOnline(Context con) {
        context=con;
        ConnectivityManager cm =
                (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        isOnline=netInfo!=null&&netInfo.isConnected();
        return netInfo != null && netInfo.isConnected();
    }

    public void setTimeValues() {

        switch(getNetworkClass(context))
        {
            case "WIFI":
                MainActivity.rsstime_out=10000;
                MainActivity.wait_time=4000;
                break;
            case "2G":
                MainActivity.rsstime_out=20000;
                MainActivity.wait_time=7000;
                break;
            case"3G":
                MainActivity.rsstime_out=10000;
                MainActivity.wait_time=5000;
                break;
            case "4G":
                MainActivity.rsstime_out=10000;
                MainActivity.wait_time=4000;
                break;
            default:
                MainActivity.rsstime_out=10000;
                MainActivity.wait_time=6000;
                break;
        }

    }
}
