package com.task.noteapp.di

import androidx.room.Room
import com.task.noteapp.data.localdatasource.NotesDatabase
import com.task.noteapp.data.localdatasource.dao.NotesDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        val builder = Room.databaseBuilder(
            androidContext(),
            NotesDatabase::class.java,
            "NotesDatabase.db"
        )
        builder.build()
    }
    single<NotesDao> { get<NotesDatabase>().getNotesDao() }

}