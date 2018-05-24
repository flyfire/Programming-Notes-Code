package _java.sam

fun main(args: Array<String>) {
    val samInJava = SAMInJava()
    val lambda = {
        println("Hello")
    }

    //调用Java时，lambda会被转换为Runnable类型，每一次调用都会有一次新的转换，这种变换即SAM
    samInJava.addTask(lambda)
    samInJava.addTask(lambda)
    samInJava.addTask(lambda)
    samInJava.addTask(lambda)

    //以下调用下面代码是，参数lambda会应用一个新的转换，所以下面代码无法把lambda remove掉
    samInJava.removeTask(lambda)
    samInJava.removeTask(lambda)
    samInJava.removeTask(lambda)
    samInJava.removeTask(lambda)

    //typealias
    val samInKotlin = SAMInKotlin()
    samInKotlin.addTask {

    }
}