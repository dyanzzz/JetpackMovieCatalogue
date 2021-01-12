package com.imucreative.jetpackmoviecatalogue.ui.movie;

import android.content.Intent;
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
import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.databinding.FragmentMovieBinding;
import com.imucreative.jetpackmoviecatalogue.ui.detail.DetailActivity;
import com.imucreative.jetpackmoviecatalogue.ui.main.MovieAdapter;
import com.imucreative.jetpackmoviecatalogue.ui.main.MovieFragmentCallback;
import com.imucreative.jetpackmoviecatalogue.viewmodel.ViewModelFactory;

public class MovieFragment extends Fragment implements MovieFragmentCallback {

    private FragmentMovieBinding fragmentMovieBinding;
    private MovieViewModel viewModel;

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
            viewModel = new ViewModelProvider(this, factory).get(MovieViewModel.class);

            MovieAdapter movieAdapter = new MovieAdapter(this);

            fragmentMovieBinding.progressBar.setVisibility(View.VISIBLE);

            viewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
                if (movies != null) {
                    switch (movies.status) {
                        case LOADING:
                            fragmentMovieBinding.progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            fragmentMovieBinding.progressBar.setVisibility(View.GONE);
                            movieAdapter.setMovies(movies.data);
                            movieAdapter.notifyDataSetChanged();

                            break;
                        case ERROR:
                            fragmentMovieBinding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
            viewModel.setFavorite(movieEntity);
        }
    }

    @Override
    public void onDetailClick(MovieEntity movieEntity) {
        MovieEntity movies = new MovieEntity();
        movies.setMovieId(movieEntity.getMovieId());
        movies.setTitle(movieEntity.getTitle());
        movies.setDescription(movieEntity.getDescription());
        movies.setDate(movieEntity.getDate());
        movies.setTvShow(movieEntity.getTvShow());
        movies.setImagePath(movieEntity.getImagePath());
        movies.setImageBackdropPath(movieEntity.getImageBackdropPath());
        movies.setVoteCount(movieEntity.getVoteCount());
        movies.setStatus(movieEntity.getStatus());
        movies.setPopularity(movieEntity.getPopularity());
        movies.setLanguage(movieEntity.getLanguage());

        Intent moveWithObjectIntent = new Intent(getActivity(), DetailActivity.class);
        moveWithObjectIntent.putExtra(DetailActivity.EXTRA_DETAIL, movies);
        getActivity().startActivity(moveWithObjectIntent);
    }


}