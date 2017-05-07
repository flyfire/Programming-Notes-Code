# 演示Python中的字符串

# 定义字符串
str1 = "abcdefg"
str2 = 'opqrst'
str3 = 'abcabcabc'

# 多行字符串
str4 = """234141
212
"""

# 双引号于单引号的不同.双引号内部可以使用单引号。
str5 = '''423414141'''
str6 = "3424234 '234234'----"

# 遍历字符串
for s in str1:
    print(s)

# 通过下标访问
print(str1[0])

# 通过下标获取的还是字符串
print(type(str1[1]))

# 字符串切片
print(str2[2:4])

# 原始(非转义)字符串
print(R"\n")
