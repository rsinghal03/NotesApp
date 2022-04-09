package com.task.noteapp.domain.repository

import com.task.noteapp.data.localdatasource.entity.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNotes(): Flow<List<Note>>
}