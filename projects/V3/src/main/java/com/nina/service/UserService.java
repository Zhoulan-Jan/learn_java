package com.nina.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.nina.entity.User;
import com.nina.mapper.UserMapper;

import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    
    public int deleteByPrimaryKey(Integer userid) {
        return userMapper.deleteByPrimaryKey(userid);
    }

    
    public int insert(User record) {
        return userMapper.insert(record);
    }

    
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    
    public User selectByPrimaryKey(Integer userid) {
        return userMapper.selectByPrimaryKey(userid);
    }

    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    public int selectByUseridAndUsernameAndPassword(Integer userid, String username, String password) {
        return userMapper.selectByUseridAndUsernameAndPassword(userid, username, password);
    }

    public int selectByUseridAndPassword(Integer userid,String password) {
        return userMapper.selectByUseridAndPassword(userid, password);
    }

    public List<User> getUsers() {
        return userMapper.getUsers();
    }


    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

}
