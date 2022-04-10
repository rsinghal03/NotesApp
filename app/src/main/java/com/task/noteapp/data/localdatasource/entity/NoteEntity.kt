package com.task.noteapp.data.localdatasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "Notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val url: String = "",
    var createdDate: OffsetDateTime = OffsetDateTime.now(),
    var editedDate: OffsetDateTime? = null
)