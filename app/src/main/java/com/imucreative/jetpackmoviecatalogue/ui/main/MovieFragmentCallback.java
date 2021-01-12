package com.imucreative.jetpackmoviecatalogue.ui.main;

import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;

public interface MovieFragmentCallback {
    void onShareClick(MovieEntity movie);
    void onFavoriteClick(MovieEntity movie);
    void onDetailClick(MovieEntity movie);
}
