package merge;

public class Merge {
    //合并排序
    public static void mergeSort(int[] nums) {
        mergeSortHelper(0, nums.length, nums); //没有-1
    }

    private static void mergeSortHelper(int low, int high, int[] nums) {
        if (low >= high) {
            return;
        }
        int mid = (low+high)/2;
        mergeSortHelper(low, mid, nums);//[low, mid)
        mergeSortHelper(mid, high, nums);//[mid, high)
        merge(low, mid, high, nums);
    }

    //合并两个有序数组
    private static void merge(int low, int mid, int high, int[] nums) {
        int i = low;
        int j = mid;
        int t = high - low;
        int[] extra = new int[t];
        int k = 0;
        while (i < mid && j < high) {
            if (nums[i] <= nums[j]) {
                extra[k++] = nums[i++];
            } else {
                extra[k++] = nums[j++];
            }
        }

        while (i < mid) {
            extra[k++] = nums[i++];
        }
        while (j < high) {
            extra[k++] = nums[j++];
        }

        for (int x = 0; x < t; x++) {
            nums[low+x] = extra[x];
        }
    }

    //非递归版本
    public static void mergeSort2(int[] nums) {
        for (int i = 1; i < nums.length; i *= 2) {
            for (int j = 0; j < nums.length; j += 2*i) {
                int low = j;
                int mid = j+i;
                if (mid >= nums.length) {
                    continue;
                }
                int high = mid + i;
                if (high > nums.length) {
                    high = nums.length;
                }
                merge(low, mid, high, nums);
            }
        }
    }

}
