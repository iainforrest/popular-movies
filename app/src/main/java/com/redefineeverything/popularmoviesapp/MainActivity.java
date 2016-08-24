package com.redefineeverything.popularmoviesapp;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MovieAdapter mMovieAdapter;

    //used to hide details overlay on main screen when new movie selected
    View mPreviousView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gridView = (GridView) findViewById(R.id.main_gridview);
        mMovieAdapter = new MovieAdapter(this, new ArrayList<Movie>());
        gridView.setAdapter(mMovieAdapter);
        gridView.setOnItemClickListener(new MovieItemClick());
        updatePageWithSort(getString(R.string.popular));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menuSortPopularity:
                updatePageWithSort(getString(R.string.popular));
                break;
            case R.id.menuSortRating:
                updatePageWithSort(getString(R.string.top_rated));
                break;
        }

        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_menu,menu);
        return true;
    }

    private void updatePageWithSort(String sortType){
        MovieAsyncTask movieAsyncTask = new MovieAsyncTask();
        movieAsyncTask.execute(sortType);


    }



    private class MovieItemClick implements AdapterView.OnItemClickListener {

        private MovieItemClick(){}

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
            /*
                uses the previous view (see below) and hides the info overlay.
                So that only one is showing at a time.

                problem was this doesn't work if you scroll the previous item off the screen
                before selecting a new one. Solved (not perfectly) by resetting every item to
                have the info hidden in the MovieAdapter.
                */
            if (mPreviousView != null){
                RelativeLayout previousOverlayInfo = (RelativeLayout) mPreviousView.findViewById(R.id.overlay_info);
                previousOverlayInfo.setVisibility(View.GONE);
            }

            //Find the hidden View with movie info and make visible
            RelativeLayout overlayInfo = (RelativeLayout) view.findViewById(R.id.overlay_info);
            overlayInfo.setVisibility(View.VISIBLE);
                /*
                * Set an onclick listener on the hidden info field, so that if the field is
                * tapped then the user is taken to the details screen
                * TODO: replace toast with Intent to send to details screen
                * */
            overlayInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent  = new Intent(MainActivity.this, MovieDetailsActivity.class);
                    intent.putExtra("Movie",(Movie)mMovieAdapter.getItem(i));
                    startActivity(intent);
                }
            });

            //sets this view to be the previous view for the next time an item is tapped
            mPreviousView = view;
        }
    }


    private class MovieAsyncTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        private final String LOG_TAG = MovieAsyncTask.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            toggleProgressBar(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            toggleProgressBar(View.GONE);
            if (movies == null){
                Toast.makeText(MainActivity.this, "Sorry, No Movies found, Check Internet connection and preferences", Toast.LENGTH_SHORT).show();
            }else {
                mMovieAdapter.clear();
                mMovieAdapter.addAll(movies);
            }

        }

        @Override
        protected ArrayList<Movie> doInBackground(String... sortOrder) {

            ArrayList<Movie> allMovies = new ArrayList<Movie>();
            String moviesJSONString;

            URL url = Utils.buildQueryUrl(sortOrder[0]);

            try {
                moviesJSONString = Utils.getJSONfromURL(url);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(LOG_TAG,"Error with HTTP request and receiving JSON : "+ e.getMessage());
                return null;
            }

            try {
                allMovies = Utils.getMoviesFromJSON(moviesJSONString);
            } catch (JSONException e) {
                Log.e(LOG_TAG + " :DoInBackground", "Error Parsing JSON : "+e.getMessage());
                return null;
            }


            return allMovies;
        }


    }

    public void toggleProgressBar(int visibility){
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        progressBar.setVisibility(visibility);

    }
}
