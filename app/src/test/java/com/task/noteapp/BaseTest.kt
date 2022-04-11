package com.task.noteapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
open class BaseTest : KoinTest {

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    open fun setUp() {
        startKoin { modules(module) }
        Dispatchers.setMain(dispatcher)
    }

    @After
    open fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }
}