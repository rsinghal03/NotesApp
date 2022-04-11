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
import timber.log.Timber

class NotesListAdapter : PagingDataAdapter<NoteEntity, NotesListViewHolder>(ITEM_COMPARATOR) {

    var itemClickListener: ((item: NoteEntity?, binding: NoteListItemBinding) -> Unit)? = null
    var itemLongClickListener: ((item: NoteEntity?, binding: NoteListItemBinding) -> Unit)? = null

    val listOfSelectedItem: MutableList<NoteEntity> = mutableListOf()
    var isActionModeOn = false
    var actionMode: ActionMode? = null
    var onDeleteClickListener: ((list: List<NoteEntity>) -> Unit)? = null


    val actionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            actionMode = mode
            isActionModeOn = true
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
                    val list = arrayListOf<NoteEntity>().apply { addAll(listOfSelectedItem) }
                    onDeleteClickListener?.invoke(list)
                }
            }
            mode?.finish()
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            isActionModeOn = false
            actionMode = null
            listOfSelectedItem.clear()
            notifyDataSetChanged()
            Timber.d("onDestroyActionMode")
        }
    }


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
        return NotesListViewHolder(binding, itemClickListener, itemLongClickListener)
    }

    fun selectItem(item: NoteEntity?, binding: NoteListItemBinding) {
        when (actionMode) {
            null -> Unit
            else -> {
                if (listOfSelectedItem.contains(item)) {
                    listOfSelectedItem.remove(item)
                    binding.itemCard.setCardBackgroundColor(
                        binding.root.context.resources.getColor(
                            R.color.purple_200
                        )
                    )
                    if (listOfSelectedItem.size == 0) {
                        actionMode?.finish()
                    }
                } else {
                    item?.let { listOfSelectedItem.add(it) }
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