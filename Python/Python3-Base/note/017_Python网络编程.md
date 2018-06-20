# Python网络编程

---
## 1 socket 编程

Python 提供了两个级别访问的网络服务：

- 低级别的网络服务支持基本的 Socket，它提供了标准的 BSD Sockets API，可以访问底层操作系统 Socket 接口的全部方法。
- 高级别的网络服务模块 SocketServer， 它提供了服务器中心类，可以简化网络服务器的开发。

Python 的 socket 模块提供了socket 网络编程相关API，比如TCP、UDP等。

### socket 模块

在 Python 中 使用 socket 模块的函数 socket 就可以完成：

```python
"""
函数 socket.socket 创建一个 socket，返回该 socket 的描述符，该函数带有两个参数
            Address Family：可以选择 AF_INET（用于 Internet 进程间通信） 或者 AF_UNIX（用于同一台机器进程间通信）,实际工作中常用AF_INET
            Type：套接字类型，可以是 SOCK_STREAM（流式套接字，主要用于 TCP 协议）或者 SOCK_DGRAM（数据报套接字，主要用于 UDP 协议）
"""
socket.socket(AddressFamily, Type)
```

### Python TCP Socket listen队列长度

```python
from socket import *

# 创建socket
tcpSerSocket = socket(AF_INET, SOCK_STREAM)

# 绑定本地信息
address = ('', 7788)
tcpSerSocket.bind(address)

connNum = int(input("请输入要最大的链接数:"))

# 使用socket创建的套接字默认的属性是主动的，使用listen将其变为被动的，这样就可以接收别人的链接了
tcpSerSocket.listen(connNum)

while True:

    # 如果有新的客户端来链接服务器，那么就产生一个新的套接字专门为这个客户端服务器
    newSocket, clientAddr = tcpSerSocket.accept()
    print (clientAddr)
```

`tcpSerSocket.listen(connNum)`中 connNum 表示半连接数和已建立连接的最大数量：

- 半连接：TCP三次握手服务器收到SYN信号
- 已连接：TCP三次握手完成
- 如果服务器调用accept创建了一个客户端socket，那么linux底层的半连接与已连接的中客户端个数就会减一
- 如果当前已建立链接数和半链接数以达到设定值，那么新客户端就不会connect成功，而是等待服务器

---
## 2 其他网络编程模块

- HTTP访问功能：`http, urllib, xmlrpclib`
- FTP文件传输：`ftplib, urllib`
- SMTP发送邮件：`smtplib`
- POP3	接收邮件：`poplib`
- Telnet命令行	：`telnetlib`

---
##  3 并发服务器

- 单进程服务器
- 多进程服务器
- 多线程服务器
- 单进程非阻塞服务器
- 单进程select非阻塞服务器
- 单进程epoll非阻塞服务器
- 单进程协程服务器

---
## 4 WSGI

WSGI(Python Web Server Gateway Interface)：WSGI允许开发者将选择web框架和web服务器分开。可以混合匹配web服务
器和web框架，选择⼀个适合的配对。⽐如,可以在Gunicorn 或者Nginx/uWSGI 或者 Waitress上运⾏ Django, Flask, 或 Pyramid。
真正的混合匹配，得益于WSGI同时⽀持服务器和架构。

web服务器必须具备WSGI接⼝，所有的现代Python Web框架都已具备WSGI接⼝，它让你不对代码作修改就能使服务器和特点的web框架协同⼯作。
WSGI由web服务器⽀持，⽽web框架允许你选择适合⾃⼰的配对，但它同样对于服务器和框架开发者提供便利使他们可以专注于⾃⼰偏爱的领域和专⻓
⽽不⾄于相互牵制。其他语⾔也有类似接⼝：Java有Servlet API，Ruby 有Rack。

WSGI接⼝定义⾮常简单，它只要求Web开发者实现⼀个函数，就可以响应HTTP请求。

```python
# 符合WSGI标准的⼀个HTTP处理函数
def application(environ, start_response):
    start_response('200 OK', [('Content-Type', 'text/html')])
    return 'Hello World!'
```

- environ：⼀个包含所有HTTP请求信息的dict对象；
- start_response：⼀个发送HTTP响应的函数。

整个 application() 函数本身没有涉及到任何解析HTTP的部分，也就是说，把底层web服务器解析部分和应⽤程序逻辑部分进⾏了分离，这样开发
者就可以专⼼做⼀个领域了，application() 函数必须由WSGI服务器来调⽤。

---
## 引用

- UDP打包与解包——[Python使用struct处理二进制](https://www.cnblogs.com/gala/archive/2011/09/22/2184801.html)
- [tcp的半连接与完全连接队列](https://segmentfault.com/a/1190000008224853)
