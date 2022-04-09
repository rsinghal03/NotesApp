package com.task.noteapp.data.localdatasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "Note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val url: String,
    val createdDate: OffsetDateTime,
    val editedDate: OffsetDateTime
)