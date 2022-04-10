package com.task.noteapp.domain.usecase

import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.repository.NotesRepository

class GetNote(private val notesRepository: NotesRepository) {

    suspend fun getNote(id: Int): NoteEntity {
        return notesRepository.getNoteById(id)
    }
}