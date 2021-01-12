package com.imucreative.jetpackmoviecatalogue.ui.favorite.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.imucreative.jetpackmoviecatalogue.data.source.MovieRepository;
import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.vo.Resource;

import java.util.List;

public class FavoriteMovieViewModel extends ViewModel {
    MutableLiveData<String> type = new MutableLiveData<>();
    private MovieRepository movieRepository;

    public LiveData<Resource<PagedList<MovieEntity>>> entity = Transformations.switchMap(type, data -> movieRepository.getFavoriteMovies());

    public FavoriteMovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void setType(String type) {
        this.type.setValue(type);
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
