package com.imucreative.jetpackmoviecatalogue.di;

import android.content.Context;

import com.imucreative.jetpackmoviecatalogue.data.source.MovieRepository;
import com.imucreative.jetpackmoviecatalogue.data.source.local.LocalRepository;
import com.imucreative.jetpackmoviecatalogue.data.source.local.room.MovieDatabase;
import com.imucreative.jetpackmoviecatalogue.data.source.remote.RemoteDataSource;
import com.imucreative.jetpackmoviecatalogue.utils.AppExecutors;
import com.imucreative.jetpackmoviecatalogue.utils.JsonHelper;

public class Injection {
    public static MovieRepository provideRepository(Context context) {

        MovieDatabase database = MovieDatabase.getInstance(context);

        LocalRepository localRepository = LocalRepository.getInstance(database.movieDao());
        RemoteDataSource remoteRepository = RemoteDataSource.getInstance(new JsonHelper(context));
        AppExecutors appExecutors = new AppExecutors();

        return MovieRepository.getInstance(localRepository, remoteRepository, appExecutors);
    }
}
