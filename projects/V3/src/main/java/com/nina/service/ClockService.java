package com.nina.service;

import com.nina.entity.Leave;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.nina.mapper.ClockMapper;
import com.nina.entity.Clock;
@Service
public class ClockService{

    @Resource
    private ClockMapper clockMapper;

    
    public int deleteByPrimaryKey(Integer clockid) {
        return clockMapper.deleteByPrimaryKey(clockid);
    }


    public int insert(Clock record) {
        return clockMapper.insert(record);
    }

    
    public int insertSelective(Clock record) {
        return clockMapper.insertSelective(record);
    }

    
    public Clock selectByPrimaryKey(Integer clockid) {
        return clockMapper.selectByPrimaryKey(clockid);
    }

//    public Clock selectByUsername(String username) {
//        return clockMapper.selectByUsername(username);
//    }

    public List<Clock> selectByUsername(String username) {
        return clockMapper.selectByUsername(username);
    }

    public List<Clock> selectByUserid(Integer userid){
        return clockMapper.selectByUserid(userid);
    }

    public Clock selectByUseridAndTime(Integer userid, String time) {
        return clockMapper.selectByUseridAndTime(userid, time);
    }

    public List<Clock> select() {
        return clockMapper.select();
    }

    public int updateByPrimaryKeySelective(Clock record) {
        return clockMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Clock record) {
        return clockMapper.updateByPrimaryKey(record);
    }

    
    public int batchInsert(List<Clock> list) {
        return clockMapper.batchInsert(list);
    }

}
