package com.imucreative.jetpackmoviecatalogue.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.data.source.MovieRepository;

import java.util.List;

public class TvShowViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public TvShowViewModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    public LiveData<List<MovieEntity>> getTvShow() {
        return movieRepository.getAllTvShow();
    }
}