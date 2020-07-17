package web;

import model.Cart;
import model.CartItem;
import model.Sushi;
import service.SushiService;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends BaseServlet {
    SushiService sushiService = new SushiService();
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("加入购物车");
//        System.out.println("商品编号 " + req.getParameter("id"));

        //1.获取请求参数中的商品编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2.调用sushiService.querySushiById(id)得到商品信息
        Sushi sushi = sushiService.querySushiById(id);
        //3.把商品信息转化为CartItem商品项
        CartItem cartItem = new CartItem(sushi.getId(), sushi.getName(), 1,
                sushi.getPrice(), sushi.getPrice());
        //4.调用Cart.addItem(CartItem)添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        //5.重定向到商品列表页req.getContextPath()
        //改为重定向到原来商品所在的页面
//        System.out.println(req.getHeader("Referer"));
        resp.sendRedirect(req.getHeader("Referer"));
        //最后一个商品回显
        req.getSession().setAttribute("lastName", cartItem.getName());
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            //3.删除商品
            cart.deleteItem(id);
            //4.重定向到订单列表页
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //2.清空购物车
        cart.clear();
        //3.重定向到订单列表页
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 0);
        //2.获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null) {
            cart.updateCount(id , count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
