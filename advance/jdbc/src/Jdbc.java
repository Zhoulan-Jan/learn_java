import java.sql.*;

public class Jdbc {

    public static void main(String[] args) throws ClassNotFoundException {
        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //配置连接字符串
        String url = "jdbc:mysql://127.0.0.1:3306/test?user=root&" +
                "password=123456&useUnicode=true&characterEncoding=UTF-8";
        //创建数据库连接对象
        try {
            Connection connection = DriverManager.getConnection(url);
//            String sql = "select * from table";
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);

//            String sql = "select * from table where name=?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, "harry");
//            ResultSet resultSet = preparedStatement.executeQuery();

            String sql = "insert into table values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "harry");
            preparedStatement.setString(2, "man");
            preparedStatement.setInt(3, 18);
            int ret = preparedStatement.executeUpdate();

//            while (resultSet.next()) {
//                String name = resultSet.getString("name");
//                String sex = resultSet.getString("sex");
//                int age = resultSet.getInt("age");
//            }

            if (ret != 1) {
                System.out.println("数据库执行出错");
            } else {
                System.out.println("数据库插入成功");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
