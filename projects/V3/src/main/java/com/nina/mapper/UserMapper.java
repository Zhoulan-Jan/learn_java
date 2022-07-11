package com.nina.mapper;

import com.nina.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    User selectByUsername(String username);

    int selectByUseridAndUsernameAndPassword(@Param("userid") Integer userid, @Param("username") String username, @Param("password") String password);

    int selectByUseridAndPassword(@Param("userid") Integer userid, @Param("password") String password);

    List<User> getUsers();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}