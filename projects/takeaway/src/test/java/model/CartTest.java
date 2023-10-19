package model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "三文鱼寿司", 1, new BigDecimal(10), new BigDecimal(10)));
        cart.addItem(new CartItem(1, "三文鱼寿司", 1, new BigDecimal(10), new BigDecimal(10)));
        cart.addItem(new CartItem(2, "绿茶", 1, new BigDecimal(50), new BigDecimal(50)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "三文鱼寿司", 1, new BigDecimal(10), new BigDecimal(10)));
        cart.addItem(new CartItem(1, "三文鱼寿司", 1, new BigDecimal(10), new BigDecimal(10)));
        cart.addItem(new CartItem(2, "绿茶", 1, new BigDecimal(50), new BigDecimal(50)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "三文鱼寿司", 1, new BigDecimal(10), new BigDecimal(10)));
        cart.addItem(new CartItem(1, "三文鱼寿司", 1, new BigDecimal(10), new BigDecimal(10)));
        cart.addItem(new CartItem(2, "绿茶", 1, new BigDecimal(50), new BigDecimal(50)));
        System.out.println(cart);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "三文鱼寿司", 1, new BigDecimal(10), new BigDecimal(10)));
        cart.addItem(new CartItem(1, "三文鱼寿司", 1, new BigDecimal(10), new BigDecimal(10)));
        cart.addItem(new CartItem(2, "绿茶", 1, new BigDecimal(50), new BigDecimal(50)));
        cart.updateCount(2, 10);
        System.out.println(cart);
    }
}