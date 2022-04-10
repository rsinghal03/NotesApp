package com.task.noteapp.presentation.notes

import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.databinding.NoteListItemBinding
import com.task.noteapp.extension.format

class NotesListViewHolder(
    private val newsListItemBinding: NoteListItemBinding,
    private val itemClick: ((item: NoteEntity?) -> Unit)?
) : RecyclerView.ViewHolder(newsListItemBinding.root) {

    fun bind(item: NoteEntity?) {
        newsListItemBinding.run {
            noteTitle.text = item?.title
            noteDescription.text = item?.description
            editedTag.isGone = item?.editedDate != null
            createdDate.text = item?.createdDate?.format()
            noteImage.isGone = true
        }
        bindClickListener(item)
    }

    private fun bindClickListener(item: NoteEntity?) {
        this.itemView.setOnClickListener {
            itemClick?.invoke(item)
        }
    }
}