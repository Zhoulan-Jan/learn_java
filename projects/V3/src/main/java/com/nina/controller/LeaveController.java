package com.nina.controller;

import com.alibaba.fastjson.JSON;
import com.nina.entity.Clock;
import com.nina.entity.Leave;
import com.nina.entity.User;
import com.nina.service.LeaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class LeaveController {
    @Resource
    private LeaveService leaveService;

    @RequestMapping("/leaveList")
    public String leaveList(Model model) {
        List<Leave> list = leaveService.select();
        model.addAttribute("list", list);
        return "manager/leave_list";
    }

    @RequestMapping("/selectByUsername")
    public String selectByUsername(String username, Model model) {
        //List<Leave> list = new ArrayList<>();
        List<Leave> list  = leaveService.selectByUsername(username);
        //list.add(leave);
        model.addAttribute("list", list);
        return "manager/leave_list";
    }

    @RequestMapping("/selectNoApp")
    public  String selectNoApp(Model model) {
        List<Leave> list  = leaveService.selectNoApp(0);
        model.addAttribute("list", list);
        return "manager/leave_list";
    }


    @RequestMapping("/updateAgree")
    public String updateAgree(Integer id) {
        Leave leave = leaveService.selectByPrimaryKey(id);
        leave.setApprovalStatus(1);
        leave.setApprovalResult("同意");
        System.out.println("修改后：" + leave);
        leaveService.updateByPrimaryKeySelective(leave);
        return "redirect:leaveList";
    }

    @RequestMapping("/updateRefuse")
    public String updateRefuse(Integer id) {
        Leave leave = leaveService.selectByPrimaryKey(id);
        leave.setApprovalStatus(1);
        leave.setApprovalResult("拒绝");
        System.out.println("修改后：" + leave);
        leaveService.updateByPrimaryKeySelective(leave);
        return "redirect:leaveList";
    }

    @RequestMapping("/addLeave")
    public void addLeave(Leave leave, HttpServletResponse response) throws IOException, ParseException {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String time = df.format(new Date());
//        leave.setStartTime(df.parse(time));
//        leave.setEndTime(df.parse(time));

        int cnt = leaveService.insertSelective(leave);
        if (cnt > 0) {
            response.setStatus(200);
            response.getWriter().println("ok");
        } else {
            response.setStatus(500);
        }
    }

    @RequestMapping("/selectLeaves")
    public void selectTime(Integer userid, HttpServletResponse response) throws IOException {
        List<Leave> list = leaveService.selectByUserid(userid);
        if (list != null) {
            response.setStatus(200);
            String leaveJson = JSON.toJSONString(list);
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(leaveJson);
            //return leaveJson;
        } else {
            response.setStatus(500);
//            return "no";
        }

    }

    @RequestMapping("/deleteLeave")
    public void deleteLeave(Integer leaveid, HttpServletResponse response) {
        int cnt = leaveService.deleteByPrimaryKey(leaveid);
        if (cnt != 0) {
            response.setStatus(200);
        } else {
            response.setStatus(500);
        }
    }
}
