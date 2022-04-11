package com.task.noteapp.presentation.notes

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.usecase.DeleteNote
import com.task.noteapp.domain.usecase.GetNotes
import com.task.noteapp.presentation.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NotesViewModel(
    private val getNotesUseCase: GetNotes,
    private val deleteNoteUseCase: DeleteNote,
    private val io: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : BaseViewModel() {

    private val _pagedNotes: MutableStateFlow<PagingData<NoteEntity>> =
        MutableStateFlow(PagingData.from(listOf()))
    val pagedNotes: StateFlow<PagingData<NoteEntity>> = _pagedNotes.asStateFlow()

    init {
        viewModelScope.launch {
            Pager(config = PagingConfig(10)) { getNotesUseCase.getNotes() }
                .flow
                .cachedIn(viewModelScope)
                .collectLatest { _pagedNotes.emit(it) }
        }
    }

    fun deleteNotes(ids: List<Int>) {
        io.launch {
            deleteNoteUseCase.deleteNoteById(ids)
        }
    }
}