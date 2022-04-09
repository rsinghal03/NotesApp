package com.task.noteapp.domain.usecase

import com.task.noteapp.data.localdatasource.entity.Note
import com.task.noteapp.domain.repository.NotesRepository

class AddNote(private val notesRepository: NotesRepository) {

    suspend fun addNote(note: Note) {
        notesRepository.insertNote(note)
    }
}