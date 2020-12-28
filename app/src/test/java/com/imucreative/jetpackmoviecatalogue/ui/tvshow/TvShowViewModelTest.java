package com.imucreative.jetpackmoviecatalogue.ui.tvshow;

import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TvShowViewModelTest {

    private TvShowViewModel viewModel;
    @Before
    public void setUp() {
        viewModel = new TvShowViewModel();
    }

    @Test
    public void getTvShow() {
        List<MovieEntity> movieEntities = viewModel.getTvShow();
        assertNotNull(movieEntities);
        assertEquals(10, movieEntities.size());
    }
}