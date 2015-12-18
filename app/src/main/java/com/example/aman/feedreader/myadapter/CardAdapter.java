package com.example.aman.feedreader.myadapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aman.feedreader.R;
import com.example.aman.feedreader.RssDataController;

import java.util.List;

/**
 * Created by aman on 12/12/15.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    public postData[] c_newsDetailses;
    public CardAdapter(postData[] newsDetailses) {
        super();
        c_newsDetailses=newsDetailses;


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


        if(c_newsDetailses[position].postThumbUrl!=null)
        {
            holder.n_image.setImageBitmap(RssDataController.got_images[position]);
        }
        else
        {
            holder.n_image.setImageResource(R.mipmap.ic_launcher);
        }
       holder.n_headline.setText(c_newsDetailses[position].postTitle);
        holder.n_date.setText(c_newsDetailses[position].postDate);


    }

    @Override
    public int getItemCount() {
        return c_newsDetailses.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView n_date;

        public TextView n_headline;




        public ImageView  n_image;

        public ViewHolder(View itemView) {
            super(itemView);

            n_image=(ImageView) itemView.findViewById(R.id.news_thumbnail);

            n_headline=(TextView)itemView.findViewById(R.id.news_headline);

            n_date=(TextView)itemView.findViewById(R.id.news_date);


        }

        //TODO: Implement card on click.
        private View.OnClickListener mOnTitleClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }
}
