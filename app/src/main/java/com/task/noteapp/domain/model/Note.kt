package com.task.noteapp.domain.model

data class Note(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val edited: Boolean = false,
    val createdDate: String = ""
)
