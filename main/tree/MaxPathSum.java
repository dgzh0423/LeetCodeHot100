package main.tree;

/**
 * 124. 二叉树中的最大路径和
 * @author 15304
 */
public class MaxPathSum {

    private int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 左,右子树最大链和
        int lVal = dfs(node.left);
        int rVal = dfs(node.right);
        ans = Math.max(ans, lVal + rVal + node.val);
        // dfs返回的是从叶子到当前节点的路径和
        return Math.max(Math.max(lVal, rVal) + node.val, 0);
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(new MaxPathSum().maxPathSum(root));
    }
}
