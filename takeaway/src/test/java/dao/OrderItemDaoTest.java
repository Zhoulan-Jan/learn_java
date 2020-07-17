package dao;

import model.Order;
import model.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {
    OrderItemDao orderItemDao = new OrderItemDao();

    @Test
    public void saveOrderItem() {

        orderItemDao.saveOrderItem(new OrderItem(null, "金枪鱼寿司",
                1, new BigDecimal(100), new BigDecimal(100), "45677"));

        orderItemDao.saveOrderItem(new OrderItem(null, "绿茶",
                1, new BigDecimal(100), new BigDecimal(100), "45677"));

        orderItemDao.saveOrderItem(new OrderItem(null, "天妇罗",
                1, new BigDecimal(100), new BigDecimal(100), "123458"));
    }

    @Test
    public void queryOrderItemsByOrderId() {
        System.out.println(orderItemDao.queryOrderItemsByOrderId("15946122600891"));
    }
}