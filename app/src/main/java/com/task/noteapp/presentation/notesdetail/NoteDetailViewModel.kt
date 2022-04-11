package com.task.noteapp.presentation.notesdetail

import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.usecase.AddNote
import com.task.noteapp.domain.usecase.GetNote
import com.task.noteapp.presentation.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class NoteDetailViewModel(
    private val addNoteUseCase: AddNote,
    private val getNoteUseCase: GetNote,
    noteId: Int,
    private val io: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : BaseViewModel() {

    private val _noteDetail = MutableStateFlow(NoteEntity())
    val noteDetail get() = _noteDetail.asStateFlow()

    init {
        getNoteById(noteId)
    }

    fun createOrUpdate(title: String, body: String) {
        Timber.d("title:$title, body$body")
        io.launch {
            val isEdited = noteDetail.value.title != title || noteDetail.value.description != body
            val note = _noteDetail.value.copy(
                title = title,
                description = body
            )
            addNoteUseCase.addNote(note, isEdited)
        }
    }

    private fun getNoteById(id: Int) {
        viewModelScope.launch {
            if (id != -1)
                _noteDetail.value = getNoteUseCase.getNote(id)
        }
    }
}