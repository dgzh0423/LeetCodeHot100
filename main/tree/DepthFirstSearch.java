package main.tree;

/**
 * @author 15304
 */
public class DepthFirstSearch {

    /**
     * DFS递归版
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        } else {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right) + 1;
        }
    }
    public static void main(String[] args) {

    }
}
