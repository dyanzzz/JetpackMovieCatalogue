package com.imucreative.jetpackmoviecatalogue.ui.movie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.imucreative.jetpackmoviecatalogue.R;
import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.databinding.FragmentMovieBinding;

import java.util.List;

public class MovieFragment extends Fragment implements MovieFragmentCallback{

    private FragmentMovieBinding fragmentMovieBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentMovieBinding = FragmentMovieBinding.inflate(inflater, container, false);
        return fragmentMovieBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            MovieViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
            List<MovieEntity> movies = viewModel.getMovies();

            MovieAdapter movieAdapter = new MovieAdapter(this);
            movieAdapter.setMovies(movies);

            fragmentMovieBinding.rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            fragmentMovieBinding.rvMovie.setHasFixedSize(true);
            fragmentMovieBinding.rvMovie.setAdapter(movieAdapter);
        }
    }

    @Override
    public void onShareClick(MovieEntity movieEntity) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle(R.string.share_title)
                    .setText(String.format(String.valueOf(R.string.share_content), movieEntity.getTitle()))
                    .startChooser();
        }
    }

    @Override
    public void onFavoriteClick(MovieEntity movieEntity) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), "I Like this movie "+movieEntity.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }
}