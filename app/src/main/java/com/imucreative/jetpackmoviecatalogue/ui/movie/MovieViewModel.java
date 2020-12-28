package com.imucreative.jetpackmoviecatalogue.ui.movie;

import androidx.lifecycle.ViewModel;

import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.utils.DataDummy;

import java.util.List;

public class MovieViewModel extends ViewModel {
    public List<MovieEntity> getMovies() {
        return DataDummy.getListData("movie");
    }
}
