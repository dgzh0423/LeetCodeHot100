package main.tree;

/**
 * @author 15304
 */
public class TreeDiameter {
    //二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过，也可能不经过根节点 root
    //两节点之间路径的 长度 由它们之间边数表示
    private int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //递归求左右子树最大高度
        int left = dfs(root.left);
        int right = dfs(root.right);
        //求递归过程中可能的最大直径
        ans = Math.max(ans, left + right);
        //二叉树的最大深度
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {

    }
}
