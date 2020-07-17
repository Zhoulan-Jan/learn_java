package service;

import model.User;
import org.junit.Test;
import service.UserService;

public class UserServiceTest {

    UserService userService = new UserService();

    @Test
    public void registerUser() {
        userService.registerUser(new User("harr", "456","456@qq.com"));
        userService.registerUser(new User("potte", "789","789@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User("admin", "admin", "456@qq.com")));
        System.out.println(userService.login(new User("admin", "234",null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("admin")) {
            System.out.println("用户已存在"); //存在
        } else {
            System.out.println("用户不存在");
        }

        if (userService.existsUsername("potter")) {
            System.out.println("用户已存在");
        } else {
            System.out.println("用户不存在");//不存在
        }
    }
}