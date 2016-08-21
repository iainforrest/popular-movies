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
    private double mPopularity;
    private double mScore;
    private ArrayList<Integer> mGenres;

    public Movie(String imagePath){
        mImagePath = imagePath;
        mTitle = "default1 Title";
    }

    public Movie(String imagePath, String title){
        mImagePath = imagePath;
        mTitle = title;
    }

    public Movie(int mID, String mTitle, String mImagePath, String mOverview, String mReleaseDate, double mPopularity, double mScore, ArrayList<Integer> mGenres) {
        this.mID = mID;
        this.mTitle = mTitle;
        this.mImagePath = mImagePath;
        this.mOverview = mOverview;
        this.mReleaseDate = mReleaseDate;
        this.mPopularity = mPopularity;
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

    public double getPopularity() {
        return mPopularity;
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
                ", mPopularity=" + mPopularity +
                ", mScore=" + mScore +
                ", mGenres=" + mGenres +
                '}';
    }
}
