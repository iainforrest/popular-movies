package com.redefineeverything.popularmoviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MovieAdapter mMovieAdapter;

    ArrayList<Movie> mMovieList;

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
        for(Movie movie:mMovieList){
            Log.i("MainActivity","Movie Thumb = " + movie.getImagePath());
        }

        GridView listView = (GridView) findViewById(R.id.main_gridview);
        mMovieAdapter = new MovieAdapter(this, mMovieList);
        listView.setAdapter(mMovieAdapter);

    }

    private ArrayList<Movie> createFakeData(){
        ArrayList<Movie> movieImages = new ArrayList<>();
        movieImages.add(new Movie("/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg"));
        movieImages.add(new Movie("/cGOPbv9wA5gEejkUN892JrveARt.jpg"));
        movieImages.add(new Movie("/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg"));
        movieImages.add(new Movie("/AoT2YrJUJlg5vKE3iMOLvHlTd3m.jpg"));
        movieImages.add(new Movie("/vOipe2myi26UDwP978hsYOrnUWC.jpg"));
        movieImages.add(new Movie("/kqjL17yufvn9OVLyXYpvtyrFfak.jpg"));
        movieImages.add(new Movie("/5N20rQURev5CNDcMjHVUZhpoCNC.jpg"));
        movieImages.add(new Movie("/inVq3FRqcYIRl2la8iZikYYxFNR.jpg"));
        movieImages.add(new Movie("/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg"));
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
