package web;

import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 处理用户的登录、注册
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            //目的：避免出现大量的if-else
            //1.获取action鉴别字符串，获取响应的业务方法反射对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //2.调用目标业务方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理登录的需求");

        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2.判断用户名是否正确
        User loginUser = userService.login(new User(username, password, null));
        if (loginUser == null) {
            System.out.println("登录失败");
            // 把错误信息，和回显的表单项信息，保存到Request域中
            req.setAttribute("msg", "用户或密码错误！");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        } else {
            System.out.println("登录成功");
            //保存用户登录的信息
            req.getSession().setAttribute("user", loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理注册的需求");

        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //2.判断用户名是否可用
        if (userService.existsUsername(username)) {
            //用户名不可用，跳转到注册页面
            System.out.println("用户名已存在" + username);
            // 把回显信息，保存到Request域中
            req.setAttribute("msg", "用户名已存在！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
        } else {
            //用户名可用，跳转到注册成功页面
            userService.registerUser(new User(username, password, email));
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.销毁Session中用户登录的信息
        req.getSession().invalidate();
        // 2.重定向到首页（或登录页面）
        resp.sendRedirect(req.getContextPath());
    }
}
