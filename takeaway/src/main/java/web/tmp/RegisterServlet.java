package web.tmp;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        //2.判断用户名是否可用
        if (userService.existsUsername(username)) {
            //用户名不可用，跳转到注册页面
            System.out.println("用户名已存在" + username);
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
        } else {
            //用户名可用，跳转到注册成功页面
            userService.registerUser(new User(username, password, email));
            req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req, resp);
        }

    }
}
