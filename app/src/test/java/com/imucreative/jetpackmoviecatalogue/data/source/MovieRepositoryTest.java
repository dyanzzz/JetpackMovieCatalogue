package com.imucreative.jetpackmoviecatalogue.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.imucreative.jetpackmoviecatalogue.data.source.local.LocalRepository;
import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.data.source.remote.RemoteDataSource;
import com.imucreative.jetpackmoviecatalogue.data.source.remote.response.MovieResponse;
import com.imucreative.jetpackmoviecatalogue.utils.AppExecutors;
import com.imucreative.jetpackmoviecatalogue.utils.DataDummy;
import com.imucreative.jetpackmoviecatalogue.utils.FakeDataDummy;
import com.imucreative.jetpackmoviecatalogue.utils.LiveDataTestUtil;
import com.imucreative.jetpackmoviecatalogue.vo.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteDataSource remoteRepository = Mockito.mock(RemoteDataSource.class);
    private LocalRepository localRepository = Mockito.mock(LocalRepository.class);
    private AppExecutors appExecutors = Mockito.mock(AppExecutors.class);

    private FakeMovieRepositoryTest movieRepository = new FakeMovieRepositoryTest(remoteRepository, localRepository, appExecutors);

    private List<MovieResponse> movieResponses = FakeDataDummy.getRemoteMovies("movie");
    private int movieId = movieResponses.get(0).getId();
    private List<MovieResponse> tvShowResponses = FakeDataDummy.getRemoteTvShows("tv");
    private int tvShowId = tvShowResponses.get(0).getId();

    @Test
    public void getAllMovies() {
        MutableLiveData<List<MovieEntity>> dummyCourses = new MutableLiveData<>();
        dummyCourses.setValue(DataDummy.getListData("movie"));
        when(localRepository.getAllMovies()).thenReturn(dummyCourses);

        Resource<List<MovieEntity>> result = LiveDataTestUtil.getValue(movieRepository.getAllMovies());
        verify(localRepository).getAllMovies();

        assertNotNull(result.data);
        assertEquals(movieResponses.size(), result.data.size());
    }

    @Test
    public void getMovieById() {
        MutableLiveData<MovieEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(DataDummy.getListData("movie").get(0));

        when(localRepository.getMovieById(movieId)).thenReturn(dummyEntity);
        Resource<MovieEntity> result = LiveDataTestUtil.getValue(movieRepository.getMovieById(movieId));
        verify(localRepository).getMovieById(movieId);

        MovieResponse response = movieResponses.get(0);
        MovieEntity resultData = result.data;

        assertNotNull(resultData);
        assertEquals(response.getId(), resultData.getMovieId());
        assertEquals(response.getTitle(), resultData.getTitle());
        assertEquals(response.getOverview(), resultData.getDescription());
        assertEquals(response.getDate(), resultData.getDate());
        assertEquals("movie", resultData.getTvShow());
        assertEquals(response.getPosterPath(), resultData.getImagePath());
        assertEquals(response.getBackdropPath(), resultData.getImageBackdropPath());
        assertEquals(response.getVoteCount(), resultData.getVoteCount());
    }

    @Test
    public void getAllTvShows() {
        MutableLiveData<List<MovieEntity>> dummyEntities = new MutableLiveData<>();
        dummyEntities.setValue(DataDummy.getListData("tv"));
        when(localRepository.getAllTvShows()).thenReturn(dummyEntities);

        Resource<List<MovieEntity>> result = LiveDataTestUtil.getValue(movieRepository.getAllTvShow());
        verify(localRepository).getAllTvShows();

        assertNotNull(result.data);
        assertEquals(tvShowResponses.size(), result.data.size());
    }

    @Test
    public void getTvShowById() {
        MutableLiveData<MovieEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(DataDummy.getListData("tv").get(0));

        when(localRepository.getTvShowById(tvShowId)).thenReturn(dummyEntity);
        Resource<MovieEntity> result = LiveDataTestUtil.getValue(movieRepository.getTvShowById(tvShowId));
        verify(localRepository).getTvShowById(tvShowId);

        MovieResponse response = tvShowResponses.get(0);
        MovieEntity resultData = result.data;

        assertNotNull(resultData);
        assertEquals(response.getId(), resultData.getMovieId());
        assertEquals(response.getTitle(), resultData.getTitle());
        assertEquals(response.getOverview(), resultData.getDescription());
        assertEquals(response.getDate(), resultData.getDate());
        assertEquals("tv", resultData.getTvShow());
        assertEquals(response.getPosterPath(), resultData.getImagePath());
        assertEquals(response.getBackdropPath(), resultData.getImageBackdropPath());
        assertEquals(response.getVoteCount(), resultData.getVoteCount());
    }
}
