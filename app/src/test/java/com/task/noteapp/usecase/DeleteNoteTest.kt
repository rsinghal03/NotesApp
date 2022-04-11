package com.task.noteapp.usecase

import com.task.noteapp.BaseTest
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.repository.NotesRepository
import com.task.noteapp.domain.usecase.DeleteNote
import io.mockk.coVerify
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Test
import org.koin.test.inject

@ExperimentalCoroutinesApi
class DeleteNoteTest : BaseTest() {

    private val notesRepository: NotesRepository by inject()
    private val deleteNote: DeleteNote by lazy { DeleteNote(notesRepository) }

    @Test
    fun deleteNoteTest() {
        val note = mockkClass(NoteEntity::class)
        TestCoroutineScope().launch {
            deleteNote.deleteNote(note)
        }
        coVerify { notesRepository.deleteNote(note) }
    }

    @Test
    fun deleteNotesById() {
        TestCoroutineScope().launch {
            deleteNote.deleteNoteById(listOf())
        }
        coVerify { notesRepository.deleteNote(listOf()) }
    }
}