package com.task.noteapp.presentation.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.R
import com.task.noteapp.data.localdatasource.entity.Note
import com.task.noteapp.databinding.NoteListItemBinding

class NotesListAdapter : RecyclerView.Adapter<NotesListViewHolder>() {

    var itemClickListener: ((item: Note?) -> Unit)? = null

    var notes: List<Note> = ArrayList()
        set(value) {
            if (field === value) return

            val diff = DiffUtil.calculateDiff(getDiffUtilCallback(field, value))
            field = value
            diff.dispatchUpdatesTo(this)
        }

    override fun onBindViewHolder(holder: NotesListViewHolder, position: Int) {
        holder.bind(notes[position])
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

    override fun getItemCount(): Int = notes.size

    private fun getDiffUtilCallback(oldData: List<Note>, newData: List<Note>): DiffUtil.Callback {
        return object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldData.size

            override fun getNewListSize(): Int = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldData[oldItemPosition].id == newData[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = oldData[oldItemPosition]
                val newItem = newData[newItemPosition]
                return oldItem.createdDate == newItem.createdDate && oldItem.title == newItem.title && oldItem.description == newItem.description && oldItem.url ==
                        newItem.url && oldItem.editedDate == newItem.editedDate
            }
        }
    }
}