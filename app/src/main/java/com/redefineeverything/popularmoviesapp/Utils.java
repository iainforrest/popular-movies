package com.redefineeverything.popularmoviesapp;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IainForrest on 21/08/2016.
 */
public class Utils {

    private final static String LOG_TAG = Utils.class.getSimpleName();

    public Utils() {
    }

    public final static HashMap<Integer, String> movieGenreMap = getMovieGenres();

    private static HashMap<Integer, String> getMovieGenres() {
        //TODO edit to work with getting genres from the website
        String url = "http://api.themoviedb.org/3/genre/movie/list?api_key=" + BuildConfig.APPLICATION_ID;

        JSONArray genres = null;
        Integer id;
        String genre;

        HashMap<Integer, String> movieGenresMap = new HashMap<Integer, String>();

        try {
            genres = new JSONArray(
                    "[{\"id\":28,\"name\":\"Action\"},{\"id\":12,\"name\":\"Adventure\"},{\"id\":16,\"name\":\"Animation\"},{\"id\":35,\"name\":\"Comedy\"},{\"id\":80,\"name\":\"Crime\"},{\"id\":99,\"name\":\"Documentary\"},{\"id\":18,\"name\":\"Drama\"},{\"id\":10751,\"name\":\"Family\"},{\"id\":14,\"name\":\"Fantasy\"},{\"id\":10769,\"name\":\"Foreign\"},{\"id\":36,\"name\":\"History\"},{\"id\":27,\"name\":\"Horror\"},{\"id\":10402,\"name\":\"Music\"},{\"id\":9648,\"name\":\"Mystery\"},{\"id\":10749,\"name\":\"Romance\"},{\"id\":878,\"name\":\"Science Fiction\"},{\"id\":10770,\"name\":\"TV Movie\"},{\"id\":53,\"name\":\"Thriller\"},{\"id\":10752,\"name\":\"War\"},{\"id\":37,\"name\":\"Western\"}]");
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

    public static URL buildQueryUrl(String sortOrder){
        URL url = null;

        //http://api.themoviedb.org/3/movie/top_rated?api_key=
        String BASE_URL = "http://api.themoviedb.org/3/movie";
        String API_PARAM = "api_key";
        String API_ID = BuildConfig.MY_MOVIE_DB_API_KEY;

        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(sortOrder)
                .appendQueryParameter(API_PARAM,API_ID)
                .build();

        try {
            url = new URL(uri.toString());
            Log.e("UrlBuilder","url built is : " + url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(LOG_TAG + " BuildQueryUrl","Error creating URL");
        }

        return url;
    }

    public static String getJSONfromURL (URL url) throws IOException{

        String JSONresponse = "";

        if (url == null){
            Log.e(LOG_TAG + " getJSONfromURL ","Null URL error");
            throw new IOException();
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /*milliseconds*/);
            urlConnection.setConnectTimeout(15000 /*Milliseconds*/);
            urlConnection.connect();
            int connectionResponse = urlConnection.getResponseCode();
            if (connectionResponse == 200){
                inputStream = urlConnection.getInputStream();
                JSONresponse = readFromStream(inputStream);
            }else{
                Log.e(LOG_TAG + " getJSONfromURL", "getJSONfromURL - connection error with response : " + connectionResponse);
                throw new IOException();
            }
        } catch (IOException e) {
            Log.e(LOG_TAG+ " GetJSONFromUrl", "IO Exception thrown : "+e.getMessage());
            throw new IOException();
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }

        return JSONresponse;
    }

    public static String readFromStream(InputStream inputStream) throws IOException{
        BufferedReader bufferReader;
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            bufferReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = bufferReader.readLine();
            while (line != null){
                output.append(line);
                line = bufferReader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<Movie> getMoviesFromJSON(String moviesJSONString) throws JSONException{
        final String JSONResults = "results";
        final String JSON_POSTER_IMAGE = "poster_path";
        final String JSON_DESCRIPTION = "overview";
        final String JSON_RELEASE_DATE = "release_date";
        final String JSON_GENRE_IDS = "genre_ids";
        final String JSON_MOVIE_ID = "id";
        final String JSON_TITLE = "title";
        final String JSON_SCORE = "vote_average";

        ArrayList<Integer> genres;

        ArrayList<Movie> allMovies = new ArrayList<>();

        if (TextUtils.isEmpty(moviesJSONString)){
            throw new JSONException("JSON string was empty");
        }

        try {
            JSONObject root = new JSONObject(moviesJSONString);
            JSONArray results = root.getJSONArray(JSONResults);

            if (results != null && results.length() > 0){
                for (int i = 0; i < results.length() ; i++) {
                    try {
                        JSONObject movieDetails = results.getJSONObject(i);
                        String thumbImagePath = movieDetails.getString(JSON_POSTER_IMAGE);
                        String description = movieDetails.getString(JSON_DESCRIPTION);
                        String releaseDate = movieDetails.getString(JSON_RELEASE_DATE);
                        int id = movieDetails.getInt(JSON_MOVIE_ID);
                        double score = movieDetails.getDouble(JSON_SCORE);
                        String title = movieDetails.getString(JSON_TITLE);

                        try {
                            JSONArray genreArray = movieDetails.getJSONArray(JSON_GENRE_IDS);
                            genres = new ArrayList<Integer>();
                            if (genreArray != null) {
                                for (int j = 0; j < genreArray.length() ; j++) {
                                    genres.add(genreArray.getInt(j));
                                }
                            }
                        } catch (JSONException e) {
                            genres = new ArrayList<Integer>();
                        }

                        allMovies.add(new Movie(id,title,thumbImagePath,description,
                                releaseDate,score,genres));
                    } catch (JSONException e) {
                        Log.e(LOG_TAG + " GetMoviesFromJSON", "Error parsing single Movie JSON object");
                    }

                }
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG + " GetMoviesFromJSON", "Error parsing the JSON file");
        }

        return allMovies;
    }


}