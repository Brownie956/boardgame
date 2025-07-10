package com.cbmedia.boardgame.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cbmedia.boardgame.data.models.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM Task ORDER BY id ASC")
    suspend fun getAll(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)
}

