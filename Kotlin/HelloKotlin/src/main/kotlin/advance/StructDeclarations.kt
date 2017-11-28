package advance

//todo  理解解构声明

/**
 *解构声明
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.6.3 18:40
 */
private class Person constructor(name: String, age: Int) {

    var mName = name
    var mAge = age

    operator fun component1(): String {
        return mName
    }

    operator fun component2(): Int {
        return mAge
    }
}

private data class Result(val result: Int, val status: Int)

private fun getResult(): Result {
    // 各种计算
    return Result(11, 12)
}

private fun structDeclarationsSample() {

    val person = Person("ztiany", 27)
    val (name, age) = person
    //数据类自动声明 componentN() 函数，所以这里可以用解构声明。
    var (result, statue) = getResult()

    //下划线用于未使用的变量
    val (_, status) = getResult()

    //遍历map，之所致可以这样遍历，是应为Map提供了component1函数
    val map = mapOf(Pair("A", 1), Pair("B", 2), Pair("C", 3))
    for ((key, value) in map) {
        println("key is $key, value is $value")
    }

    //在 lambda 表达式中解构
    println(map.mapValues {
        entry ->
        "${entry.value}!"
    })
    println(map.mapValues {
        (key, value) ->
        "$key->$value"
    })
}