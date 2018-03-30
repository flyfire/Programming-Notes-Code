# re为正则表达式模块
import re

string1 = "1C|2C++|3Java|4C#|5Python|6JavaScript"
string2 = "100000001"

# 找到Python
python = re.findall("Python", string1)  # 返回包含结果的list
print(python)

# 找到所有数字
allDigit = re.findall('\d', string1)
print(allDigit)

# 贪婪与非贪婪
print(re.findall("[A-z]{3,6}", string1))  # 贪婪模式 ['Java', 'Python', 'JavaSc', 'ript']
print(re.findall("[A-z]{3,6}?", string1))  # 非贪婪模式 ['Jav', 'Pyt', 'hon', 'Jav', 'aSc', 'rip']

# 边界匹配
print(re.findall("\d{4,8}", string2))
print(re.findall("^\d{4,8}$", string2))

# 模式：re.I表示忽略大小写
print(re.findall("python", string1), re.I)
