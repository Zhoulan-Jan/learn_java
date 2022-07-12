package pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/*
实现一个自定义拒绝策略，该策略根据传入的参数丢弃最老的n个线程
 */
public class DiscardOldestNPolicy implements RejectedExecutionHandler {
    private int discardNum = 5;
    private List<Runnable> discardList = new ArrayList<>();
    public DiscardOldestNPolicy (int discardNum) {
        this.discardNum = discardNum;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        if (e.getQueue().size() > discardNum) {
            //1.批量移除线程队列中n个线程任务
            e.getQueue().drainTo(discardList, discardNum);
            //2.清空列表
            discardList.clear();
            if (!e.isShutdown()) {
                //3.尝试提交当前任务
                e.execute(r);
            }
        }
    }
}
