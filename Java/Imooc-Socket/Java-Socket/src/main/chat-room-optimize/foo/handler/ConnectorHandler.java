package foo.handler;

import java.io.File;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executor;

import clink.box.StringReceivePacket;
import clink.core.Connector;
import clink.core.Packet;
import clink.core.ReceivePacket;
import clink.utils.CloseUtils;
import foo.Foo;


/**
 * 用于处理客户端连接，读取客户端信息，向客户端发送消息。
 *
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 2018/11/1 23:15
 */
public class ClientHandler extends Connector {

    private final ConnectorCloseChain mCloseChain = new DefaultPrintConnectorCloseChain();//用于处理关闭
    private final ConnectorStringPacketChain mStringPacketChain = new DefaultNonConnectorStringPacketChain();//用于处理字符串消息

    private final String mClientInfo;
    private final File mCachePath;
    private final Executor mDeliveryPool;

    public ClientHandler(SocketChannel client, File cachePath, Executor deliveryPool) throws IOException {
        //初始化客户端信息
        mClientInfo = client.getLocalAddress().toString();
        mCachePath = cachePath;
        System.out.println("新客户端连接：" + mClientInfo);
        mDeliveryPool = deliveryPool;
        setup(client);
    }

    @Override
    public void onChannelClosed(SocketChannel channel) {
        super.onChannelClosed(channel);
        mCloseChain.handle(this, this);
    }

    @Override
    protected File createNewReceiveFile() {
        return Foo.createRandomTemp(mCachePath);
    }

    public String getClientInfo() {
        return mClientInfo;
    }

    public void exit() {
        CloseUtils.close(this);
        mCloseChain.handle(this, this);
    }

    @Override
    protected void onReceiveNewPacket(ReceivePacket packet) {
        super.onReceiveNewPacket(packet);
        switch (packet.getType()) {
            case Packet.TYPE_MEMORY_STRING: {
                deliveryStringPacket((StringReceivePacket) packet);
                break;
            }
            default: {
                System.out.println("New Packet:" + packet.getType() + "-" + packet.getLength());
            }
        }
    }

    private void deliveryStringPacket(StringReceivePacket packet) {
        mDeliveryPool.execute(() -> mStringPacketChain.handle(this, packet));
    }

    /**
     * 获取当前链接的消息处理责任链 链头
     *
     * @return ConnectorStringPacketChain
     */
    public ConnectorStringPacketChain getStringPacketChain() {
        return mStringPacketChain;
    }

    /**
     * 获取当前链接的关闭链接处理责任链 链头
     *
     * @return ConnectorCloseChain
     */
    public ConnectorCloseChain getCloseChain() {
        return mCloseChain;
    }


}
