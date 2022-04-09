package com.task.noteapp.presentation.notes

import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.data.localdatasource.entity.Note
import com.task.noteapp.databinding.NoteListItemBinding

class NotesListViewHolder(
    private val newsListItemBinding: NoteListItemBinding,
    private val itemClick: ((item: Note?) -> Unit)?
) : RecyclerView.ViewHolder(newsListItemBinding.root) {

    fun bind(item: Note?) {
        newsListItemBinding.run {
            noteTitle.text = item?.title
            noteDescription.text = item?.description
        }
    }

    private fun bindClickListener(item: Note?) {
        this.itemView.setOnClickListener {
            itemClick?.invoke(item)
        }
    }
}