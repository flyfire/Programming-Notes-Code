package com.ztiany.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.4.10 23:50
 */
public class Query {

    public static void main(String... args) {
        queryAllWeatherInfo();
    }

    private static void queryAllWeatherInfo() {

        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = JdbcUtil.getConnection();
        try {
            stmt = connection.createStatement();
            String sql = "select * from weather";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("城市：" + rs.getString("city") + " 温度：" + rs.getString("temperature"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.realease(rs, stmt, connection);
        }
    }

}

