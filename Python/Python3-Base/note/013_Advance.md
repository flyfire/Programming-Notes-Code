# 高级部分

---
##  生成器

通过列表生成式，我们可以直接创建一个列表。但是受到内存限制，列表容量肯定是有限的。而且如果创建一个包含100万个元素的列表，不仅占用很大的存储空间，
如果我们仅仅需要访问前面几个元素，那后面绝大多数元素占用的空间都白白浪费了。所以如果列表元素可以按照某种算法推算出来，那我们是否可以在循环的过程中不断推算出后续的元素呢？
这样就不必创建完整的list，从而节省大量的空间。在Python中，这种一边循环一边计算的机制，称为生成器：generator。

### 使用`()`创建生成器

生成器保存的是算法，每次调用 `next(G)` ，就计算出 G 的下一个元素的值，直到计算到最后一个元素，没有更多的元素时，抛出 StopIteration 的异常。
但是正确的方法是使用 for 循环，因为生成器也是可迭代对象。通过 for 循环来迭代它不需要关心 StopIteration 异常。

```python
L = (x * 2 for x in range(5))  # 创建一个生成器

for value in L:
    print(value)
```

### 创建函数生成器

```python
def fib(times):
    n = 0
    a, b = 0, 1
    while n < times:
        """
        在循环过程中不断调用 yield ，就会不断中断。当然要给循环设置一个条件来退出循环，不然就会产生一个无限数列出来。
        同样的，把函数改成generator后，我们基本上从来不会用 next() 来获取下一个返回值，而是直接使用 for 循环来迭代
        """
        yield b
        a, b = b, a + b
        n += 1
    return 'done'

for value in fib(10):
    print(value, end=' ')
```

### 总结

生成器是这样一个函数，它记住上一次返回时在函数体中的位置。对生成器函数的第二次（或第 n 次）调用跳转至该函数中间，而上次调用的所有局部变量都保持不变。
生成器不仅“记住”了它数据状态；生成器还“记住”了它在流控制构造（在命令式编程中，这种构造不只是数据值）中的位置。生成器具有以下特点：

- 节约内存
- 迭代到下一次的调用时，所使用的参数都是第一次所保留下的

---
## 迭代器

迭代是访问集合元素的一种方式。迭代器是一个可以记住遍历的位置的对象。迭代器对象从集合的第一个元素开始访问，直到所有的元素被访问完结束。一般迭代器只能往前不会后退。

### 可迭代对象

以直接作用于 for 循环的数据类型有以下几种：

- 一类是集合数据类型，如 list 、 tuple 、 dict 、 set 、 str 等；
- 一类是 generator ，包括生成器和带 yield 的generator function。

这些可以直接作用于 for 循环的对象统称为可迭代对象： Iterable 。

### 迭代器与判断对象是否可以迭代

- 可以被next()函数调用并不断返回下一个值的对象称为迭代器：Iterator
- 可以使用 `isinstance()` 判断一个对象是否是 Iterable 对象

生成器不但可以作用于 for 循环，还可以被 next() 函数不断调用并返回下一个值，直到最后抛出 StopIteration 错误表示无法继续返回下一个值了。

python shell
```
from collections import Iterable

isinstance([], Iterable)
True

isinstance({}, Iterable)
True

isinstance('abc', Iterable)
True

isinstance((x for x in range(10)), Iterable)
True

isinstance(100, Iterable)
False
```

### `iter()`函数

生成器都是 Iterator 对象，但 `list 、 dict 、 str`虽然是 Iterable ，却不是 Iterator 。把 `list 、 dict 、 str` 等 Iterable 变成 Iterator 可以使用 `iter()` 函数：

python shell
```
from collections import Iterator

isinstance(iter([]), Iterator)
True

isinstance(iter('abc'), Iterator)
True
```

- 凡是可作用于 for 循环的对象都是 Iterable 类型；
- 凡是可作用于 next() 函数的对象都是 Iterator 类型
- 集合数据类型如 list 、 dict 、 str 等是 Iterable 但不是 Iterator ，不过可以通过 iter() 函数获得一个 Iterator 对象。

