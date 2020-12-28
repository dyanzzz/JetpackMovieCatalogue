package com.imucreative.jetpackmoviecatalogue.ui.tvshow;

import androidx.lifecycle.ViewModel;

import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.utils.DataDummy;

import java.util.List;

public class TvShowViewModel extends ViewModel {
    public List<MovieEntity> getTvShow() {
        return DataDummy.getListData("tv");
    }
}