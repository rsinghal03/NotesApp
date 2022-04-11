package com.task.noteapp.domain.usecase

import androidx.paging.PagingSource
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.repository.NotesRepository

class GetNotes(private val notesRepository: NotesRepository) {

    fun getNotes(): PagingSource<Int, NoteEntity> {
        return notesRepository.getNotes()
    }
}