package com.redefineeverything.popularmoviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> mMovieAdapter;

    String THUMBNAIL_BASE_URL = "http://image.tmdb.org/t/p/w185";

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




    }

    private String[] createFakeData(){
        String[] movieImages = new String[]{
                "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
                "/cGOPbv9wA5gEejkUN892JrveARt.jpg",
                "/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg",
                "/AoT2YrJUJlg5vKE3iMOLvHlTd3m.jpg",
                "/vOipe2myi26UDwP978hsYOrnUWC.jpg",
                "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
                "/5N20rQURev5CNDcMjHVUZhpoCNC.jpg",
                "/inVq3FRqcYIRl2la8iZikYYxFNR.jpg",
                "/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
                "/a1DAxfDpUhZOn6j42HOJxfaIBPw.jpg",
                "/gj282Pniaa78ZJfbaixyLXnXEDI.jpg",
                "/jjBgi2r5cRt36xF6iNUEhzscEcb.jpg",
                "/h28t2JNNGrZx0fIuAw8aHQFhIxR.jpg",
                "/ghL4ub6vwbYShlqCFHpoIRwx2sm.jpg",
                "/dCgm7efXDmiABSdWDHBDBx2jwmn.jpg",
                "/sM33SANp9z6rXW8Itn7NnG1GOEs.jpg",
                "/vNCeqxbKyDHL9LUza03V2Im16wB.jpg",
                "/5JU9ytZJyR3zmClGmVm9q4Geqbd.jpg",
                "/weUSwMdQIa3NaXVzwUoIIcAi85d.jpg",
                "/2eQfjqlvPAxd9aLDs8DvsKLnfed.jpg",
                "/y31QB9kn3XSudA15tV7UWQ9XLuW.jpg"
        };

        return movieImages;
    }
}
