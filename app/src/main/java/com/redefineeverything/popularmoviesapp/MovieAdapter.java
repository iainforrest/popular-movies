package com.redefineeverything.popularmoviesapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by IainForrest on 19/08/2016.
 */
public class MovieAdapter extends ArrayAdapter {
    private Context mContext;
    private final static Double WIDTH_HEIGHT_RATIO = 1.5;
    private final int NUM_OF_COLUMNS = getContext().getResources().getInteger(R.integer.num_columns);
    private int mWidth;
    private Double mHeight;


    public MovieAdapter (Context context, ArrayList<Movie> movies){
        super(context,0,movies);
        mContext = context;
        mWidth= (getContext().getResources().getDisplayMetrics().widthPixels) / NUM_OF_COLUMNS;
        mHeight = mWidth * WIDTH_HEIGHT_RATIO;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout,parent,false);
            holder = new ViewHolder();
            holder.thumbImage = (ImageView) convertView.findViewById(R.id.list_item_imageview);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Movie currentMovie = (Movie) getItem(position);

        String imageURL = MainActivity.THUMBNAIL_BASE_URL + currentMovie.getImagePath();
        Picasso.with(getContext())
                .load(imageURL)
                .centerCrop().resize(mWidth,mHeight.intValue())
                .into(holder.thumbImage);


        return convertView;
    }


    static class ViewHolder {
        public ImageView thumbImage;
    }
}
