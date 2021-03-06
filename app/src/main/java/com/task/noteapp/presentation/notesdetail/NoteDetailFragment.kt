package com.task.noteapp.presentation.notesdetail

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentNotesDetailsBinding
import com.task.noteapp.presentation.base.BaseFragment
import com.task.noteapp.util.Constant
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NoteDetailFragment : BaseFragment<FragmentNotesDetailsBinding, NoteDetailViewModel>() {

    private val noteDetailViewModel: NoteDetailViewModel by viewModel {
        parametersOf(
            requireArguments().getInt(Constant.NOTE_ID)
        )
    }

    override fun getLayoutId(): Int = R.layout.fragment_notes_details

    override fun getViewModel(): NoteDetailViewModel = noteDetailViewModel

    override fun getBindingVariable(): Int = BR.noteDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
        initObserver()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                noteDetailViewModel.noteDetail.collect {
                    viewDataBinding?.noteDetailBody?.setText(it.description)
                    viewDataBinding?.noteDetailTitle?.setText(it.title)
                }
            }
        }
    }

    private fun setUpListener() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    noteDetailViewModel.createOrUpdate(
                        viewDataBinding?.noteDetailTitle?.text.toString(),
                        viewDataBinding?.noteDetailBody?.text.toString()
                    )
                    findNavController().popBackStack()
                }
            })
    }

}