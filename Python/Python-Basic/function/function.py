# 演示Python中的函数


# 定义函数
def get_name():
    return 'Jordan'


print(get_name())


# 函数的说明，下面的注释是函数说明
def add(b, a):
    """用来对两个数求和"""
    return a + b


# 不按顺序传参调用函数
print(add(a=3, b=4))


# 含有默认值的函数
def get_completed_name(first, end, middle=""):
    return str(first) + str(middle) + str(end)


print(get_completed_name("Zhan", "Tianyou"))
print(get_completed_name("Zhan", "Tianyou", "-Big-"))


# * 与 **
# 对于 *形参 python创建一个空的元组，把接收到的参数存储在元组中
# **形参接收的是字典

def arg(name, *numbers, **address):
    print(name)
    print(numbers)
    print(type(numbers))
    print(address)
    print(type(address))


arg("ztiany", "139", "158", "188", beijin="故宫", changsha="橘子洲", shenzhen="白石龙")

# 匿名函数 .PEP8 不建议使用lambda
sum3 = lambda a, b, c: (a + b + c)
print(sum3(1, 3, 4))
