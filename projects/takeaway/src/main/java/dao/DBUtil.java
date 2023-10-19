package dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//采用单例模式获取数据库的连接
public class DBUtil {
    private static String URL = "jdbc:mysql://127.0.0.1:3306/sushi?characterEncoding=utf-8&useSSL=true";
    private static String USERNAME = "root";
    private static String PASSWORD = "123456";

    private static volatile DataSource dataSource = null;

    private static DataSource getDataSource() {
        //双重if判定
        if (dataSource == null) {
            //加锁，保证线程安全
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    //向下转型
                    MysqlDataSource mysqlDataSource = (MysqlDataSource) dataSource;
                    mysqlDataSource.setURL(URL);
                    mysqlDataSource.setUser(USERNAME);
                    mysqlDataSource.setPassword(PASSWORD);

                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection connection, PreparedStatement preparedStatement,
                             ResultSet resultSet) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
