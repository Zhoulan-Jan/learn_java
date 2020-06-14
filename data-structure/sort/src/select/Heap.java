package select;

//堆排序
public class Heap {
    public static void heapSort(int[] nums) {
        createHeap(nums);

        for (int i = 0; i < nums.length; i++) {
            swap(0, nums.length-1-i, nums);
            shiftDown(nums.length-1-i, 0, nums);
        }
    }

    //建堆
    private static void createHeap(int[] nums) {
        for (int i = (nums.length-1-1)/2; i >= 0; i--) { //从倒数第一个非叶子节点开始调整
            shiftDown(nums.length, i, nums);
        }
    }

    //向下调整（大根堆）
    private static void shiftDown(int size, int index, int[] nums) {
        int parent = index;
        int child = 2*parent+1;
        while (child < size) {
            //找到左右子树最大的那个
            if (child + 1 < size && nums[child] < nums[child+1]) {
                child = child+1;
            }
            //结点与子树比较，若小于子树则交换，否则退出循环，不必向下调整
            if (nums[parent] < nums[child]) {
                swap(parent, child, nums);
            } else {
                break;
            }
            parent = child;
            child = 2*parent+1;
        }
    }

    public static void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
