package dao;

import model.OrderItem;
import model.Sushi;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    public int saveOrderItem(OrderItem orderItem) {
        Connection connection = DBUtil.getConnection();
        String sql = "insert into t_order_item (name, count, price, total_price, order_id) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        int flg = 1;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderItem.getName());
            preparedStatement.setInt(2, orderItem.getCount());
            preparedStatement.setBigDecimal(3, orderItem.getPrice());
            preparedStatement.setBigDecimal(4, orderItem.getTotalPrice());
            preparedStatement.setString(5, orderItem.getOrderId());

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
     * 根据订单号查询订单明细
     * @param orderId
     * @return
     */
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from t_order_item where order_id=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<OrderItem> orderItemList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int count = resultSet.getInt("count");
                BigDecimal price = resultSet.getBigDecimal("price");
                BigDecimal total_price = resultSet.getBigDecimal("total_price");

                orderItemList.add(new OrderItem(id, name, count, price, total_price, orderId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return orderItemList;
    }
}
