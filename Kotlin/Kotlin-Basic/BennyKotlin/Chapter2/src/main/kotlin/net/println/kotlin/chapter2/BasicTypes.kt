package net.println.kotlin.chapter2

/**
 * Created by benny on 2/26/17.
 */
val aBoolean: Boolean = true
val anotherBoolean: Boolean = false

val anInt: Int = 8
val anotherInt: Int = 0xFF
val moreInt: Int = 0b00000011
val maxInt: Int = Int.MAX_VALUE
val minInt: Int = Int.MIN_VALUE

val aLong: Long = 12368172397127391
val another: Long = 123
val maxLong: Long = Long.MAX_VALUE
val minLong: Long = Long.MIN_VALUE

val aFloat: Float = 2.0F
val anotherFloat: Float = 1E3f
val maxFloat: Float = Float.MAX_VALUE
val minFloat: Float = -Float.MAX_VALUE

val aDouble: Double = 3.0
val anotherDouble: Double = 3.1415926
val maxDouble: Double= Double.MAX_VALUE
val minDouble: Double= -Double.MAX_VALUE

val aShort: Short = 127
val maxShort: Short = Short.MAX_VALUE
val minShort: Short = Short.MIN_VALUE

val maxByte: Byte = Byte.MAX_VALUE
val minByte: Byte = Byte.MIN_VALUE

fun main(args: Array<String>) {
    println(anotherInt)
    println(moreInt)

    println(maxInt)
    println(Math.pow(2.0, 31.0) - 1)
    println(minInt)
    println( - Math.pow(2.0, 31.0))

    println(maxLong)
    println(Math.pow(2.0, 63.0) - 1)
    println(minLong)
    println(- Math.pow(2.0, 63.0))

    println(aFloat)
    println(anotherFloat)
    println(maxFloat)
    println(minFloat)

    println(maxDouble)
    println(minDouble)

    println(maxShort)
    println(minShort)

    println(maxByte)
    println(minByte)
}