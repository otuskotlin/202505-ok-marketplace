package ru.otus.otuskotlin.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.time.delay
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.test.Test

class Ex10Dispatchers {
    private fun CoroutineScope.createCoro() {
        repeat(30) {
            launch {
                println("coroutine $it, start")
                delay(500)
                println("coroutine $it, end")
            }
        }
    }

    @Test
    fun default() = runBlocking(Dispatchers.Default) {
        createCoro()
    }

    @Test
    fun io() = runBlocking {
        withContext(Dispatchers.IO) { createCoro() }
    }

    @Test
    fun custom() = runBlocking {
//        val dispatcher = Executors.newFixedThreadPool(8).asCoroutineDispatcher()
        @OptIn(DelicateCoroutinesApi::class)
        val dispatcher = newFixedThreadPoolContext(1, "single")
        dispatcher.use {
            withContext(Job() + dispatcher) { createCoro() }
        }
    }

    @Test
    fun unconfined(): Unit = runBlocking(Dispatchers.Unconfined) {
        withContext(Dispatchers.Unconfined) {
            launch() {
                println("start coroutine ${Thread.currentThread().name}")
                suspendCoroutine {
                    println("suspend function, start ${Thread.currentThread().name}")
                    thread {
                        println("suspend function, background work ${Thread.currentThread().name}")
                        Thread.sleep(1000)
                        it.resume("Data! ${Thread.currentThread().name}")
                    }
                }
                println("end coroutine ${Thread.currentThread().name}")
            }
        }
    }
}