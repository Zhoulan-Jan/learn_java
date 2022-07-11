package com.nina.controller;

import com.alibaba.fastjson.JSON;
import com.nina.entity.User;
import com.nina.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tuser")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/userList")
    public String userList(Model model) {
        List<User> list = userService.getUsers();
        model.addAttribute("key", list);
        return "admin/user_list";
    }

    @RequestMapping("/select_by_username")
    public String selectByUsername(String username, Model model) {
        List<User> list = new ArrayList<>();
        User user = userService.selectByUsername(username);
        list.add(user);
        model.addAttribute("key", list);
        return "admin/user_list";
    }

    @RequestMapping("/add")
    public String addUsers(User user) {
        int res = userService.insert(user);
        System.out.println("插入受影响的行数 " + res);
        return "redirect:userList";
    }

    @RequestMapping("/delete")
    public String deleteUser(Integer id) {
        int res = userService.deleteByPrimaryKey(id);
        return "redirect:userList";
    }

    @RequestMapping("/userInfo")
    public String userInfo(Integer id, Model model) {
        User user = userService.selectByPrimaryKey(id);
        model.addAttribute("user", user);
        return "admin/user_update";
    }

    @RequestMapping("/update")
    public String updateUser(User user) {
        int res = userService.updateByPrimaryKey(user);
        return "redirect:userList";
    }

    public static boolean isNum(String str){
        Pattern pattern = Pattern.compile("^-?[0-9]+");
        if(pattern.matcher(str).matches()){
            //数字
            return true;
        } else {
            //非数字
            return false;
        }
    }

    @RequestMapping("/loginWeb")
    public String loginWeb(String useridStr, String password, HttpServletRequest req) {
//        Pattern pattern = Pattern.compile("^-?[0-9]+");
        System.out.println("usrStr" + useridStr);
        if (isNum(useridStr)) {
            System.out.println("全数字");
            Integer userid = Integer.parseInt(useridStr);
            int cnt = userService.selectByUseridAndPassword(userid, password);
            if (cnt > 0) {
                System.out.println(req.getParameter("userid"));
                if (userid == 202101) {
                    //管理员
                    return "redirect: userList";
                } else if (userid == 202102) {
                    //人事经理
                    return "redirect: ../manager/clockList";
                }
            } else {
                req.setAttribute("msg", "工号或密码错误！");
                return "index";
            }
        } else {
            System.out.println("非全数字");
            req.setAttribute("msg", "工号不合法");
            return "index";
        }
//        Integer userid = Integer.parseInt(useridStr);
//        int cnt = userService.selectByUseridAndPassword(userid, password);
//        if (cnt > 0) {
//            System.out.println(req.getParameter("userid"));
//            if (userid == 202101) {
//               //管理员
//                return "redirect: userList";
//            } else if (userid == 202102) {
//                //人事经理
//                return "redirect: ../manager/clockList";
//            }
//        }
        return "index";
    }


    @RequestMapping("/login")
    public void login(Integer userid, String username, String password, HttpServletResponse response) throws IOException {
        int cnt = userService.selectByUseridAndUsernameAndPassword(userid, username, password);
        if (cnt > 0) {
            response.setStatus(200);
        } else {
            response.setStatus(500);
        }
    }

    @RequestMapping("/getUserByUsername")
    public void getUserByUsername(String username, HttpServletResponse response) throws IOException {
        User user = userService.selectByUsername(username);
        String userJson = JSON.toJSONString(user);
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(userJson);
        //return userJson;
    }

    @RequestMapping("/updateTeleOrEmail")
    public void updateTeleOrEmail(User user, HttpServletResponse response) {
        int cnt = userService.updateByPrimaryKeySelective(user);
        if (cnt > 0) {
            response.setStatus(200);
        } else {
            response.setStatus(500);
        }
    }
}
