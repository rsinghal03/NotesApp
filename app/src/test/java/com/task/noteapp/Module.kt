package com.task.noteapp

import com.task.noteapp.domain.repository.NotesRepository
import com.task.noteapp.domain.usecase.DeleteNote
import com.task.noteapp.domain.usecase.GetNotes
import com.task.noteapp.presentation.notes.NotesViewModel
import io.mockk.mockkClass
import kotlinx.coroutines.test.TestCoroutineScope
import org.koin.dsl.module

val module = module {

    //mock repository
    single { mockkClass(NotesRepository::class) }

    // viewModel
    single { NotesViewModel(get(), get(), TestCoroutineScope()) }

    //use case
    single { mockkClass(GetNotes::class) }
    single { mockkClass((DeleteNote::class)) }

}