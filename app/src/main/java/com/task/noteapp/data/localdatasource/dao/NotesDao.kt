package com.task.noteapp.data.localdatasource.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao : BaseDao<NoteEntity> {

    @Query("SELECT * FROM Notes")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("select * from Notes")
    fun getNotesByPage(): PagingSource<Int, NoteEntity>

    @Query("SELECT * FROM Notes WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity

    @Delete
    suspend fun deleteNote(note: List<NoteEntity>)

}