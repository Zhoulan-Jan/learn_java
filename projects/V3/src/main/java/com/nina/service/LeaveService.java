package com.nina.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.nina.entity.Leave;
import com.nina.mapper.LeaveMapper;
import java.util.List;
@Service
public class LeaveService{

    @Resource
    private LeaveMapper leaveMapper;

    
    public int deleteByPrimaryKey(Integer leaveid) {
        return leaveMapper.deleteByPrimaryKey(leaveid);
    }

    
    public int insert(Leave record) {
        return leaveMapper.insert(record);
    }

    
    public int insertSelective(Leave record) {
        return leaveMapper.insertSelective(record);
    }

    
    public Leave selectByPrimaryKey(Integer leaveid) {
        return leaveMapper.selectByPrimaryKey(leaveid);
    }

//    public Leave selectByUsername(String username) {
//        return leaveMapper.selectByUsername(username);
//    }

    public List<Leave> selectByUsername(String username) {
        return leaveMapper.selectByUsername(username);
    }

    public List<Leave> selectByUserid(Integer userid){
        return leaveMapper.selectByUserid(userid);
    }

    public List<Leave> selectNoApp(Integer approvalResult) {
        return leaveMapper.selectNoApp(approvalResult);
    }

    public List<Leave> select() {
        return leaveMapper.select();
    }
    
    public int updateByPrimaryKeySelective(Leave record) {
        return leaveMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Leave record) {
        return leaveMapper.updateByPrimaryKey(record);
    }

    
    public int batchInsert(List<Leave> list) {
        return leaveMapper.batchInsert(list);
    }

}
