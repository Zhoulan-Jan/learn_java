package web;

import model.Page;
import model.Sushi;
import model.User;
import service.SushiService;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//商家对寿司的增删改查
@WebServlet("/manager/sushi")
public class SushiServlet extends BaseServlet {
    SushiService sushiService = new SushiService();

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取Session域中的user对象
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        if ("admin".equals(user.getUsername())){
            //1.通过Sushi查询全部图书
            List<Sushi> sushis = sushiService.querySushis();
            //2.把全部图书保存到Request域中
            req.setAttribute("sushis", sushis);
            //3.请求转发到/pages/manager/sushi_manager.jsp中
            req.getRequestDispatcher("/pages/manager/sushi_manager.jsp").forward(req, resp);
        } else {
            resp.getWriter().println("非管理员不能访问");
        }
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
//        pageNo += 1;
        //1.获取的请求参数封装成对象
        Sushi sushi = WebUtils.copyParamToBean(req.getParameterMap(), new Sushi());
        //2.调用addSushi保存寿司
        sushiService.addSushi(sushi);
        //3.跳到图书列表页面
        //req.getRequestDispatcher("/manager/sushi?action=list").forward(req, resp);
        resp.sendRedirect(req.getContextPath()+"/manager/sushi?action=list");
       //resp.sendRedirect(req.getContextPath() + "/manager/sushi?action=page&pageNo=" + pageNo); //失败
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取的请求参数封装成对象
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.调用deleteSushi保存寿司
        sushiService.deleteSushiById(id);
        //3.跳到图书列表页面
        resp.sendRedirect(req.getContextPath()+"/manager/sushi?action=list");
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取的请求参数封装成对象
        Sushi sushi = WebUtils.copyParamToBean(req.getParameterMap(), new Sushi());
        //2.调用updateSushi修改寿司
        sushiService.updateSushi(sushi);
        //3.重定向到图书列表
        resp.sendRedirect(req.getContextPath()+"/manager/sushi?action=list");
    }

    protected void getSushi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数的寿司编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.调用querySushiById查询图书
        Sushi sushi = sushiService.querySushiById(id);
        //3.保存图书到Request域中
        req.setAttribute("sushi", sushi);
        //4.请求转发到/pages/manager/sushi_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/sushi_edit.jsp").forward(req, resp);
    }

    protected void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用SushiService.page(pageNo，pageSize)：Page对象
        Page<Sushi> page = sushiService.page(pageNo,pageSize);

        page.setUrl("manager/sushi?action=page");

        //3 保存Page对象到Request域中
        req.setAttribute("page",page);
        //4 请求转发到pages/manager/sushi_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/sushi_manager.jsp").forward(req,resp);
    }
}
