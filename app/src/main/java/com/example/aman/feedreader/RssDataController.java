package com.example.aman.feedreader;

import android.app.ProgressDialog;
import android.appwidget.AppWidgetHost;
import android.graphics.Bitmap;
import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;


import com.example.aman.feedreader.myadapter.DownloadImages;

import com.example.aman.feedreader.myadapter.postData;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Aman  on 10/28/2015.
 */
public class RssDataController extends
        AsyncTask <String,Integer,ArrayList> {

    //Arraylist for db
   public static ArrayList db_data;

    //saving image urls
    public static String[] image_urls = new String[10];

    //save returned images from DownloadImages
  public static  Bitmap[] got_images= new Bitmap[10];

    private MainActivity.RSSXMLTag currentTag;
    public static postData[] listData;
    int i=0;
    int img_index=0;
    public ProgressDialog ringProgressDialog;

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);


    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();


    /* ringProgressDialog= ProgressDialog.show(MainActivity.con,"Please wait...","Downloading stuff", true);

        ringProgressDialog.setCancelable(true);*/




    }

    @Override
    protected ArrayList doInBackground(String... params) {
// TODO Auto-generated method stub
        String urlStr = params[0];
        InputStream is = null;
        ArrayList postDataList = new ArrayList();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setReadTimeout(10 * 1000);
            connection.setConnectTimeout(10 * 1000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int response = connection.getResponseCode();
            Log.d("debug", "The response is : " + response);
            is = connection.getInputStream();

// parse xml after getting the data
            XmlPullParserFactory factory = XmlPullParserFactory
                    .newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(is, null);

            int eventType = xpp.getEventType();
            postData pdData = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, DD MMM yyyy HH:MM:SS");
            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if (eventType == XmlPullParser.START_DOCUMENT)
                {

                }
                else if (eventType == XmlPullParser.START_TAG)
                {
                    Log.i("Name : ",xpp.getName());
                    if (xpp.getName().equals("item"))
                    {
                        pdData = new postData();
                        currentTag = MainActivity.RSSXMLTag.IGNORETAG;
                    }
                    else if (xpp.getName().equals("title"))
                    {
                        currentTag = MainActivity.RSSXMLTag.TITLE;
                    }
                    else if (xpp.getName().equals("link"))
                    {
                        currentTag = MainActivity.RSSXMLTag.LINK;
                    }
                    else if (xpp.getName().equals("pubDate"))
                    {

                        currentTag = MainActivity.RSSXMLTag.DATE;
                    }
                    else if(xpp.getName().equalsIgnoreCase(("img")))
                    {
                        currentTag=MainActivity.RSSXMLTag.IMAGE;
                        Log.d("Image","found");
                    }
                    else if(xpp.getName().equals("description"))
                    {
                        currentTag=MainActivity.RSSXMLTag.DESC;
                    }
                }
                else if (eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equals("item"))
                    {
// format the data here, otherwise format data in
// Adapter
                        Date postDate = dateFormat.parse(pdData.postDate);
                        pdData.postDate = dateFormat.format(postDate);
                        postDataList.add(pdData);
                    }
                    else
                    {
                        currentTag = MainActivity.RSSXMLTag.IGNORETAG;
                    }
                }


                else if (eventType == XmlPullParser.TEXT)
                {

                    String content = xpp.getText();
                    content = content.trim();
                 //   Log.d("Content ","content"+(i++)+content);
               //     Log.d("debug", content);
                    if (pdData != null)
                    {
                        switch (currentTag)
                        {
                            case DESC:
                                if((content.length()>0) &&(content.contains("<img src")))
                                { //fetching img url from inside description tag...

                                    String closer_to_url=getSubString(content, "alt=", "src");
                                    String almost_got_imgurl=closer_to_url.substring(closer_to_url.indexOf("//"));
                                    Log.i("almost url",almost_got_imgurl);
                                    String imgurl= almost_got_imgurl.substring(almost_got_imgurl.indexOf("//"),almost_got_imgurl.indexOf("\""));
                                    imgurl="https:"+imgurl;
                                    Log.i("new url",imgurl);


                                    //save image urls
                                    if(img_index<10)
                                    {
                                        image_urls[img_index++] = imgurl;
                                    }
                                    if(pdData.postThumbUrl!=null)
                                    {
                                        pdData.postThumbUrl+=imgurl;
                                    }
                                    else
                                    {
                                        pdData.postThumbUrl=imgurl;
                                    }
                                }
                                else
                                {
                                    if(img_index<10)
                                    {
                                        image_urls[img_index++] = null;
                                    }
                                }



                                break;



                            case IMAGE:
                                if(content.length()!=0)
                                {
                                    if(pdData.postThumbUrl!=null)
                                    {
                                        Log.d("Image url:", content);
                                    }
                                    else
                                        Log.d("Image url:", content);
                                }

                            case TITLE:
                                if (content.length() != 0)
                                {
                                    if (pdData.postTitle != null)
                                    {
                                        pdData.postTitle += content;
                                    } else
                                    {
                                        pdData.postTitle = content;
                                    }
                                }
                                break;
                            case LINK:
                                if (content.length() != 0)
                                {
                                    if (pdData.postLink != null)
                                    {
                                        pdData.postLink += content;
                                        Log.d("link", content);
                                    } else
                                    {
                                        pdData.postLink = content;
                                        Log.d("link", content);
                                    }
                                }
                                break;

                            case DATE:
                                if (content.length() != 0)
                                {
                                    if (pdData.postDate != null)
                                    {
                                        pdData.postDate += content;
                                    } else
                                    {
                                        pdData.postDate = content;
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }

                eventType = xpp.next();
            }
          //  Log.v("tst", String.valueOf((postDataList.size())));
            connection.disconnect();
        } catch (MalformedURLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
      //  Log.i("Lel ", MainActivity.con + "---" + R.layout.postitem + "--  " + listData);

        return postDataList;
    }

    private String getSubString(String mainString, String  lastString, String startString) {
        String endString = "";
        int endIndex = mainString.indexOf(lastString);
        int startIndex = mainString.indexOf(startString);
        //Log.d("message" + i++, "" + mainString.substring(startIndex, endIndex));
        endString = mainString.substring(startIndex, endIndex);
        //Log.d("Here: ", endString);
        return endString;

    }


    @Override
    protected void onPostExecute(ArrayList result) {
// TODO Auto-generated method stub

        db_data=result;
      //  Log.i("Lel ", MainActivity.con+"---"+R.layout.postitem+"---"+listData);

        DownloadImages downimgs = new DownloadImages();
        try { got_images=downimgs.execute(image_urls).get();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("Insider:2","yes");
       /* if(downimgs.getStatus().equals(AsyncTask.Status.FINISHED))*/ {

            //Log.i("Insider:3", "yes");
            listData = new postData[10];

            for (int i = 0; i < result.size(); i++) {

              //  System.out.println("data : " + result.get(i).toString());
                listData[i] = (postData) result.get(i);

               // System.out.println("data : " + result.get(i).toString());
            }


        //  PostItemAdapter itemAdapter = new PostItemAdapter(MainActivity.con, R.layout.postitem, listData);

            //set adapter here and above
          //  MainActivity.my_listview.setAdapter(itemAdapter);



           // MyProgressDialog.ringProgressDialog.dismiss();

            //TODO : Check this part later
            /*Dataentry de = new Dataentry();
            de.execute(RssDataController.db_data);*/




        }

       //
   //MyProgressDialog. ringProgressDialog.dismiss();

        //finish
        Log.i("Again?","yes");
        MainActivity.viewPager.setAdapter(new SampleFragmentPagerAdapter(MainActivity.spa,MainActivity.con));


    }




}