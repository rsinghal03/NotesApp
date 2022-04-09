package com.task.noteapp.domain.repository

interface NotesRepository {

    suspend fun insertNote()

    suspend fun deleteNote()
}