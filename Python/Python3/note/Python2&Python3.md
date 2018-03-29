---
## 1 编码

Python2默认`ASCII`编码

所以在python2中，如果在程序中用到了中文，比如：

```python
print('你好')
```
此时直接运行输出，程序会出错，解决的办法为：在程序的开头写入如下代码

```python
# coding=utf-8
print('你好')
```

---
## 2 输入输出

Python2中，无需将要打印的内容放在括号内，python2 print语句也包含括号，但其行为与Python3稍有不同。

Python2中的input数与Pyhon3的input()类似，但其接受的输入必须是表达式,input()接受表达式输入，并把表达式的结果赋值给等号左边的变量，Python2中的**`raw_input`**函数与Python3的input函数表现一致。

---
## 3 整数的除法

Python2中的整数：将两个整数相除得到的结果也是两个整数，采取的方式不是四舍五入而是直接删除小数部分。`3/2=1`
 
---
## 4 异常处理

Python2支持：

 - 处理多个异常
 - 异常参数

Python2的异常参数： `except exc, var`
Python3的异常参数： `except exc as var`

---
## 5 公共函数

python3移除的函数：

- cmp
