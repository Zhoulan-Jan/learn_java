package dao;

import model.Order;
import model.Sushi;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    /**
     * 保存订单
     * @param order
     * @return
     */
    public int saveOrder(Order order) {
        Connection connection = DBUtil.getConnection();
        String sql = "insert into t_order (order_id, create_time, price, status, user_id) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        int flg = 1;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, order.getOrderId());
            preparedStatement.setObject(2, order.getCreateTime());
            preparedStatement.setBigDecimal(3, order.getPrice());
            preparedStatement.setInt(4, order.getStatus());
            preparedStatement.setInt(5, order.getUserId());

            int ret = preparedStatement.executeUpdate();

            if (ret != 1) {
                System.out.println("数据库执行出错");
                flg = 1;
            } else {
                System.out.println("数据库插入成功");
                flg = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
        return flg;
    }

    /**
     * 查询全部订单
     * @return
     */
    public List<Order> queryOrders() {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from t_order";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orderList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("order_id");
                Date createTime = resultSet.getDate("create_time");
                BigDecimal price = resultSet.getBigDecimal("price");
                int status = resultSet.getInt("status");
                int userId = resultSet.getInt("user_id");

                orderList.add(new Order(id, createTime, price, status, userId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return orderList;
    }

    /**
     * 修改订单状态
     * @param orderId
     * @param status
     * @return
     */
    public int changeOrderStatus(String orderId, int status) {
        Connection connection = DBUtil.getConnection();
        String sql = "update t_order set status=? where order_id=?";
        PreparedStatement preparedStatement = null;
        int flg = 1;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, status);
            preparedStatement.setString(2, orderId);

            int ret = preparedStatement.executeUpdate();

            if (ret != 1) {
                System.out.println("数据库执行出错");
                flg = 1;
            } else {
                System.out.println("数据库修改成功");
                flg = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
        return flg;
    }


    /**
     * 根据用户编号查询订单信息
     * @param userId
     * @return
     */
    public List<Order> queryOrdersByUserId(int userId) {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from t_order where user_id=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orderList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("order_id");
                Date createTime = resultSet.getDate("create_time");
                BigDecimal price = resultSet.getBigDecimal("price");
                int status = resultSet.getInt("status");

                orderList.add(new Order(id, createTime, price, status, userId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return orderList;
    }
}
