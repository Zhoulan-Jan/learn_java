import java.util.*;
/*
二叉树的非递归遍历
 */
public class NonRecursionOrder {
    //前序遍历--非递归遍历--栈
    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        //根节点入栈
        stack.push(root);
        while (!stack.isEmpty()) {
            //出栈 需暂时保存栈顶元素
            TreeNode tmp= stack.pop();
            res.add(tmp.val);
            //左右子树不为空的情况下，出栈元素的右子树入栈，左子树入栈
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
        }
        return res;
    }

    //中序遍历--非递归--栈
    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (true) {
            //cur从根节点出发一直向左保存左子树，直到cur=null
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            //若栈为空，说明无节点
            if (stack.isEmpty()) {
                break;
            }
            //出栈
            TreeNode tmp = stack.pop();
            res.add(tmp.val);
            //cur指向出栈元素的右子树，若为空则继续出栈，若不为空则继续向左保存子树
            cur = tmp.right;
        }
        return res;
    }

    //后序遍历--非递归--栈
    public List<Integer> postorderTraversal2(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode prev = null;
        while (true) {
            //cur从根节点出发一直向左保存左子树，直到cur=null
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            //若栈为空，说明无节点
            if (stack.isEmpty()) {
                break;
            }
            //得到栈顶元素，先不访问（满足条件才可以访问）
            TreeNode tmp = stack.peek();
            //若栈顶元素无右子树或者右子树已被访问，则可以访问
            //若上一个访问的节点为右子树，则下一个访问的节点必是该右子树的根节点，因为是下层访问完，就访问上一层
            if (tmp.right == null || prev == tmp.right) {
                res.add(tmp.val);
                stack.pop();
                prev = tmp;
            } else {
                //指向栈顶元素的右子树
                cur = tmp.right;
            }
        }
        return res;
    }

    //层序遍历--非递归--队列
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size(); //for循环里 queue.size()会变化
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                assert tmp != null;
                level.add(tmp.val);
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
            res.add(level);
        }

        return res;
    }
    public static void main(String[] args) {
        NonRecursionOrder a = new NonRecursionOrder();
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode seven = new TreeNode(7);

        root.left = two;
        root.right = three;
        two.left = four;
        four.right = seven;

        System.out.println(a.preorderTraversal2(root));
        System.out.println(a.inorderTraversal2(root));
        System.out.println(a.postorderTraversal2(root));

        System.out.println(a.levelOrder2(root));
    }
}
