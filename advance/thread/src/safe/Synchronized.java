package safe;
/*
synchronized的作用范围和用法
 */
public class Synchronized {
    //1.作用于成员变量和非静态方法，锁住的是对象的实例
    synchronized void 普通方法() {
        //同步代码块
    }

    void 普通方法2() {
        synchronized (this){
            //同步代码块
        }
    }

    //2.作用于静态方法时，锁住的是Class实例
    synchronized static void 静态方法() {
        //同步代码块
    }

    void 静态方法2() {
        synchronized (Synchronized.class){
            //同步代码块
        }
    }

    //3.作用于代码块时，锁住的是代码块配置的对象
    Object o = new Object();
    void 其他方法() {
        synchronized (o) {

        }
    }
}
