#########################
#  函数式编程：Lambda表达式
#########################

from pkg.Tools import print_divider

sum = lambda x, y: x + y
max = lambda x, y: x if x > y  else y  # x if x > y  else y 是Python中的三元表达式

print(sum(1, 2))
print(max(100, 200))
