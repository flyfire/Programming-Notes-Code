# 第一章：Groovy起步


到官网下载groovy开发包并安装，然后推荐使用IDEA来编写Groovy代码，但在使用IDEA之前先来学习使用Groovy的一些工具

## 1 使用Groovysh

groovysh用于打开Groovyshell

groovysh是以交互式方式尝试一些小型的groovy代码的好工具，需要注意的是，**当按下回车键groovysh就会编译并执行输入完的语句，打印代码执行过程中的所有语句，并打印这条语句的执行结果**，对于某些语句，Groovy会等待你输入完毕才执行，比如下面的为String添加isPalindrome函数。

groovysh：

    Math.sqrt(16)//执行Math.sqrt(16)，并打印其结果
    ===> 4.0
    groovy:000> println 'test'//执行println 'test'，
    test
    ===> null//打印println函数的结果，由于println函数返回null
    groovy:000> String.metaClass.isPalindrome = {
    groovy:001> delegate == delegate.reverse()
    groovy:002> }
    ===> groovysh_evaluate$_run_closure1@6e4566f1
    groovy:000> "MMMdMMM".isPalindrome()
    ===> true
    
    
    "dd".  //在命令行中，如果不太确定命令，可以使用tab键，groovy会做出提示
    
    
    capitalize()           center(                charAt(                chars()                codePointAt(
    codePointBefore(       codePointCount(        codePoints()           collectReplacements(   compareTo(
    compareToIgnoreCase(   concat(                contains(   //......

使用？可以查看groovysh的帮助命令：

                  :help      (:h ) Display this help message
                  ?          (:? ) Alias to: :help
                  :exit      (:x ) Exit the shell
                  :quit      (:q ) Alias to: :exit
                  import     (:i ) Import a class into the namespace
                  :display   (:d ) Display the current buffer
                  :clear     (:c ) Clear the buffer and reset the prompt counter.
                  :show      (:S ) Show variables, classes or imports
                  :inspect   (:n ) Inspect a variable or the last result with the GUI object browser
                  :purge     (:p ) Purge variables, classes, imports or preferences
                  :edit      (:e ) Edit the current buffer
                  :load      (:l ) Load a file or URL into the buffer
                  .          (:. ) Alias to: :load
                  :save      (:s ) Save the current buffer to a file
                  :record    (:r ) Record the current session to a file
                  :history   (:H ) Display, manage and recall edit-line history
                  :alias     (:a ) Create an alias
                  :set       (:= ) Set (or list) preferences
                  :register  (:rc) Registers a new command with the shell
                  :doc       (:D ) Opens a browser window displaying the doc for the argument
                  
 使用inspect命名可以检测对象和变量                 
 
## 2 使用GroovyConsole
 
 在终端输入`groovyConsole`即可以打开groovyConsole，在groovyConsole中可以输入脚本代码，使用快捷键`control + R`既可以运行：
 
## 3 在命令行使用Groovy
 
 对于hello.groovy脚本文件，也可以直接使用groovy hello来运行hello中的代码，如果想直接运行一些语句，可以使用`groovy -e` 命名