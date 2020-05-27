package interruption;

import java.util.Scanner;

public class NativeMethod {
    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            Thread current = Thread.currentThread();
            //鼓励第三方使用

            while (!current.isInterrupted() ) { //如果没睡，通过这里知道
                System.out.println("do homework1");
                //Thread.sleep(3*1000);
                System.out.println("do homework2");
                //Thread.sleep(3*1000);
                System.out.println("do homework3");
                //Thread.sleep(3*1000);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new MyRunnable());
        t.start();

        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        System.out.println("准备通知");
        t.interrupt(); //调用结束后，线程内部收到异常，但isInterrupted仍返回false
        System.out.println("已通知");
//        t.join();
//        System.out.println("线程已停止");

    }
}
