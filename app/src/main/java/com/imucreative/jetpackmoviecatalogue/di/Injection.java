package com.imucreative.jetpackmoviecatalogue.di;

import android.content.Context;

import com.imucreative.jetpackmoviecatalogue.data.source.MovieRepository;
import com.imucreative.jetpackmoviecatalogue.data.source.remote.RemoteDataSource;
import com.imucreative.jetpackmoviecatalogue.utils.JsonHelper;

public class Injection {
    public static MovieRepository provideRepository(Context context) {

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));

        return MovieRepository.getInstance(remoteDataSource);
    }
}
