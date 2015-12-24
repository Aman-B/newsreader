package com.example.aman.feedreader;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.tech.IsoDep;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aman.feedreader.myadapter.postData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {

    //lists of news;
    public  static  postData[] w_listData=null;
    public  static  postData[] n_listData=null;
    public static  postData[] b_listData=null;
    public static  postData[] sp_listData=null;
    public static  postData[] sc_listData=null;
    public static  postData[] t_listData=null;
    public static  postData[] e_listData=null;
    public static  postData[] h_listData=null;

    //save returned images from DownloadImages
    public static  Bitmap[] w_got_images= new Bitmap[10];
    public static  Bitmap[] b_got_images= new Bitmap[10];
    public static  Bitmap[] sp_got_images= new Bitmap[10];
    public static  Bitmap[] sc_got_images= new Bitmap[10];


    public static  Bitmap[] n_got_images= new Bitmap[10];
    public static  Bitmap[] t_got_images= new Bitmap[10];
    public static  Bitmap[] e_got_images= new Bitmap[10];
    public static  Bitmap[] h_got_images= new Bitmap[10];



    public static Context con;
    public static int async_rssfinished= 0;
    public static ProgressBar pb;
   public static ViewPager viewPager;

   public static TabLayout tabLayout;
    public static android.support.v4.app.FragmentManager spa;
    public static String news_type,news_type2;
    public  static SampleFragmentPagerAdapter sampleFragmentPagerAdapter;
    public static int RSS_lock;
    public static int [] RSS_done = new int[8];


    public static String net_type;
    public static int rsstime_out, wait_time;

    public enum RSSXMLTag {
        TITLE, DATE, LINK, CONTENT, GUID, IGNORETAG, RSSXMLTag,IMAGE,DESC;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //checkIfOnlineAndLaunchRss();
        con = getApplicationContext();  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pb=(ProgressBar) findViewById(R.id.pbr);
        spa=getSupportFragmentManager();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        viewPager = (ViewPager) findViewById(R.id.viewpager);
         tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

NetworkandTimeSetting nts = new NetworkandTimeSetting();

        if(nts.isOnline())
        {

            String type= nts.getNetworkClass(con);
            nts.setTimeValues();
            Toast.makeText(con,"Network type: "+type,Toast.LENGTH_SHORT).show();
            Log.i("Network type : ", " "+type);


            setupnews();


        }   else
        {
            Toast.makeText(con, "Damn! No internet connection. ", Toast.LENGTH_SHORT).show();
            pb.setVisibility(View.GONE);
        }


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



    }

    public  void setupnews() {
        // Get the ViewPager and set it's PagerAdapter so that it can display items


         sampleFragmentPagerAdapter = new
                SampleFragmentPagerAdapter(getSupportFragmentManager(),MainActivity.this);


        viewPager.setAdapter(sampleFragmentPagerAdapter);

        viewPager.addOnPageChangeListener(this);
            viewPager.setOffscreenPageLimit(1);

        // Give the TabLayout the ViewPager

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);

       /* viewPager.setVisibility(View.GONE);*/



        news_type="world";
        RSS_lock=1;
       /* checkIfOnlineAndLaunchRss();
*/


    }

    private void checkIfOnlineAndLaunchRss() {


        {
            RssDataController rc = new RssDataController();
            switch(news_type)
            {
                case "world":
                    rc.execute("http://news.google.co.in/news?cf=all&hl=en&pz=1&ned=in&topic=w&output=rss");
                    break;

                case "nation":
                    Log.i("On nation selected"," yes");
                    rc.execute("http://news.google.co.in/news?cf=all&hl=en&pz=1&ned=in&topic=n&output=rss");
                    break;

                case "busy":
                    rc.execute("http://news.google.co.in/news?cf=all&hl=en&pz=1&ned=in&topic=b&output=rss");
                    break;

                case "tech":
                    rc.execute("http://news.google.co.in/news?cf=all&hl=en&pz=1&ned=in&topic=tc&output=rss");
                    break;

                case "sports":
                    rc.execute("http://news.google.co.in/news?cf=all&hl=en&pz=1&ned=in&topic=s&output=rss");
                    break;

                case "enter":
                    rc.execute("http://news.google.co.in/news?cf=all&hl=en&pz=1&ned=in&topic=e&output=rss");
                    break;

                case "science":
                    break;

                case "health":
                    rc.execute("http://news.google.co.in/news?cf=all&hl=en&pz=1&ned=in&topic=m&output=rss");
                    break;
            /*.execute("http://news.google.co.in/news?cf=all&hl=hi&ned=hi_in&output=rss");*/
            }
        }

        {
            Toast.makeText(con, "Damn! No internet connection. ", Toast.LENGTH_SHORT).show();
            pb.setVisibility(View.GONE);
        }
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

@Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
// Page change listener
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position==1)
        {
            //sampleFragmentPagerAdapter.notifyDataSetChanged();

          //  Toast.makeText(con,"Page : "+(position+1),Toast.LENGTH_SHORT).show();
        }
        viewPager.setVisibility(View.GONE);
        Fragment fragment = (Fragment) sampleFragmentPagerAdapter.instantiateItem(viewPager,position);
        if(fragment instanceof IShowedFragment)
        {
            ((IShowedFragment) fragment).onShowedFragment();
        }


/*
        RSS_lock=position+1;
*/
       /* pb.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.GONE);*/






    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

//tab layout methods
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        viewPager.setCurrentItem(tab.getPosition());
      /*  if((viewPager.getVisibility()==View.VISIBLE)&&(RSS_done[tab.getPosition()]!=1)){
            viewPager.setVisibility(View.GONE);
            pb.setVisibility(View.VISIBLE);
            RSS_lock=tab.getPosition()+1;
        }
        else{
            viewPager.setVisibility(View.VISIBLE);
            pb.setVisibility(View.GONE);
        }*/
/*
        Toast.makeText(con,"Tab selected",Toast.LENGTH_SHORT).show();
*/


/*
        switch(tab.getPosition())
        {
            case 0 :
                Log.i("On tab selected"," "+(tab.getPosition()+1));
                news_type="world";
                viewPager.setVisibility(View.GONE);
                checkIfOnlineAndLaunchRss();
                break;

            case 1:
                Log.i("On tab selected"," "+(tab.getPosition()+1));
                news_type="nation";
                viewPager.setVisibility(View.GONE);
                checkIfOnlineAndLaunchRss();
                break;

            case 2 :

                news_type="busy";
                viewPager.setVisibility(View.GONE);
                checkIfOnlineAndLaunchRss();

                break;

            case 3 :
                news_type="tech";
                viewPager.setVisibility(View.GONE);
                checkIfOnlineAndLaunchRss();
                break;

            case 4 :
                news_type="sports";
                viewPager.setVisibility(View.GONE);
                checkIfOnlineAndLaunchRss();

            case 5 :
                news_type="enter";
                viewPager.setVisibility(View.GONE);
                checkIfOnlineAndLaunchRss();
                break;

            case 6 :
                news_type="science";
                viewPager.setVisibility(View.GONE);
                checkIfOnlineAndLaunchRss();

            case 7 :
                news_type="health";
                viewPager.setVisibility(View.GONE);
                checkIfOnlineAndLaunchRss();
                break;
        }
*/


    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

        if((tab.getText()=="Worldwide")&&(tab.getText()!=null))
        {
           Toast.makeText(con,"Did nothing",Toast.LENGTH_SHORT).show();
        }

    }




}