package com.redefineeverything.popularmoviesapp;

import java.util.Date;

/**
 * Created by IainForrest on 18/08/2016.
 */
public class Movie {

    private int mID;
    private String mTitle;
    private String mImagePath;
    private String mOverview;
    private Date mReleaseDate;
    private float mPopularity;
    private float mVoteAverage;
    private int mVoteCount;

    public Movie(String imagePath){
        mImagePath = imagePath;
    }

    public Movie(int mID, String mTitle, String mImagePath, String mOverview, Date mReleaseDate, float mPopularity, float mVoteAverage, int mVoteCount) {
        this.mID = mID;
        this.mTitle = mTitle;
        this.mImagePath = mImagePath;
        this.mOverview = mOverview;
        this.mReleaseDate = mReleaseDate;
        this.mPopularity = mPopularity;
        this.mVoteAverage = mVoteAverage;
        this.mVoteCount = mVoteCount;
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

    public Date getReleaseDate() {
        return mReleaseDate;
    }

    public float getPopularity() {
        return mPopularity;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }

    public int getVoteCount() {
        return mVoteCount;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "ID=" + mID +
                ", Title='" + mTitle + '\'' +
                ", ImagePath='" + mImagePath + '\'' +
                ", Overview='" + mOverview + '\'' +
                ", ReleaseDate=" + mReleaseDate +
                ", Popularity=" + mPopularity +
                ", VoteAverage=" + mVoteAverage +
                ", VoteCount=" + mVoteCount +
                '}';
    }
}
