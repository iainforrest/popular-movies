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


    public MovieAdapter (Context context, ArrayList<Movie> movies){
        super(context,0,movies);
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
        Log.d("MovieAdapter","Got to imageloader in Apadter for item : " + Integer.toString(position));
        Picasso.with(getContext()).load(imageURL).into(holder.thumbImage);


        return convertView;
    }


    static class ViewHolder {
        public ImageView thumbImage;
    }
}
