package com.redefineeverything.popularmoviesapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MovieAdapter mMovieAdapter;

    ArrayList<Movie> mMovieList;

    View mPreviousView;

    public final static String THUMBNAIL_BASE_URL = "http://image.tmdb.org/t/p/w185";

    /*Implementaion
    * https://docs.google.com/document/d/1ZlN1fUsCSKuInLECcJkslIqvpKlP7jWL2TP9m6UiA6I/pub?embedded=true
    *
    * http://api.themoviedb.org/3/movie/popular?api_key=
    * http://api.themoviedb.org/3/movie/top_rated?api_key=
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieList = createFakeData();

        final GridView gridView = (GridView) findViewById(R.id.main_gridview);
        mMovieAdapter = new MovieAdapter(this, mMovieList);
        gridView.setAdapter(mMovieAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /*
                uses the previous view (see below) and hides the info overlay.
                So that only one is showing at a time.

                problem was this doesn't work if you scroll the previous item off the screen
                before selecting a new one. Solved (not perfectly) by resetting every item to
                have the info hidden in the MovieAdapter.
                */
                if (mPreviousView != null){
                    TextView previousMovieTitle = (TextView) mPreviousView.findViewById(R.id.movie_title);
                    previousMovieTitle.setVisibility(View.GONE);
                }

                //Find the hidden text view with movie info and make visible
                TextView movieTitle = (TextView) view.findViewById(R.id.movie_title);
                movieTitle.setVisibility(View.VISIBLE);

                /*
                * Set an onclick listener on the hidden info field, so that if the field is
                * tapped then the user is taken to the details screen
                * TODO: replace toast with Intent to send to details screen
                * */
                movieTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "you clicked on the title" , Toast.LENGTH_SHORT).show();
                    }
                });

                //sets this view to be the previous view for the next time an item is tapped
                mPreviousView = view;

            }
        });



    }

    private ArrayList<Movie> createFakeData(){
        ArrayList<Movie> movieImages = new ArrayList<>();
        movieImages.add(new Movie("/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg","Suicide Squad"));
        movieImages.add(new Movie("/cGOPbv9wA5gEejkUN892JrveARt.jpg","Batman VS Superman"));
        movieImages.add(new Movie("/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg","Jason Bourne"));
        movieImages.add(new Movie("/AoT2YrJUJlg5vKE3iMOLvHlTd3m.jpg","Bourne 2"));
        movieImages.add(new Movie("/vOipe2myi26UDwP978hsYOrnUWC.jpg","Jungle Book"));
        movieImages.add(new Movie("/kqjL17yufvn9OVLyXYpvtyrFfak.jpg","Mad Max"));
        movieImages.add(new Movie("/5N20rQURev5CNDcMjHVUZhpoCNC.jpg","Civil War"));
        movieImages.add(new Movie("/inVq3FRqcYIRl2la8iZikYYxFNR.jpg","Deadpool"));
        movieImages.add(new Movie("/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","Interstellar"));
        movieImages.add(new Movie("/a1DAxfDpUhZOn6j42HOJxfaIBPw.jpg"));
        movieImages.add(new Movie("/gj282Pniaa78ZJfbaixyLXnXEDI.jpg"));
        movieImages.add(new Movie("/jjBgi2r5cRt36xF6iNUEhzscEcb.jpg"));
        movieImages.add(new Movie("/h28t2JNNGrZx0fIuAw8aHQFhIxR.jpg"));
        movieImages.add(new Movie("/ghL4ub6vwbYShlqCFHpoIRwx2sm.jpg"));
        movieImages.add(new Movie("/dCgm7efXDmiABSdWDHBDBx2jwmn.jpg"));
        movieImages.add(new Movie("/sM33SANp9z6rXW8Itn7NnG1GOEs.jpg"));
        movieImages.add(new Movie("/vNCeqxbKyDHL9LUza03V2Im16wB.jpg"));
        movieImages.add(new Movie("/5JU9ytZJyR3zmClGmVm9q4Geqbd.jpg"));
        movieImages.add(new Movie("/weUSwMdQIa3NaXVzwUoIIcAi85d.jpg"));
        movieImages.add(new Movie("/2eQfjqlvPAxd9aLDs8DvsKLnfed.jpg"));
        movieImages.add(new Movie("/y31QB9kn3XSudA15tV7UWQ9XLuW.jpg"));

        return movieImages;
    }
}
