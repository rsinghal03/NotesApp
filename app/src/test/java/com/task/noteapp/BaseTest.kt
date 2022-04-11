package com.task.noteapp

import android.content.Context
import io.mockk.mockkClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
open class BaseTest : KoinTest {

    private val mockContext = mockkClass(Context::class)
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    open fun setUp() {
        startKoin {
            androidContext(mockContext)
            modules(module)
        }
        Dispatchers.setMain(dispatcher)
    }

    @After
    open fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }
}