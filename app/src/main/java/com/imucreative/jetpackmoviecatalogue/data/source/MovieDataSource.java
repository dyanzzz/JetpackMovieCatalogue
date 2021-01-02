package com.imucreative.jetpackmoviecatalogue.data.source;

import androidx.lifecycle.LiveData;

import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;

import java.util.List;

public interface MovieDataSource {
    LiveData<List<MovieEntity>> getAllMovies();
    LiveData<MovieEntity> getMovieById(int movieId);

    LiveData<List<MovieEntity>> getAllTvShow();
    LiveData<MovieEntity> getTvShowById(int tvShowId);
}
