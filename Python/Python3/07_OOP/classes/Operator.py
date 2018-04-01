#########################
# 类的专有方法和操作符重载
#########################

"""
__str__和__repr__区别在于__str__目的不在于总是尝试返回一个适用于eval()的字符串，而是返回一个很好地向人描述对象的字符串。
而__repr__目的在于”makes an attempt to return a string that would yield an object with the same value when passed to eval()”，
尝试让返回的字符串能够通过eval()来生成一个一样的对象。
"""


class AB:
    def __init__(self, a, b):
        self.a = a
        self.b = b

    # 类似Java的toString
    def __str__(self):
        return "a = %d , b = %d" % (self.a, self.b)

    # 打印，转换
    def __repr__(self):
        pass

    # 操作符重载
    def __add__(self, other):
        self.a += other.a
        self.b += other.b

    #     按照索引赋值
    def __setitem__(self):
        pass

    #     按照索引获取值
    def __getitem__(self):
        pass

    #     获得长度
    def __len__(self):
        pass

    #     比较运算
    def __cmp__(self, other):
        pass

    #     函数调用
    def __call__(self):
        pass

    #     减运算
    def __sub__(self, other):
        pass

    #     乘运算
    def __mul__(self, other):
        pass

    #     除运算
    def __div__(self, other):
        pass

    #     求余运算
    def __mod__(self, other):
        pass

    #     乘方
    def __pow__(self, other):
        pass

    # 布尔转换判断，如果没有实现该方法，则由len函数返回值决定
    def __bool__(self):
        pass


ab1 = AB(1, 4)
ab2 = AB(4, 5)
ab1 + ab2
print(ab1)
