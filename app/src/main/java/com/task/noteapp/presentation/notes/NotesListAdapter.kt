package com.task.noteapp.presentation.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.task.noteapp.R
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.databinding.NoteListItemBinding

class NotesListAdapter : PagingDataAdapter<NoteEntity, NotesListViewHolder>(ITEM_COMPARATOR) {

    var itemClickListener: ((item: NoteEntity?) -> Unit)? = null

    override fun onBindViewHolder(holder: NotesListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesListViewHolder {
        val binding: NoteListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.note_list_item,
            parent,
            false
        )
        return NotesListViewHolder(binding, itemClickListener)
    }

    companion object {
        val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<NoteEntity>() {
            override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean =
                oldItem.id == newItem.id
        }
    }
}