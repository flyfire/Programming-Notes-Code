package dsl

import java.util.*

open class Tag(private val name: String) : Node {

    /*所有子节点*/
    private val children = ArrayList<Node>()

    //该节点的属性
    val properties = HashMap<String, String>()

    operator fun String.invoke(value: String) {
        properties[this] = value
    }

    operator fun String.invoke(block: Tag.() -> Unit) {
        this@Tag.children.add(Tag(this).apply(block))
    }

    operator fun String.unaryPlus() {
        children.add(StringNode(this))
    }

    operator fun plus(node: Node) {
        children.add(node)
    }

    // <html id="htmlId" style=""><head> </head> <body></body></html>
    override fun render(): String {
        return StringBuilder()
                .append("<")
                .append(name)
                .let { stringBuilder ->
                    if (!this.properties.isEmpty()) {
                        stringBuilder.append(" ")
                        this.properties.forEach {
                            stringBuilder.append(it.key)
                                    .append("=\"")
                                    .append(it.value)
                                    .append("\" ")
                        }
                    }
                    stringBuilder
                }
                .append(">")
                .let { stringBuilder ->
                    //遍历每一个子节点，让其渲染，然后添加到父节点内容当中
                    children
                            .map(Node::render)
                            .map(stringBuilder::append)
                    stringBuilder
                }
                .append("</$name>")
                .append("\n")
                .toString()
    }

}