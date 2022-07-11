package com.nina.mapper;

import com.nina.entity.Clock;
import java.util.List;

import com.nina.entity.Leave;
import org.apache.ibatis.annotations.Param;


public interface ClockMapper {
    int deleteByPrimaryKey(Integer clockid);

    int insert(Clock record);

    int insertSelective(Clock record);

    Clock selectByPrimaryKey(Integer clockid);

    //Clock selectByUsername(String username);

    List<Clock> selectByUsername(String username);

    List<Clock> selectByUserid(Integer userid);

    Clock selectByUseridAndTime(@Param("userid") Integer userid, @Param("time") String time);

    List<Clock> select();

    int updateByPrimaryKeySelective(Clock record);

    int updateByPrimaryKey(Clock record);

    int batchInsert(@Param("list") List<Clock> list);
}