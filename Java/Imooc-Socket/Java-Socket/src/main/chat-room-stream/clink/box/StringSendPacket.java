package clink.box;

import java.io.ByteArrayInputStream;

import clink.core.SendPacket;

/**
 * 字符串包
 *
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 2018/11/18 16:50
 */
public class StringSendPacket extends SendPacket<ByteArrayInputStream> {

    private final byte[] bytes;

    public StringSendPacket(String send) {
        this.bytes = send.getBytes();
        this.length = bytes.length;
    }

    @Override
    protected ByteArrayInputStream createStream() {
        return new ByteArrayInputStream(bytes);
    }

}
