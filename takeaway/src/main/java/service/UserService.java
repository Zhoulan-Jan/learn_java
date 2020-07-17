package service;

import dao.UserDao;
import model.User;


public class UserService {
    private UserDao userDao = new UserDao();

    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    /**
     * 登录
     * @param user
     * @return 若返回null，表示登录失败
     */
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    /**
     * 用户是否存在
     * @param username
     * @return true表示用户存在
     */
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null) {
            return false;
        }
        return true;
    }
}
