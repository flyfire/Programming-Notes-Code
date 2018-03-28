# 基础语法

---
## 1 数据类型

Python有以下数据类型：

    Numbers 数字：
                int 有符号整型
                long 长整型(也可代表八进制和十六进制)
                float 浮点型
                complex 复数

    布尔类型：Flase、True
    String 字符串
    List 列表
    Tuple 元组
    Dictionary 字典

定义数据时不需要加任何关键字，直接定义即可。

### type

使用`type`函数可以获取变量大数据类型:

```python
a = 3
print(type(a))
```

### 布尔类型

数据都可以转换为布尔类型，比如：

- 数字0表示False，任何非零值都是True
- 非空集合都表示True
- None表示False

---
## 2 标识符

- 在 Python 里，标识符有字母、数字、下划线组成。
- 在 Python 中，所有标识符可以包括英文、数字以及下划线`(_)`，但不能以数字开头。
- Python 中的标识符是区分大小写的。
- 以下划线开头的标识符是有特殊意义的。以单下划线开头 `_foo` 的代表不能直接访问的类属性，需通过类提供的接口进行访问，不能用 `from xxx import * `而导入；
- 以双下划线开头的 `__foo `代表类的私有成员；以双下划线开头和结尾的` __foo__` 代表 Python 里特殊方法专用的标识，如` __init__() `代表类的构造函数。

---
## 3 Python中的关键字

        False               def                 if                  raise
        None                del                 import              return
        True                elif                in                  try
        and                 else                is                  while
        as                  except              lambda              with
        assert              finally             nonlocal            yield
        break               for                 not
        class               from                or
        continue            global              pass

可以通过以下命令进行查看当前系统中python的关键字

```python
import keyword
print(keyword.kwlist)
```

---
## 4 注释

以`#`开头，`#`右边的所有字符都当做说明，而不是真正要执行的程序
