package singleton;

import java.io.ObjectInputStream;
//延迟加载
public class LazyMan {
    private static volatile LazyMan instance = null;

    private  LazyMan() {

    }

    //单线程版，多线程环境下可能会出现问题
    public static LazyMan getInstance() {
        if (instance == null) {
            instance = new LazyMan();
        }
        return instance;
    }

    //法一二三四都是多线程版

    //法一：
    public static LazyMan getInstance1() {
            synchronized (LazyMan.class) {
                if (instance == null) {
                instance = new LazyMan();
            }
        }
        return instance;
    }

//    //法二：
//    public synchronized static LazyMan getInstance2() {
//        if (instance == null) {
//            instance = new LazyMan();
//        }
//        return instance;
//    }
//
//    //法三：
//    private static Object lock = new Object(); //在方法外面
//    public static LazyMan getInstance3() {
//        synchronized (lock) {
//            if (instance == null) {
//                instance = new LazyMan();
//            }
//        }
//        return instance;
//    }

    //二次判断-性能高
    public static LazyMan getInstance4() {
        if (instance == null) {
            synchronized (LazyMan.class) {
                if (instance == null) {
                    instance = new LazyMan(); //有重排序问题，加入volatile
                }
            }
        }
        return instance;
    }



    static class MyThread extends Thread {
        @Override
        public void run() {
            LazyMan ins = LazyMan.getInstance4();
            System.out.println(ins);
        }
    }

    public static void main(String[] args) {
//        //单线程
//        LazyMan ins1 = LazyMan.getInstance();
//        LazyMan ins2 = LazyMan.getInstance();
//        LazyMan ins3 = LazyMan.getInstance();
//        System.out.println(ins1 == ins2); //true
//        System.out.println(ins2 == ins3); //true

        //多线程环境下
        MyThread[] threads = new MyThread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new MyThread();
        }

        for (int i = 0; i < 20; i++) {
            threads[i].start();
        }
    }
}
