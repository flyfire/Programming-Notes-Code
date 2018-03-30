#########################
#  类的属性与相关内置函数
#########################


class Test:
    def __init__(self):
        self.a = 12


# hasattr(obj,name) : 检查是否存在一个属性。
print(hasattr(Test, "a"))
print(hasattr(Test(), "a"))

# 访问对象的属性。
print(getattr(Test(), "a"))
test = Test()

# 设置一个属性。如果属性不存在，会创建一个新属性。
setattr(test, "a", 222)
print(getattr(test, "a"))

# delattr(obj, name) : 删除属性。
delattr(test, "a")
print(hasattr(test, "a"))
