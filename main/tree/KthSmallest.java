package main.tree;

/**
 * @author 15304
 */
public class KthSmallest {
    int res, k;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    /**
     * BST的中序遍历是升序的
     * @param root
     */
    void dfs(TreeNode root) {
        if (root == null|| k == 0) {
            return;
        }

        dfs(root.left);
        if (--k == 0) {
            res = root.val;
        }
        dfs(root.right);
    }

    public static void main(String[] args) {

    }
}
