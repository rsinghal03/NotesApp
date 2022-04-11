package com.task.noteapp.domain.usecase

import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.repository.NotesRepository

class DeleteNote(private val notesRepository: NotesRepository) {

    suspend fun deleteNote(note: NoteEntity) {
        notesRepository.deleteNote(note)
    }

    suspend fun deleteNoteById(ids: List<Int>) {
        ids.forEach { id ->
            notesRepository.deleteNote(id)
        }
    }
}