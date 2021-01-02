package com.imucreative.jetpackmoviecatalogue.ui.tvshow;

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
import com.imucreative.jetpackmoviecatalogue.ui.movie.MovieAdapter;
import com.imucreative.jetpackmoviecatalogue.ui.movie.MovieFragmentCallback;
import com.imucreative.jetpackmoviecatalogue.viewmodel.ViewModelFactory;

public class TvShowFragment extends Fragment implements MovieFragmentCallback {

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
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            TvShowViewModel viewModel = new ViewModelProvider(this, factory).get(TvShowViewModel.class);

            MovieAdapter movieAdapter = new MovieAdapter(this);

            fragmentMovieBinding.progressBar.setVisibility(View.VISIBLE);
            viewModel.getTvShow().observe(requireActivity(), courses -> {
                fragmentMovieBinding.progressBar.setVisibility(View.GONE);
                movieAdapter.setMovies(courses);
                movieAdapter.notifyDataSetChanged();
            }
            );

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
            Toast.makeText(getActivity(), "I Like this tv show "+movieEntity.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }
}