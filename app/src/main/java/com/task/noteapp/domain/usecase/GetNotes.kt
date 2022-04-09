package com.task.noteapp.domain.usecase

import com.task.noteapp.data.localdatasource.entity.Note
import com.task.noteapp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(private val notesRepository: NotesRepository) {

    suspend fun getNotes(): Flow<List<Note>> {
        return notesRepository.getNotes()
    }
}