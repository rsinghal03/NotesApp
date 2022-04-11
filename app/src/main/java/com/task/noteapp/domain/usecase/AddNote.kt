package com.task.noteapp.domain.usecase

import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.repository.NotesRepository
import com.task.noteapp.extension.isValid
import com.task.noteapp.util.Constant

class AddNote(
    private val deleteNoteUseCase: DeleteNote,
    private val updateNoteUseCase: UpdateNote,
    private val notesRepository: NotesRepository
) {

    suspend fun addNote(note: NoteEntity, isEdited: Boolean) {
        when (note.id) {
            Constant.NEW_NOTE_ID -> {
                if (note.isValid())
                    notesRepository.insertNote(note)
            }
            else -> {
                when {
                    note.isValid() -> {
                        updateNoteUseCase.updateNote(note, isEdited)
                    }
                    else -> deleteNoteUseCase.deleteNote(note)
                }
            }
        }
    }
}