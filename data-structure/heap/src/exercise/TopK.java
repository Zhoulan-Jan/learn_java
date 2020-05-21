package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class TopK {
    // Pair 表示数对, 方便使用 优先队列 来进行管理
    // 一个普通的类, 无法直接放到优先队列中的.
    // 此时优先级如何定义的, 还不明确.
    // 可以把当前的类实现 Comparable 接口, 并实现 compareTo 方法
    // 此时优先队列就可以借助 compareTo 决定谁是优先级高, 谁是优先级低
    static class Pair implements Comparable<Pair> {
        public int n1;
        public int n2;
        public int sum;

        public Pair(int n1, int n2) {
            this.n1 = n1;
            this.n2 = n2;
            this.sum = n1 + n2;
        }

        @Override
        public int compareTo(Pair o) {
            // this, other
            // 如果希望 this 在前 other 在后, 返回 < 0
            // 如果希望 this 在后 other 在前, 返回 > 0
            // 如果希望相等, 返回 0
            if (this.sum < o.sum) {
                return 1;
            }
            if (this.sum > o.sum) {
                return -1;
            }
            return 0;
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (k < 1) {
            return result;
        }
        // 创建一个优先队列, 通过这个优先队列来作为堆, 完成最终的 topk 求解
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        for (int i = 0; i < nums1.length && i < k; i++) {
            for (int j = 0; j < nums2.length && i < k; j++) {
                queue.offer(new Pair(nums1[i], nums2[j]));
                if (queue.size() > k) {
                    // 始终保证 队列中 不超过 k 个元素
                    // 超过的话就需要淘汰掉弱者
                    queue.poll();
                }
            }
        }
        // 这两重循环结束之后, 此时 queue 就保存了需要的 k 对数字
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            List<Integer> tmp = new ArrayList<>();
            tmp.add(pair.n1);
            tmp.add(pair.n2);
            // 每次出队列的值插入到 result 最前面
            result.add(0, tmp);
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
