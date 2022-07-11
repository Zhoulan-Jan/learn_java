package com.nina.controller;

import com.alibaba.fastjson.JSON;
import com.nina.entity.Clock;
import com.nina.service.ClockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ClockController {
   @Resource
   private ClockService clockService;

   @RequestMapping("/clockList")
    public String clockList(Model model) {
       List<Clock> list = clockService.select();
       model.addAttribute("list", list);
       return "manager/clock_list";
   }

   @RequestMapping("/selectClockByUsername")
   public String selectClockByUsername(String username, Model model) {
      //List<Clock> list = new ArrayList<>();
      List<Clock> list = clockService.selectByUsername(username);
      //list.add(clock);
      model.addAttribute("list", list);
      return "manager/clock_list";
   }

   //新增记录，插入上班时间和当天日期（需要员工id和员工name）
   @RequestMapping("/startClock")
   public void addStartClock(Clock clock, HttpServletResponse response) throws IOException, ParseException {
      //得到当前日期
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      String time = df.format(new Date());
      clock.setTime(time);

      //上班打卡时间
      SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date clockStartTime = df2.parse(df2.format(new Date()));
      clock.setStartTime(df2.format(new Date()));
      //上班规定时间
      Date workStartTime = df2.parse(time + " 9:00:00");
      //判断是否迟到
      if(workStartTime.before(clockStartTime)) {
         //迟到
         clock.setStartStatus("迟到");
      } else {
         clock.setStartStatus("正常");
      }

      int cnt = clockService.insertSelective(clock);
      if (cnt > 0) {
         response.getWriter().println("ok");
      } else {
         response.setStatus(500);
         response.getWriter().println("no");
      }
   }

   @RequestMapping("/endClock")
   public void updateEndClock(Clock clock, HttpServletResponse response) throws ParseException, IOException {
      //得到当前日期
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      String time = df.format(new Date());
      clock.setTime(time);

      //下班打卡时间
      SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date clockEndTime = df2.parse(df2.format(new Date()));
      clock.setEndTime(df2.format(new Date()));
      //下班规定时间
      Date workEndTime = df2.parse(df.format(new Date()) + " 17:00:00");
      //判断是否早退
      if (workEndTime.after(clockEndTime)) {
         //早退
         clock.setEndStatus("早退");
      } else {
         clock.setEndStatus("正常");
      }
      int cnt = clockService.updateByPrimaryKeySelective(clock);
      if (cnt > 0) {
         response.getWriter().println("ok");
      } else {
         response.setStatus(500);
         response.getWriter().println("no");
      }
   }

   @RequestMapping("/selectTimes")
   public void selectTime(Integer userid, HttpServletResponse response) throws IOException {
      List<Clock> list = clockService.selectByUserid(userid);
      if (list != null) {
         response.setStatus(200);
         String clockJson = JSON.toJSONString(list);
         response.setCharacterEncoding("utf-8");
         response.getWriter().println(clockJson);
      } else {
         response.setStatus(500);
      }
   }

   @RequestMapping("/selectByUseridAndTime")
   public void selectByUseridAndTime(Integer userid, HttpServletResponse response) throws IOException {
      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      String time = df.format(new Date());

      Clock clock = clockService.selectByUseridAndTime(userid, time);
      //若查询结果有多条，说明数据库记录错误。
      if (clock == null) { //说明未上下班打卡
         response.setStatus(201);
      } else { //已打卡，获取上下班打卡情况
         response.setStatus(200);
         String clockJson = JSON.toJSONString(clock);
         response.setCharacterEncoding("utf-8");
         response.getWriter().println(clockJson);
      }
   }
}
