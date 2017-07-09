package context_and_dispatchers

import kotlinx.coroutines.experimental.*

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.9 17:19
 */
/*
Naming coroutines for debugging
 */
fun main(args: Array<String>) = runBlocking(CoroutineName("main")) {
    log("Started main coroutine")
    // run two background value computations
    val v1 = async(CommonPool + CoroutineName("v1coroutine")) {
        log("Computing v1")
        delay(500)
        252
    }
    val v2 = async(CommonPool + CoroutineName("v2coroutine")) {
        log("Computing v2")
        delay(1000)
        6
    }
    log("The answer for v1 / v2 = ${v1.await() / v2.await()}")
}