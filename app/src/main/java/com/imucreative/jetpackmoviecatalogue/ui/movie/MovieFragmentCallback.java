package com.imucreative.jetpackmoviecatalogue.ui.movie;

import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;

public interface MovieFragmentCallback {
    void onShareClick(MovieEntity movie);
    void onFavoriteClick(MovieEntity movie);
}
