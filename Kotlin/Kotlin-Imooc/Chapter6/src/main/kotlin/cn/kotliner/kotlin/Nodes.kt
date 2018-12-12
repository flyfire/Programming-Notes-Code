package cn.kotliner.kotlin

/**
 * Created by benny on 5/29/17.
 */
fun html(block: Tag.()->Unit): Tag{
    return Tag("html").apply(block)
}

fun Tag.head(block: Head.()->Unit){
    this + Head().apply(block)
}

fun Tag.body(block: Body.()->Unit){
    this + Body().apply(block)
}

class StringNode(val content: String): Node{
    override fun render() = content
}

class Head: Tag("head")

class Body: Tag("body"){
    var id by MapDelegate(proerties)

    var `class` by MapDelegate(proerties)
}