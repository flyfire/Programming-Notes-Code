package net.println.kotlin.chapter3

/**
 * Created by benny on 3/19/17.
 */
fun main(args: Array<String>) {

    var x = 5
    while(x > 0){
        println(x)
        x--
    }

    do{
        println(x)
        x--
    }while (x > 0)

//    for (arg in args){
//        println(arg)
//    }
//
//    for((index, value) in args.withIndex()){
//        println("$index -> $value")
//    }
//
//    for(indexedValue in args.withIndex()){
//        println("${indexedValue.index} -> ${indexedValue.value}")
//    }
//
//    val list = MyIntList()
//    list.add(1)
//    list.add(2)
//    list.add(3)
//
//    for(i in list){
//        println(i)
//    }
}

class MyIterator(val iterator: Iterator<Int>){
    operator fun next(): Int{
        return iterator.next()
    }

    operator fun hasNext(): Boolean{
        return iterator.hasNext()
    }
}

class MyIntList{
    private val list = ArrayList<Int>()

    fun add(int : Int){
        list.add(int)
    }

    fun remove(int: Int){
        list.remove(int)
    }

    operator fun iterator(): MyIterator{
        return MyIterator(list.iterator())
    }
}