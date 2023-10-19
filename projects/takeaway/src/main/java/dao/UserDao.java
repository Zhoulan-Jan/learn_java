package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao {
    /**
     *根据用户名查询一条用户信息
     * @param username
     * @return 用户信息
     */
    public User queryUserByUsername(String username)  {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from t_user where username=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        User user = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");

                user = new User(id, username, password, email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return user;
    }

    /**
     * 根据用户名和密码查询一条用户信息
     * @param username
     * @param password
     * @return 用户信息
     */
    public User queryUserByUsernameAndPassword(String username, String password)  {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from t_user where username=? and password=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");

                user = new User(id, username, password, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return user;
    }

    /**
     * 增加一条用户信息
     * @param user
     */
    public void saveUser(User user)  {
        Connection connection = DBUtil.getConnection();
        String sql = "insert into t_user (username,password,email) values (?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            int ret = preparedStatement.executeUpdate();

            if (ret != 1) {
                System.out.println("数据库执行出错");
            } else {
                System.out.println("数据库插入成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }
}
