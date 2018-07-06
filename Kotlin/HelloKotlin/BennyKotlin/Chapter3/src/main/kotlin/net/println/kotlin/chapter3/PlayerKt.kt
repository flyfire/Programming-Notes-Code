package net.println.kotlin.chapter3

/**
 * Created by benny on 3/19/17.
 */
class PlayerKt {
    enum class State {
        IDLE, BUFFERING, PLAYING, PAUSED
    }

    private var state = State.IDLE


    fun pause() {
        when (state) {
            PlayerKt.State.BUFFERING, PlayerKt.State.PLAYING -> doPause()
            else -> {

            }
        }
    }

    fun resume() {
        when (state) {
            PlayerKt.State.PAUSED -> doResume()
        }//什么都不做
    }

    private fun doResume() {
        state = State.PLAYING
        //处理真正的继续播放逻辑
    }

    private fun doPause() {
        state = State.PAUSED
        //处理真正的暂停逻辑
    }
}
