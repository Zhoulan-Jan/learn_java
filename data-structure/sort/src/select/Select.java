package select;

public class Select {
    public static void selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            swap(i, minIndex, nums);
        }

    }

    //双向选择排序
    public static void opSelectSort(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        //[low, high]表示一个无序区间
        //在这个区间里找到最大值和最小值
        while (low <= high) {
            int min = low;
            int max = low;
            for (int i = low + 1; i <= high; i++) {
                if (nums[min] > nums[i]) {
                    min = i;
                }
                if (nums[max] < nums[i]) {
                    max = i;
                }
            }

            swap(min, low, nums); //将最小值移到最前面
            //如果最大值的位置刚好是最前面的位置，那么上一步的交换之后，最大值的位置就在最小值上
            if (max == low) {
                max = min;
            }

            swap(max, high, nums); //将最大值移到最后面
            low++;
            high--;
        }
    }

    public static void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
