package com.ztiany.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试sql注入问题
 *
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.4.11 0:32
 */
public class SQLInjection {

    public void testLogin() {
        try {
            login1("zs' or 'zs", "zs");
            ConnectSQl.getInstance().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login(String username, String password) throws SQLException {
        // 3.创建执行sql语句的对象
        Statement stmt = ConnectSQl.getInstance().getConnection().createStatement();
        // 4.书写一个sql语句
        String sql = "select * from tbl_user where " + "uname='" + username + "' and upassword='" + password + "'";
        // 5.执行sql语句
        ResultSet rs = stmt.executeQuery(sql);
        // 6.对结果集进行处理
        if (rs.next()) {
            System.out.println("恭喜您，" + username + ",登录成功!");
            System.out.println(sql);
        } else {
            System.out.println("账号或密码错误!");
        }
        rs.close();
        stmt.close();
    }

    private void login1(String username, String password) throws SQLException {
        // 3.编写sql语句
        String sql = "select * from tbl_user where uname=? and upassword=?";
        // 4.创建预处理对象
        PreparedStatement preparedStatement = ConnectSQl.getInstance().getConnection().prepareStatement(sql);
        // 5.设置参数(给占位符)
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        // 6.执行查询操作
        ResultSet rs = preparedStatement.executeQuery();
        // 7.对结果集进行处理
        if (rs.next()) {
            System.out.println("恭喜您，" + username + ",登录成功!");
            System.out.println(sql);
        } else {
            System.out.println("账号或密码错误!");
        }
        rs.close();
        preparedStatement.close();
    }
}


