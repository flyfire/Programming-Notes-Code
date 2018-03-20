package sample

import javax.swing.JFrame.EXIT_ON_CLOSE


/**
 * 该实例来自 Kotlin系统入门与进阶：https://coding.imooc.com/class/108.html
 */

const val LOGO_URL = "http://www.imooc.com/static/img/index/logo.png?t=1.1"

fun main(args: Array<String>) {

    val frame = MainWindow()

    frame.title = "Coroutine@Bennyhuo"
    frame.setSize(200, 150)
    frame.isResizable = true
    frame.defaultCloseOperation = EXIT_ON_CLOSE
    frame.init()
    frame.isVisible = true

    frame.onButtonClick {
        log("协程之前")

        我要开始协程啦(DownloadContext(LOGO_URL)) {

            log("协程开始")

            try {
                val imageData = 我要开始耗时操作了 {
                    我要开始加载图片啦(this[DownloadContext]!!.url)
                }
                log("拿到图片")
                frame.setLogo(imageData)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        log("协程之后")
    }
}