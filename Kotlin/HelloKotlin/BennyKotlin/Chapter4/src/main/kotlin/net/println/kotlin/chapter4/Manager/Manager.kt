package net.println.kotlin.chapter4.Manager


/**
 * Created by benny on 4/3/17.
 */
class Manager: Driver, Writer {
    override fun write() {

    }

    override fun drive() {

    }
}

class SeniorManager(val driver: Driver, val writer: Writer): Driver by driver, Writer by writer

class CarDriver: Driver {
    override fun drive() {
        println("开车呢")
    }
}

class PPTWriter: Writer {
    override fun write() {
        println("做PPT呢")
    }

}

interface Driver{
    fun drive()
}

interface Writer{
    fun write()
}

fun main(args: Array<String>) {
    val driver = CarDriver()
    val writer = PPTWriter()
    val seniorManager = SeniorManager(driver, writer)
    seniorManager.drive()
    seniorManager.write()
}