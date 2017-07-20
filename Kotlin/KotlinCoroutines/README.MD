[Kotlin协程](https://github.com/Kotlin/kotlinx.coroutines/blob/master/coroutines-guide.md)学习

# 1 协程的概念

- [我理解的协程](https://www.zybuluo.com/kuailezhishang/note/128823)
- [协程](http://www.liaoxuefeng.com/wiki/001374738125095c955c1e6d8bb493182103fac9270762a000/0013868328689835ecd883d910145dfa8227b539725e5ed000)
 
# 2 Kotlin协程基本内容

- 协程基础：coroutines_01_base
    - 桥接阻塞和非阻塞世界
    - 等待一个job
    - Coroutines是轻量级的
    - Coroutines类似守护线程
- 撤销和超时
    - 取消协程执行
    - 创建可以取消的计算任务
    - 在finally中释放资源
    - run一个不能取消的的块
    - 超时
- 组合暂停函数
    - 顺序执行
    - 使用async并发执行
    - 懒执行
    - Async-style functions
- Coroutine context and dispatchers(调度器)
     - Dispatchers and threads
    - 无限制的dispatcher vs confined(限制的) dispatcher
    - Debugging coroutines_01_base and threads
    - Jumping between threads(在不同的线程中跳转)
    - Job in the context(Job是context的属性)
    - coroutine的子coroutine
    - 结合 contexts
    - Naming coroutines_01_base for debugging
    - 明确的取消Job

- [ ] Channels
- [ ] Shared mutable state and concurrency
- [ ] Select expression