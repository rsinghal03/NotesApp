package com.task.noteapp

import com.task.noteapp.domain.repository.NotesRepository
import com.task.noteapp.domain.usecase.DeleteNote
import com.task.noteapp.domain.usecase.GetNotes
import io.mockk.mockkClass
import org.koin.dsl.module

val module = module {

    //mock repository
    single { mockkClass(NotesRepository::class) }

    //use case
    single { mockkClass(GetNotes::class) }
    single { mockkClass((DeleteNote::class)) }

}