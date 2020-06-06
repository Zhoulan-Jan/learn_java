package blockingQueue;

/**
 * 循环阻塞队列
 * 多生产者多消费者情况
 */

public class MyArrayBlockingQueue2 {
    int[] array = new int[10];
    int front = 0; //只有消费者会改变
    int rear = 0; //只有生产者会改变
    int size = 0; //共享且会被修改

    synchronized void put(int value) throws InterruptedException {
        while (size == array.length) {
            wait();
        }

        array[rear] = value;
        rear = (rear + 1) % array.length;
        size++;
        System.out.println(size);
        notifyAll();
    }

    synchronized void put2(int value) throws InterruptedException {
        if (size == array.length) {
            //throw new RuntimeException("full");
            this.wait(); //MN：当他被唤醒时，以为是消费者唤醒的，以为队列不满了，就继续执行了
            //而实际上，可能是被另一个生产者唤醒的，那么队不满是不成立的
            //修改方式：if->while，当醒了之后，再判断队列是否满
        }
        //通过while时，一定是满足队列满的要求
        //take 同理

        array[rear] = value;
        rear = (rear + 1) % array.length;
        size++;
        System.out.println(size);
        notify();
    }

    synchronized int take() throws InterruptedException {
        if (size == 0) {
            //throw  new RuntimeException("empty");
            this.wait();
        }

        int t = array[front];
        front = (front + 1) % array.length;
        size--;
        notify();
        return t;
    }

    static MyArrayBlockingQueue2 queue = new MyArrayBlockingQueue2();
    static class Producer extends Thread {
        @Override
        public void run() {
//            for (int i = 0; i < 100; i++) {
//                try {
//                    System.out.println("准备放入" + i);
//                    queue.put(i);
//                    System.out.println("放入成功" + i);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
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
//        Producer producer = new Producer();
//        producer.start();
//
//        Thread.sleep(2*1000);
//
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            sc.nextLine();
//            System.out.println(" 取出" + queue.take());
//        }

        //多个生产者和多个消费者情况下会有错误
        //演示错误情况
        MyArrayBlockingQueue1.Producer producer1 = new MyArrayBlockingQueue1.Producer();
        MyArrayBlockingQueue1.Producer producer2 = new MyArrayBlockingQueue1.Producer();
        MyArrayBlockingQueue1.Producer producer3 = new MyArrayBlockingQueue1.Producer();
        producer1.start();
        producer2.start();
        producer3.start();
        Thread.sleep(2*1000);
        while (true) {
            queue.take();
        }
    }
}
