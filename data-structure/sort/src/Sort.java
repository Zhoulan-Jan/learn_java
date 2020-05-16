import java.util.Arrays;

public class Sort {
    //选择排序
    public static void SelectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
//            int min = nums[i];
//            int v = i; //用来记录找到最小值的下标
            int min = i;
            for (int j = i; j < nums.length; j++) {
//                if (min > nums[j]) {
//                    min = nums[j];
//                    v = j;
//                }
                if (nums[min] > nums[j]) {
                    min = j;
                }
            }
//            swap(i, v, nums);
            swap(i, min, nums);
        }
    }

    //冒泡排序
    public static void BubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j+1]) {
                    swap(j, j+1, nums);
                }
            }
        }
    }

    //插入排序
    public static void InsertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int v = nums[i];
            int j = i - 1;
            for (; j >= 0 && nums[j] > v; j--) {
                nums[j+1] = nums[j];
            }
            nums[j+1] = v;
        }
    }

    //希尔排序
    public static void ShellSort(int[] nums) {
        int gap = nums.length;
        while (gap > 1) {
            hillSortHelper(nums, gap);
            gap = gap / 2;
        }
        hillSortHelper(nums, gap);
    }

    private static void hillSortHelper(int[] nums, int gap) {
        for (int i = 1; i < nums.length; i++) {
            int v = nums[i];
            int j = i - gap;
            for (; j >= 0 && nums[j] > v; j -= gap) {
                nums[j+gap] = nums[j];
            }
            nums[j+gap] = v;
        }
    }

    //合并排序
    public static void MergeSort(int[] nums) {
        mergeSortHelper(0, nums.length, nums);
    }

    private static void mergeSortHelper(int low, int high, int[] nums) {
        if (low >= high || high - low == 1) {
            return;
        }
        int mid = (low + high) / 2;
        mergeSortHelper(low, mid, nums); //[low, mid)
        mergeSortHelper(mid, high, nums); //[mid, high)
        merge(low, mid, high, nums); //[low, high) 进行合并
    }

    //合并
    private static void merge(int low, int mid, int high, int[] nums) {
        int i = low;
        int j = mid;
        int len = high - low;
        int[] extra = new int[len];
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

        for (int t = 0; t < len; t++) {
            nums[low+t] = extra[t];
        }
    }

//    public static void MergeSort(int []arr){
//        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
//        mergeSortHelper(arr,0,arr.length-1,temp);
//    }
//    private static void mergeSortHelper(int[] arr, int left, int right, int []temp){
//        if(left<right){
//            int mid = (left+right)/2;
//            mergeSortHelper(arr,left,mid,temp);//左边归并排序，使得左子序列有序
//            mergeSortHelper(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
//            merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
//        }
//    }
//    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
//        int i = left;//左序列指针
//        int j = mid+1;//右序列指针
//        int t = 0;//临时数组指针
//        while (i<=mid && j<=right){
//            if(arr[i]<=arr[j]){
//                temp[t++] = arr[i++];
//            }else {
//                temp[t++] = arr[j++];
//            }
//        }
//        while(i<=mid){//将左边剩余元素填充进temp中
//            temp[t++] = arr[i++];
//        }
//        while(j<=right){//将右序列剩余元素填充进temp中
//            temp[t++] = arr[j++];
//        }
//        t = 0;
//        //将temp中的元素全部拷贝到原数组中
//        while(left <= right){
//            arr[left++] = temp[t++];
//        }
//    }

    //堆排序
    public static void HeapSort(int[] nums) {
        createHeap(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            swap(0, nums.length - i - 1, nums);
            shiftDown(nums.length - 1 - i, 0, nums);
        }
    }

    //建堆
    private static void createHeap(int[] nums) {
        for (int i = (nums.length - 1 - 1) /2; i >= 0; i--) {
            shiftDown(nums.length, i, nums);
        }
    }

    //向下调整 (大根堆）
    private static void shiftDown(int size, int index, int[] nums) {
        int parent = index;
        int child = 2 * parent + 1;
        while (child < size) {
            if (child + 1 < size && nums[child] < nums[child + 1]) {
                child = child + 1;
            }

            if (nums[parent] < nums[child]) {
                swap(parent, child, nums);
            } else {
                break;
            }

            parent = child;
            child = 2 * parent + 1;
        }
    }

    //快速排序
    public static void QuickSort(int[] nums) {
        quickSortHelper(0, nums.length - 1, nums);
    }

    private static void quickSortHelper(int left, int right, int[] nums) {
        if (left >= right) {
            return;
        }

        int pivotInx = partition(left, right, nums);
        quickSortHelper(left, pivotInx - 1, nums);
        quickSortHelper(pivotInx + 1, right, nums);

    }

    //一次快排
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

    private static void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {8,2,1,4,6,3,7,9,5};
        int[] nums2 = {6,1,2,7,9,3,4,5,10,8};
//        QuickSort(nums);
//        QuickSort(nums2);
//
//        HeapSort(nums);
//        HeapSort(nums2);
//
        MergeSort(nums);
        MergeSort(nums2);
//
//        BubbleSort(nums);
//        BubbleSort(nums2);
//
//        InsertSort(nums);
//        InsertSort(nums2);
//
//        ShellSort(nums);
//        ShellSort(nums2);
//
//        SelectSort(nums);
//        SelectSort(nums2);

        System.out.println(Arrays.toString(nums));
//        int[] arr = {2,8,3,7};
//        merge(0,2,4, arr);
//        System.out.println("merge" + Arrays.toString(arr));
        System.out.println(Arrays.toString(nums2));
    }
}
