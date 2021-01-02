package com.imucreative.jetpackmoviecatalogue.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.data.source.MovieRepository;

public class DetailViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private LiveData<MovieEntity> entity;
    private int id;
    private String type;

    public DetailViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    LiveData<MovieEntity> getDetails() {
        switch (type) {
            case "movie":
                entity = movieRepository.getMovieById(id);
                break;
            case "tv":
                entity = movieRepository.getTvShowById(id);
                break;
        }
        return entity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
