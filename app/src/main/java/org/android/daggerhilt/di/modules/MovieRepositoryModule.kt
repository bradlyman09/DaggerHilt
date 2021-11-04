package org.android.daggerhilt.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.daggerhilt.mapper.MovieMapper
import org.android.daggerhilt.network.services.MovieRetrofit
import org.android.daggerhilt.repository.MovieRepository
import org.android.daggerhilt.network.AppDispatchersImpl
import org.android.daggerhilt.room.dao.MovieDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieRepositoryModule {

    @Singleton
    @Provides
    fun providesMovieRepository(movieRetrofit: MovieRetrofit, movieDao: MovieDao, movieMapper: MovieMapper, appDispatchersImpl: AppDispatchersImpl) : MovieRepository{
        return MovieRepository(movieRetrofit, movieDao, movieMapper, appDispatchersImpl)
    }
}