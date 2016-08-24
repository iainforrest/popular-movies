package com.redefineeverything.popularmoviesapp;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Movie currentMovie = getIntent().getExtras().getParcelable("Movie");

        int mWidth = Utils.getAreaWidth(this);

        TextView title = (TextView) findViewById(R.id.title);
        ImageView thumbnail = (ImageView) findViewById(R.id.thumbnail);
        TextView releaseDate = (TextView) findViewById(R.id.release_date);
        TextView genres = (TextView) findViewById(R.id.genres);
        TextView score = (TextView) findViewById(R.id.score);

        // switches the position of the description depending on screen
        // rotation so that it is visible in landscape

        TextView description = getOrientationSpecificDescription();

        title.setText(currentMovie.getTitle());
        Picasso.with(this).load(currentMovie.getImagePath()).into(thumbnail);
        Picasso.with(this)
                .load(currentMovie.getImagePath())
                .placeholder(R.drawable.movie_placeholder)
                .centerCrop().resize(mWidth,Utils.getImageHeight(mWidth))
                .into(thumbnail);
        releaseDate.setText(currentMovie.getFormattedReleaseDate());
        genres.setText(currentMovie.getGenres());
        score.setText(currentMovie.getFormattedScore());
        description.setText(currentMovie.getOverview());

    }



    private TextView getOrientationSpecificDescription(){
        TextView descriptionPortrait = (TextView) findViewById(R.id.description_portrait);
        TextView descriptionLandscape = (TextView) findViewById(R.id.description_landscape);

        switch (this.getResources().getConfiguration().orientation){
            case Configuration.ORIENTATION_LANDSCAPE:
                descriptionLandscape.setVisibility(View.VISIBLE);
                descriptionPortrait.setVisibility(View.GONE);
                return descriptionLandscape;
            case Configuration.ORIENTATION_PORTRAIT:
                descriptionLandscape.setVisibility(View.GONE);
                descriptionPortrait.setVisibility(View.VISIBLE);
                return descriptionPortrait;
            default:
                return descriptionPortrait;
        }
    }
}
