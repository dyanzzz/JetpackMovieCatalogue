package com.imucreative.jetpackmoviecatalogue.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.imucreative.jetpackmoviecatalogue.R;
import com.imucreative.jetpackmoviecatalogue.databinding.FragmentFavoriteBinding;
import com.imucreative.jetpackmoviecatalogue.ui.favorite.movie.FavoriteMovieFragment;
import com.imucreative.jetpackmoviecatalogue.ui.favorite.tvshow.FavoriteTvShowFragment;
import com.imucreative.jetpackmoviecatalogue.utils.ViewPagerAdapter;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding fragmentFavoriteBinding;

    private ViewPagerAdapter adapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false);
        return fragmentFavoriteBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPager(fragmentFavoriteBinding.viewpager);
        fragmentFavoriteBinding.tabs.setupWithViewPager(fragmentFavoriteBinding.viewpager);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getChildFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new FavoriteMovieFragment(), getString(R.string.favorite_movie));
        adapter.addFragment(new FavoriteTvShowFragment(), getString(R.string.favorite_tv_show));
        viewPager.setAdapter(adapter);
    }
}
