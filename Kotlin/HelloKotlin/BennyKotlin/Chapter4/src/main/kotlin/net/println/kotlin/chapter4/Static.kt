package net.println.kotlin.chapter4

/**
 * Created by benny on 4/4/17.
 */
fun main(args: Array<String>) {
    val latitude = Latitude.ofDouble(3.0)
    val latitude2 = Latitude.ofLatitude(latitude)

    println(Latitude.TAG)
}

class Latitude private constructor(val value: Double){
    companion object{
        @JvmStatic
        fun ofDouble(double: Double): Latitude{
            return Latitude(double)
        }

        fun ofLatitude(latitude: Latitude): Latitude{
            return Latitude(latitude.value)
        }

        @JvmField
        val TAG: String = "Latitude"
    }
}