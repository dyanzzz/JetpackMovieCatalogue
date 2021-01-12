package com.imucreative.jetpackmoviecatalogue.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.data.source.local.room.MovieDao;

import java.util.List;

public class LocalRepository {
    private static LocalRepository INSTANCE;
    private final MovieDao movieDao;

    private LocalRepository(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public static LocalRepository getInstance(MovieDao movieDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(movieDao);
        }
        return INSTANCE;
    }

    public LiveData<List<MovieEntity>> getAllMovies() {
        return movieDao.getMovies();
    }
    public LiveData<MovieEntity> getMovieById(int id) {
        return movieDao.getMovieById(id);
    }

    public LiveData<List<MovieEntity>> getAllTvShows() {
        return movieDao.getTvShows();
    }
    public LiveData<MovieEntity> getTvShowById(int id) {
        return movieDao.getTvShowById(id);
    }

    public DataSource.Factory<Integer, MovieEntity> getFavoriteMovies() {
        return movieDao.getFavoriteMovies();
    }
    public DataSource.Factory<Integer, MovieEntity> getFavoriteTvShows() {
        return movieDao.getFavoriteTvShows();
    }

    public void insertMovies(List<MovieEntity> movies) {
        movieDao.insertMovies(movies);
    }
    public void updateFavoriteStatus(MovieEntity movie) {
        movieDao.updateMovie(movie);
    }

}
