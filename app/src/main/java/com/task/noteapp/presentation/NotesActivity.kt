package com.task.noteapp.presentation

import com.task.noteapp.R
import com.task.noteapp.databinding.ActivityMainBinding
import com.task.noteapp.presentation.base.BaseActivity
import com.task.noteapp.presentation.base.BaseViewModel
import com.task.noteapp.presentation.notes.NotesViewModel
import org.koin.android.ext.android.inject

class NotesActivity : BaseActivity<ActivityMainBinding>() {

    private val notesViewModel: NotesViewModel by inject()

    override fun getViewModel(): BaseViewModel = notesViewModel

    override var layoutId: Int = R.layout.activity_main
}