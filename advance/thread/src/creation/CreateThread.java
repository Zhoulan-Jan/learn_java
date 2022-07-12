package creation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CreateThread {
    //法一：继承Thread类
     private static class MyThread extends Thread {
         @Override
         public void run() {
             for (int i = 0; i < 10; i++) {
                 System.out.println(i);
             }
         }
     }

     //法二：实现Runnable接口
     private static class MyRunnable implements Runnable {
         @Override
         public void run() {
             for (int i = 0; i < 10; i++) {
                 System.out.println(i);
             }
         }
     }

     //法三：实现Callable接口
    private static class MyCallable implements Callable<String> {
        private String name;
        public MyCallable(String name) {
            this.name = name;
        }

         @Override
         public String call() throws Exception {
             return name;
         }
     }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Thread a = new MyThread();
        a.start();
        a.join(); //1.主线程放弃CPU 2.主线程阻塞在这里，直到a线程执行结束，
        System.out.println("a 一定结束");

        Thread b = new Thread(new MyRunnable());
        b.start();
        b.join();
        System.out.println("b 一定结束");

        Thread c = new Thread(new MyThread());
        c.start();
        c.join();
        System.out.println("c 一定结束");

        //创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        //创建用于接收结果的list
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < 5; i++) {
            //创建有返回值的线程实例
            Callable ca = new MyCallable(i+ " ");
            //提交线程，并将结果保存到Future中，将Future保存到list中
            Future future = pool.submit(ca);
            System.out.println("submit " +i);
            list.add(future);
        }
        //关闭线程池，等待线程执行结束
        pool.shutdown();
        //遍历所有线程的运行结果
        for (Future f:list) {
            System.out.println("get result " + f.get().toString());
        }

        //法四：基于线程池
        ExecutorService pool1 = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            pool1.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " is running");
                }
            });
        }
        pool1.shutdown();
    }

    public static void UseAnonymous() {
         //使用匿名类创建子类对象
        // == 直接创建线程对象
        Thread a = new Thread() {
            @Override
            public void run() {
                //线程要执行的内容
            }
        };

        //使用匿名类创建 Runnable 子类对象
        // == 先创建目标对象，再创建线程对象
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                //线程要执行的内容
            }
        });

        //使用lambda表达式创建 Runnable 子类对象
        Thread c = new Thread(() -> {
            //线程要执行的内容
        });
    }
}
