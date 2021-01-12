package com.imucreative.jetpackmoviecatalogue.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.imucreative.jetpackmoviecatalogue.R;
import com.imucreative.jetpackmoviecatalogue.databinding.ActivityMainBinding;
import com.imucreative.jetpackmoviecatalogue.ui.favorite.FavoriteFragment;
import com.imucreative.jetpackmoviecatalogue.ui.movie.MovieFragment;
import com.imucreative.jetpackmoviecatalogue.ui.tvshow.TvShowFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(activityMainBinding.getRoot());

        activityMainBinding.navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        if (savedInstanceState == null) {
            activityMainBinding.navigation.setSelectedItemId(R.id.nav_movie);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = menuItem -> {
        Fragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.nav_tv:
                fragment = new TvShowFragment();
                break;
            case R.id.nav_favorite:
                fragment = new FavoriteFragment();
                break;
            default:
                fragment = new MovieFragment();
        }
        loadFragment(fragment);
        return true;
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}
