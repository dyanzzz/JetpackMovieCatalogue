package com.imucreative.jetpackmoviecatalogue.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.imucreative.jetpackmoviecatalogue.R;
import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.databinding.ActivityDetailBinding;
import com.imucreative.jetpackmoviecatalogue.databinding.ContentDetailBinding;

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

        MovieEntity movie = getIntent().getParcelableExtra(EXTRA_DETAIL);
        if(movie != null){
            populateCourse(movie);
        }

    }

    private void populateCourse(MovieEntity movieEntity) {
        detailContentBinding.textTitle.setText(movieEntity.getTitle());
        detailContentBinding.textTag.setText((movieEntity.isTvShow().equals("movie"))?"Movie":"Tv Show");
        detailContentBinding.textDescription.setText(movieEntity.getDescription());
        detailContentBinding.textDate.setText(movieEntity.getDate());

        Glide.with(this)
                .load(movieEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imagePoster);
    }
}
