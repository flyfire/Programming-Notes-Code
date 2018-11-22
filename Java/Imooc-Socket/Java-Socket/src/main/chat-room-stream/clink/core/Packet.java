package clink.core;

import java.io.Closeable;
import java.io.IOException;

/**
 * 公共的数据封装，提供了类型以及数据长度的定义。
 *
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 2018/11/18 16:35
 */
public abstract class Packet<T extends Closeable> implements Closeable {

    /**
     * 包所代表的数据类型
     */
    protected long length;

    /**
     * 包的长度
     */
    protected byte type;

    private T mStream;

    public byte getType() {
        return type;
    }

    public long getLength() {
        return length;
    }

    @Override
    public final void close() throws IOException {
        if (mStream != null) {
            closeStream(mStream);
            mStream = null;
        }
    }

    public final T open() {
        if (mStream == null) {
            mStream = createStream();
        }
        return mStream;
    }

    protected abstract T createStream();

    protected void closeStream(T stream) throws IOException {
        stream.close();
    }

}
