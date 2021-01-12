package com.imucreative.jetpackmoviecatalogue.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.data.source.MovieRepository;
import com.imucreative.jetpackmoviecatalogue.vo.Resource;

import java.util.List;

public class TvShowViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private LiveData<Resource<List<MovieEntity>>> entity;

    public TvShowViewModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    public LiveData<Resource<List<MovieEntity>>> getTvShow() {
        entity = movieRepository.getAllTvShow();
        return entity;
    }

    public void setFavorite(MovieEntity movie) {
        if (entity.getValue() != null) {
            List<MovieEntity> movieEntity = entity.getValue().data;
            if(movieEntity != null) {
                for(int i=0; i<movieEntity.size(); i++){
                    if(movieEntity.get(i).getMovieId() == movie.getMovieId()){
                        movieRepository.setFavoriteStatus(movieEntity.get(i));
                    }
                }
            }
        }
    }
}