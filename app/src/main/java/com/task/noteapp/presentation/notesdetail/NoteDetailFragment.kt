package com.task.noteapp.presentation.notesdetail

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentNotesDetailsBinding
import com.task.noteapp.presentation.base.BaseFragment
import org.koin.android.ext.android.inject

class NoteDetailFragment : BaseFragment<FragmentNotesDetailsBinding, NoteDetailViewModel>() {

    private val noteDetailViewModel: NoteDetailViewModel by inject()

    override fun getLayoutId(): Int = R.layout.fragment_notes_details

    override fun getViewModel(): NoteDetailViewModel = noteDetailViewModel

    override fun getBindingVariable(): Int = BR.noteDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}