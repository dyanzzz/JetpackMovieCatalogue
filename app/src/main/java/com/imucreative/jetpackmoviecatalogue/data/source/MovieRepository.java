package com.imucreative.jetpackmoviecatalogue.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.data.source.remote.RemoteDataSource;
import com.imucreative.jetpackmoviecatalogue.data.source.remote.response.MovieResponse;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository implements MovieDataSource {
    private volatile static MovieRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;

    private MovieRepository(@NonNull RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static MovieRepository getInstance(RemoteDataSource remoteData) {
        if (INSTANCE == null) {
            synchronized (MovieRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieRepository(remoteData);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<List<MovieEntity>> getAllMovies() {
        MutableLiveData<List<MovieEntity>> movieResults = new MutableLiveData<>();

        remoteDataSource.getAllMovies(movieResponses -> {
            List<MovieEntity> movieLIst = new ArrayList<>();
            for (int i = 0; i < movieResponses.size(); i++) {
                MovieResponse response = movieResponses.get(i);
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
                movieLIst.add(movie);
            }
            movieResults.postValue(movieLIst);
        });
        return movieResults;
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
        MutableLiveData<List<MovieEntity>> tvShowResults = new MutableLiveData<>();

        remoteDataSource.getAllTvShow(movieResponses -> {
            List<MovieEntity> tvShowList = new ArrayList<>();
            for (int i = 0; i < movieResponses.size(); i++) {
                MovieResponse response = movieResponses.get(i);
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
                tvShowList.add(tvShow);
            }
            tvShowResults.postValue(tvShowList);
        });
        return tvShowResults;
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
