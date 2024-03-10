package main.tree;

/**
 * @author 15304
 */
public class TreeDiameter {
    private int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {return 0;}
        //递归求左右子树最大高度
        int left = dfs(root.left);
        int right = dfs(root.right);
        //求递归过程中可能的最大直径
        ans = Math.max(ans, left + right);

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {

    }
}
