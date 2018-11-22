package clink.core;

import java.io.OutputStream;

/**
 * 接收包的定义，不同的数据类型对应不同的 ReceivePack 实现。
 *
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 2018/11/18 16:38
 */
public abstract class ReceivePacket<T extends OutputStream> extends Packet<T> {

}
