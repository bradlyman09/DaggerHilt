package org.android.daggerhilt.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.android.daggerhilt.room.MovieDatabase
import org.android.daggerhilt.room.dao.MovieDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule{

    @Singleton
    @Provides
    fun providesMovieDatabase(@ApplicationContext context: Context) : MovieDatabase{
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            MovieDatabase.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesMovieDao(movieDatabase: MovieDatabase) : MovieDao{
        return movieDatabase.movieDao()
    }
}