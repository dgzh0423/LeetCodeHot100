package main.tree;

/**
 * @author 15304
 * 对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）
 */
public class LowestCommonAncestor {

    /**
     * 236. 二叉树的最近公共祖先
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

    /**
     * 235. 二叉搜索树的最近公共祖先
     */
    public TreeNode lowestCommonAncestorOfBST(TreeNode root, TreeNode p, TreeNode q) {
        // 根据根节点的值，判断p和q在左子树还是右子树
        int x = root.val;
        // 如果p和q都小于根节点，则p和q都在左子树
        if (p.val < x && q.val < x) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // 如果p和q都大于根节点，则p和q都在右子树
        if (p.val > x && q.val > x) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // 其他情况如：p和q分别在左，右子树/当前节点是p/当前节点是q，则返回当前节点
        return root;
    }

    public static void main(String[] args) {}
}
