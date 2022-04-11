package com.task.noteapp.domain.repository

import androidx.paging.PagingSource
import com.task.noteapp.data.localdatasource.entity.NoteEntity

interface NotesRepository {

    suspend fun insertNote(note: NoteEntity)

    suspend fun deleteNote(note: NoteEntity)

    suspend fun deleteNote(ids: List<Int>)

    suspend fun updateNote(note: NoteEntity)

    suspend fun getNoteById(id: Int): NoteEntity

    fun getNotes(): PagingSource<Int, NoteEntity>
}