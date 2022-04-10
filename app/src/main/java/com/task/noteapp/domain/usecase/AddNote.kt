package com.task.noteapp.domain.usecase

import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.repository.NotesRepository

class AddNote(private val notesRepository: NotesRepository) {

    suspend fun addNote(note: NoteEntity) {
        notesRepository.insertNote(note)
    }
}