package insert;

//希尔排序
public class Shell {
    public static void shellSort(int[] nums) {
        for (int gap = nums.length; gap > 1; gap /= 2) {
            shellSortHelper(nums, gap);
        }
        shellSortHelper(nums, 1);
    }

    private static void shellSortHelper(int[] nums, int gap) {
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            int j = i - gap;
            for (; j >= 0 && v < nums[j]; j -= gap) {
                nums[j+gap] = nums[j];
            }
            nums[j+gap] = v;
        }
    }

}
