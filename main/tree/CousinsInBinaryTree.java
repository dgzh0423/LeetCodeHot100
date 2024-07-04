package main.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 993.二叉树的堂兄弟节点 && 2641.二叉树的堂兄弟节点 II
 * @author 15304
 */
public class CousinsInBinaryTree {

    /**
     * 堂兄弟节点：节点值 x 和 y 的父节点不同，且 x 和 y 的深度相同，一个节点的深度指的是从树根节点到这个节点经过的边数。
     * @param root 树的根节点
     * @param x 节点值
     * @param y 节点值
     * @return 值 x 和 y 对应的节点是不是堂兄弟节点
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        int[] xi = dfs(root, null, 0, x);
        int[] yi = dfs(root, null, 0, y);
        return xi[1] == yi[1] && xi[0] != yi[0];
    }

    /**
     * 查找 t 的「父节点值」&「所在深度」
     * @param root 当前搜索到的节点
     * @param fa root 的父节点
     * @param depth 当前深度
     * @param t 要查找的值
     * @return [fa.val, depth]
     */
    private int[] dfs(TreeNode root, TreeNode fa, int depth, int t) {
        // 没有 t 对应的节点
        if (root == null) {
            return new int[]{-1, -1};
        }
        // t 为当前节点，特例：当 t 为根节点，fa.val = 0，表示没有父节点
        if (root.val == t) {
            return new int[]{fa != null ? fa.val : 0, depth};
        }
        int[] l = dfs(root.left, root, depth + 1, t);
        // t 在当前节点的左子树
        if (l[0] != -1) {
            return l;
        }
        // t 在当前节点的右子树
        return dfs(root.right, root, depth + 1, t);
    }

    /**
     * 将树中所有节点的值替换成该节点的「堂兄弟节点」的值之和
     * 对于某一层的节点 X, 其「堂兄弟节点」的值之和 = 该层所有节点的值之和 - X和它的兄弟节点(如果有的话)的值之和
     * @param root 原二叉树，从根节点开始BFS遍历
     * @return 更新后的二叉树
     */
    public TreeNode replaceValueInTree(TreeNode root) {
        // 根节点无堂兄弟节点，故更新后的值为0
        root.val = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            // 第一遍遍历，记录下一层所有节点的值之和
            int nextLevelSum = 0;
            for(TreeNode node : queue){
                if(node.left != null){
                    nextLevelSum += node.left.val;
                }
                if(node.right != null){
                    nextLevelSum += node.right.val;
                }
            }
            // 第二遍遍历，更新下一层节点的值，并加入队列
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                int BrotherSum = (node.left == null ? 0 : node.left.val) + (node.right == null ? 0: node.right.val);
                // 如果当前节点有左孩子
                if(node.left != null){
                    node.left.val = nextLevelSum - BrotherSum;
                    queue.offer(node.left);
                }
                // 如果当前节点有右孩子
                if(node.right != null){
                    node.right.val = nextLevelSum - BrotherSum;
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println(new CousinsInBinaryTree().isCousins(root, 1, 5));
        System.out.println(new CousinsInBinaryTree().replaceValueInTree(root));
    }
}
