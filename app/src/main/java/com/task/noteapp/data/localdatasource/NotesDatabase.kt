package com.task.noteapp.data.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.noteapp.data.localdatasource.converter.DateTypeConverters
import com.task.noteapp.data.localdatasource.dao.NotesDao
import com.task.noteapp.data.localdatasource.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
@TypeConverters(DateTypeConverters::class)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun getNotesDao(): NotesDao
}