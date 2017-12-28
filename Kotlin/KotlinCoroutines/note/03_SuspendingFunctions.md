# 编写异步风格的代码

示例代码：
```kotlin
    val result1 = async(CommonPool) {
        delay(3000)
        1
    }
    val result2 = async(CommonPool) {
        delay(3000)
        2
    }

    //await表示，当Deferred代表的协程调度完毕，会在指定的协程剩下文中调度await所在的代码块。
    val launch = launch(UI) {
        println("${result1.await() + result2.await()}")
    }
```

上面是一个标准的异步风格写成的代码，以CommonPool开启两个异步协程，并获取结果，launch一个新的协程，优雅无阻塞的等待
异步异步任务返回结果。 