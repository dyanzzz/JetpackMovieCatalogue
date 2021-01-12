package com.imucreative.jetpackmoviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.vo.Resource;

import java.util.List;

public interface MovieDataSource {
    LiveData<Resource<List<MovieEntity>>> getAllMovies();
    LiveData<Resource<MovieEntity>> getMovieById(int movieId);

    LiveData<Resource<List<MovieEntity>>> getAllTvShow();
    LiveData<Resource<MovieEntity>> getTvShowById(int tvShowId);

    LiveData<Resource<PagedList<MovieEntity>>> getFavoriteMovies();
    LiveData<Resource<PagedList<MovieEntity>>> getFavoriteTvShows();

    void setFavoriteStatus(MovieEntity movieEntity);
}
