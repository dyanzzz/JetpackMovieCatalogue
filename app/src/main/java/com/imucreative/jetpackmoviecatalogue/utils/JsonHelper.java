package com.imucreative.jetpackmoviecatalogue.utils;

import android.content.Context;

import com.imucreative.jetpackmoviecatalogue.data.source.remote.response.MovieResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    private Context context;

    public JsonHelper(Context context) {
        this.context = context;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private String parsingFileToString(String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<MovieResponse> loadMovies() {
        ArrayList<MovieResponse> list = new ArrayList<>();
        try {
            String json = parsingFileToString("movie.json");
            if (json != null) {
                JSONObject responseObject = new JSONObject(json);
                JSONArray listArray = responseObject.getJSONArray("results");
                for (int i = 0; i < listArray.length(); i++) {
                    JSONObject movie = listArray.getJSONObject(i);

                    int id = movie.getInt("id");
                    String title = movie.getString("title");
                    String description = movie.getString("overview");
                    String date = movie.getString("release_date");
                    String posterPath = movie.getString("poster_path");
                    String backdropPath = movie.getString("backdrop_path");
                    String originalLanguage = movie.getString("original_language");
                    double popularity = movie.getDouble("popularity");
                    double voteAverage = movie.getDouble("vote_average");
                    int voteCount = movie.getInt("vote_count");

                    MovieResponse movieResponse = new MovieResponse(id, title, description, date, posterPath,
                            backdropPath, originalLanguage, popularity, voteAverage, voteCount);
                    list.add(movieResponse);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MovieResponse> loadTvShow() {
        ArrayList<MovieResponse> list = new ArrayList<>();
        try {
            String json = parsingFileToString("tvshow.json");
            if (json != null) {
                JSONObject responseObject = new JSONObject(json);
                JSONArray listArray = responseObject.getJSONArray("results");
                for (int i = 0; i < listArray.length(); i++) {
                    JSONObject tv = listArray.getJSONObject(i);

                    int id = tv.getInt("id");
                    String title = tv.getString("name");
                    String description = tv.getString("overview");
                    String date = tv.getString("first_air_date");
                    String posterPath = tv.getString("poster_path");
                    String backdropPath = tv.getString("backdrop_path");
                    String originalLanguage = tv.getString("original_language");
                    double popularity = tv.getDouble("popularity");
                    double voteAverage = tv.getDouble("vote_average");
                    int voteCount = tv.getInt("vote_count");

                    MovieResponse movieResponse = new MovieResponse(id, title, description, date, posterPath,
                            backdropPath, originalLanguage, popularity, voteAverage, voteCount);

                    list.add(movieResponse);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
