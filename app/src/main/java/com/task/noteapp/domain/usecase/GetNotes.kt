package com.task.noteapp.domain.usecase

import androidx.paging.PagingData
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(
    private val notesRepository: NotesRepository
) {

    fun getNotesByPage(): Flow<PagingData<NoteEntity>> {
        return notesRepository.getNotesByPage()
    }
}