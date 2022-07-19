import java.util.*;
/*
二叉树的递归遍历
 */
public class RecursionOrder {
    //前序遍历--递归
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> res = new ArrayList<>();
        res.add(root.val);
        if (root.left != null) {
            res.addAll(preorderTraversal(root.left));
        }
        if (root.right != null) {
            res.addAll(preorderTraversal(root.right));
        }
        return res;
    }

    //中序遍历--递归
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        if (root.left != null) {
            res.addAll(inorderTraversal(root.left));
        }
        res.add(root.val);
        if (root.right != null) {
            res.addAll(inorderTraversal(root.right));
        }
        return res;
    }



    //后序遍历--递归
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        if (root.left != null) {
            res.addAll(postorderTraversal(root.left));
        }
        if (root.right != null) {
            res.addAll(postorderTraversal(root.right));
        }
        res.add(root.val);
        return res;
    }



    //层序遍历--递归
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        helper(root, 0);
        return res;
    }

    public void helper(TreeNode root, int level) {
        if (level == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        if (root.left != null) {
            helper(root.left, level+1);
        }
        if (root.right != null) {
            helper(root.right, level+1);
        }
    }


    public static void main(String[] args) {
        RecursionOrder a = new RecursionOrder();
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode seven = new TreeNode(7);

        root.left = two;
        root.right = three;
        two.left = four;
        four.right = seven;
        System.out.println(a.preorderTraversal(root));
        System.out.println(a.inorderTraversal(root));
        System.out.println(a.postorderTraversal(root));

        System.out.println(a.levelOrder(root));

    }
}
