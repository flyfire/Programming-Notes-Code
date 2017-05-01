# 定义字符串

str1 = "abcdefg"
str2 = 'opqrst'
str3 = 'abcabcabc'

# 双引号于单引号的不同.双引号内部可以使用单引号。

str4 = "你好世界 '哈哈'----"

for s in str1:  # 遍历字符串
    print(s)

print(str1[0])  # 通过下标访问
print(type(str1[1]))  # 通过下标获取的还是字符串
print(str2[2:4])  # 字符串切片

# find

index = str1.find("d", 2, len(str1))  # len 获取items的长度
print("find result = %d" % index)

# index

index = str1.index("d", 2, len(str1))  # len 获取items的长度
print("index result = %d" % index)

# count

count = str3.count('b', 0, 7)
print("count = "+str(count))
