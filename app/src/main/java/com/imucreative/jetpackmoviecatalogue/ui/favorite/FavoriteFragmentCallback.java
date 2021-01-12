package com.imucreative.jetpackmoviecatalogue.ui.favorite;

import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;

public interface FavoriteFragmentCallback {
    void onShareClick(MovieEntity movie);
    void onFavoriteClick(MovieEntity movie);
    void onDetailClick(MovieEntity movieEntity);
}
