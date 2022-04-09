package com.task.noteapp

import android.app.Application
import com.task.noteapp.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class NoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@NoteApplication)
            modules(modules = applicationModule)
        }
    }
}