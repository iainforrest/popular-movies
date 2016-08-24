package com.redefineeverything.popularmoviesapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MovieAdapter mMovieAdapter;
    //sortOrder variable chosen to future proof to pass order on activity resume
    String mSortOrder;

    //used to hide details overlay on main screen when new movie selected
    View mPreviousView;


    @Override
    protected void onStart() {
        super.onStart();
        mSortOrder = getString(R.string.popular);
        updatePageWithSort(mSortOrder);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gridView = (GridView) findViewById(R.id.main_gridview);
        mMovieAdapter = new MovieAdapter(this, new ArrayList<Movie>());
        gridView.setAdapter(mMovieAdapter);
        gridView.setOnItemClickListener(new MovieItemClick());

    }

    /*
    * Only updates if the sort order chosen is different to the current sort order.
    * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String sort_choice = null;

        switch (id) {
            case R.id.menuSortPopularity:
                sort_choice = getString(R.string.popular);
                break;
            case R.id.menuSortRating:
                sort_choice = getString(R.string.top_rated);
                break;
        }

        if (sort_choice != null && !sort_choice.equals(mSortOrder) ){
            mSortOrder = sort_choice;
            updatePageWithSort(mSortOrder);
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
                Then removes the setHasTransientSate so that the view can be recycled.
                */
            if (mPreviousView != null){
                RelativeLayout previousOverlayInfo = (RelativeLayout) mPreviousView.findViewById(R.id.overlay_info);
                previousOverlayInfo.animate().setDuration(200).alpha(0.0f);
                previousOverlayInfo.setVisibility(View.GONE);
                mPreviousView.setHasTransientState(false);

            }


            //Find the hidden View with movie info and make visible with an annimation.
            //Sets the view to HasTransientState so that the view doesn't get recycled
            //when the user scrolls and the selection state is saved.
            view.setHasTransientState(true);
            RelativeLayout overlayInfo = (RelativeLayout) view.findViewById(R.id.overlay_info);
            overlayInfo.setAlpha(0);
            overlayInfo.setVisibility(View.VISIBLE);
            overlayInfo.animate().setDuration(350).alpha(1);

                /*
                * Set an onclick listener on the hidden info field, so that if the field is
                * tapped then the user is taken to the details screen
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

            if (movies == null){
                Toast.makeText(MainActivity.this, "Sorry, No Movies found, Check Internet connection and preferences", Toast.LENGTH_SHORT).show();
            }else {
                mMovieAdapter.clear();
                mMovieAdapter.addAll(movies);
            }
            toggleProgressBar(View.GONE);

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
