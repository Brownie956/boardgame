package com.cbmedia.boardgame.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cbmedia.boardgame.data.dao.BoardSpaceDao
import com.cbmedia.boardgame.data.dao.CategoryDao
import com.cbmedia.boardgame.data.dao.TaskDao
import com.cbmedia.boardgame.data.models.BoardSpace
import com.cbmedia.boardgame.data.models.Category
import com.cbmedia.boardgame.data.models.Task

@Database(
    entities = [BoardSpace::class, Category::class, Task::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun boardSpaceDao(): BoardSpaceDao
    abstract fun categoryDao(): CategoryDao
    abstract fun taskDao(): TaskDao
}
