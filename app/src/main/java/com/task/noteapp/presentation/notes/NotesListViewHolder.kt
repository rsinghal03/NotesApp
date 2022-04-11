package com.task.noteapp.presentation.notes

import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.R
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.databinding.NoteListItemBinding
import com.task.noteapp.extension.format

class NotesListViewHolder(
    private val noteItemBinding: NoteListItemBinding,
    private val itemClick: ((item: Int, binding: NoteListItemBinding) -> Unit)?,
    private val itemLongClick: ((item: Int, binding: NoteListItemBinding) -> Unit)?
) : RecyclerView.ViewHolder(noteItemBinding.root) {

    fun bind(item: NoteEntity, isSelected: Boolean) {
        noteItemBinding.run {
            noteTitle.text = item.title
            noteDescription.text = item.description
            editedTag.isGone = item.editedDate == null
            createdDate.text = item.createdDate.format()
            noteImage.isGone = true
            if (isSelected) {
                itemCard.setCardBackgroundColor(root.context.resources.getColor(R.color.grey))
            } else {
                itemCard.setCardBackgroundColor(root.context.resources.getColor(R.color.purple_200))
            }
        }
        bindClickListener(item.id)
    }

    private fun bindClickListener(item: Int) {
        this.itemView.setOnClickListener {
            itemClick?.invoke(item, noteItemBinding)
        }
        this.itemView.setOnLongClickListener {
            itemLongClick?.invoke(item, noteItemBinding)
            true
        }
    }
}