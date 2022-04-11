package com.task.noteapp.viewmodel

import androidx.paging.PagingData
import com.task.noteapp.BaseTest
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.usecase.DeleteNote
import com.task.noteapp.domain.usecase.GetNotes
import com.task.noteapp.presentation.notes.NotesViewModel
import com.task.noteapp.util.getFakePagingSource
import io.mockk.coVerify
import io.mockk.every
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.TestInstance
import org.koin.test.inject


@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NotesViewModelTest : BaseTest() {

    private val deleteNote: DeleteNote by inject()
    private val getNotes: GetNotes by inject()
    private lateinit var notesViewModel: NotesViewModel

    @Before
    fun setup() {
        every { getNotes.getNotes() } returns getFakePagingSource()
        notesViewModel = NotesViewModel(getNotes, deleteNote, TestCoroutineScope())
    }

    @Test
    fun deleteNotesTest() {
        notesViewModel.deleteNotes(listOf())
        coVerify { deleteNote.deleteNoteById(listOf()) }
    }

    @Test
    fun getNotesTest() {
        val expected = PagingData.from(listOf<NoteEntity>())
        TestCoroutineScope().launch {
            every { getNotes.getNotes() } returns getFakePagingSource()
            Assert.assertEquals(expected, notesViewModel.pagedNotes.single())
        }
    }
}