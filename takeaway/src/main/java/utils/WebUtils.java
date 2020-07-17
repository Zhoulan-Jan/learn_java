package utils;

import model.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

//把所有请求的参数注入到user对象中，方便获取请求参数的书写
//String->int
public class WebUtils {
    /**
     * 把Map中的值注入到对应的JavaBean中
     * 优化版，方便dao，service，web层，使用泛型，无须强转
     * @param value
     * @param bean
     */
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            System.out.println("注入之前" + bean);
            BeanUtils.populate(bean, value);
            System.out.println("注入之后" + bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     *
     * @param req
     * @param user
     */
    public static void copyParamToBean2(HttpServletRequest req, User user) {
        try {
            //System.out.println("注入之前" + user);
            BeanUtils.populate(user, req.getParameterMap());
            //System.out.println("注入之后" + user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将String转为int
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue) {
        try{
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return defaultValue;
    }

}
