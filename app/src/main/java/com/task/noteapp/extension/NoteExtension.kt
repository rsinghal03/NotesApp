package com.task.noteapp.extension

import com.task.noteapp.data.localdatasource.entity.NoteEntity

fun NoteEntity.isValid() = this.title.isNotBlank() && description.isNotBlank()