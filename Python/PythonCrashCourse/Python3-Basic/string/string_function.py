#########################
# 演示各种String的常用操作方法
#########################

str1 = "abcdefg"
str2 = 'opqrst'
str3 = 'abcabcabc'

# find
index = str1.find("d", 2, len(str1))  # len 获取items的长度
print("find result = %d" % index)

# index
index = str1.index("d", 2, len(str1))  # len 获取items的长度
print("index result = %d" % index)

# count
count = str3.count('b', 0, 7)
print("count = " + str(count))
