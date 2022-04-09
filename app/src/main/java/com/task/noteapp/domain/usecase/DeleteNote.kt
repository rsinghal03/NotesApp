package com.task.noteapp.domain.usecase

import com.task.noteapp.data.localdatasource.entity.Note
import com.task.noteapp.domain.repository.NotesRepository

class DeleteNote(private val notesRepository: NotesRepository) {

    suspend fun deleteNote(note: Note) {
        notesRepository.deleteNote(note)
    }
}