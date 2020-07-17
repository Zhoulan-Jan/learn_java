package dao;

import dao.DBUtil;
import org.junit.Test;

public class DBUtilTest {
    @Test
    public void testDBUtil() {
        System.out.println(DBUtil.getConnection());
    }

}
