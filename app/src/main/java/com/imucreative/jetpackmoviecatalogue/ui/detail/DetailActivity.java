package com.imucreative.jetpackmoviecatalogue.ui.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.imucreative.jetpackmoviecatalogue.R;
import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.databinding.ActivityDetailBinding;
import com.imucreative.jetpackmoviecatalogue.databinding.ContentDetailBinding;
import com.imucreative.jetpackmoviecatalogue.viewmodel.ViewModelFactory;

import static com.imucreative.jetpackmoviecatalogue.utils.Constant.IMAGE_URL;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_DETAIL = "extra_detail";
    private ContentDetailBinding detailContentBinding;

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

        DetailViewModel viewModel = obtainViewModel(this);
        MovieEntity movie = getIntent().getParcelableExtra(EXTRA_DETAIL);

        if (movie != null) {
            int entityId = movie.getMovieId();
            String entityType = movie.isTvShow();
            if (entityId != 0) {
                viewModel.setId(entityId);
                viewModel.setType(entityType);
            } else {
                finish();
            }
        }
        viewModel.getDetails().observe(this, entity -> {
            if (entity != null) {
                detailContentBinding.loadingDetail.setVisibility(View.GONE);
                detailContentBinding.textDateTv.setVisibility(View.VISIBLE);
                detailContentBinding.textVotersDetailTv.setVisibility(View.VISIBLE);
                populateDetails(entity);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void populateDetails(MovieEntity movieEntity) {
        detailContentBinding.textTitle.setText(movieEntity.getTitle());
        detailContentBinding.textTag.setText((movieEntity.isTvShow().equals("movie"))?"Movie":"Tv Show");
        detailContentBinding.txtDescription.setText(movieEntity.getDescription());
        detailContentBinding.textDate.setText(movieEntity.getDate());
        detailContentBinding.textVotersDetail.setText(String.valueOf(movieEntity.getVoteCount()));

        Glide.with(this)
                .load(IMAGE_URL + "/" + movieEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imgPoster);

        Glide.with(this)
                .load(IMAGE_URL + "/" + movieEntity.getImageBackdropPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imgBackdrop);
    }

    @NonNull
    private static DetailViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailViewModel.class);
    }

}
