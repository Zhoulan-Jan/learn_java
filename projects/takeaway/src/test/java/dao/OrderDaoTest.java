package dao;

import model.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {
    OrderDao orderDao = new OrderDao();

    @Test
    public void saveOrder() {


        orderDao.saveOrder(new Order("123458", new Date(), new BigDecimal(100), 0, 1));
    }

    @Test
    public void queryOrders() {
        System.out.println(orderDao.queryOrders());
    }

    @Test
    public void queryOrdersByUserId() {
        System.out.println(orderDao.queryOrdersByUserId(2));
    }
}