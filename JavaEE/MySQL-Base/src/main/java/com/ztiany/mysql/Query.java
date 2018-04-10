package com.ztiany.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.4.10 23:50
 */
public class Query {

    public static void main(String... args) {
        queryAllWeatherInfo();
        ConnectSQl.getInstance().close();
    }

    private static void queryAllWeatherInfo() {

        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1.获取执行sql语句对象
            stmt = ConnectSQl.getInstance().getConnection().createStatement();
            //2.编写sql语句
            String sql = "select * from weather";
            //3.执行sql语句
            rs = stmt.executeQuery(sql);
            //4.处理结果集
            while (rs.next()) {
                System.out.println("城市：" + rs.getString("city") + " 温度：" + rs.getString("temperature"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

}

