package com.task.noteapp.domain.repository

import androidx.paging.PagingData
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun insertNote(note: NoteEntity)

    suspend fun deleteNote(note: NoteEntity)

    suspend fun deleteNote(ids: List<Int>)

    suspend fun updateNote(note: NoteEntity)

    suspend fun getNoteById(id: Int): NoteEntity

    fun getNotesByPage(): Flow<PagingData<NoteEntity>>
}