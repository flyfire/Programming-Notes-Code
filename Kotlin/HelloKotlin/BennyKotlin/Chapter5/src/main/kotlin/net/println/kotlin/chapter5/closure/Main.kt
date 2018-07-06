package net.println.kotlin.chapter5.closure

/**
 * Created by benny on 4/15/17.
 */
val string = "HelloWorld"

fun makeFun(): ()->Unit{
    var count = 0
    return fun(){
        println(++count)
    }
}

fun fibonacci(): Iterable<Long>{
    var first = 0L
    var second = 1L
    return Iterable {
        object : LongIterator(){
            override fun nextLong(): Long {
                val result = second
                second += first
                first = second - first
                return result
            }

            override fun hasNext() = true

        }

    }
}

fun main(args: Array<String>) {
   val add5 = add(5)
    println(add5(2))
}

//fun add(x: Int) = fun(y: Int) = x + y

fun add(x: Int): (Int)-> Int{

    data class Person(val name: String, val age: Int)

    return fun(y: Int): Int{
        return x + y
    }
}
