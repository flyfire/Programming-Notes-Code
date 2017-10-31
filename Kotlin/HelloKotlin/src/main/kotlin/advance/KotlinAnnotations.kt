package advance

/**
 *kotlin注解
 *
 * @author Ztiany
 *          Email ztiany3@gmail.com
 *          Date 17.6.7 23:50
 */

//todo 学习注解

/**
 * 注解声明：
 *
 * 1，注解是将元数据附加到代码的方法。要声明注解，需要将 annotation 修饰符放在类的前面
 * 2，如果需要对类的主构造函数进行标注，则需要在构造函数声明中添加 constructor 关键字 ，并将注解添加到其前面
 * 3，如果需要将一个类指定为注解的参数，请使用 Kotlin 类 （KClass）。Kotlin 编译器会自动将其转换为 Java 类
 * */

private annotation class Ann(val abc: Int)

