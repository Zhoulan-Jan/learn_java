package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class SushiDaoTest {
    SushiDao sushiDao = new SushiDao();

    @Test
    public void queryForPageTotalCount() {
        System.out.println(sushiDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        System.out.println(sushiDao.queryForPageItems(0,5));
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(sushiDao.queryForPageTotalCountByPrice(70,85));
    }

    @Test
    public void queryForPageItemsByPrice() {
        System.out.println(sushiDao.queryForPageItemsByPrice(0,5,70,85));
    }
}