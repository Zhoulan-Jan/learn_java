package exchange;

import java.util.Stack;

//快速排序
public class Quick {
    public static void quickSort(int[] nums) {
        quickSortHelper(0, nums.length-1, nums);
    }

    private static void quickSortHelper(int left, int right, int[] nums) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition3(left, right, nums);
        quickSortHelper(left, pivotIndex-1, nums);
        quickSortHelper(pivotIndex+1, right, nums);
    }

    //非递归版本
    private static void quickSort2(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        stack.push(nums.length - 1);
        stack.push(0);

        while (!stack.isEmpty()) {
            int left = stack.pop();
            int right = stack.pop();
            if (left >= right) {
                continue;
            }

            int pivotIndex = partition(left, right, nums);
            stack.push(right);
            stack.push(pivotIndex+1);

            stack.push(pivotIndex-1);
            stack.push(left);
        }
    }

    //一次快排 hoare
    private static int partition(int left, int right, int[] nums) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] > pivot) {
                right--;
            }
            while (left < right && nums[left] < pivot) {
                left++;
            }
            swap(left, right, nums);
        }
        nums[left] = pivot;
        return left;
    }

    //挖坑法 不进行交换，而是赋值
    private static int partition2(int left, int right, int[] nums) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] > pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] < pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

    //前后遍历法 能解决数字相同的情况 其余的不行
    //定义基准值为nums[right]，定义两个指针，初始时，front指向起始位置，rear/j指向起始位置
    //rear向后走，找到比基准值小的数，交换rear与front，front++，直至rear/j走到right结束
    //最后交换front与right
    private static int partition3(int left, int right, int[] nums) {
        int front = left;
        int pivot = nums[right];
        for (int j = left; j < right; j++) { //j找小
            if (nums[j] < pivot) {
                swap(j, front++, nums);
            }
        }
        swap(front, right, nums);
        return front;
    }

    public static void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
