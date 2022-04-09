package com.task.noteapp.data.localdatasource.dao

import androidx.room.Dao
import androidx.room.Query
import com.task.noteapp.data.localdatasource.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM Note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

}