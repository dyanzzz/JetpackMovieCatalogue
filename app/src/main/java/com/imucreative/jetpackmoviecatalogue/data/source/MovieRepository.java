package com.imucreative.jetpackmoviecatalogue.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.imucreative.jetpackmoviecatalogue.data.source.local.LocalRepository;
import com.imucreative.jetpackmoviecatalogue.data.source.local.entity.MovieEntity;
import com.imucreative.jetpackmoviecatalogue.data.source.remote.ApiResponse;
import com.imucreative.jetpackmoviecatalogue.data.source.remote.RemoteDataSource;
import com.imucreative.jetpackmoviecatalogue.data.source.remote.response.MovieResponse;
import com.imucreative.jetpackmoviecatalogue.utils.AppExecutors;
import com.imucreative.jetpackmoviecatalogue.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository implements MovieDataSource {
    private volatile static MovieRepository INSTANCE = null;

    private final LocalRepository localRepository;
    private final RemoteDataSource remoteDataSource;
    private final AppExecutors appExecutors;

    public MovieRepository(@NonNull LocalRepository localRepository, @NonNull RemoteDataSource remoteDataSource, @NonNull AppExecutors appExecutors) {
        this.localRepository = localRepository;
        this.remoteDataSource = remoteDataSource;
        this.appExecutors = appExecutors;
    }

    public static MovieRepository getInstance(LocalRepository localRepository, RemoteDataSource remoteDataSource, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (MovieRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieRepository(localRepository, remoteDataSource, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getAllMovies() {

        return new NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.getAllMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return remoteDataSource.getAllMovies();
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {
                List<MovieEntity> movieEntities = new ArrayList<>();

                for (MovieResponse response : data) {
                    movieEntities.add(new MovieEntity(
                            response.getId(),
                            response.getTitle(),
                            response.getOverview(),
                            response.getDate(),
                            "movie",
                            response.getPosterPath(),
                            response.getBackdropPath(),
                            response.getVoteCount(),
                            response.getPopularity(),
                            response.getOriginalLanguage()
                    ));
                    localRepository.insertMovies(movieEntities);
                }
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> getMovieById(int movieId) {
        return new NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {

            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localRepository.getMovieById(movieId);
            }

            @Override
            protected Boolean shouldFetch(MovieEntity data) {
                return data == null;
            }

            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(MovieResponse data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getAllTvShow() {
        return new NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.getAllTvShows();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return remoteDataSource.getAllTvShow();
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {
                List<MovieEntity> tvShowEntities = new ArrayList<>();

                for (MovieResponse response : data) {
                    tvShowEntities.add(new MovieEntity(
                            response.getId(),
                            response.getTitle(),
                            response.getOverview(),
                            response.getDate(),
                            "tv",
                            response.getPosterPath(),
                            response.getBackdropPath(),
                            response.getVoteCount(),
                            response.getPopularity(),
                            response.getOriginalLanguage()
                    ));
                    localRepository.insertMovies(tvShowEntities);
                }
            }
        }.asLiveData();

    }

    @Override
    public LiveData<Resource<MovieEntity>> getTvShowById(int tvShowId) {
        return new NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {

            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localRepository.getTvShowById(tvShowId);
            }

            @Override
            protected Boolean shouldFetch(MovieEntity data) {
                return data == null;
            }

            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(MovieResponse data) {

            }
        }.asLiveData();

    }




    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getFavoriteMovies() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {

            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavoriteMovies(), 20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getFavoriteTvShows() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {

            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavoriteTvShows(), 20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public void setFavoriteStatus(MovieEntity movieEntity) {
        boolean status = !movieEntity.getStatus();
        movieEntity.setStatus(status);
        Runnable runnable = () -> localRepository.updateFavoriteStatus(movieEntity);
        appExecutors.diskIO().execute(runnable);
    }
}
