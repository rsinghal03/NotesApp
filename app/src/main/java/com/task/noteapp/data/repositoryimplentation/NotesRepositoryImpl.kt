package com.task.noteapp.data.repositoryimplentation

import com.task.noteapp.data.localdatasource.dao.NotesDao
import com.task.noteapp.data.localdatasource.entity.Note
import com.task.noteapp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(private val notesDao: NotesDao) : NotesRepository {
    override suspend fun insertNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun getNotes(): Flow<List<Note>> {
        TODO("Not yet implemented")
    }

}