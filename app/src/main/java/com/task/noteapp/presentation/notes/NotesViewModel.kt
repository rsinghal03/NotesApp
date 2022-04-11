package com.task.noteapp.presentation.notes

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.usecase.DeleteNote
import com.task.noteapp.domain.usecase.GetNotes
import com.task.noteapp.presentation.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NotesViewModel(
    private val getNotesUseCase: GetNotes,
    private val deleteNoteUseCase: DeleteNote,
    private val io: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : BaseViewModel() {

    fun getNotes(): Flow<PagingData<NoteEntity>> {
        return getNotesUseCase.getNotesByPage().cachedIn(viewModelScope)
    }

    fun deleteNotes(ids: List<Int>) {
        io.launch {
            deleteNoteUseCase.deleteNoteById(ids)
        }
    }
}