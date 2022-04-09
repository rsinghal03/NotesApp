package com.task.noteapp.di

import com.task.noteapp.data.repositoryimplentation.NotesRepositoryImpl
import com.task.noteapp.domain.repository.NotesRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<NotesRepository> { NotesRepositoryImpl(get()) }
}