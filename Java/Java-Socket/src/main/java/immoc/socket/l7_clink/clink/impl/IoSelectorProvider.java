package immoc.socket.l7_clink.clink.impl;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import immoc.socket.l7_clink.clink.core.IoProvider;
import immoc.socket.l7_clink.clink.utils.CloseUtils;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 2018/11/8 22:57
 */
public class IoSelectorProvider implements IoProvider {

    private final AtomicBoolean isClosed = new AtomicBoolean(false);

    // 是否处于注册读过程
    private final AtomicBoolean inRegInput = new AtomicBoolean(false);
    // 是否处于注册写过程
    private final AtomicBoolean inRegOutput = new AtomicBoolean(false);

    //读选择器，读写分离
    private final Selector readSelector;
    //写选择器，读写分离
    private final Selector writeSelector;


    private final HashMap<SelectionKey, Runnable> inputCallbackMap = new HashMap<>();
    private final HashMap<SelectionKey, Runnable> outputCallbackMap = new HashMap<>();

    private final ExecutorService inputHandlePool;
    private final ExecutorService outputHandlePool;

    /**
     * 创建并启动IoSelectorProvider
     */
    public IoSelectorProvider() throws IOException {
        readSelector = Selector.open();
        writeSelector = Selector.open();

        inputHandlePool = Executors.newFixedThreadPool(4,
                new IoProviderThreadFactory("IoProvider-Input-Thread-"));
        outputHandlePool = Executors.newFixedThreadPool(4,
                new IoProviderThreadFactory("IoProvider-Output-Thread-"));

        // 开始输出输入的监听
        startRead();
        startWrite();
    }

