[TOC]
# Aspectj 学习

## 1 基本基本概念

### Aspect (切面)

是针对切面的模块。最常见的是logging模块、方法执行耗时统计模块，这样程序按功能被分为好几层，
而通过创建一个切面就可以使用AOP来实现相同的功能了，我们可以针对不同的需求做出不同的切面。

切面模块不会干扰到业务逻辑模块，但是又能把具体的业务逻辑模块按照自己的切面功能切开，形成各个细粒度的切入点。
在切入点中插入自己的功能逻辑。

### Join Points(连接点)

连接点是切面插入应用程序的地方，该点能被方法调用，而且也会被抛出意外。连接点是应用程序提供给切面插入的地方，
在插入地建立AspectJ程序与源程序的连接。例如，**构造方法调用、调用方法、方法执行、异常等等**，这些都是Join Points，
实际上，也就是你想把新的代码插在程序的哪个地方，是插在构造方法中，还是插在某个方法调用前，或者是插在某个方法中，这个地方都是是Join Points

### Pointcuts(切入点)

     在Join Points中通过一定条件选择出所需要的Join Points

###Advice(具体插入的代码)

     - Before：在插入点前插入代码
     - After：在插入点后插入代码
     - Around：在插入点前后插入代码

Advance为Before和After时，切入方法的参数应该JoinPoint，而Advance为Around时，方法参数应该为ProceedingJoinPoint，ProceedingJoinPoint继承JoinPoint，多了proceed功能，
此时如果我们不调用proceed方法，被切入的方法将不会被调用,Around和After是不能同时作用在同一个方法上的，会产生重复切入的问题。


## 2 Method 参数规则

