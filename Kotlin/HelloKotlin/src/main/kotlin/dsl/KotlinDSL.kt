package dsl

/**
 * 慕课网例子：使用kotlin 为构建html编写的dsl
 *
 * Kotlin的DSL在于 kotlin强大的函数和属性扩展功能
 */
fun main(args: Array<String>) {

    html {

        //添加id属性
        "id"("HtmlId")

        //添加子tag，代码块运行在子tag中
        "head"{
            "id"("headId")
        }

        //添加一个body
        body {
            id = "bodyId"
            `class` = "bodyClass"

            "a"{
                "href"("https://www.kotliner.cn")
                +"Kotlin 中文博客"
            }
        }

        "div"{

        }

    }.render().let(::println)
}