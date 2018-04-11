package com.ztiany.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.4.11 0:07
 */
public class Create {

    public static void main(String... args) {
        createWeatherInfo();
        ConnectSQl.getInstance().close();
    }

    private static void createWeatherInfo() {
        Connection connection = ConnectSQl.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            boolean execute = statement.execute("create table weather(" +
                    "city varchar(100) not null," +
                    "temperature integer not null" +
                    ");");

            if (!execute) {
                return;
            }

            statement.execute("insert into weather (city,temperature) values ('Austin', 48);");
            statement.execute("insert into weather (city,temperature) values ('Boton Rouge', 21);");
            statement.execute("insert into weather (city,temperature) values ('Jsckson', 2);");
            statement.execute("insert into weather (city,temperature) values ('Montgomery', 32);");
            statement.execute("insert into weather (city,temperature) values ('Sacramento', 22);");
            statement.execute("insert into weather (city,temperature) values ('Santa', 1);");
            statement.execute("insert into weather (city,temperature) values ('Tallahassee', 23);");
            statement.execute("insert into weather (city,temperature) values ('Shenzhen', 17);");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
