package blockingQueue;

/*
单生产者-单消费者情况
1. 不是线程安全，要通过加锁解决 - synchronized
2. 生产者在队列满时等待，消费者在队列空时等待 -wait
3. 生产者需要唤醒在等的消费者，消费者需要唤醒在等的生产者 -notify
 */

public class MyArrayBlockingQueue1 {
    int[] array = new int[10];
    int front = 0; //只有消费者会改变
    int rear = 0; //只有生产者会改变
    int size = 0; //共享且会被修改

    synchronized void put(int value) throws InterruptedException {
        while (size == array.length) {
            this.wait();
        }

        array[rear] = value;
        rear = (rear + 1) % array.length;
        size++;
        System.out.println("生产的苹果总数" + size);
        notifyAll();
    }


    synchronized int take() throws InterruptedException {
        while (size == 0) {
            this.wait();
        }

        int t = array[front];
        front = (front + 1) % array.length;
        size--;
        notifyAll();
        return t;
    }

    static MyArrayBlockingQueue1 queue = new MyArrayBlockingQueue1();
    static class Producer extends Thread {
        @Override
        public void run() {
            int j = 0;
            while (true) {
                try {
                    queue.put(j);
                    j++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer1 = new Producer();
        Producer producer2 = new Producer();
        Producer producer3 = new Producer();
        producer1.start();
        producer2.start();
        producer3.start();
        Thread.sleep(2*1000);
        while (true) {
            queue.take();
            System.out.println("取出1个苹果");
        }
    }
}
