package com.imucreative.jetpackmoviecatalogue.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.imucreative.jetpackmoviecatalogue.data.source.MovieRepository;
import com.imucreative.jetpackmoviecatalogue.di.Injection;
import com.imucreative.jetpackmoviecatalogue.ui.detail.DetailViewModel;
import com.imucreative.jetpackmoviecatalogue.ui.favorite.movie.FavoriteMovieViewModel;
import com.imucreative.jetpackmoviecatalogue.ui.favorite.tvshow.FavoriteTvShowViewModel;
import com.imucreative.jetpackmoviecatalogue.ui.movie.MovieViewModel;
import com.imucreative.jetpackmoviecatalogue.ui.tvshow.TvShowViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final MovieRepository mMovieRepository;

    private ViewModelFactory(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
    }

    public static ViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(context));
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(mMovieRepository);
        } else if (modelClass.isAssignableFrom(TvShowViewModel.class)) {
            return (T) new TvShowViewModel(mMovieRepository);
        } else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            return (T) new DetailViewModel(mMovieRepository);
        } else if (modelClass.isAssignableFrom(FavoriteMovieViewModel.class)) {
            return (T) new FavoriteMovieViewModel(mMovieRepository);
        } else if (modelClass.isAssignableFrom(FavoriteTvShowViewModel.class)) {
            return (T) new FavoriteTvShowViewModel(mMovieRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
