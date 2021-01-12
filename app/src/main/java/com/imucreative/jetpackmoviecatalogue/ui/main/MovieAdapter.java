package com.imucreative.jetpackmoviecatalogue.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.imucreative.jetpackmoviecatalogue.BuildConfig;
import com.imucreative.jetpackmoviecatalogue.R;
import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.databinding.ItemsBinding;

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
            setFavoriteStatus(movieEntity.getStatus());

            binding.tvItemTitle.setText(movieEntity.getTitle());
            binding.tvItemDate.setText(movieEntity.getDate());
            binding.tvItemDescription.setText(movieEntity.getDescription());

            itemView.setOnClickListener(v -> callback.onDetailClick(movieEntity));
            binding.imgShare.setOnClickListener(v -> callback.onShareClick(movieEntity));
            binding.imgFavorite.setOnClickListener(v -> callback.onFavoriteClick(movieEntity));

            Glide.with(itemView.getContext())
                    .load(BuildConfig.BASE_URL_IMG + "/" + movieEntity.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.imgPoster);
        }

        private void setFavoriteStatus(boolean status) {
            if (status) {
                binding.imgFavorite.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_favorite));
            } else {
                binding.imgFavorite.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_favorite_border));
            }
        }
    }

}
