# 演示定义类

# 定义类，属性、方法、私有属性和方法


class Dog:
    """一只狗"""

    def __init__(self, name, age):
        self.name = name  # 属性
        self.age = age

    # 方法
    def sit(self):
        print(self.name.title() + " is new sitting")

    def roll_over(self):
        print(self.name.title + " rolled over!")


my_dog = Dog("小黄", 2)


class Car:
    """一辆汽车"""

    __code = "2123120FJJ"  # 私有类属性
    _motor = "牛逼的要死"  # protected的类属性

    def __init__(self, make, year):
        self.make = make
        self.year = year
        self.color = "red"
        self.__address = "china"  # 私有对象属性

    # 类似Java的toString方法
    def __str__(self):
        return "Car made in %s brand is %s year is %d color is %s" % (self.__address, self.make, self.year, self.color)

    def __del__(self):
        print(self.__str__() + " is deleted")

    def get_address(self):
        return self.__address

    # 私有方法
    def __start_motor(self):
        print(self.make + "motor is start")

    def start(self):
        self.__start_motor()


BMW = Car("BMW", 2014)
BMW.color = "white"
print(BMW)


# 继承


class Person:
    """一个人"""

    def __init__(self, name, age):
        self.__name = name
        self.__age = age

    def get_name(self):
        return self.__name

    def get_age(self):
        return self.__age


class Man(Person):
    """男人"""

    def __init__(self, name, age):
        super().__init__(name, age)  # 调用父类的方法

    def work(self):
        print("man %s is working" % (self.get_name()))


class Driver:
    __is_driving = False

    def __init__(self, car_type):
        self.car_type = car_type

    def drive(self, car):
        self.__is_driving = True
        car.start()


speed_car = 100


# 多继承


class SpeedRacer(Person, Driver):
    def __init__(self, name, age):
        Person.__init__(self, name, age)
        Driver.__init__(self, speed_car)


speed_racer = SpeedRacer("ztiany", '27')
speed_racer.drive(Car("audi", 2014))

# 对象内置方法和内置属性

print(Dog.__dict__)  # 类的属性（包含一个字典，由类的数据属性组成）
print(Dog.__doc__)  # 类的文档字符串
print(Dog.__name__)  # 类名
print(Dog.__module__)  # 类定义所在的模块
print(Dog.__bases__)  # 父类


# 静态属性和方法


class TestStatic:
    country = 'china'  # 类属性
    _city = "beijin"

    # 类方法：是类对象所拥有的方法，第一个参数必须是类对象
    @classmethod
    def test_class(cls):
        return cls.country

    @classmethod
    def test_class_set(cls, country):
        cls.country = country

    @staticmethod
    def test_static():
        return TestStatic.country


print(TestStatic.country)
print(TestStatic.test_class())
print(TestStatic.test_static())
