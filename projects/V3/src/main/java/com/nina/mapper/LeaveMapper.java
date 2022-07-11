package com.nina.mapper;

import com.nina.entity.Leave;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LeaveMapper {
    int deleteByPrimaryKey(Integer leaveid);

    int insert(Leave record);

    int insertSelective(Leave record);

    Leave selectByPrimaryKey(Integer leaveid);

//    Leave selectByUsername(String username);

    List<Leave> selectByUsername(String username);

    List<Leave> selectByUserid(Integer userid);

    List<Leave> selectNoApp(Integer approvalResult);

    List<Leave> select();

    int updateByPrimaryKeySelective(Leave record);

    int updateByPrimaryKey(Leave record);

    int batchInsert(@Param("list") List<Leave> list);
}