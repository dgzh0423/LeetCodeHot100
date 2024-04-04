package main.tree;

import java.util.LinkedList;

/**
 * @author 15304
 */
public class ReverseTree {

    /**
     * 递归
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        //递归函数的终止条件，节点为空时返回
        if(root==null) {
            return null;
        }
        //将当前节点的左右子树交换
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        //递归交换当前节点的 左子树
        invertTree(root.left);
        //递归交换当前节点的 右子树
        invertTree(root.right);
        //函数返回时就表示当前这个节点，以及它的左右子树
        //都已经交换完了
        return root;
    }

    /**
     * BFS迭代法
     * @param root
     * @return
     */
    public TreeNode invert(TreeNode root) {
        if(root==null) {
            return null;
        }
        //将二叉树中的节点逐层放入队列中，再迭代处理队列中的元素
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            //每次都从队列中拿一个节点，并交换这个节点的左右子树
            TreeNode cur = queue.poll();
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
            //如果当前节点的左子树不为空，则放入队列等待后续处理
            if(cur.left != null) {
                queue.add(cur.left);
            }
            //如果当前节点的右子树不为空，则放入队列等待后续处理
            if(cur.right != null) {
                queue.add(cur.right);
            }
        }
        //返回处理完的根节点
        return root;
    }


    public static void main(String[] args) {

    }
}
