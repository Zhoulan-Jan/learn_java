package internal;

import java.util.Arrays;

public class Heap {
    //建堆
    public static void createHeap(int[] arr, int size) {
//        for (int i = (size - 1) / 2; i >= 0; i--) {
//            shiftDown(arr, size, i);
//        }

        for (int i = 0; i < size ; i++) {
            shiftUp(arr, i);
        }
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
        int[] arr = {28,27,19,18,15,34,65,49,25,37};
        createHeap(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
