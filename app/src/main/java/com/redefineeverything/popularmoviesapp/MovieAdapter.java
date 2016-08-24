package com.redefineeverything.popularmoviesapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by IainForrest on 19/08/2016.
 */
public class MovieAdapter extends ArrayAdapter {
    private Context mContext;
    private final int NUM_OF_COLUMNS = getContext().getResources().getInteger(R.integer.num_columns);
    private int mWidth;
    private int mHeight;


    public MovieAdapter (Context context, ArrayList<Movie> movies){
        super(context,0,movies);
        mContext = context;
        mWidth= Utils.getAreaWidth(context);
        mHeight = Utils.getImageHeight(mWidth);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout,parent,false);
            holder = new ViewHolder();
            holder.rootLayout = (RelativeLayout) convertView.findViewById(R.id.root_layout);

            holder.thumbImage = (ImageView) convertView.findViewById(R.id.list_item_imageview);
            holder.overlayInfo = (RelativeLayout) convertView.findViewById(R.id.overlay_info);
            holder.title = (TextView) convertView.findViewById(R.id.movie_title);
            holder.score = (TextView) convertView.findViewById(R.id.score);
            holder.releaseDate = (TextView) convertView.findViewById(R.id.release_date);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.progress_bar);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Movie currentMovie = (Movie) getItem(position);

        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(getContext())
                .load(currentMovie.getImagePath())
                .error(R.drawable.movie_placeholder)
                .centerCrop().resize(mWidth,mHeight)
                .into(holder.thumbImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
        holder.rootLayout.getLayoutParams().height = mHeight;
        holder.rootLayout.getLayoutParams().width = mWidth;

        holder.title.setText(currentMovie.getTitle());
        holder.score.setText(Double.toString(currentMovie.getScore()));
        holder.releaseDate.setText(currentMovie.getFormattedReleaseDate());

        holder.overlayInfo.setVisibility(View.GONE);


        return convertView;
    }


    static class ViewHolder {
        private RelativeLayout rootLayout;
        private ImageView thumbImage;
        private TextView title;
        private RelativeLayout overlayInfo;
        private TextView score;
        private TextView releaseDate;
        private ProgressBar progressBar;
    }
}
