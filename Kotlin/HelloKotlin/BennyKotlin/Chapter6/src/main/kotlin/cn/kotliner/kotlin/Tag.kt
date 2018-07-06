package cn.kotliner.kotlin

/**
 * Created by benny on 5/29/17.
 */
open class Tag(val name: String): Node{

    val children = ArrayList<Node>()

    val proerties = HashMap<String, String>()

    operator fun String.invoke(value: String){
        proerties[this] = value
    }

    operator fun String.invoke(block: Tag.()->Unit){
        this@Tag.children.add(Tag(this).apply(block))
    }

    operator fun String.unaryPlus(){
        children.add(StringNode(this))
    }

    operator fun plus(node: Node){
        children.add(node)
    }

    // <html id="htmlId" style=""><head> </head> <body></body></html>
    override fun render(): String {
        return StringBuilder()
                .append("<")
                .append(name)
                .let {
                    stringBuilder ->
                    if(!this.proerties.isEmpty()){
                        stringBuilder.append(" ")
                        this.proerties.forEach{
                            stringBuilder.append(it.key)
                                    .append("=\"")
                                    .append(it.value)
                                    .append("\" ")
                        }
                    }
                    stringBuilder
                }
                .append(">")
                .let {
                    stringBuilder ->
                    children.map(Node::render).map(stringBuilder::append)
                    stringBuilder
                }
                .append("</$name>")
                .toString()
    }

}