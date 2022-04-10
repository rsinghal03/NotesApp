package com.task.noteapp.data.repositoryimplentation

import com.task.noteapp.data.localdatasource.dao.NotesDao
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(private val notesDao: NotesDao) : NotesRepository {

    override suspend fun insertNote(note: NoteEntity) {
        notesDao.insert(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        notesDao.delete(note)
    }

    override suspend fun updateNote(note: NoteEntity) {
        notesDao.update(note)
    }

    override suspend fun getNoteById(id: Int): NoteEntity {
        return notesDao.getNoteById(id)
    }

    override fun getNotes(): Flow<List<NoteEntity>> {
        return notesDao.getNotes()
    }
}