package com.example.aman.feedreader.myadapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.aman.feedreader.MainActivity;
import com.example.aman.feedreader.RssDataController;
import com.example.aman.feedreader.RssDataController2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by aman on 5/11/15.
 */
public class DownloadImages2 extends AsyncTask <String[],URI,Bitmap[]>{

    static boolean  image= false;
    public static String[] img_urls= new String[10];
    @Override
    protected void onPostExecute(Bitmap[] bitmap) {

        super.onPostExecute(bitmap);

        Log.i("Insider:1", "yes");


        switch (MainActivity.news_type2) {
            case "nation":
                Log.i("Got in?", "n_images " + MainActivity.n_got_images);
                MainActivity.n_got_images =bitmap;

                break;



            case "tech":
                Log.i("Got in?", "t_images " + MainActivity.t_got_images);
                MainActivity.t_got_images =bitmap;

                break;


            case "enter":
                Log.i("Got in?", "e_images " + MainActivity.e_got_images);
                MainActivity.e_got_images =bitmap;

                break;


            case "health":
                Log.i("Got in?", "h_images " + MainActivity.h_got_images);
                MainActivity.h_got_images =bitmap;

                break;

            case "high_hin":
                Log.i("Got in?", "h_images " + MainActivity.hh_got_images);
                MainActivity.hh_got_images=bitmap;
                break;




        }


    }

    @Override
    protected Bitmap[] doInBackground(String[]... strings) {
        URL url = null;
        Bitmap[] bmp = new Bitmap[10];
        img_urls=strings[0];
        for(int i=0;i<10;i++) {
            try {
                if(img_urls[i]!=null) {
                    url = new URL(img_urls[i]);
                }



            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try { if(img_urls[i]!=null) {
                bmp[i] = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            ;
        }

        return bmp;
    }
}
