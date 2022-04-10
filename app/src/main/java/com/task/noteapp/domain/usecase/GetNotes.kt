package com.task.noteapp.domain.usecase

import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(
    private val notesRepository: NotesRepository,
    private val formatDateUseCase: FormatDate
) {

    fun getNotes(): Flow<List<NoteEntity>> {
        return notesRepository.getNotes()
    }
}