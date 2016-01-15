package com.amanb.aman.feedreader.myadapter;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amanb.aman.feedreader.MainActivity;
import com.amanb.aman.feedreader.OnDownloadImagesComplete;
import com.amanb.aman.feedreader.R;
import com.squareup.picasso.Picasso;

/**
 * Created by aman on 12/12/15.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> implements OnDownloadImagesComplete {

    public postData[] c_newsDetailses;
    private static final String EXTRA_CUSTOM_TABS_SESSION = "android.support.customtabs.extra.SESSION";

    private static final String KEY_CUSTOM_TABS_ICON = "android.support.customtabs.customaction.ICON";
    // Key that specifies the PendingIntent to launch when the action button
// or menu item was tapped. Chrome will be calling PendingIntent#send() on
// taps after adding the url as data. The client app can call
// Intent#getDataString() to get the url.
    public static final String KEY_CUSTOM_TABS_PENDING_INTENT = "android.support.customtabs.customaction.PENDING_INTENT";
    /* Optional. Use a bundle for parameters if an the action button is specified.*/
    public static final String EXTRA_CUSTOM_TABS_ACTION_BUTTON_BUNDLE =
            "android.support.customtabs.extra.ACTION_BUNDLE_BUTTON";

    public static final String EXTRA_CUSTOM_TABS_EXIT_ANIMATION_BUNDLE =
            "android.support.customtabs.extra.EXIT_ANIMATION_BUNDLE";

    public static final String EXTRA_CLOSE_BUTTON_ICON =
            "android.support.customtabs.extra.CLOSE_BUTTON_ICON";

    public String c_newstype;
    public CardAdapter(postData[] newsDetailses,String news_type) {
        super();
        c_newsDetailses=newsDetailses;
        c_newstype=news_type;



    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("Here are youc? ", "yes");
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_card_item,parent,false);

        ViewHolder viewHolder=new ViewHolder(v);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        try {
            if (c_newsDetailses[position].postThumbUrl != null) {

                Picasso.with(MainActivity.con).load(c_newsDetailses[position].postThumbUrl).into(holder.n_image);
             /*   switch (c_newstype)

                {

                    case "high":
                        holder.n_image.setImageBitmap(MainActivity.hh_got_images[position]);
                        break;

                    case "world":
                        holder.n_image.setImageBitmap(MainActivity.w_got_images[position]);
                        break;

                    case "nation":
                        holder.n_image.setImageBitmap(MainActivity.n_got_images[position]);
                        break;

                    case "busy":
                        holder.n_image.setImageBitmap(MainActivity.b_got_images[position]);
                        break;

                    case "tech":
                        holder.n_image.setImageBitmap(MainActivity.t_got_images[position]);
                        break;

                    case "sports":
                        holder.n_image.setImageBitmap(MainActivity.sp_got_images[position]);
                        break;

                    case "enter":
                        holder.n_image.setImageBitmap(MainActivity.e_got_images[position]);
                        break;

                    case "science":
                        holder.n_image.setImageBitmap(MainActivity.sc_got_images[position]);
                        break;

                    case "health":
                        holder.n_image.setImageBitmap(MainActivity.h_got_images[position]);
                        break;

                }*/

            }




        }
        catch (NullPointerException npe)
        {
            Log.d("Exception","caught npe");


            holder.n_image.setImageResource(R.mipmap.images);

        }
       /* try{*/
            holder.n_headline.setText(c_newsDetailses[position].postTitle);
            holder.n_date.setText(c_newsDetailses[position].postDate);
      /*  }
        catch(NullPointerException npe)
        {*/

      /*  }*/

    }

    @Override
    public int getItemCount() {
        return c_newsDetailses.length;
    }

    @Override
    public void OnDownloadImagesCompleted(Bitmap[] bmp) {
        MainActivity.w_got_images=bmp;
        showImages(bmp);
    }

    private void showImages(Bitmap[] bmp) {
        for(int i=0;i<10;i++)
        {

        }
    }

    @Override
    public void OnDownloadImagesInComplete() {
        Log.i("Downloading images"," failed.");

    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView n_date;

        public TextView n_headline;




        public ImageView  n_image;

        public ViewHolder(View itemView) {
            super(itemView);

            n_image=(ImageView) itemView.findViewById(R.id.news_thumbnail);

            n_headline=(TextView)itemView.findViewById(R.id.news_headline);

            n_date=(TextView)itemView.findViewById(R.id.news_date);
            itemView.setOnClickListener(this);


        }



        @Override
        public void onClick(View v) {

            int position =getAdapterPosition();
            Intent browserIntent;
         //   Toast.makeText(MainActivity.con,"Clicked: "+position,Toast.LENGTH_SHORT).show();
            try{  browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(c_newsDetailses[position].postLink));

            }
            catch (NullPointerException npe)
            {
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://news.google.co.in/news"));
            }



            Bundle extras = new Bundle();
            extras.putBinder(EXTRA_CUSTOM_TABS_SESSION,
                    null /* Set to null for no session */);
           browserIntent.putExtras(extras);

            Bundle actionButtonBundle = new Bundle();
            /*actionButtonBundle.putParcelable(KEY_CUSTOM_TABS_ICON, "");
            actionButtonBundle.putParcelable(KEY_CUSTOM_TABS_PENDING_INTENT, pendingIntent);*/
            //browserIntent.putExtra(EXTRA_CUSTOM_TABS_ACTION_BUTTON_BUNDLE, actionButtonBundle);
            Resources resources=MainActivity.con.getResources();
            Bitmap icon = BitmapFactory.decodeResource(resources, R.mipmap.ic_action_back);
            browserIntent.putExtra(EXTRA_CLOSE_BUTTON_ICON,
                   icon);

            //enter and exit animations

            Bundle finishBundle = ActivityOptions.makeCustomAnimation(MainActivity.con, R.anim.slide_in_left, R.anim.slide_out_right).toBundle();
            browserIntent.putExtra(EXTRA_CUSTOM_TABS_EXIT_ANIMATION_BUNDLE, finishBundle);

            Bundle startBundle = ActivityOptions.makeCustomAnimation(MainActivity.con,
                    R.anim.slide_in_right, R.anim.slide_out_left).toBundle();

            browserIntent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", Color.parseColor("#0097A7"));
            browserIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            browserIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MainActivity.con.startActivity(browserIntent,startBundle);


        }
    }
}
