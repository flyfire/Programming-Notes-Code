# 领域定义语言(DSL)


## 1 什么是DSL

### 领域定义语言(Domain Specific Language )相关概念
 
 - 针对的是某一特定类型的问题，
 - 语法聚焦于指定的领域问题，
 - DSL的应用领域和能力都是非常有限的。
 - 其基本思想是“求专不求全”
 
### 与DSL相对的GPL
 
 与领域定义语言相对的就是通用编程语(GPL)了，GPL即General Purpose Language 的简称,通用编程语言也就是我们非常熟悉的Java、Groovy、Python 以及 C 语言等。
 通用的计算机编程语言是可以用来编写任意计算机程序的，并且能表达任何的可被计算的逻辑，即图灵完备的
 
>  **图灵完备**是对计算能力的描述。一门语言为什么要图灵完备呢？可以这么理解：一台计算机也是一个图灵机，一个图灵完备的语言意味着这个语言可以使用计算机完成任何计算机可以完成的任务，也就能够发挥计算机的所有能力。（这句话有点绕口）反之，一个图灵不完备的语言，就意味着不能发挥计算机的所有能力。[参考](https://www.zhihu.com/question/20115374)
 
而DSL并不是图灵完备的，它们的表达能力有限，只是在特定领域解决特定任务的，DSL 通过在表达能力上做的妥协换取在某一领域内的高效。


### DSL特点

一般书写DSL有如下特点：

- 没有计算和执行的概念；
- 其本身并不需要直接表示计算；
- 使用时只需要声明规则、事实以及某些元素之间的层级和关系；



一门DSL语言会很小，很简单，很有表现力，聚焦于一个问题区域或领域，DSL本身有两大特点：**上下文驱动；非常流畅**。

#### 上下文驱动

上下文(Context)是DSL的特点之一，上下文也是人类赖以生存的基础，在人类的交流中，上下文提供的连续性功不可没。


一个订购Pizza的例子：

java中没有上下文的例子，需要每次都使用pizzaShop引用：

                PizzaShop pizzaShop = new PizzaShop();
                pizzaShop.setSize(Size.LARGE);
                pizzaShop.setCrust(Crust.THIN);
                pizzaShop.setTopping("olives", "Onions", "Bell Pepper");
                pizzaShop.setAddress("101 ... Main St");
                int time = pizzaShop.setCard(CardType.VISA, "1013-5222-5366-4136");
                System.out.println(String.format("pizza will arrive in %d minutes\n", time));

在Groovy中因为有了with方法，而且括号几乎总是可选的。使用Groovy实现就没有那么乱了：

                def pizzaShop = new PizzaShop()
               
                pizzaShop.with {
               
                        setSize              Size.LARGE
                        setCrust             Crust.THIN
                        setTopping         "olives", "Onions", "Bell Pepper"
                        setAddress         "101 ... Main St"
                        int time =            setCard CardType.VISA, "1013-5222-5366-4136"
                        printf                 "pizza will arrive in %d minutes\n", time
                
                }

上下文是一切变得紧凑，也减少了混乱，提升了实际效果。


#### 流畅性

流畅性是DSL的另一个特点，它改进了代码的可读性，同时使代码自然的流动，比如在Groovy中编写循环的方式

                //传统的java方式
               for (int i = 0; i < 10; i++) {
                       System.out.println(i);
                }
                //groovy的方式
                for(i in 9){println i}
                0.upto(9){println it)
                10.times{println it}

Groovy生成器是DSL很好的例子。


## 2 DSL的分类

DSL可以分为 外部DSL和内部DSL

- 外部DSL：要构建一种DSL，先定义它的语法，然后通过代码生成技术把DSL代码转成一种通用语言代码，或者写一个这种DSL的解释器。XML配置文件是外部DSL的另一种常见形式。
- 内部DSL：利用编程语言自带的语法结构定义出来的DSL，我称之为“内部DSL”，也叫做“内嵌DSL”。其语法受到现有语言的约束，不适用任何解析器，但是巧妙地将语法映射到底层语言中的方法和属性等构造


### 创建内部的DSL

动态语言很适合设计和实现内部的DSL，这个语言提供了很多的元编程能力和语法，便于很轻松的加载和执行代码片段，单并不是所有的动态语言都是这样的。

- ruby 创建DSL非常容易
- groovy 的动态类型和元编程能力对创建内部的DSL帮助很大，其对括号的处理没有ruby显得优雅
- python 创建DSL面临着一些挑战。























