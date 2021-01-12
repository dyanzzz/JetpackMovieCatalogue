package com.imucreative.jetpackmoviecatalogue.ui.favorite;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.imucreative.jetpackmoviecatalogue.BuildConfig;
import com.imucreative.jetpackmoviecatalogue.R;
import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.databinding.ItemsBinding;

public class FavoriteAdapter extends PagedListAdapter<MovieEntity, FavoriteAdapter.FavoriteViewHolder> {

    private static DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<MovieEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
            return oldItem.getMovieId() == newItem.getMovieId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    private FavoriteFragmentCallback callback;

    public FavoriteAdapter(FavoriteFragmentCallback callback) {
        super(DIFF_CALLBACK);
        this.callback = callback;
    }

    @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsBinding binding = ItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FavoriteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteAdapter.FavoriteViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private final ItemsBinding binding;

        public FavoriteViewHolder(ItemsBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(MovieEntity movieEntity) {
            setFavoriteStatus(movieEntity.getStatus());

            binding.tvItemTitle.setText(movieEntity.getTitle());
            binding.tvItemDate.setText(movieEntity.getDate());
            binding.tvItemDescription.setText(movieEntity.getDescription());

            binding.imgShare.setOnClickListener(v -> callback.onShareClick(movieEntity));
            binding.imgFavorite.setOnClickListener(v -> callback.onFavoriteClick(movieEntity));
            itemView.setOnClickListener(view -> callback.onDetailClick(movieEntity));

            Glide.with(itemView.getContext())
                    .load(BuildConfig.BASE_URL_IMG + movieEntity.getImagePath())
                    .apply(new RequestOptions())
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
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
