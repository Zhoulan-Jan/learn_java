package dao;

import dao.UserDao;
import model.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao = new UserDao();
//        System.out.println(userDao.queryUserByUsername("admin"));
        if (userDao.queryUserByUsername("admin") != null) {
            System.out.println("查询成功");
            System.out.println("用户名不可用");
        } else {
            System.out.println("查询不成功");
            System.out.println("用户名可用");
        }

        if (userDao.queryUserByUsername("potter") != null) {
            System.out.println("查询成功");
            System.out.println("用户名不可用");
        } else {
            System.out.println("查询不成功");
            System.out.println("用户名可用");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao = new UserDao();
//        System.out.println(userDao.queryUserByUsername("admin"));
        if (userDao.queryUserByUsernameAndPassword("admin","admin") != null) {
            System.out.println("查询成功");
        } else {
            System.out.println("查询不成功");
        }
    }

    @Test
    public void saveUser() {
        UserDao userDao = new UserDao();

        User user = new User();
        user.setId(12);
        user.setUsername("a"); //当为admin 插入不成功
        user.setPassword("123456");
        user.setEmail("456@sdfa.com");

        userDao.saveUser(user);
    }
}