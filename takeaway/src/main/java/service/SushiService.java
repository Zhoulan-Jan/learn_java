package service;

import dao.SushiDao;
import model.Page;
import model.Sushi;

import java.util.List;

public class SushiService {
    SushiDao sushiDao = new SushiDao();

    public void addSushi(Sushi sushi) {
        sushiDao.addSushi(sushi);
    }

    public void deleteSushiById(Integer id) {
        sushiDao.deleteSushiById(id);
    }

    public void updateSushi(Sushi sushi) {
        sushiDao.updateSushi(sushi);
    }

    public Sushi querySushiById(Integer id) {
        return sushiDao.querySushiById(id);
    }

    public List<Sushi> querySushis() {
        return sushiDao.querySushis();
    }

    public Page<Sushi> page(int pageNo, int pageSize) {
        Page<Sushi> page = new Page<>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = sushiDao.queryForPageTotalCount();
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Sushi> items = sushiDao.queryForPageItems(begin,pageSize);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    public Page<Sushi> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Sushi> page = new Page<>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = sushiDao.queryForPageTotalCountByPrice(min,max);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Sushi> items = sushiDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }
}
