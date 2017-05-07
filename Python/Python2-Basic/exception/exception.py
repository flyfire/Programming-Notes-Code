# coding=utf-8
# 使用except而带多种异常类型
try:
    a = 9 / 0
except(ZeroDivisionError, SystemError):
    a = 0
else:
    print a


# 异常的参数
def temp_convert(var):
    try:
        return int(var)
    except ValueError, Argument:
        print "参数没有包含数字:\n", Argument


temp_convert("xyz")
