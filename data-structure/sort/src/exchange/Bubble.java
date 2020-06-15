package exchange;

//冒泡排序
public class Bubble {
    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) { //要比较多少趟
            boolean isSorted = true;
            for (int j = 0; j < nums.length-i-1; j++) { //在一趟中，相邻比较
                if (nums[j] > nums[j+1]) {
                    swap(j, j+1, nums);
                    isSorted = false;
                }
            }
            //如果这一趟中没有比较则说明数组已经有序
            if (isSorted) {
                break;
            }
        }
    }

    public static void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
