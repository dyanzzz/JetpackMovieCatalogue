package com.imucreative.jetpackmoviecatalogue.ui.favorite.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.imucreative.jetpackmoviecatalogue.R;
import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.databinding.FragmentFavoriteMovieBinding;
import com.imucreative.jetpackmoviecatalogue.ui.detail.DetailActivity;
import com.imucreative.jetpackmoviecatalogue.ui.favorite.FavoriteAdapter;
import com.imucreative.jetpackmoviecatalogue.ui.favorite.FavoriteFragmentCallback;
import com.imucreative.jetpackmoviecatalogue.viewmodel.ViewModelFactory;

public class FavoriteMovieFragment extends Fragment implements FavoriteFragmentCallback {

    private FragmentFavoriteMovieBinding fragmentFavoriteMovieBinding;
    private FavoriteMovieViewModel viewModel;

    public FavoriteMovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFavoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(inflater, container, false);
        return fragmentFavoriteMovieBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            viewModel = obtainViewModel(getActivity());
            FavoriteAdapter adapter = new FavoriteAdapter(this);

            fragmentFavoriteMovieBinding.progress.setVisibility(View.VISIBLE);

            viewModel.setType(getString(R.string.favorite_movie));
            viewModel.entity.observe(getViewLifecycleOwner(), movies -> {
                if (movies.data != null) {
                    fragmentFavoriteMovieBinding.progress.setVisibility(View.GONE);
                    adapter.submitList(movies.data);
                    adapter.notifyDataSetChanged();
                }
            });

            fragmentFavoriteMovieBinding.rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            fragmentFavoriteMovieBinding.rvMovie.setHasFixedSize(true);
            fragmentFavoriteMovieBinding.rvMovie.setAdapter(adapter);
        }
    }

    private FavoriteMovieViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(FavoriteMovieViewModel.class);
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
