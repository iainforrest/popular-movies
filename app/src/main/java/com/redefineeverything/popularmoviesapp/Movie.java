package com.redefineeverything.popularmoviesapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by IainForrest on 18/08/2016.
 */
public class Movie implements Parcelable {

    private final String THUMBNAIL_BASE_URL = "http://image.tmdb.org/t/p/w342";


    private int mID;
    private String mTitle;
    private String mImagePath;
    private String mOverview;
    private String mReleaseDate;
    private double mScore;
    private ArrayList<Integer> mGenres;


    public Movie(int mID, String mTitle, String mImagePath, String mOverview, String mReleaseDate, double mScore, ArrayList<Integer> mGenres) {
        this.mID = mID;
        if (!TextUtils.isEmpty(mTitle)) {
            this.mTitle = mTitle;
        } else {
            this.mTitle = "Sorry, No Title Available";
        }
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
        return THUMBNAIL_BASE_URL + mImagePath;
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

    public String getFormattedScore(){
        return Double.toString(mScore) + "/10";
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mID);
        dest.writeString(this.mTitle);
        dest.writeString(this.mImagePath);
        dest.writeString(this.mOverview);
        dest.writeString(this.mReleaseDate);
        dest.writeDouble(this.mScore);
        dest.writeList(this.mGenres);
    }

    protected Movie(Parcel in) {
        this.mID = in.readInt();
        this.mTitle = in.readString();
        this.mImagePath = in.readString();
        this.mOverview = in.readString();
        this.mReleaseDate = in.readString();
        this.mScore = in.readDouble();
        this.mGenres = new ArrayList<Integer>();
        in.readList(this.mGenres, Integer.class.getClassLoader());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
