第二章：面向Java开发者的Groovy

因为groovy支持java语法，并保留了java语义，所以我们可以随心所欲的混用这两种语言风格，但是当我们把java代码重构为groovy代码后，会发现groovy会更加简洁简单。


## 本章主要内容

- 从java到groovy
  - Hello Groovy
  - Groovy实现循环
  - 了解GDK，[GroovyJDK](http://groovy-lang.org/gdk.html)
  - 安全操作符
  - 异常处理
  - 静态方法中可以使用this
- JavaBean
- 灵活初始化与具名参数
- 可选形参
- 使用多赋值
- 实现接口的新方式
- 布尔求值
- 操作符重载
- 对Java5的支持
- 自动装箱
- for each
- enum
- 可变参数
- 注解
- 静态导入
- 泛型
- 使用Groovy生成代码
- Groovy中的陷阱

## 1 从java到groovy

### Hello Groovy

java打印`ho ho ho merry groovy`需要以下代码：

        public static void main(String... args) {
            for (int i = 0; i < 3; i++) {
                System.out.print("ho ");
            }
            System.out.println("merry groovy");
        }

而Groovy的方式如下：

    for (int i = 0; i < 3; i++) {
        print 'ha '
    }
    println 'merry groovy'
    //或者
    for (i in 0..2) {//0..2 表示一直range数据结构
        print 'ha '
    }
    println 'merry groovy'


### Groovy实现循环

    0.upto(2) {//integer的upto方法 实现循环
        print "$it "//当闭包值由一个参数时，可以使用it引用这个参数
    }
    3.upto 5, {//参数可以不带括号哦
        print "$it "
    }
    //如果遍历从0开始，可以使用times
    4.times {
        print "$it "
    }
    0.step 10 ,2 , {//使用step可以指定循环的目标数，但每次会跳过指定的数据
        print("$it ")
    }

### GDK

GDK扩展了JDK，并在各个类中添加了很多方法，例如String的execute方法，更多的方法可以从[GroovyJDK](http://groovy-lang.org/gdk.html)获取

process 非常有用，可以与系统级进程进行交互：

    println "git help".execute().text;//String 的execute方法用于执行String表达的命令
    println "git help".execute().getClass().name;//java.lang.ProcessImpl
    println "cmd /C dir".execute().text//dir并不是一个程序，而只是一个shell命令，所以需要通过调用cmd来执行dir命令


 如果使用Java，则需要些如此多的代码：
 
            private static void getProcessText() {
                BufferedReader br = null;
                try {
                    Process proc = Runtime.getRuntime().exec("git help");
                     br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                    String line;
                    while ((line = br.readLine() )!= null){
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

### 安全操作符

java中需要进行各种判断来避免空指针，而groovy使用操作符"?."

    def revereStringSafe(String str) {
        str?.reverse()
    }
    
    println revereStringSafe("ddffaa")
    println revereStringSafe(null)            

### 异常处理

Groovy并不强制处理任何异常，我们不处理的异常都会别自动的传递到上一层
​    
    def openFile(fileName) {
        new FileInputStream(fileName)//这里并没有强制我们检测一次
    }
    
    try {
        openFile(openFile("noExist"))//这里会抛出异常
    } catch (Exception ex) {
        println ex
    }

如果需要捕获的是Exception，也可使用如下方式(省略掉Exception)：
​    
    try {
        openFile(openFile("noExist"))//这里会抛出异常
    } catch (ex) {//可以省略掉Exception，但是对于Error，Throwable就不能省略了
        println ex
    }

###   静态方法中可以使用this

    class Wizard {
        def static learn(int a) {
            println(a)
            this
        }
    }
    
    Wizard.learn(1).learn(3).learn(4)


### Groovy还有如下特性：

- return 几乎是可选的，最后一个语句的执行结果将自动作为返回值
- 分号计划总是可选的
- 方法和类默认都是公开的
- `?.`操作符只有对象引用不完空是才会被调用
- 可以使用具名参数初始化JavaBean
- Groovy不强迫开发者捕获自己不关心的异常，没有被捕获的将自动向上传递
- 静态方法中可以使用this来引用Class对象




## 2 JavaBean

在Java中，JavaBean中的字段总是伴随者setter和getter方法，这样看起来非常的繁琐，而在Groovy中，javaBean变得很简单：


```groovy
class Car{
    def miles = 0;
    final  year;

    Car(year) {
        this.year = year
    }
}
```
Groovy会自动给JavaBean生成**访问器和更改器**，当使用`Car.miles`其实是调用的访问器，对于被final修饰的year，Groovy不会提供**更改器**，当视图修改year时会抛出异常，可以根据需要给字段加上类型信息，比如可以把miles设置为private的，但是groovy并不遵守这一点(还是可以被访问)，最好的做法是手动添加一个拒绝修改的**更改器**


```groovy
    private def miles = 0;
    void setMiles(miles) {
        throw new IllegalAccessException("no allow")
    }
```


## 3 灵活初始化与具名参数

定义Robot类

```groovy
    class Robot {
        def type, height, width
        def access(location, weight, fragile) {
            println "received fragile? $fragile, weight:$weight, loc:$location"
        }
    }
```
robot实例把type，height，width等实参当作了名值对,**这种具名参数要求类必须有一个无参的构造器**

    robot = new Robot(type: 'arm', width: 10, height: 40)//
    println "$robot.type , $robot.height, $robot.width"
access方法有三个形参，如果第一个是map，则可以将这个映射中的键值对展开放在实参列表中：

    robot.access(x: 20, y: 20, z: 10, 50, true)//这里我们依次放入了映射、weight、fragile
    robot.access(50, true, x: 20, y: 20, z: 10)//映射的传递可以往后移
如果发生的实参的个数多余形参的个数，并且多出来的实参是名值对，那么groovy就会假设方法的第一个形参是一个Map，然后将所有的名值对组织到一起作为方法的第一个实参

    def access(Map location, weight, fragile) {
        println "received fragile? $fragile, weight:$weight, loc:$location"
    }
    access(x: 20, y: 20, z: 10, 50, true)//这时如果传入的不是两个对象+一个名值对就会报错。
## 4 可选形参

Groovy可以把方法和构造器中的形参设置为可选的，想设置多少就可以设置多少，但是要求这些可选的形参必须放在参数列表的最后面，**定义形参只需 为其设置一个默认值**

```groovy
def log(x, base = 10) {
    Math.log(x) / Math.log(base)
}

println log(1024)
println log(1024, 10)
println log(1024, 2)
```
不仅如此，Groovy会把末尾的数据形参是做可选的，可以为最后一个参数提供0个或者多个值

```groovy
def task(name, String[] details) {
    println "$name ,  $details"
}

task 'call', '123-456-7890'
task 'call', '123-456-7890', 'ee3-456-rwe2'
task 'call'
```


## 5 使用多赋值


    def splitName(fullName) {
        fullName?.split(' ')
    }
    
    def (firstName, secondName) = splitName("Zhan tianyou")//从splitName方法接受多个参数
    println firstName + "  " + secondName
还可以使用该特性来交换变量，无需创建中间变量来保存被交换值


    def name1 = "Ztiany"
    def name2 = "MeiNv"
    println "$name1 and $name2"
    (name1, name2) = [name2, name1]
    println "$name1 and $name2"
当 变量与值的数量不相等时，如果有多余的变量，Groovy会将它们设置为null，多余的值则会被抛弃，**如果对于的变量是不能设置为null的基本类型，Groovy将会抛出一个异常**,在Groovy2中，只要可能int会被看作基本类型，而非Integer

## 6 实现接口

在Groovy中，可以把一个映射或者一个代码块转换为接口，因此可以快速实现带有多个方法的接口

具体可以参考代码

##  7 Boolean求值

Groovy中的布尔求职与Java不同，根据上下文，Groovy会把表达式计算为布尔值，java代码中的if语句必须要求表达式的结果是个布尔值，而Groovy不会，Groovy会尝试推断，比如在需要boolean的地方放一个对象引用，Groovy会尝试检查引用是否为null，它将null视作false，将非null视作true，如：

```groovy
str1 = "hello"
str2 = null
if (str1) {
    println str1
}
if (str2) {//不会被打印
    println str2
}
```

注意，当面所说的判断true/false的条件并不全面，对于集合来说，Groovy判断false的条件是 集合是否为空，只有当集合不为null，并且含有至少一个元素时才会计算为true

下面是一个被特殊对待的类型列表：

| 类型           | 为真条件                    |
| ------------ | ----------------------- |
| Boolean      | 值为true                  |
| Collection   | 集合不为空                   |
| Character    | 值不为0                    |
| CharSequence | 长度大于0                   |
| Enumeration  | Has More Elements 为true |
| Iterator     | hasNext 返回true          |
| Number       | Double值不为0              |
| Map          | 该映射不为空                  |
| Matcher      | 至少又要给匹配                 |
| Object[]     | 长度大于0                   |
| 其他类型         | 引用不为null                |

## 8 操作符重载

Groovy支持操作符重载，可以巧妙的应用这一点来创建DSL领域特定语言，java不支持，Groovy的做法的每一个操作符都会映射到一个标准的方法，在java可以使用的那些方法，在groovy中既可以使用操作符，也可以使用与之对应的方法

例如 ,这里的ch++ 映射到的是String的next方法

```groovy
for (ch = 'a'; ch < 'd'; ch++) {
    println(ch)
}
for (ch in 'a'..'d') {
    println(ch)
}
//String和ArrayList重载了很多的操作符，如ArrayList的leftShift
list = []
list << "d"
println list
```

下面为一个类添加一个+的操作符

```groovy
class ComplexNumber {
    def real, imaginary

    def plus(other) {
        new ComplexNumber(real: real + other.real, imaginary: imaginary + other.imaginary)
    }

    @Override
    public String toString() {
        return "ComplexNumber{" +
                "real=" + real +
                ", imaginary=" + imaginary +
                '}';
    }
}
println new ComplexNumber(real: 2, imaginary: 4) + new ComplexNumber(real: 5, imaginary: 3)
```

当应用于某个上下文时，操作符重载可以使代码更富于表现力，应该只重载那些能够使事物变得显而易见的操作符


下面的表格描	述了groovy中的操作符所映射到的方法：

| Operator                | Method            |
| ----------------------- | ----------------- |
| a + b                   | a.plus(b)         |
| a – b                   | a.minus(b)        |
| a * b                   | a.multiply(b)     |
| a ** b                  | a.power(b)        |
| a / b                   | a.div(b)          |
| a % b                   | a.mod(b)          |
| a 或b                    | a.or(b)           |
| a & b                   | a.and(b)          |
| a ^ b                   | a.xor(b)          |
| a++ or ++a              | a.next()          |
| a– or –a                | a.previous()      |
| a[b]                    | a.getAt(b)        |
| a[b] = c                | a.putAt(b, c)     |
| a << b                  | a.leftShift(b)    |
| a >> b                  | a.rightShift(b)   |
| switch(a) { case(b) : } | b.isCase(a)       |
| ~a                      | a.bitwiseNegate() |
| -a                      | a.negative()      |
| +a                      | a.positive()      |

> a 或b 即 a |b

## 9 Groovy对java1.5的支持

下面是JDK1.5的新特性：

- 自动装箱
- for-each循环
- enum
- 变长参数
- 注解
- 静态导入
- 泛型

### 自动装箱

Groovy从一开始就支持自动装箱，必要时Groovy会将基本类型视作对象

	int val = 4;
	println val.class.getName()//java.lang.Integer

虽然定义的是int，但是创建的Integer实例，而不是基本类型，Groovy会根据该实例的使用方式来决定将其存储为int类型还是Integer类型，Groovy2.0之前，所有的基本类型都会视作对象，之后为了改善性能，只有在需要的时候才会被视作对象

### fro each groovy

对循环的支持强于java，传统的增强for循环，groovy需要指明类型，而用in 则不需要

```groovy
list = [1, 2, 3, 4]
for (int i : list) {
    println i
}
for (i in list) {
    println i
}
```

### 枚举 

Groovy同样支持枚举，它是类型安全的


```
enum CoffeeSize{
    Short, Small, Medium, Large,Mug
}

def orderCoffee(size) {
    switch (size) {
        case [CoffeeSize.Short, CoffeeSize.Small]:
            println('111111111')
            break
        case [CoffeeSize.Medium,CoffeeSize.Large]:
            println('22222222')

            break
        case CoffeeSize.Mug:
            println('333333')
            break
    }
}

orderCoffee CoffeeSize.Short
orderCoffee CoffeeSize.Small
orderCoffee CoffeeSize.Medium
orderCoffee CoffeeSize.Large
orderCoffee CoffeeSize.Mug

for (coffee in CoffeeSize.values()) {
    println coffee.name()
}
```

### 变长参数 

Groovy以两种形式支持Java的编程参数，一种是用... 另一种是以数据为末尾的形参方法

```groovy
def receiveVarArgs(int a, int ... b) {
    println "You passed $a and $b"
}
def receiveArray(int a, int [] b) {
    println "You passed $a and $b"
}
receiveVarArgs 1,3,5,6,8
receiveArray 1,2,32,34,54
//在向带数组形参的方法传递数组时，需要注意.[1,2,3]默认类型为List，所以传递数据是需要使用下面方法
int[] arr = [1, 3, 7, 77]
arr2 = [3, 44, 34, 65] as int[]
receiveArray 1, arr
receiveArray 4, arr2
```

### 注解

Java使用注解来表示元数据，Java5中也定义了一些注解，@Override，@Deprecated，@SuppressWarnings,Groovy也可以定义和使用注解，使用方式与java相同，但对于Java编译相关的注解，Groovy的理解会有所不同，比如Groovy会忽略Override注解

### 静态导入

Groovy不仅支持静态导入，还支持静态导入别名

### 泛型

Groovy 是支持可选类型的动态类型语言，作为java的超级它也支持泛型，但Groovy编译器不会像Java编译器那样检查类型，Groovy的动态类型和泛型类型互相作用使我们的代码运行起来

```groovy
ArrayList<Integer> list = new ArrayList<>()
list.add(33)
list.add("Dd")
for (int i   : list) {//Cannot cast object 'Dd' with class 'java.lang.String' to class 'int'
    println i
}
//在add方法中，groovy只会把类型看做一种建议，对集合进行循环的时候，Groovy会尝试将元素转换成int类型，这是就导致错误
```



##  10 使用Groovy生成代码

Groovy在groovy.transform包和其他一些包中提供了很多代码生成的注解

- **@Canonical** 用于生成toString
- **@Delegate** 实现代理
- **@Lazy** 延迟加载对象 
- **@Newify**  声明的方式创建对象
- **@Singleton ** 
- **@InheritConstructors**  创建构造函数

具体可以参照代码

## 11 Groovy的陷阱

- Groovy的 == 等于Java中的equals
- 编译时类型检查默认关闭
- def in 都是groovy中的关键字
- 在Groovy中，方法内不能有局部代码块
- 闭包与匿名内部类冲突
- 分号总是可选的，但有些地方却必不可少
- 创建基本类型的数组语法不同
- 一般情况下，应该使用getClass方法，向Map、生成器等一些类对class属性有特殊的处理，为了避免出现意外，应使用getClass方法









