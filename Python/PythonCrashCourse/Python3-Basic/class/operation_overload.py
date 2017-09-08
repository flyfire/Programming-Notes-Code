class AB:
    def __init__(self, a, b):
        self.a = a;
        self.b = b

    # 操作符重载
    def __add__(self, other):
        self.a += other.a
        self.b += other.b

    def __str__(self):
        return "a = %d , b = %d" % (self.a, self.b)


ab1 = AB(1, 4)
ab2 = AB(4, 5)
ab1 + ab2
print(ab1)
