package net.println.kotlin.chapter4

/**
 * Created by benny on 4/3/17.
 */
abstract class Person(open val age: Int){
    abstract fun work()
}

class MaNong(age: Int): Person(age){

    override val age: Int
        get() = 0

    override fun work() {
        println("我是码农，我在写代码")
    }
}

class Doctor(age: Int): Person(age){
    override fun work() {
        println("我是医生，我在给病人看病")
    }
}

fun main(args: Array<String>) {
    val person: Person = MaNong(23)
    person.work()
    println(person.age)

    val person2 : Person = Doctor(24)
    person2.work()
    println(person2.age)
}