package com.task.noteapp.presentation.notes

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.task.noteapp.BR
import com.task.noteapp.R
import com.task.noteapp.databinding.FramentNotesBinding
import com.task.noteapp.presentation.base.BaseFragment
import com.task.noteapp.util.Constant
import org.koin.android.ext.android.inject

class NotesFragment : BaseFragment<FramentNotesBinding, NotesViewModel>() {

    private val notesViewModel: NotesViewModel by inject()

    private val notesAdapter: NotesListAdapter by lazy { NotesListAdapter() }

    override fun getLayoutId(): Int = R.layout.frament_notes

    override fun getViewModel(): NotesViewModel = notesViewModel

    override fun getBindingVariable(): Int = BR.notesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    /**
     * Initialise adapter
     *
     */
    private fun initAdapter() {
        setRecyclerProperty()
        setListItemClickListener()
        setAddNoteClickListener()
    }

    private fun setAddNoteClickListener() {
        viewDataBinding?.addNoteFab?.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_noteDetailFragment)
        }
    }

    /**
     * Set recycler view property
     *
     */
    private fun setRecyclerProperty() {
        viewDataBinding?.notesRecyclerView?.run {
            adapter = notesAdapter
            setHasFixedSize(true)
        }
    }

    /**
     * on list item click, navigate to details fragment
     *
     */
    private fun setListItemClickListener() {
        notesAdapter.itemClickListener = {
            val bundle = bundleOf(
                Constant.NOTE_ID to it?.id
            )
            findNavController().navigate(
                R.id.action_notesFragment_to_noteDetailFragment,
                bundle
            )
        }
    }

}