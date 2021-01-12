package com.imucreative.jetpackmoviecatalogue.data.source.remote;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    public LiveData<ApiResponse<List<MovieResponse>>> getAllMovies() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MovieResponse>>> movieResponses = new MutableLiveData<>();
        handler.postDelayed(()-> {
            movieResponses.setValue(ApiResponse.success(jsonHelper.loadMovies()));
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);
        return movieResponses;
    }

    public LiveData<ApiResponse<List<MovieResponse>>> getAllTvShow() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MovieResponse>>> tvShowResponses = new MutableLiveData<>();
        handler.postDelayed(() -> {
            tvShowResponses.setValue(ApiResponse.success(jsonHelper.loadTvShow()));
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);
        return tvShowResponses;
    }

    public interface LoadMoviesCallback {
        void onAllMoviesReceived(List<MovieResponse> movieResponses);
    }

    public interface LoadTvShowCallback {
        void onAllTvShowsReceived(List<MovieResponse> tvShowResponses);
    }

}
