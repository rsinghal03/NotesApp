package com.task.noteapp.di

import com.task.noteapp.domain.usecase.AddNote
import com.task.noteapp.domain.usecase.DeleteNote
import com.task.noteapp.domain.usecase.GetNotes
import org.koin.dsl.module

val useCaseModule = module {

    factory { AddNote(get()) }
    factory { GetNotes(get()) }
    factory { DeleteNote(get()) }
}