package com.imucreative.jetpackmoviecatalogue.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.data.source.MovieRepository;
import com.imucreative.jetpackmoviecatalogue.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieViewModelTest {

    private MovieViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Observer<List<MovieEntity>> observer;

    @Before
    public void setUp() {
        viewModel = new MovieViewModel(movieRepository);
    }

    @Test
    public void getMovies() {

        List<MovieEntity> dummyCourses = DataDummy.getListData("movie");
        MutableLiveData<List<MovieEntity>> courses = new MutableLiveData<>();
        courses.setValue(dummyCourses);

        when(movieRepository.getAllMovies()).thenReturn(courses);
        List<MovieEntity> movieEntities = viewModel.getMovies().getValue();
        verify(movieRepository).getAllMovies();
        assertNotNull(movieEntities);
        assertEquals(12, movieEntities.size());

        viewModel.getMovies().observeForever(observer);
        verify(observer).onChanged(dummyCourses);
    }
}