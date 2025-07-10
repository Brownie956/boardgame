package com.cbmedia.boardgame.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cbmedia.boardgame.data.models.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM Category ORDER BY id ASC")
    suspend fun getAll(): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category)
}
