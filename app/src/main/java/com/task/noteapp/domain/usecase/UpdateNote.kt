package com.task.noteapp.domain.usecase

import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.repository.NotesRepository
import org.threeten.bp.OffsetDateTime

class UpdateNote(private val notesRepository: NotesRepository) {

    suspend fun updateNote(note: NoteEntity, isEdited: Boolean) {
        if (isEdited) {
            note.editedDate = OffsetDateTime.now()
            notesRepository.updateNote(note)
        }
    }
}