    private void startWrite() {
        Thread thread = new Thread("Clink IoSelectorProvider ReadSelector Thread") {

            @Override
            public void run() {
                while (!isClosed.get()) {
                    try {
                        //阻塞等待可写
                        if (writeSelector.select() == 0) {
                            waitSelection(inRegInput);
                            continue;
                        }
                        //获取到可写的 SelectionKey，开始处理
                        Set<SelectionKey> selectionKeys = writeSelector.selectedKeys();
                        for (SelectionKey selectionKey : selectionKeys) {
                            if (selectionKey.isWritable()) {
                                handleSelection(selectionKey, SelectionKey.OP_READ, inputCallbackMap, inputHandlePool);
                            }
                        }
                        //处理完后需要清理
                        selectionKeys.clear();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }//try end
                }//while end
            }//run end

        };
        //因为希望客户端得到最快的响应，所以设置读写为最高的优先级
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    private void startRead() {
        Thread thread = new Thread("Clink IoSelectorProvider WriteSelector Thread") {
            @Override
            public void run() {
                while (!isClosed.get()) {
                    try {
                        //select 方法用于选择一组键，其相应的通道已为 I/O 操作准备就绪。 此方法执行处于阻塞模式的选择操作。
                        // 仅在至少选择一个通道、调用此选择器的 wakeup 方法，或者当前的线程已中断（以先到者为准）后此方法才返回。
                        if (writeSelector.select() == 0) {  //阻塞等待可写
                            waitSelection(inRegOutput);
                            continue;
                        }
                        //获取到可写的 SelectionKey，开始处理
                        Set<SelectionKey> selectionKeys = writeSelector.selectedKeys();
                        for (SelectionKey selectionKey : selectionKeys) {
                            if (selectionKey.isValid()) {
                                handleSelection(selectionKey, SelectionKey.OP_WRITE, outputCallbackMap, outputHandlePool);
                            }
                        }
                        //处理完后需要清理
                        selectionKeys.clear();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }//try end
                }//while end
            }//run end
        };
        //因为希望客户端得到最快的响应，所以设置读写为最高的优先级
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    @Override
    public boolean registerInput(SocketChannel channel, HandleInputCallback callback) {
        //注册关心可读的SocketChannel
        return registerSelection(channel, readSelector, SelectionKey.OP_READ, inRegInput, inputCallbackMap, callback) != null;
    }

    @Override
    public boolean registerOutput(SocketChannel channel, HandleOutputCallback callback) {
        //注册关心可写的SocketChannel
        return registerSelection(channel, writeSelector, SelectionKey.OP_WRITE, inRegOutput, outputCallbackMap, callback) != null;
    }

    @Override
    public void unRegisterInput(SocketChannel channel) {
        if (channel.isRegistered()) {
            SelectionKey selectionKey = channel.keyFor(readSelector);
            // 取消监听的方法
            selectionKey.cancel();
            inputCallbackMap.remove(selectionKey);
            readSelector.wakeup();
        }
    }

    @Override
    public void unRegisterOutput(SocketChannel channel) {
        if (channel.isRegistered()) {
            SelectionKey selectionKey = channel.keyFor(writeSelector);
            // 取消监听的方法
            selectionKey.cancel();
            outputCallbackMap.remove(selectionKey);
            writeSelector.wakeup();
        }
    }

    @Override
    public void close() {
        if (isClosed.compareAndSet(false, true)) {
            inputHandlePool.shutdown();
            outputHandlePool.shutdown();

            inputCallbackMap.clear();
            outputCallbackMap.clear();

            readSelector.wakeup();
            writeSelector.wakeup();

            CloseUtils.close(readSelector, writeSelector);
        }
    }


    private void handleSelection(SelectionKey selectionKey, int keyOps, HashMap<SelectionKey, Runnable> map, ExecutorService executorService) {
        // 重点
        // 取消继续对keyOps的监听
        selectionKey.interestOps(selectionKey.readyOps() & ~keyOps);

        Runnable runnable = null;

        try {
            runnable = map.get(selectionKey);
        } catch (Exception ignored) {
        }

        // 异步调度
        if (runnable != null && !executorService.isShutdown()) {
            executorService.execute(runnable);
        }
    }

    private void waitSelection(AtomicBoolean locker) {
        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (locker) {
            //如果处于 locker 所表示的状态，让该线程等待。
            if (locker.get()) {
                try {
                    locker.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 给SocketChannel注册对应的事件，该方法时线程安全的
     */
    private SelectionKey registerSelection(SocketChannel channel, Selector selector, int registerOps,
                                           AtomicBoolean locker, Map<SelectionKey, Runnable> map, Runnable runnable) {
        //要求线程安全
        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (locker) {
            //设置位锁定状态
            locker.set(true);
            try {
                selector.wakeup();
                SelectionKey selectionKey = null;
                //如果注册过就获取Key后修改Key的组合值
                if (channel.isRegistered()) {
                    //获取表示通道向给定选择器注册的键。 当此通道是向给定选择器注册的最后一个通道时返回该键，如果此通道当前未向该选择器注册，则返回 null
                    selectionKey = channel.keyFor(selector);
                    if (selectionKey != null) {
                        // selectionKey 中的 readyOps 值是一个复合值，注册多个事件到 selectionKey 中，将以位的形式保存。
                        // readyOps 用于获取此键的 ready 操作集合。
                        // interestOps 用于将此键的 interest 集合设置为给定值。下面按位或的操作就是组合多个操作
                        selectionKey.interestOps(selectionKey.readyOps() | registerOps);
                    }
                }
                //如果没有注册过，则注册后添加
                if (selectionKey == null) {
                    selectionKey = channel.register(selector, registerOps);
                    map.put(selectionKey, runnable);
                }
                return selectionKey;
            } catch (ClosedChannelException e) {
                e.printStackTrace();
                return null;
            } finally {
                //设置为非锁定状态
                locker.set(false);
                try {
                    //通知被锁定的线程
                    locker.notify();
                } catch (Exception ignored) {
                }
            }
        }
    }

    static class IoProviderThreadFactory implements ThreadFactory {

        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        IoProviderThreadFactory(String namePrefix) {
            SecurityManager s = System.getSecurityManager();
            this.group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = namePrefix;
        }

        public Thread newThread(@NotNull Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }//IoProviderThreadFactory end

}
