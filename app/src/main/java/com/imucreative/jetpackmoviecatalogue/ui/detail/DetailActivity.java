package com.imucreative.jetpackmoviecatalogue.ui.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.imucreative.jetpackmoviecatalogue.BuildConfig;
import com.imucreative.jetpackmoviecatalogue.R;
import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.databinding.ActivityDetailBinding;
import com.imucreative.jetpackmoviecatalogue.databinding.ContentDetailBinding;
import com.imucreative.jetpackmoviecatalogue.viewmodel.ViewModelFactory;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_DETAIL = "extra_detail";
    private ContentDetailBinding detailContentBinding;
    private DetailViewModel viewModel;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDetailBinding activityDetailCourseBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        detailContentBinding = activityDetailCourseBinding.detailContent;

        setContentView(activityDetailCourseBinding.getRoot());

        setSupportActionBar(activityDetailCourseBinding.toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewModel = obtainViewModel(this);

        MovieEntity movie = getIntent().getParcelableExtra(EXTRA_DETAIL);

        if (movie != null) {
            int entityId = movie.getMovieId();
            String entityType = movie.getTvShow();
            if (entityId != 0) {
                viewModel.setId(entityId);
                viewModel.setType(entityType);
            } else {
                finish();
            }
        }

        observeData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite_button, menu);
        this.menu = menu;
        viewModel.getDetails().observe(this, detail -> {
            switch (detail.status) {
                case LOADING:
                    detailContentBinding.loadingDetail.setVisibility(View.VISIBLE);
                    detailContentBinding.textDateTv.setVisibility(View.GONE);
                    detailContentBinding.textVotersDetailTv.setVisibility(View.GONE);
                    detailContentBinding.textPopularityDetailTv.setVisibility(View.GONE);
                    detailContentBinding.textLanguageDetailTv.setVisibility(View.GONE);
                    break;
                case SUCCESS:
                    if (detail.data != null) {
                        detailContentBinding.loadingDetail.setVisibility(View.GONE);
                        detailContentBinding.textDateTv.setVisibility(View.VISIBLE);
                        detailContentBinding.textVotersDetailTv.setVisibility(View.VISIBLE);
                        detailContentBinding.textPopularityDetailTv.setVisibility(View.VISIBLE);
                        detailContentBinding.textLanguageDetailTv.setVisibility(View.VISIBLE);
                        boolean status = detail.data.getStatus();
                        setFavoriteStatus(status);
                    }
                    break;
                case ERROR:
                    detailContentBinding.loadingDetail.setVisibility(View.GONE);
                    detailContentBinding.textDateTv.setVisibility(View.VISIBLE);
                    detailContentBinding.textVotersDetailTv.setVisibility(View.VISIBLE);
                    detailContentBinding.textPopularityDetailTv.setVisibility(View.VISIBLE);
                    detailContentBinding.textLanguageDetailTv.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void setFavoriteStatus(boolean status) {
        if (menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.favorite_button);
        if (status) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border));
        }
        menuItem.setTitle(R.string.favorite);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.favorite_button) {
            viewModel.setFavorite();
        }
        return super.onOptionsItemSelected(item);
    }

    private void observeData() {
        viewModel.getDetails().observe(this, entity -> {
            if (entity.data != null) {
                switch (entity.status) {
                    case LOADING:
                        detailContentBinding.loadingDetail.setVisibility(View.VISIBLE);
                        detailContentBinding.textDateTv.setVisibility(View.GONE);
                        detailContentBinding.textVotersDetailTv.setVisibility(View.GONE);
                        detailContentBinding.textPopularityDetailTv.setVisibility(View.GONE);
                        detailContentBinding.textLanguageDetailTv.setVisibility(View.GONE);
                        break;
                    case SUCCESS:
                        detailContentBinding.loadingDetail.setVisibility(View.GONE);
                        detailContentBinding.textDateTv.setVisibility(View.VISIBLE);
                        detailContentBinding.textVotersDetailTv.setVisibility(View.VISIBLE);
                        detailContentBinding.textPopularityDetailTv.setVisibility(View.VISIBLE);
                        detailContentBinding.textLanguageDetailTv.setVisibility(View.VISIBLE);
                        populateDetails(entity.data);
                        break;
                    case ERROR:
                        detailContentBinding.loadingDetail.setVisibility(View.GONE);
                        detailContentBinding.textDateTv.setVisibility(View.VISIBLE);
                        detailContentBinding.textVotersDetailTv.setVisibility(View.VISIBLE);
                        detailContentBinding.textPopularityDetailTv.setVisibility(View.VISIBLE);
                        detailContentBinding.textLanguageDetailTv.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void populateDetails(MovieEntity movieEntity) {
        detailContentBinding.textTitle.setText(movieEntity.getTitle());
        detailContentBinding.textTag.setText((movieEntity.getTvShow().equals("movie"))?"Category Movie":"Category Tv Show");
        detailContentBinding.txtDescription.setText(movieEntity.getDescription());
        detailContentBinding.textDate.setText(movieEntity.getDate());
        detailContentBinding.textVotersDetail.setText(String.valueOf(movieEntity.getVoteCount()));
        detailContentBinding.textPopularityDetail.setText(String.valueOf(movieEntity.getPopularity()));
        detailContentBinding.textLanguageDetail.setText(String.valueOf(movieEntity.getLanguage()));

        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMG + "/" + movieEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imgPoster);

        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMG + "/" + movieEntity.getImageBackdropPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imgBackdrop);
    }

    @NonNull
    private static DetailViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailViewModel.class);
    }

}
