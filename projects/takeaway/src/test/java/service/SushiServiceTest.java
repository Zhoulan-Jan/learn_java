package service;

import model.Sushi;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class SushiServiceTest {
    SushiService sushiService = new SushiService();

    @Test
    public void addSushi() {
        sushiService.addSushi(new Sushi(null, "好吃寿司",new BigDecimal(88),88,88,null));
    }

    @Test
    public void deleteSushiById() {
        sushiService.deleteSushiById(13);
    }

    @Test
    public void updateSushi() {
        sushiService.updateSushi(new Sushi(14, "好吃寿司",new BigDecimal(7),7,7,null));
    }

    @Test
    public void querySushiById() {
        System.out.println(sushiService.querySushiById(14));
    }

    @Test
    public void querySushis() {
        for (Sushi sushi: sushiService.querySushis()) {
            System.out.println(sushi);
        }
    }

    @Test
    public void page() {
        System.out.println(sushiService.page(0, 5));
    }

    @Test
    public void pageByPrice() {
        System.out.println(sushiService.pageByPrice(0, 5, 70, 85));
    }
}