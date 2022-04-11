package com.task.noteapp.viewmodel

import androidx.paging.PagingData
import com.task.noteapp.BaseTest
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.usecase.DeleteNote
import com.task.noteapp.domain.usecase.GetNotes
import com.task.noteapp.presentation.notes.NotesViewModel
import io.mockk.coVerify
import io.mockk.every
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.TestInstance
import org.koin.test.inject


@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NotesViewModelTest : BaseTest() {

    private val notesViewModel: NotesViewModel by inject()
    private val deleteNote: DeleteNote by inject()
    private val getNotes: GetNotes by inject()

    @Test
    fun deleteNotesTest() {
        notesViewModel.deleteNotes(listOf())
        coVerify { deleteNote.deleteNoteById(listOf()) }
    }

    @Test
    fun getNotesTest() {
        val expected = PagingData.from(listOf<NoteEntity>())
        TestCoroutineScope().launch {
            val flow =
                MutableSharedFlow<PagingData<NoteEntity>>(replay = 1).apply { emit(expected) }
            every { getNotes.getNotesByPage() } returns flow
            Assert.assertEquals(expected, notesViewModel.getNotes().single())
        }
    }
}