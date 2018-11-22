package clink.core;

import java.io.InputStream;

/**
 * 发送包的定义
 *
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 2018/11/18 17:18
 */
public abstract class SendPacket<T extends InputStream> extends Packet<T> {

    private boolean isCanceled = false;

    public boolean isCanceled() {
        return isCanceled;
    }

}
