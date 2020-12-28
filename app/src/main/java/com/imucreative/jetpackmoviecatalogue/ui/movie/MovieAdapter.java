package com.imucreative.jetpackmoviecatalogue.ui.movie;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.imucreative.jetpackmoviecatalogue.R;
import com.imucreative.jetpackmoviecatalogue.data.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.databinding.ItemsBinding;
import com.imucreative.jetpackmoviecatalogue.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final MovieFragmentCallback callback;
    private final List<MovieEntity> listMovies = new ArrayList<>();

    public MovieAdapter(MovieFragmentCallback callback) {
        this.callback = callback;
    }

    public void setMovies(List<MovieEntity> listMovies) {
        if (listMovies == null) return;
        this.listMovies.clear();
        this.listMovies.addAll(listMovies);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsBinding binding = ItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        MovieEntity movie = listMovies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ItemsBinding binding;

        MovieViewHolder(ItemsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(MovieEntity movieEntity) {
            binding.tvItemTitle.setText(movieEntity.getTitle());
            binding.tvItemDate.setText(movieEntity.getDate());
            binding.tvItemDescription.setText(movieEntity.getDescription());
            itemView.setOnClickListener(v -> {
                MovieEntity movies = new MovieEntity();
                movies.setMovieId(movieEntity.getMovieId());
                movies.setTitle(movieEntity.getTitle());
                movies.setDescription(movieEntity.getDescription());
                movies.setDate(movieEntity.getDate());
                movies.setTvShow(movieEntity.isTvShow());
                movies.setImagePath(movieEntity.getImagePath());

                Intent moveWithObjectIntent = new Intent(itemView.getContext(), DetailActivity.class);
                moveWithObjectIntent.putExtra(DetailActivity.EXTRA_DETAIL, movies);
                itemView.getContext().startActivity(moveWithObjectIntent);

            });
            binding.imgShare.setOnClickListener(v -> callback.onShareClick(movieEntity));
            binding.imgFavorite.setOnClickListener(v -> callback.onFavoriteClick(movieEntity));
            Glide.with(itemView.getContext())
                    .load(movieEntity.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.imgPoster);
        }
    }

}
