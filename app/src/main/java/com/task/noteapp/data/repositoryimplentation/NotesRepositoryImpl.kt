package com.task.noteapp.data.repositoryimplentation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
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

    override suspend fun deleteNote(id: Int) {
        notesDao.deleteNoteById(id)
    }

    override suspend fun updateNote(note: NoteEntity) {
        notesDao.update(note)
    }

    override suspend fun getNoteById(id: Int): NoteEntity {
        return notesDao.getNoteById(id)
    }

    override fun getNotesByPage(): Flow<PagingData<NoteEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            )
        ) {
            notesDao.getNotesByPage()
        }.flow
    }

}