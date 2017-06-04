package basic

/**
 *布尔类型
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.5.24 0:12
 */

/**
 *布尔类型：布尔用 Boolean 类型表示，它有两个值：true 和 false，若需要可空引用布尔会被装箱
 */

fun boolean() {
    val b: Boolean? = false
    if (b == true) {
        print("true")
    }
}