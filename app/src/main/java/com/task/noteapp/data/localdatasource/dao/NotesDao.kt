package com.task.noteapp.data.localdatasource.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.task.noteapp.data.localdatasource.entity.NoteEntity

@Dao
interface NotesDao : BaseDao<NoteEntity> {

    @Query("select * from Notes")
    fun getNotesByPage(): PagingSource<Int, NoteEntity>

    @Query("SELECT * FROM Notes WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity

    @Query("Delete from Notes where id=:id")
    suspend fun deleteNoteById(id: Int)


}