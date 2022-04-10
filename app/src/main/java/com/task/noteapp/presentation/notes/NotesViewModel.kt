package com.task.noteapp.presentation.notes

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.usecase.GetNotes
import com.task.noteapp.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class NotesViewModel(private val getNotesUseCase: GetNotes) : BaseViewModel() {

    fun getNotes(): Flow<PagingData<NoteEntity>> {
        return getNotesUseCase.getNotesByPage().cachedIn(viewModelScope)
    }
}