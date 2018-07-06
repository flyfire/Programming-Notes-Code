package net.println.kotlin.chapter5.currying

import java.io.OutputStream
import java.nio.charset.Charset

/**
 * Created by benny on 4/15/17.
 */
fun log(tag: String, target: OutputStream, message: Any?){
    target.write("[$tag] $message\n".toByteArray())
}

//fun log(tag: String)
//    = fun(target: OutputStream)
//    = fun(message: Any?)
//    = target.write("[$tag] $message\n".toByteArray())

fun main(args: Array<String>) {
    log("benny", System.out, "HelloWorld")
//    log("benny")(System.out)("HelloWorld Again.")
    ::log.curried()("benny")(System.out)("HelloWorld Again.")

    val consoleLogWithTag = (::log.curried())("benny")(System.out)
    consoleLogWithTag("HelloAgain Again.")
    consoleLogWithTag("HelloAgain Again.")
    consoleLogWithTag("HelloAgain Again.")
    consoleLogWithTag("HelloAgain Again.")

    val bytes = "我是中国人".toByteArray(charset("GBK"))
    val stringFromGBK = makeStringFromGbkBytes(bytes)
}

fun <P1, P2, P3, R> Function3<P1, P2, P3, R>.curried()
    = fun(p1: P1) = fun(p2: P2) = fun(p3: P3) = this(p1, p2, p3)

val makeString = fun(byteArray: ByteArray, charset: Charset): String{
    return String(byteArray, charset)
}

val makeStringFromGbkBytes = makeString.partial2(charset("GBK"))



fun <P1, P2, R> Function2<P1, P2, R>.partial2(p2: P2) = fun(p1: P1) = this(p1, p2)
fun <P1, P2, R> Function2<P1, P2, R>.partial1(p1: P1) = fun(p2: P2) = this(p1, p2)