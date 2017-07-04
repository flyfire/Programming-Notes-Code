package api

/**
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.7.4 0:32
 */
fun main(args: Array<String>) {
    letSample()
}

private fun letSample() {
    val abc = 1.let {
        println(it)
        "ABC"
    }
    println(abc)
}