---
##  垃圾回收

### 对象池

- 整数在程序中的使用非常广泛，Python为了优化速度，使用了小整数对象池， 避免为整数频繁申请和销毁内存空间。
- Python 对小整数的定义是 `[-5, 257)` 这些整数对象是提前建立好的，不会被垃圾回收。在一个 Python 的程序中，所有位于这个范围内的整数使用的都是同一个对象，同理，单个字母也是这样的。
- 但是当定义2个相同的字符串时，引用计数为0，触发垃圾回收
- 每一个大整数，均创建一个新的对象
- 单个单词，不可修改，默认开启intern机制，共用对象，引用计数为0，则销毁 
- 字符串（含有空格），不可修改，没开启intern机制，不共用对象，引用计数为0，销毁 
- 数值类型和字符串类型在 Python 中都是不可变的

### GC垃圾回收

 python里也同java一样采用了垃圾收集机制，不过不一样的是: python采用的是引用计数机制为主，标记-清除和分代收集两种机制为辅的策略
 
 引用计数机制：
 
 ```C
 //python里每一个东西都是对象，它们的核心就是一个结构体：PyObject
typedef struct_object {
    int ob_refcnt;
    struct_typeobject *ob_type;
} PyObject;
```

PyObject是每个对象必有的内容，其中`ob_refcnt`就是做为引用计数。当一个对象有新的引用时，它的`ob_refcnt`就会增加，当引用它的对象被删除，它的`ob_refcnt`就会减少，当引用计数为0时，该对象生命就结束了

引用计数机制的优点：

- 简单
- 实时性：一旦没有引用，内存就直接释放了。不用像其他机制等到特定时机。实时性还带来一个好处：处理回收内存的时间分摊到了平时。

引用计数机制的缺点：

- 维护引用计数消耗资源
- 循环引用

导致引用计数+1的情况

   - 对象被创建，例如a=23
   - 对象被引用，例如b=a
   - 对象被作为参数，传入到一个函数中，例如`func(a)`
   - 对象作为一个元素，存储在容器中，例如`list1=[a,a]`
    
导致引用计数-1的情况

   - 对象的别名被显式销毁，例如del a
   - 对象的别名被赋予新的对象，例如a=24
   - 一个对象离开它的作用域，例如f函数执行完毕时，func函数中的局部变量（全局变量不会）
   - 对象所在的容器被销毁，或从容器中删除对象
    
- 查看一个对象的引用计数

```python
import  sys
o= "a"
sys.getrefcount(o) # 查看变量的引用计数
```


### GC系统

GC系统所承担的工作远比"垃圾回收"多得多。实际上，它们负责三个重要任务

- 为新生成的对象分配内存
- 识别那些垃圾对象，并且
- 从垃圾对象那回收内存

### gc模块常用功能

垃圾回收后的对象会放在gc.garbage列表里面，gc.collect()会返回不可达的对象数目，4等于两个对象以及它们对应的dict

有三种情况会触发垃圾回收：

- 调用gc.collect()
- 当gc模块的计数器达到阀值的时候
- 程序退出的时候

gc模块常用功能：

1. `gc.set_debug(flags)` 设置gc的debug日志，一般设置为gc.DEBUG_LEAK
2. `gc.collect([generation])` 显式进行垃圾回收，可以输入参数，0代表只检查第一代的对象，1代表检查一，二代的对象，2代表检查一，二，三代的对象，
如果不传参数，执行一个full collection，也就是等于传2。 返回不可达（unreachable objects）对象的数目
3. `gc.get_threshold()` 获取的gc模块中自动执行垃圾回收的频率
4. `gc.set_threshold(threshold0[, threshold1[, threshold2])` 设置自动执行垃圾回收的频率
5. `gc.get_count()` 获取当前自动执行垃圾回收的计数器，返回一个长度为3的列表
7. `gc.disable()`：把python的gc关闭