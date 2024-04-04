package main.tree;

/**
 * @author 15304
 *
 * 对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）
 */
public class LowestCommonAncestor {

    /**
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 只要当前节点是p和q中的任意一个，就返回
        if(root == null || root == p || root == q) {
            return root;
        }
        // 继续分别往当前节点的左子树和右子树找p和q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 因为 p 和 q 均存在于给定的二叉树中，故不存在左右子树都没找到的情况
        // 左子树没有找到p,q，则在右子树
        if(left == null) {
            return right;
        }
        // 右子树没有找到p,q，则在左子树
        if(right == null) {
            return left;
        }
        // p,q分别在左，右子树，故返回当前节点
        return root;
    }

    public static void main(String[] args) {

    }
}
