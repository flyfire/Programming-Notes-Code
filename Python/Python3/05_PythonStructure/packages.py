#########################
# 演示python中的模块导入
#########################

import math  # 导入模块
from calendar import calendar  # 导入模块的一部分
from time import *  # 导入模块内部所有元素

import pkg.module_a as ma
import pkg.module_b as mb

print("time() = %s" % time())
print("calendar = ", calendar)
print("module_a = ", ma.ma_a)
print("module_b = ", mb.mb_a)
print("math.fabs(-1) = %s" % math.fabs(-1))

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
