package com.task.noteapp.presentation.notes

import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.usecase.GetNotes
import com.task.noteapp.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NotesViewModel(private val getNotesUseCase: GetNotes) : BaseViewModel() {

    init {
        getNotes()
    }

    private val _notes = MutableStateFlow(listOf<NoteEntity>())
    val notes = _notes.asStateFlow()

    private fun getNotes() {
        viewModelScope.launch {
            getNotesUseCase.getNotes().collect {
                _notes.value = it
            }
        }
    }

}