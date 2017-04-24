# if 语句

if语句是用来进行判断的，其使用格式如下：

```
if要判断的条件:
    条件成立时，要做的事情
```

与java中的大括号不同，**if中执行的内容必须使用tab缩进**，

if , else , elif的使用：

```python
condition = True
condition2 = False
if condition:
    print("A")
elif condition2
    print("B")
else:
    print("C")
```

# while语句

while循环的格式：
```
while条件:
    条件满足时，做的事情1
    条件满足时，做的事情2条件
    满足时，做的事情3        .
    ..(省略)...
```
打印九九乘法表：
```python
i = 1
while i<=9:        
    j=1
    while j<=i:            
        print("%d*%d=%-2d "%(j,i,i*j),end='')
        j+=1        
    print('\n')        
    i+=1
```
# for循环

for循环的格式:

```
for临时变量 in 列表或者字符串等:
    循环满足条件时执行的代码
else:循环不满足条件时执行的代码
```
```python
name = 'ztiany'
for x in name:        
    print(x)
```

# break和continue



