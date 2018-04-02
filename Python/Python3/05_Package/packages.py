#########################
# 演示python中的模块与包导入
#########################

import math  # 导入模块
import os
from calendar import calendar  # 导入模块的一部分
from time import *  # 导入模块内部所有元素
import sys
import pkg.module_a as ma  # 导入自己的模块
import pkg.module_b as mb  # 导入自己的模块

print("time() = %s" % time())
print("calendar = ", calendar)
print("module_a = ", ma.ma_a)
print("module_b = ", mb.mb_a)
print("math.fabs(-1) = %s" % math.fabs(-1))
print(os.__file__)
print(os.path)

# 添加导入模块的搜索路径
# sys.path.append("../01_Basic")
# import data_type

# 全局遍历与局部遍历
a = 3
b = 4
c = 5


def get_number():
    a = 5
    return a


def get_number2():
    global a  # 引用全局变量
    a = 5
    return a


print(get_number())
print(a)

print(get_number2())
print(a)


def test_global_local():
    d = 4
    f = 7
    print(globals())  # 返回可以访问的所有全局变量
    print(locals())  # 返回所有可以访问的本地变量


test_global_local()

# 打印一个模块里的元素
print(dir(math))
