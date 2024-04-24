package main.tree;

/**
 * @author 15304
 */
public class DepthFirstSearch {

    /**
     * 一棵二叉树的最大深度可以通过子树的最大深度推导出来
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    // 记录最大深度
    int res = 0;
    // 记录遍历到的节点的深度
    int depth = 0;
    /**
     * 遍历一遍二叉树，用一个外部变量记录每个节点所在的深度，取最大值就可以得到最大深度
     * @param root
     * @return
     */
    public int maxDepthII(TreeNode root) {
        if(root == null) {
            return 0;
        }
        traverse(root);
        return res;
    }
    // 二叉树遍历框架
    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序位置
        depth++;
        if (root.left == null && root.right == null) {
            // 到达叶子节点，更新最大深度
            res = Math.max(res, depth);
        }
        traverse(root.left);
        traverse(root.right);
        // 后序位置
        depth--;
    }

    public static void main(String[] args) {}
}
