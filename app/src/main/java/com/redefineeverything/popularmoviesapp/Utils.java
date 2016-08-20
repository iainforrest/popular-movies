package com.redefineeverything.popularmoviesapp;

import android.os.Build;
import android.view.animation.Animation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IainForrest on 21/08/2016.
 */
public class Utils {

    public Utils() {
    }

    public final static HashMap<Integer, String> movieGenreMap = getMovieGenres();

    private static HashMap<Integer, String> getMovieGenres() {
        String url = "http://api.themoviedb.org/3/genre/movie/list?api_key=" + BuildConfig.APPLICATION_ID;

        JSONArray genres = null;
        Integer id;
        String genre;

        HashMap<Integer, String> movieGenresMap = new HashMap<Integer, String>();

        try {
            genres = new JSONArray("[{\"id\":28,\"name\":\"Action\"},{\"id\":12,\"name\":\"Adventure\"},{\"id\":16,\"name\":\"Animation\"},{\"id\":35,\"name\":\"Comedy\"},{\"id\":80,\"name\":\"Crime\"},{\"id\":99,\"name\":\"Documentary\"},{\"id\":18,\"name\":\"Drama\"},{\"id\":10751,\"name\":\"Family\"},{\"id\":14,\"name\":\"Fantasy\"},{\"id\":10769,\"name\":\"Foreign\"},{\"id\":36,\"name\":\"History\"},{\"id\":27,\"name\":\"Horror\"},{\"id\":10402,\"name\":\"Music\"},{\"id\":9648,\"name\":\"Mystery\"},{\"id\":10749,\"name\":\"Romance\"},{\"id\":878,\"name\":\"Science Fiction\"},{\"id\":10770,\"name\":\"TV Movie\"},{\"id\":53,\"name\":\"Thriller\"},{\"id\":10752,\"name\":\"War\"},{\"id\":37,\"name\":\"Western\"}]");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (genres != null && genres.length() > 0) {
            try {
                for (int i = 0; i < genres.length(); i++) {
                    JSONObject genrePair = genres.getJSONObject(i);
                    id = genrePair.getInt("id");
                    genre = genrePair.getString("name");
                    movieGenresMap.put(id, genre);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return movieGenresMap;

    }

    public static String getMovieGenres(ArrayList<Integer> genres) {

        if (genres == null) {
            return "";
        }
        StringBuilder genreString = new StringBuilder();
        for (Integer id : genres) {
            genreString.append(movieGenreMap.get(id));
        }
        return genreString.toString();

    }
}
