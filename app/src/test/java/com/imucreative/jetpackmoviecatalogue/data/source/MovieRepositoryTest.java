package com.imucreative.jetpackmoviecatalogue.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.data.source.remote.RemoteDataSource;
import com.imucreative.jetpackmoviecatalogue.data.source.remote.response.MovieResponse;
import com.imucreative.jetpackmoviecatalogue.utils.FakeDataDummy;
import com.imucreative.jetpackmoviecatalogue.utils.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteDataSource remoteRepository = Mockito.mock(RemoteDataSource.class);
    private FakeMovieRepositoryTest movieRepository = new FakeMovieRepositoryTest(remoteRepository);

    private List<MovieResponse> movieResponses = FakeDataDummy.getRemoteMovies("movie");
    private int movieId = movieResponses.get(0).getId();
    private List<MovieResponse> tvShowResponses = FakeDataDummy.getRemoteTvShows("tv");
    private int tvShowId = tvShowResponses.get(0).getId();

    @Test
    public void getAllMovies() {

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMoviesCallback) invocation.getArguments()[0])
                    .onAllMoviesReceived(movieResponses);
            return null;
        }).when(remoteRepository).getAllMovies(any(RemoteDataSource.LoadMoviesCallback.class));

        List<MovieEntity> result = LiveDataTestUtil.getValue(movieRepository.getAllMovies());
        verify(remoteRepository).getAllMovies(any(RemoteDataSource.LoadMoviesCallback.class));

        assertNotNull(result);
        assertEquals(movieResponses.size(), result.size());
    }

    @Test
    public void getMovieById() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMoviesCallback) invocation.getArguments()[0])
                    .onAllMoviesReceived(movieResponses);
            return null;
        }).when(remoteRepository).getAllMovies(any(RemoteDataSource.LoadMoviesCallback.class));

        MovieEntity result = LiveDataTestUtil.getValue(movieRepository.getMovieById(movieId));

        verify(remoteRepository, times(1)).getAllMovies(any(RemoteDataSource.LoadMoviesCallback.class));

        MovieResponse response = movieResponses.get(0);

        assertNotNull(result);
        assertEquals(response.getId(), result.getMovieId());
        assertEquals(response.getTitle(), result.getTitle());
        assertEquals(response.getOverview(), result.getDescription());
        assertEquals(response.getDate(), result.getDate());
        assertEquals("movie", result.isTvShow());
        assertEquals(response.getPosterPath(), result.getImagePath());
        assertEquals(response.getBackdropPath(), result.getImageBackdropPath());
        assertEquals(response.getVoteCount(), result.getVoteCount());
    }

    @Test
    public void getAllTvShows() {

        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTvShowCallback) invocation.getArguments()[0])
                    .onAllTvShowsReceived(tvShowResponses);
            return null;
        }).when(remoteRepository).getAllTvShow(any(RemoteDataSource.LoadTvShowCallback.class));

        List<MovieEntity> result = LiveDataTestUtil.getValue(movieRepository.getAllTvShow());
        verify(remoteRepository).getAllTvShow(any(RemoteDataSource.LoadTvShowCallback.class));

        assertNotNull(result);
        assertEquals(tvShowResponses.size(), result.size());
    }

    @Test
    public void getTvShowById() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadTvShowCallback) invocation.getArguments()[0])
                    .onAllTvShowsReceived(tvShowResponses);
            return null;
        }).when(remoteRepository).getAllTvShow(any(RemoteDataSource.LoadTvShowCallback.class));

        MovieEntity result = LiveDataTestUtil.getValue(movieRepository.getTvShowById(tvShowId));

        verify(remoteRepository, times(1)).getAllTvShow(any(RemoteDataSource.LoadTvShowCallback.class));

        MovieResponse response = tvShowResponses.get(0);

        assertNotNull(result);
        assertEquals(response.getId(), result.getMovieId());
        assertEquals(response.getTitle(), result.getTitle());
        assertEquals(response.getOverview(), result.getDescription());
        assertEquals(response.getDate(), result.getDate());
        assertEquals("tv", result.isTvShow());
        assertEquals(response.getPosterPath(), result.getImagePath());
        assertEquals(response.getBackdropPath(), result.getImageBackdropPath());
        assertEquals(response.getVoteCount(), result.getVoteCount());
    }
}