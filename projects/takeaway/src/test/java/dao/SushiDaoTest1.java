package dao;

import dao.SushiDao;
import model.Sushi;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class SushiDaoTest1 {

    private SushiDao sushiDao = new SushiDao();

    @Test
    public void addSushi() {
        sushiDao.addSushi(new Sushi(null, "好吃寿司",new BigDecimal(88),88,88,null));
    }

    @Test
    public void deleteSushiById() {
        sushiDao.deleteSushiById(99);
    }

    @Test
    public void updateSushi() {
        sushiDao.updateSushi(new Sushi(15, "好吃寿司",new BigDecimal(99),99,99,null));
    }

    @Test
    public void querySushiById() {
        System.out.println(sushiDao.querySushiById(14));
    }

    @Test
    public void querySushi() {
        for (Sushi sushi: sushiDao.querySushis()) {
            System.out.println(sushi);
        }
    }
}