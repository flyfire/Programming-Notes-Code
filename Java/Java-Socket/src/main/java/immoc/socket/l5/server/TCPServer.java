package immoc.socket.l5.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import immoc.socket.l5.clink.CloseUtils;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 2018/11/1 21:17
 */
class TCPServer {

    private final int mPortServer;
    private ClientListener mClientListener;
    private List<ClientHandler> mClientHandlers;

    TCPServer(int portServer) {
        mPortServer = portServer;
        mClientHandlers = new ArrayList<>();
    }

    /*启动服务器*/
    boolean start() {
        try {
            ClientListener clientListener = new ClientListener(mPortServer);
            clientListener.start();
            mClientListener = clientListener;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*给所有客户端发送消息*/
    void broadcast(String line) {
        for (ClientHandler clientHandler : mClientHandlers) {
            clientHandler.send(line);
        }
    }

    /*停止服务器*/
    void stop() {
        for (ClientHandler clientHandler : mClientHandlers) {
            clientHandler.exit();
        }
        mClientListener.exit();
        mClientHandlers.clear();
    }

    /**
     * TCP监听
     */
    private class ClientListener extends Thread {

        private ServerSocket mServerSocket;
        private volatile boolean mDone;

        ClientListener(int port) throws IOException {
            mServerSocket = new ServerSocket(port);
            System.out.println("服务器信息：" + mServerSocket.getInetAddress() + " P:" + mServerSocket.getLocalPort());
        }

        @Override
        public void run() {
            System.out.println("服务器准备就绪～");

            do {

                Socket client;
                try {
                    //等待客户端
                    client = mServerSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }

                try {
                    ClientHandler clientHandler = new ClientHandler(client, handler -> mClientHandlers.remove(handler));
                    // 读取数据并打印
                    clientHandler.readToPrint();
                    mClientHandlers.add(clientHandler);
                } catch (IOException e) {
                    System.out.println("客户端连接异常：" + e.getMessage());
                }

            } while (!mDone);

            System.out.println("服务器已关闭！");
        }

        private void exit() {
            CloseUtils.close(mServerSocket);
            mDone = true;
        }

    }


}
