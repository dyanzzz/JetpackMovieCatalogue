package com.imucreative.jetpackmoviecatalogue.data.source.local.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;

import java.util.List;

@Dao
public interface MovieDao {

    @WorkerThread
    @Query("SELECT * FROM movieentities WHERE tvShow = 'movie'")
    LiveData<List<MovieEntity>> getMovies();

    @WorkerThread
    @Query("SELECT * FROM movieentities WHERE tvShow = 'movie' AND movieId = :id")
    LiveData<MovieEntity> getMovieById(int id);

    @WorkerThread
    @Query("SELECT * FROM movieentities WHERE tvShow = 'tv'")
    LiveData<List<MovieEntity>> getTvShows();

    @WorkerThread
    @Query("SELECT * FROM movieentities WHERE tvShow = 'tv' AND movieId = :id")
    LiveData<MovieEntity> getTvShowById(int id);

    @WorkerThread
    @Query("SELECT * FROM movieentities WHERE tvShow = 'movie' AND status = 1")
    DataSource.Factory<Integer, MovieEntity> getFavoriteMovies();

    @WorkerThread
    @Query("SELECT * FROM movieentities WHERE tvShow = 'tv' AND status = 1")
    DataSource.Factory<Integer, MovieEntity> getFavoriteTvShows();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<MovieEntity> movies);

    @Update
    void updateMovie(MovieEntity movie);

}
