package dao;

import model.Sushi;
import model.User;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//对寿司的增删改查
public class SushiDao {

    public int addSushi(Sushi sushi) {
        Connection connection = DBUtil.getConnection();
        String sql = "insert into t_sushi ( name , price , sales , stock , img_path) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        int flg = 1;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sushi.getName());
            preparedStatement.setBigDecimal(2, sushi.getPrice());
            preparedStatement.setInt(3, sushi.getSales());
            preparedStatement.setInt(4, sushi.getStock());
            preparedStatement.setString(5, sushi.getImgPath());
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

    public int deleteSushiById(Integer id) {
        Connection connection = DBUtil.getConnection();
        String sql = "delete from t_sushi where id=?";
        PreparedStatement preparedStatement = null;
        int flg = 1;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int ret = preparedStatement.executeUpdate();

            if (ret != 1) {
                System.out.println("数据库执行出错");
                flg = 1;
            } else {
                System.out.println("数据库删除成功");
                flg = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
        return flg;
    }

    public int updateSushi(Sushi sushi) {
        Connection connection = DBUtil.getConnection();
        String sql = "update t_sushi set name=?, price=?,sales=?,stock=? where id=?";
        PreparedStatement preparedStatement = null;
        int flg = 1;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sushi.getName());
            preparedStatement.setBigDecimal(2, sushi.getPrice());
            preparedStatement.setInt(3, sushi.getSales());
            preparedStatement.setInt(4, sushi.getStock());
            //preparedStatement.setString(5, sushi.getImgPath());
            preparedStatement.setInt(5, sushi.getId());
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

    public Sushi querySushiById(int id) {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from t_sushi where id=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Sushi sushi = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                int sales = resultSet.getInt("sales");
                int stock = resultSet.getInt("stock");
                String imgPath = resultSet.getString("img_path");

                sushi = new Sushi(id, name, price, sales, stock, imgPath);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return sushi;
    }

    public List<Sushi> querySushis() {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from t_sushi";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Sushi> sushiList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                int sales = resultSet.getInt("sales");
                int stock = resultSet.getInt("stock");
                String imgPath = resultSet.getString("img_path");

                sushiList.add(new Sushi(id, name, price, sales, stock, imgPath));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return sushiList;
    }

    /**
     * 得到商品的记录数
     * @return
     */
    public Integer queryForPageTotalCount() {
        Connection connection = DBUtil.getConnection();
        String sql = "select count(*) from t_sushi";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Number count = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return count.intValue();
    }

    /**
     * 一页展示的商品
     * @param begin 初始位置
     * @param pageSize 一页展示的商品数
     * @return
     */
    public List<Sushi> queryForPageItems(int begin, int pageSize) {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from t_sushi limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Sushi> sushiList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, begin);
            preparedStatement.setInt(2, pageSize);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                int sales = resultSet.getInt("sales");
                int stock = resultSet.getInt("stock");
                String imgPath = resultSet.getString("img_path");

                sushiList.add(new Sushi(id, name, price, sales, stock, imgPath));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return sushiList;
    }

    /**
     * 在min-max价格区间的商品数
     * @param min
     * @param max
     * @return
     */
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        Connection connection = DBUtil.getConnection();
        String sql = "select count(*) from t_sushi where price between ? and ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Number count = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return count.intValue();
    }

    /**
     *
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    public List<Sushi> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from t_sushi where price between ? and ? order by price limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Sushi> sushiList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            preparedStatement.setInt(3, begin);
            preparedStatement.setInt(4, pageSize);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                int sales = resultSet.getInt("sales");
                int stock = resultSet.getInt("stock");
                String imgPath = resultSet.getString("img_path");

                sushiList.add(new Sushi(id, name, price, sales, stock, imgPath));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return sushiList;

    }
}
