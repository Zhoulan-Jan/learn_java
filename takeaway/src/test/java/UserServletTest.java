import model.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserServletTest {
    public void login() {
        System.out.println("这是login()方法调用了");
    }

    public void register() {
        System.out.println("这是register()方法调用了");
    }

    public void updateUser() {
        System.out.println("这是updateUser()方法调用了");
    }

    public void updateUserPassword() {
        System.out.println("这是updateUserPassword()方法调用了");
    }


    public static void main(String[] args) {
        String action = "updateUser";

        try {
            // 获取action业务鉴别字符串，获取相应的业务 方法反射对象
            Method method = UserServletTest.class.getDeclaredMethod(action);

            System.out.println(method);
            // 调用目标业务 方法
            method.invoke(new UserServletTest() );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //把所有请求的参数注入到user对象中，方便获取请求参数的书写
//    try {
//        User user = new User();
//        System.out.println("注入之前" + user);
//        BeanUtils.populate(user, req.getParameterMap());
//        System.out.println("注入之后" + user);
//    } catch (IllegalAccessException e) {
//        e.printStackTrace();
//    } catch (
//    InvocationTargetException e) {
//        e.printStackTrace();
//    }
}
