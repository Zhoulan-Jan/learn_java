package insert;

//插入排序
public class Insert {
    public static void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int v = nums[i];
            int j = i-1;
            //在 [0,i)找到应该插入的位置
            // j >= 0保证边界情况
            // v < nums[j]保证排序稳定性
            for (; j >= 0 && v < nums[j]; j--) {
                nums[j+1] = nums[j]; //元素后移
            }
            nums[j+1] = v;
        }
    }

    //改进 插入+二分查找
    public static void bsInsertSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            int left = 0;
            int right = i;
            //二分查找 常规版 不具有稳定性
//            while (left <= right) {
//                int mid = (left + right) / 2;
//                if (v < nums[mid]) {
//                    right = mid - 1;
//                } else if (v > nums[mid]){
//                    left = mid + 1;
//                } else {
//                    break;
//                }
//            }
            //具有稳定性
            while (left < right) {
                int mid = (left + right) / 2;
                if (v >= nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            for (int j = i; j > left; j--) {
                nums[j] = nums[j-1];
            }

            nums[left] = v;
        }
    }

}
