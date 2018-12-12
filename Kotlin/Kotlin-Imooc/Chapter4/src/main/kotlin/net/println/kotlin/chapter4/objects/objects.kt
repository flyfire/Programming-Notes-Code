package net.println.kotlin.chapter4.objects


/**
 * Created by benny on 4/3/17.
 */

class Driver

interface OnExternalDriverMountListener{
    fun onMount(driver: Driver)

    fun onUnmount(driver: Driver)
}

abstract class Player

object MusicPlayer: Player(), OnExternalDriverMountListener{
    override fun onMount(driver: Driver) {

    }

    override fun onUnmount(driver: Driver) {

    }

    val state : Int = 0

    fun play(url : String){

    }

    fun stop(){

    }
}