package com.task.noteapp.presentation.notesdetail

import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.usecase.AddNote
import com.task.noteapp.domain.usecase.GetNote
import com.task.noteapp.domain.usecase.UpdateNote
import com.task.noteapp.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime
import timber.log.Timber

class NoteDetailViewModel(
    private val addNoteUseCase: AddNote,
    private val updateNote: UpdateNote,
    private val getNoteUseCase: GetNote,
    noteId: Int
) : BaseViewModel() {

    private val _noteDetail = MutableStateFlow(NoteEntity())
    val noteDetail get() = _noteDetail.asStateFlow()

    init {
        getNoteById(noteId)
    }

    fun createOrUpdate(title: String, body: String) {
        Timber.d("title:$title, body$body")
        viewModelScope.launch {
            val note = _noteDetail.value.copy(
                title = title,
                description = body
            )
            if (note.id == 0) {
                addNoteUseCase.addNote(note)
            } else {
                note.editedDate = OffsetDateTime.now()
                updateNote.updateNote(note)
            }
        }
    }

    private fun getNoteById(id: Int) {
        viewModelScope.launch {
            if (id != -1)
                _noteDetail.value = getNoteUseCase.getNote(id)
        }
    }
}