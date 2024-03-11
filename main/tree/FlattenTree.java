package main.tree;

/**
 * @author 15304
 */
public class FlattenTree {
    /**
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同
     * 类似Morris算法
     * @param root
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
            }
            //考虑下一个节点
            root = root.right;
        }
    }

    public static void main(String[] args) {

    }
}
