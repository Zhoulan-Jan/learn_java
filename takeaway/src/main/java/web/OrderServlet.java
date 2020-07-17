package web;

import model.Cart;
import model.Order;
import model.OrderItem;
import model.User;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends BaseServlet {
    OrderService orderService = new OrderService();

    public void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = loginUser.getId();

        String orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    public void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取Session域中的user对象
        User user = (User) req.getSession().getAttribute("user");
        //System.out.println("admin".equals(user.getUsername()));

        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        //判断user如果不是管理员。
        if ("admin".equals(user.getUsername())) {
            //调用Service来获取所有的订单项,管理员查看所有订单
            List<Order> orders = orderService.showAllOrders();
            System.out.println(orders);
            //保存到request域中
            req.setAttribute("orders", orders);
            //转发到order_manager.jsp显示数据
            //resp.sendRedirect(req.getContextPath()+"/pages/manager/order_manager.jsp");
            req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
        } else {
            resp.getWriter().println("非管理员不能访问");
        }
    }

    public void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前台传来的
        String orderId = req.getParameter("orderId");

        //调用Service来修改指定ID的订单项
        orderService.sendOrder(orderId);

        //请求重定向到原来的页面。
        resp.sendRedirect(req.getContextPath() + "/order?action=showAllOrders");
    }

    public void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前台传输的订单编号和域中的登录对象
        String orderId = req.getParameter("orderId");
        User user = (User) req.getSession().getAttribute("user");

        //如果是管理员，则跳到管理员查看详情页面。
        if("admin".equals(user.getUsername())){
            List<OrderItem> orderAdminDetails = orderService.showOrderDetail(orderId);
            req.setAttribute("orderAdminDetails",orderAdminDetails);
            req.getRequestDispatcher("/pages/manager/orderItem_manager.jsp").forward(req, resp);
        } else {
            //否则就是用户查看详情页面
            List<OrderItem> orderUserDetails = orderService.showOrderDetail(orderId);
            req.setAttribute("orderUserDetails", orderUserDetails);
            req.getRequestDispatcher("/pages/order/orderItem_user.jsp").forward(req, resp);
        }
    }

    public void showUserOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session域中的user对象
        User user = (User) req.getSession().getAttribute("user");
        //其他用户查看自己的订单。
        List<Order> user_orders = orderService.showUserOrders(user.getId());
        //保存到request域中
        req.setAttribute("user_orders", user_orders);
        //转发到order_manager.jsp显示数据
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    public void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前台传来的
        String orderId = req.getParameter("orderId");

        //调用Service将指定的订单ID修改状态为已签收。
        orderService.receiverOrder(orderId);

        //请求重定向到原来的页面。
        resp.sendRedirect(req.getContextPath() + "/order?action=showUserOrders");
    }

}
