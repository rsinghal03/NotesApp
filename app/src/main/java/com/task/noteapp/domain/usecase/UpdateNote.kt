package com.task.noteapp.domain.usecase

import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.repository.NotesRepository

class UpdateNote(private val notesRepository: NotesRepository) {

    suspend fun updateNote(note: NoteEntity) {
        notesRepository.updateNote(note)
    }

}