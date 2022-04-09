package com.task.noteapp.di

import com.task.noteapp.presentation.notes.NotesViewModel
import com.task.noteapp.presentation.notesdetail.NoteDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { NotesViewModel() }
    viewModel { NoteDetailViewModel() }
}