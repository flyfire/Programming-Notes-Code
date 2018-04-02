#########################
# 装饰器
#########################
import time
import functools
import pkg.Tools as Tools

# 一般的装饰器
Tools.print_divider('一般的装饰器')


def time_track(func):
    # args为了保证可以接收不定参数的调用
    # kwargs为了保证可以装饰 **kwargs的函数调用
    def wrapper(*args, **kwargs):
        start = time.time()
        print("call function %s at %f" % (func.__name__, start))
        result = func(*args, **kwargs)
        print("use time = ", time.time() - start)
        return result

    return wrapper


@time_track
def add(x, y):
    return x + y


@time_track
def print_function():
    print("this is a function")


print(add(100, 200))
print(add.__name__)
print_function()  # 原理就是 time_track(print_function)

# 带参数的装饰器
# 如果decorator本身需要传入参数，那就需要编写一个返回decorator的高阶函数
Tools.print_divider('带参数的装饰器')


def log(text):
    def decorator(func):
        def wrapper(*args, **kw):
            print('%s %s():' % (text, func.__name__))
            return func(*args, **kw)

        return wrapper

    return decorator


@log('execute')
def now():
    print('2015-3-25')


now()  # 相当于log('execute')(now)
# 首先执行log('execute')，返回的是decorator函数，再调用返回的函数，参数是now函数，返回值最终是wrapper函数，所以下面结果是 wrapper
print(now.__name__)  # wrapper

# 标准的装饰器，加上@functools.wraps(func)修复
Tools.print_divider('标准的装饰器')


def log_fixed(text):
    def decorator(func):
        @functools.wraps(func)
        def wrapper(*args, **kw):
            print('%s %s():' % (text, func.__name__))
            return func(*args, **kw)

        return wrapper

    return decorator


@log_fixed('execute')
def now_fixed():
    print('2015-3-25')


now_fixed()
print(now_fixed.__name__)  # now_fixed
