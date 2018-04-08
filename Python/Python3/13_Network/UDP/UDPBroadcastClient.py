#########################
#  udp 广播客户端
#########################

import socket


def start_client():
    dest = ('<broadcast>', 8888)

    # 创建udp套接字
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    # 对这个需要发送广播数据的套接字进行修改设置(固定写法)，否则不能发送广播数据
    s.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)

    # 以广播的形式发送数据到本网络的所有电脑中
    s.sendto("Hi".encode(), dest)
    s.sendto("Hi".encode(), dest)
    s.sendto("Hi".encode(), dest)
    s.sendto("Hi".encode(), dest)
    s.sendto("Hi".encode(), dest)

    print("等待对方回复（按ctrl+c退出）")

    while True:
        (buf, address) = s.recvfrom(2048)
        print("Received from %s: %s" % (address, buf))


start_client()
