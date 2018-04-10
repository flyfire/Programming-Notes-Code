package com.ztiany.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.4.11 0:09
 */
public class ConnectSQl {

    public static ConnectSQl getInstance() {
        return connectSQl;
    }

    private static ConnectSQl connectSQl = new ConnectSQl();

    private Connection mConnection;
    private boolean mIsClose = true;

    private ConnectSQl() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接失败");
        }
    }

    private void init() throws Exception {
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/sqlbase?useUnicode=true&characterEncoding=utf8";
        String username = "root";
        String password = "201314";
        mConnection = DriverManager.getConnection(url, username, password);
        mIsClose = false;
    }


    public Connection getConnection() {
        if (mIsClose) {
            throw new RuntimeException("数据库连接已经关闭");
        }
        return mConnection;
    }

    public synchronized void close() {
        if (mConnection != null) {
            try {
                mConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        mIsClose = true;
    }
}
