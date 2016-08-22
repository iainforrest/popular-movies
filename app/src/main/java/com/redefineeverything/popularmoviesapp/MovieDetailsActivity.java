package com.redefineeverything.popularmoviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        TextView description = (TextView) findViewById(R.id.description);

        title.setText(currentMovie.getTitle());
        Picasso.with(this).load(currentMovie.getImagePath()).into(thumbnail);
        Picasso.with(this)
                .load(currentMovie.getImagePath())
                .placeholder(R.drawable.movie_placeholder)
                .centerCrop().resize(mWidth,Utils.getImageHeight(mWidth))
                .into(thumbnail);
        releaseDate.setText(currentMovie.getFormattedReleaseDate());
        genres.setText(currentMovie.getmGenres());
        score.setText(currentMovie.getFormattedScore());
        description.setText(currentMovie.getOverview());

    }
}
