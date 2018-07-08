package me.ztiany.advance


private class IteratorSample : Iterator<Int> {

    private var a = 0

    override fun hasNext() = true

    override fun next() = a++

}

fun main(args: Array<String>) {
    for (a in IteratorSample()) {

    }
}