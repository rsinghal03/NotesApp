package com.task.noteapp.presentation.notes

import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.databinding.NoteListItemBinding
import com.task.noteapp.extension.format

class NotesListViewHolder(
    private val noteListItemBinding: NoteListItemBinding,
    private val itemClick: ((item: NoteEntity?, binding: NoteListItemBinding) -> Unit)?,
    private val itemLongClick: ((item: NoteEntity?, binding: NoteListItemBinding) -> Unit)?
) : RecyclerView.ViewHolder(noteListItemBinding.root) {

    fun bind(item: NoteEntity?) {
        noteListItemBinding.run {
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
            itemClick?.invoke(item, noteListItemBinding)
        }
        this.itemView.setOnLongClickListener {
            itemLongClick?.invoke(item, noteListItemBinding)
            true
        }
    }
}