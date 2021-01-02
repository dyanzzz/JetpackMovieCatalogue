package com.imucreative.jetpackmoviecatalogue.data.source.remote;

import android.os.Handler;

import com.imucreative.jetpackmoviecatalogue.data.source.remote.response.MovieResponse;
import com.imucreative.jetpackmoviecatalogue.utils.EspressoIdlingResource;
import com.imucreative.jetpackmoviecatalogue.utils.JsonHelper;

import java.util.List;

public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;
    private JsonHelper jsonHelper;

    private Handler handler = new Handler();
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteDataSource getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(helper);
        }
        return INSTANCE;
    }

    public void getAllMovies(final LoadMoviesCallback callback) {
        EspressoIdlingResource.increment();
        handler.postDelayed(()-> {
            callback.onAllMoviesReceived(jsonHelper.loadMovies());
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public void getAllTvShow(final LoadTvShowCallback callback) {
        EspressoIdlingResource.increment();
        handler.postDelayed(() -> {
            callback.onAllTvShowsReceived(jsonHelper.loadTvShow());
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public interface LoadMoviesCallback {
        void onAllMoviesReceived(List<MovieResponse> movieResponses);
    }

    public interface LoadTvShowCallback {
        void onAllTvShowsReceived(List<MovieResponse> tvShowResponses);
    }

}
