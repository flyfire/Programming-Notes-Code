package _java.sam

//使用类型别名，让kotlin可以传入一个{}，而不一定要是Runnable类型
typealias Runnable = () -> Unit

class SAMInKotlin {
    fun addTask(runnable: Runnable) {

    }
}