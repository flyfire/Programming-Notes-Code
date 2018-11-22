package clink.box;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import clink.core.ReceivePacket;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 2018/11/18 16:52
 */
public class StringReceivePacket extends ReceivePacket<ByteArrayOutputStream> {

    private String mString;

    /**
     * @param bufferLength 存储这个字符串需要的字节长度
     */
    public StringReceivePacket(int bufferLength) {
        this.length = bufferLength;
    }

    @Override
    public String toString() {
        return mString;
    }

    @Override
    protected ByteArrayOutputStream createStream() {
        return new ByteArrayOutputStream((int) getLength());
    }

    @Override
    protected void closeStream(ByteArrayOutputStream stream) throws IOException {
        super.closeStream(stream);
        mString = new String(stream.toByteArray());
    }

}
