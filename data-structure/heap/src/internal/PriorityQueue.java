package internal;

import java.util.ArrayList;
import java.util.Arrays;

//以大堆为例
public class PriorityQueue {
    int[] arr = new int[11];
    int size;

    //入队
    //尾插元素，再对数组重新调整
    public void offer(int elem) {
        arr[size++] = elem;
        shiftUp(arr, size-1);

//        for (int i = (size - 1) / 2; i >= 0; i--) {
//            shiftDown(arr, size, i);
//        }
        System.out.println(Arrays.toString(arr));
    }

    //出队
    public int poll() {
        //用最后一个元素覆盖第一个元素
        int oldValue = arr[0];
        arr[0] = arr[--size];
        //第一个元素即新元素向下调整
        shiftDown(arr, size, 0);
        return oldValue;
    }

    //取队首元素
    public int peek() {
        return arr[0];
    }

    //向下调整
    //size 表示数组中被视为堆的个数， index 表示调整位置的下标（从哪个位置调整）
    public static void shiftDown(int[] arr, int size, int index) {
        int parent = index;
        int child = parent * 2 + 1;
        while (child < size) {
            //找到左右孩子的最大值
            if (child + 1 < size && arr[child] < arr[child + 1]) {
                child = child + 1;
            }
            if (arr[parent] < arr[child]) {
                swap(arr, parent, child);
            } else {
                break;
            }
            parent = child;
            child = parent * 2 + 1;
        }
    }

    //向上调整
    public static void shiftUp(int[] arr, int index) {
        int child = index;
        int parent = (child - 1) / 2;
        while (child > 0) {
            if (arr[parent] < arr[child]) {
                swap(arr, parent, child);
            } else {
                break;
            }
            child = parent;
            parent = (child - 1) / 2;
        }
    }

    public static void swap(int[] arr, int x, int y) {
        int t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }

    public static void main(String[] args) {
//        int[] arr = {27,15,19,18,28,34,65,49,25,37};
//        createHeap(arr, arr.length);
//        System.out.println(Arrays.toString(arr));

        PriorityQueue priorityQueue = new PriorityQueue();
        //priorityQueue.arr = new int[]{27,15,19,18,28,34,65,49,25,37};
        priorityQueue.offer(27);
        priorityQueue.offer(15);
        priorityQueue.offer(9);
        priorityQueue.offer(18);
        priorityQueue.offer(28);
        priorityQueue.offer(34);
        priorityQueue.offer(65);
        priorityQueue.offer(49);
        priorityQueue.offer(95);
        priorityQueue.offer(88);

        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
    }

}
