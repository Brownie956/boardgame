package com.cbmedia.boardgame.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cbmedia.boardgame.data.models.BoardSpace

@Dao
interface BoardSpaceDao {

    @Query("SELECT * FROM BoardSpace ORDER BY position ASC")
    suspend fun getAll(): List<BoardSpace>

    @Query("SELECT * FROM BoardSpace WHERE position = :position LIMIT 1")
    suspend fun getByPosition(position: Int): BoardSpace?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(space: BoardSpace)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(spaces: List<BoardSpace>)

    @Query("DELETE FROM BoardSpace")
    suspend fun clearAll()
}
