# sizeof和size_t类型

```
sizt_t intSize;
sizt = sizeof(int);
printf("iniSize=%zd",intSize);
```

C语言规定，sizeof返回的类型是size_t，这时一个无符号的整数类型，有typedef定义，根据系统的不同，所使用整型也是不一样的。

C99做了进一步的调整，新增了使用`$zd`转换说明用于`printf()`显式size_t类型，如果系统不支持，可以使用`%u`或者`&lu`代替。

