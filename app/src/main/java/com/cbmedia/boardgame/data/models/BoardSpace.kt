package com.cbmedia.boardgame.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [
        Index(value = ["categoryId"]),
        Index(value = ["specificTaskId"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["specificTaskId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class BoardSpace(
    @PrimaryKey val position: Int,
    val categoryId: Int? = null,
    val specificTaskId: Int? = null
)
