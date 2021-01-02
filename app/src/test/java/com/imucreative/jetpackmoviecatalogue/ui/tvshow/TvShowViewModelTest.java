package com.imucreative.jetpackmoviecatalogue.ui.tvshow;

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
public class TvShowViewModelTest {

    private TvShowViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Observer<List<MovieEntity>> observer;

    @Before
    public void setUp() {
        viewModel = new TvShowViewModel(movieRepository);
    }

    @Test
    public void getTvShow() {

        List<MovieEntity> dummyCourses = DataDummy.getListData("tv");
        MutableLiveData<List<MovieEntity>> courses = new MutableLiveData<>();
        courses.setValue(dummyCourses);

        when(movieRepository.getAllTvShow()).thenReturn(courses);
        List<MovieEntity> movieEntities = viewModel.getTvShow().getValue();
        verify(movieRepository).getAllTvShow();
        assertNotNull(movieEntities);
        assertEquals(11, movieEntities.size());

        viewModel.getTvShow().observeForever(observer);
        verify(observer).onChanged(dummyCourses);
    }
}