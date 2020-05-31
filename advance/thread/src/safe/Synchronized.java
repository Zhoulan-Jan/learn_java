package safe;

public class Synchronized {
    synchronized void 普通方法() {
        //同步代码块
    }

    void 普通方法2() {
        synchronized (this){
            //同步代码块
        }
    }

    synchronized static void 静态方法() {
        //同步代码块
    }

    void 静态方法2() {
        synchronized (Synchronized.class){
            //同步代码块
        }
    }

    Object o = new Object();
    void 其他方法() {
        synchronized (o) {

        }
    }
}
