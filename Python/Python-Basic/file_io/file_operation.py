#  演示重命名和删除文件
import os

# chdir()方法来改变当前的目录

# 获取当前路径
print(os.getcwd())

# os.remove("c.txt")  # 删除文件
os.rmdir("dir_c")  # 删除目录
os.mkdir("dir_c")  # 创建目录
# os.rename("test2.txt", 'test1.txt')  # 重命名
print(os.curdir)
print(os.pardir)
file_list = os.listdir("H:\codes\my_github\Programming\Python\Python-Basic")

# os的path相关操作
print(os.path.isdir("dir_c"))
print(os.path.isfile("dir_c"))
print(os.path.exists("dir_c"))
