package service;

import dao.OrderDao;
import dao.OrderItemDao;
import dao.SushiDao;
import model.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderService {
    private OrderDao orderDao = new OrderDao();
    private OrderItemDao orderItemDao = new OrderItemDao();
    private SushiDao sushiDao = new SushiDao();

    /**
     * 生成订单
     * @param cart
     * @param userId
     * @return
     */
    public String createOrder(Cart cart, Integer userId) {
        //订单唯一性
        String orderId = System.currentTimeMillis()+""+userId;
        //生成一个订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //保存订单
        orderDao.saveOrder(order);
        //遍历购物车中的每一个商品转化为订单项保存到数据库
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();

            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(),
                    cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);

            Sushi sushi = sushiDao.querySushiById(cartItem.getId());
            sushi.setSales(sushi.getSales()+cartItem.getCount());
            sushi.setStock(sushi.getStock()-cartItem.getCount());
            sushiDao.updateSushi(sushi);
        }

        cart.clear();
        return orderId;
    }

    /**
     * 查询全部订单
     * @return
     */
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    /**
     * 发货
     * @param orderId
     * @return
     */
    public int sendOrder(String orderId) {
        return orderDao.changeOrderStatus(orderId, 1);
    }

    /**
     * 查看订单详情
     * @param orderId
     * @return
     */
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }

    /**
     * 查看我的订单
     * @param userId
     * @return
     */
    public List<Order> showUserOrders(int userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    /**
     * 确认收货
     * @param orderId
     * @return
     */
    public int receiverOrder(String orderId) {
        return orderDao.changeOrderStatus(orderId, 2);
    }
}
