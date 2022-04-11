package com.task.noteapp.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.task.noteapp.data.localdatasource.entity.NoteEntity

fun getFakePagingSource(): PagingSource<Int, NoteEntity> {
    return object : PagingSource<Int, NoteEntity>() {
        override fun getRefreshKey(state: PagingState<Int, NoteEntity>): Int? {
            return 1
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NoteEntity> {
            return LoadResult.Page(listOf(), 1, 2)
        }
    }
}