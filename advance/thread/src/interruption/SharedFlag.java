package interruption;

import java.util.Scanner;

public class SharedFlag {
    public static boolean condition = true;
    private static class MyRunnable implements Runnable{

        @Override
        public void run() {
            while (condition) {
                System.out.println("写作业1");
                System.out.println("写作业2");
                //Thread.sleep(10*1000); //当增加了sleep，不会立即响应停止通知的消息
                System.out.println("写作业3");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        MyRunnable target = new MyRunnable();
        Thread t = new Thread(target);
        t.start();

        //Thread.sleep(10*1000);
        sc.nextInt();
        condition = false;
        System.out.println("通知线程停止");
        t.join();
        System.out.println("线程已停止");
    }

    public static class SyncExercise {
        static Object o1 = new Object();

        static  void method1() {
            Object o2 = o1;

            synchronized (o2) {

            }
        }

        static void method2() {
            Object o3 = o1;

            synchronized (o3) {

            }
        }

        //线程A执行method1 ，线程B执行method2，抢的是同一把锁

        //method1去掉sync，线程A执行method1 ，线程B执行method2，不会形成互斥

        synchronized static void method3() {

        }

        static void method4() {
            synchronized (SyncExercise.class) {

            }
        }

        void method5() {
            synchronized (this) {

            }
        }

        //线程A执行method3，method4，线程B执行method4，抢的同一把锁
        //线程A执行method3，线程B执行method4，互斥
        //method4去掉static，问题同上，互斥
        //线程A执行method3，线程B执行method5，不是同一把锁，不形成互斥
        //线程A执行method3，method5，线程B执行method5，互斥
    }
}
