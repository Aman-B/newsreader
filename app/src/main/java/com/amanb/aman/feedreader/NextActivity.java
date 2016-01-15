package com.amanb.aman.feedreader;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


public class NextActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {

    public ProgressBar pb;
    public static ViewPager viewPager;
   public SampleFragmenth sfh;
public Context con;
    public TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pb=(ProgressBar) findViewById(R.id.pbr);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();






        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        con= getApplicationContext();

        NetworkandTimeSetting nts = new NetworkandTimeSetting();

        if(nts.isOnline(con))
        {

            String type= nts.getNetworkClass(MainActivity.con);
            nts.setTimeValues();
            // Toast.makeText(con,"Network type: "+type,Toast.LENGTH_SHORT).show();
            Log.i("Network type : ", " "+type);


            setupnews();


        }   else
        {
            Toast.makeText(MainActivity.con, " No internet connection. ", Toast.LENGTH_SHORT).show();
            pb.setVisibility(View.GONE);
        }



    }
    public  void setupnews() {
        sfh= new SampleFragmenth(getSupportFragmentManager(),NextActivity.this);

        viewPager.setAdapter(sfh);
        tabLayout.setupWithViewPager(viewPager);


        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(1);


        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);

        viewPager.setVisibility(View.GONE);
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Fragment fragment = (Fragment) sfh.instantiateItem(viewPager, 0);


                ((IShowedFragment) fragment).onShowedFragment("next");
            }
        }, 2000);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_select_language) {

            //handle language change settings

            DialogCreator dc = new DialogCreator();
            dc.createDialog(NextActivity.this);
            item.setCheckable(true);
            item.setChecked(false);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {


        viewPager.setVisibility(View.GONE);
        Fragment fragment = (Fragment) sfh.instantiateItem(viewPager,position);
        if(fragment instanceof IShowedFragment)
        {

            ((IShowedFragment) fragment).onShowedFragment("next");
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {

            NetworkandTimeSetting nts = new NetworkandTimeSetting();

            if(nts.isOnline(con))
            {

                String type= nts.getNetworkClass(MainActivity.con);
                nts.setTimeValues();
                Toast.makeText(MainActivity.con, "Network type: " + type, Toast.LENGTH_SHORT).show();
                Log.i("Network type : ", " " + type);


                setupnews();


            }   else
            {
                Toast.makeText(MainActivity.con, " No internet connection. ", Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.GONE);
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
