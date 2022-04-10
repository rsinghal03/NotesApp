package com.task.noteapp.presentation.notes

import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.databinding.NoteListItemBinding

class NotesListViewHolder(
    private val newsListItemBinding: NoteListItemBinding,
    private val itemClick: ((item: NoteEntity?) -> Unit)?
) : RecyclerView.ViewHolder(newsListItemBinding.root) {

    fun bind(item: NoteEntity?) {
        newsListItemBinding.run {
            noteTitle.text = item?.title
            noteDescription.text = item?.description
        }
        bindClickListener(item)
    }

    private fun bindClickListener(item: NoteEntity?) {
        this.itemView.setOnClickListener {
            itemClick?.invoke(item)
        }
    }
}