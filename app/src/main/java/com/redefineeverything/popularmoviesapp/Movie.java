package com.redefineeverything.popularmoviesapp;

import java.util.ArrayList;

/**
 * Created by IainForrest on 18/08/2016.
 */
public class Movie {

    private int mID;
    private String mTitle;
    private String mImagePath;
    private String mOverview;
    private String mReleaseDate;
    private double mScore;
    private ArrayList<Integer> mGenres;


    public Movie(int mID, String mTitle, String mImagePath, String mOverview, String mReleaseDate, double mScore, ArrayList<Integer> mGenres) {
        this.mID = mID;
        this.mTitle = mTitle;
        this.mImagePath = mImagePath;
        this.mOverview = mOverview;
        this.mReleaseDate = mReleaseDate;
        this.mScore = mScore;
        this.mGenres = mGenres;
    }

    public int getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getFormattedReleaseDate() {
        return "(" + mReleaseDate.substring(0,4) + ")";
    }

    public double getScore() {
        return mScore;
    }

    public String getmGenres() {
        return mGenres.toString();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "mID=" + mID +
                ", mTitle='" + mTitle + '\'' +
                ", mImagePath='" + mImagePath + '\'' +
                ", mOverview='" + mOverview + '\'' +
                ", mReleaseDate=" + mReleaseDate +
                ", mScore=" + mScore +
                ", mGenres=" + mGenres +
                '}';
    }
}
