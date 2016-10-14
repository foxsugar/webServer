package server.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by SunXianping on 2016/10/13 0013.
 *
 */
public class Test {
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");// ¶¯Ì¬¼ÓÔØmysqlÇý¶¯
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/test1?"
            + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";


    public void test(String name) {
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String qs = "select * from user where name="+name+";";
        try {
            Statement statement = conn.createStatement();
            statement.executeQuery(qs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
