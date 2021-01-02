package com.imucreative.jetpackmoviecatalogue.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.data.source.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieViewModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    public LiveData<List<MovieEntity>> getMovies() {
        return movieRepository.getAllMovies();
    }
}
