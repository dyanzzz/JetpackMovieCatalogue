package com.imucreative.jetpackmoviecatalogue.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.data.source.remote.RemoteDataSource;
import com.imucreative.jetpackmoviecatalogue.data.source.remote.response.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FakeMovieRepositoryTest implements MovieDataSource {
    private volatile static FakeMovieRepositoryTest INSTANCE = null;

    private final RemoteDataSource remoteDataSource;

    FakeMovieRepositoryTest(@NonNull RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public LiveData<List<MovieEntity>> getAllMovies() {
        MutableLiveData<List<MovieEntity>> courseResults = new MutableLiveData<>();

        remoteDataSource.getAllMovies(movieResponses -> {
            List<MovieEntity> courseList = new ArrayList<>();
            for (int i = 0; i < movieResponses.size(); i++) {
                MovieResponse response = movieResponses.get(i);
                MovieEntity course = new MovieEntity(
                        response.getId(),
                        response.getTitle(),
                        response.getOverview(),
                        response.getDate(),
                        "movie",
                        response.getPosterPath(),
                        response.getBackdropPath(),
                        response.getVoteCount()
                );
                courseList.add(course);
            }
            courseResults.postValue(courseList);
        });
        return courseResults;
    }

    @Override
    public LiveData<MovieEntity> getMovieById(int movieId) {
        MutableLiveData<MovieEntity> movieResult = new MutableLiveData<>();

        remoteDataSource.getAllMovies(movieResponses -> {
            for (int i = 0; i < movieResponses.size(); i++) {
                MovieResponse response = movieResponses.get(i);
                if (response.getId() == movieId) {
                    MovieEntity movie = new MovieEntity(
                            response.getId(),
                            response.getTitle(),
                            response.getOverview(),
                            response.getDate(),
                            "movie",
                            response.getPosterPath(),
                            response.getBackdropPath(),
                            response.getVoteCount()
                    );
                    movieResult.postValue(movie);
                }
            }
        });

        return movieResult;
    }

    @Override
    public LiveData<List<MovieEntity>> getAllTvShow() {
        MutableLiveData<List<MovieEntity>> courseResults = new MutableLiveData<>();

        remoteDataSource.getAllTvShow(movieResponses -> {
            List<MovieEntity> courseList = new ArrayList<>();
            for (int i = 0; i < movieResponses.size(); i++) {
                MovieResponse response = movieResponses.get(i);
                MovieEntity course = new MovieEntity(
                        response.getId(),
                        response.getTitle(),
                        response.getOverview(),
                        response.getDate(),
                        "tv",
                        response.getPosterPath(),
                        response.getBackdropPath(),
                        response.getVoteCount()
                );
                courseList.add(course);
            }
            courseResults.postValue(courseList);
        });
        return courseResults;
    }

    @Override
    public LiveData<MovieEntity> getTvShowById(int tvShowId) {
        MutableLiveData<MovieEntity> tvShowResult = new MutableLiveData<>();

        remoteDataSource.getAllTvShow(tvShowResponses -> {
            for (int i = 0; i < tvShowResponses.size(); i++) {
                MovieResponse response = tvShowResponses.get(i);
                if (response.getId() == tvShowId) {
                    MovieEntity tvShow = new MovieEntity(
                            response.getId(),
                            response.getTitle(),
                            response.getOverview(),
                            response.getDate(),
                            "tv",
                            response.getPosterPath(),
                            response.getBackdropPath(),
                            response.getVoteCount()
                    );
                    tvShowResult.postValue(tvShow);
                }
            }
        });

        return tvShowResult;
    }
}
