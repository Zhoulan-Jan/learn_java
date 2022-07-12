package pool;
/*
线程池的创建
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Create {
    public static void main(String[] args) {
        //缓存线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //固定大小的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        //单线程的线程池
        ExecutorService singleThread = Executors.newSingleThreadExecutor();

        //大小无限，支持定时或周期性任务
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3s execu.");
            }
        }, 3, TimeUnit.SECONDS);

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1s, repeat execute every 3s");
            }
        }, 1,3, TimeUnit.SECONDS);
    }

}
