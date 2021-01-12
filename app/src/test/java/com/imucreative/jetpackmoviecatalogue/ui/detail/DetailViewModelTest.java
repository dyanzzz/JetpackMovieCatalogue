package com.imucreative.jetpackmoviecatalogue.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.imucreative.jetpackmoviecatalogue.data.source.MovieRepository;
import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.utils.FakeDataDummy;
import com.imucreative.jetpackmoviecatalogue.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailViewModelTest {

    private DetailViewModel viewModel;
    private MovieRepository movieRepository = mock(MovieRepository.class);
    private Resource<MovieEntity> dummyMovie = Resource.success(FakeDataDummy.getMovies("movie").get(0));
    private int movieId = dummyMovie.data.getMovieId();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        viewModel = new DetailViewModel(movieRepository);
        viewModel.setId(movieId);
        viewModel.setType("movie");
    }

    @Test
    public void getDetails() {
        MutableLiveData<Resource<MovieEntity>> movieEntity = new MutableLiveData<>();
        movieEntity.setValue(dummyMovie);

        when(movieRepository.getMovieById(movieId)).thenReturn(movieEntity);

        Observer<Resource<MovieEntity>> observer = mock(Observer.class);
        viewModel.getDetails().observeForever(observer);
        verify(observer).onChanged(dummyMovie);
    }
}