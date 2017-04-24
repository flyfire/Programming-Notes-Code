# Groovy操作符

 

### 展开操作符(*.)

通常被集合对象调用其所有的对象使用。等价于遍历调用,并将结果收集在一个集合中:

        def items = [4,5]                      
        def list = [1,2,3,*items,6]            
        assert list == [1,2,3,4,5,6]   
        
        def m1 = [c:3, d:4]                   
        def map = [a:1, b:2, *:m1]            
        assert map == [a:1, b:2, c:3, d:4]  
        
也可以参考第八章Map
        
### in操作符

in操作符等价于isCase方法。在一个集合中,它等价于调用contains,例如:
    
    def list = ['Grace','Rob','Emmy']
    assert ('Emmy' in list)

等价于调用:list.contains(‘Emmy’)或者list.isCase(‘Emmy’)

### 强制操作符

强制操作符(as)用于变量的强制转换。强制转换操作符转换对象从一个类型向另外一个类型而不考虑其兼容性。举个例子:
    
    Integer x = 123
    String s = (String) x  

由于Integer不可以自动转化成String,所以在运行时会抛出一个ClassCastException 。 
不过可以使用强制操作符替代:

        Integer x = 123
        String s = x as String  
        
[参考](http://blog.csdn.net/dabaoonline/article/list/2)