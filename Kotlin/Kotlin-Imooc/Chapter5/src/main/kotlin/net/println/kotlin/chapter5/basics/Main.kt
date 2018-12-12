package net.println.kotlin.chapter5.basics

/**
 * Created by benny on 4/15/17.
 */
fun main(args: Array<String>) {
    args.forEach(::println)

    val helloWorld = Hello::world

    args.filter(String::isNotEmpty)

    val pdfPrinter = PdfPrinter()
    args.forEach(pdfPrinter::println)
}

class PdfPrinter{
    fun println(any: Any){
        kotlin.io.println(any)
    }
}

class Hello{
    fun world(){
        println("Hello World.")
    }
}