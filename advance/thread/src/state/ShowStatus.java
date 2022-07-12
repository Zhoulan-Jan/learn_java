package state;

import java.lang.ref.PhantomReference;

public class ShowStatus {
    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {

            }
        }
    }

    private static class MyThread2 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(100*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Cat extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("小猫");
                Thread.yield(); //加了yield，小猫较于小狗数量少
            }
        }
    }

    private static class Dog extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("小狗");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        MyThread1 t = new MyThread1();
//        System.out.println(t.getState()); //NEW
//        t.start();
//        System.out.println(t.getState()); //RUNNABLE
//        while (t.isAlive()) { //不是TERMINATED 和 NEW 都返回true
//        }
//        System.out.println(t.getState()); //TERMINATED


//        MyThread2 t2 = new MyThread2();
//        t2.start();
//        System.out.println(t2.getState()); //RUNNABLE
//        Thread.sleep(1000); //主线程主动放弃CPU，使得t2大概率能抢到CPU
//        System.out.println(t2.getState()); //TIMED_WAITING

        Thread cat = new Cat();
        Thread dog = new Dog();
        cat.start();
        dog.start();
    }



}
