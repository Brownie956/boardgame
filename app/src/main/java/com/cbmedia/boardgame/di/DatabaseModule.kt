package com.cbmedia.boardgame.di

import android.content.Context
import androidx.room.Room
import com.cbmedia.boardgame.data.db.AppDatabase
import com.cbmedia.boardgame.data.dao.BoardSpaceDao
import com.cbmedia.boardgame.data.dao.CategoryDao
import com.cbmedia.boardgame.data.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "board_game_db"
        ).build()
    }

    @Provides
    fun provideBoardSpaceDao(db: AppDatabase): BoardSpaceDao = db.boardSpaceDao()

    @Provides
    fun provideCategoryDao(db: AppDatabase): CategoryDao = db.categoryDao()

    @Provides
    fun provideTaskDao(db: AppDatabase): TaskDao = db.taskDao()
}
