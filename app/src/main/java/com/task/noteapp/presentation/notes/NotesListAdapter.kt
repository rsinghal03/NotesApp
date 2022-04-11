package com.task.noteapp.presentation.notes

import android.view.ActionMode
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.task.noteapp.R
import com.task.noteapp.data.localdatasource.entity.NoteEntity
import com.task.noteapp.databinding.NoteListItemBinding

class NotesListAdapter : PagingDataAdapter<NoteEntity, NotesListViewHolder>(ITEM_COMPARATOR) {

    var itemClickListener: ((noteId: Int, binding: NoteListItemBinding) -> Unit)? = null
    var itemLongClickListener: ((noteId: Int, binding: NoteListItemBinding) -> Unit)? = null

    var listOfSelectedItem = listOf<Int>()
    var actionMode: ActionMode? = null
    var onDeleteClickListener: ((list: List<Int>) -> Unit)? = null


    val actionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            actionMode = mode
            val inflater = mode?.menuInflater
            inflater?.inflate(R.menu.group_menu, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.delete -> {
                    onDeleteClickListener?.invoke(listOfSelectedItem)
                }
            }
            mode?.finish()
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
            listOfSelectedItem = listOf()
            notifyDataSetChanged()
        }
    }


    override fun onBindViewHolder(holder: NotesListViewHolder, position: Int) {
        getItem(position)?.also {
            holder.bind(it, isSelected(it.id))
        }
    }

    private fun isSelected(id: Int?): Boolean {
        return listOfSelectedItem.contains(id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesListViewHolder {
        val binding: NoteListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.note_list_item,
            parent,
            false
        )
        return NotesListViewHolder(binding, itemClickListener, itemLongClickListener)
    }

    fun selectItem(noteId: Int, binding: NoteListItemBinding) {
        when (actionMode) {
            null -> Unit
            else -> {
                if (listOfSelectedItem.contains(noteId)) {
                    listOfSelectedItem = listOfSelectedItem - noteId
                    binding.itemCard.setCardBackgroundColor(
                        binding.root.context.resources.getColor(
                            R.color.purple_200
                        )
                    )
                    if (listOfSelectedItem.isEmpty()) {
                        actionMode?.finish()
                    }
                } else {
                    listOfSelectedItem = listOfSelectedItem + noteId
                    binding.itemCard.setCardBackgroundColor(
                        binding.root.context.resources.getColor(
                            R.color.grey
                        )
                    )
                }
            }
        }
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